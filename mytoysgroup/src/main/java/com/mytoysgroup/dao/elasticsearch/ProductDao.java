package com.mytoysgroup.dao.elasticsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;

import com.mytoysgroup.beans.ProductResponse;
import com.mytoysgroup.beans.RangeRequest;
import com.mytoysgroup.constants.ProductServiceConstant;
import com.mytoysgroup.dao.elasticsearch.client.ElasticSearchClient;
import com.mytoysgroup.dao.elasticsearch.client.ElasticSearchConstants;
import com.mytoysgroup.dao.elasticsearch.client.IndexConstants;
import com.mytoysgroup.productinterface.IProductDao;
import com.mytoysgroup.util.CSVUtil;
import com.mytoysgroup.util.CommonUtils;
import com.toysgroup.exception.MyToysDefinedException;

/**
 * This class is used to perform CRUD operation for Products.
 * 
 * @author hariprasanth.l
 *
 */
public class ProductDao implements IProductDao {

	/**
	 * Logger object
	 */
	private final static Logger LOG = Logger.getLogger(ProductDao.class);

	/**
	 * Method to create the products.
	 */
	@Override
	public void bulkCreate(List<CSVRecord> productRecords, String[] headers, String uniqueIdField)
			throws MyToysDefinedException {
		LOG.info("Performing the bulk create operation");
		// Initialization
		Client client = getClient();
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		int totalRecord = productRecords.size();
		// Execution
		for (int index = 1; index < totalRecord; index++) {
			Map<String, Object> dataMap = CSVUtil.csvRecordToMap(productRecords.get(index), headers);
			bulkRequest.add(client.prepareIndex().setIndex(IndexConstants.PRODUCTS).setSource(dataMap)
					.setId((String) dataMap.get(uniqueIdField)));
			/**
			 * To perform the efficient write, We will write for every 1000 records once.
			 */
			if (index % ElasticSearchConstants.BATCH_SIZE == 0) {
				bulkRequest = handleBulkWrite(bulkRequest, client);
			}
		}
		/**
		 * Buffer remaining write
		 */
		if (totalRecord % 1000 != 0) {
			bulkRequest = handleBulkWrite(bulkRequest, client);
		}
	}

	/**
	 * Method to handle the bulk write.
	 */
	private BulkRequestBuilder handleBulkWrite(BulkRequestBuilder bulkRequest, Client client)
			throws MyToysDefinedException {
		BulkResponse bulkResponse = bulkRequest.get();
		if (bulkResponse.hasFailures()) {
			throw new MyToysDefinedException(
					"Error in product insert operation : " + bulkResponse.buildFailureMessage());
		}
		return client.prepareBulk();
	}

	/**
	 * Method to trigger the elastic search client.
	 */
	private static Client getClient() throws MyToysDefinedException {
		return ElasticSearchClient.getClient();
	}

	/**
	 * Method to get the products based on the search keyword, scrollId
	 * (pagination), total size
	 * 
	 * @throws MyToysDefinedException - If the client is not available / not able to
	 *                                connect. - If there is an exception in the
	 *                                datastore layer
	 */
	@Override
	public ProductResponse getProducts(String search, int size, String sort) throws MyToysDefinedException {
		LOG.info("Get products based on search keyword and size");
		QueryBuilder searchQueryBuilder = new ProductQueryBuilder().searchQueryBuilder(search);
		SearchResponse searchResponse = getClient().prepareSearch(IndexConstants.PRODUCTS).setQuery(searchQueryBuilder)
				.addSort(ProductServiceConstant.getSortMap().get(sort), SortOrder.ASC)
				.setScroll(new TimeValue(ElasticSearchConstants.SCROLL_STORAGE_TIME)).setSize(size).get();
		return frameProductResponse(searchResponse);
	}

	/**
	 * Method to get the products based on the search keyword, scrollId
	 * (pagination), total size
	 * 
	 * @throws MyToysDefinedException - If the client is not available / not able to
	 *                                connect. - If there is an exception in the
	 *                                datastore layer
	 */
	@Override
	public ProductResponse getProducts(String scrollId) throws MyToysDefinedException {
		LOG.info("Get products based on scrollId");
		SearchResponse searchResponse = getClient().prepareSearchScroll(scrollId)
				.setScroll(new TimeValue(ElasticSearchConstants.SCROLL_STORAGE_TIME)).get();
		return frameProductResponse(searchResponse);
	}

	/**
	 * This service is used to get all the products based on the following criteria
	 * - Range - Say field price from 50 euros to 100 euros
	 * 
	 * @return all the products based on the above matching criteria.
	 * @throws MyToysDefinedException - validation failure - query failure
	 */
	@Override
	public ProductResponse getProducts(RangeRequest rangeRequest) throws MyToysDefinedException {
		QueryBuilder rangeQueryBuilder = new ProductQueryBuilder().rangeQueryBuilder(rangeRequest);
		SearchResponse searchResponse = getClient().prepareSearch(IndexConstants.PRODUCTS).setQuery(rangeQueryBuilder)
				.setScroll(new TimeValue(ElasticSearchConstants.SCROLL_STORAGE_TIME)).get();
		return frameProductResponse(searchResponse);
	}

	/**
	 * Method to frame the product response from the search response.
	 */
	private ProductResponse frameProductResponse(SearchResponse searchResponse) {
		ProductResponse productResponse = new ProductResponse();
		// scrollId
		productResponse.setScrollId(searchResponse.getScrollId());
		// Frame data and total
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (SearchHit searchHit : searchHits) {
			dataList.add(searchHit.getSourceAsMap());
		}
		productResponse.setData(dataList);
		productResponse.setTotal(searchResponse.getHits().getTotalHits().value);
		productResponse.setTotalRecordInCurrentSearch(searchResponse.getHits().getHits().length);
		return productResponse;
	}

}
