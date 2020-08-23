package com.mytoysgroup.dao.elasticsearch;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.mytoysgroup.beans.RangeRequest;
import com.mytoysgroup.constants.ProductServiceConstant;
import com.mytoysgroup.dao.elasticsearch.client.ElasticSearchConstants;
import com.mytoysgroup.util.CommonUtils;

/**
 * This class is the helper class for the product dao.
 * 
 * @author hariprasanth.l
 *
 */
public class ProductQueryBuilder {

	/**
	 * Build the query for the products.
	 */
	public QueryBuilder searchQueryBuilder(final String search) {
		if (!CommonUtils.isEmpty(search)) {
			String[] searchArray = search.split(ElasticSearchConstants.SPACE);
			QueryBuilder searchQueryBuilder = null;
			for (String searchText : searchArray) {
				String wildcardSearchText = frameWildCardQuery(searchText);
				if (CommonUtils.isEmptyObject(searchQueryBuilder)) {
					searchQueryBuilder = getQueryBuilders(wildcardSearchText);
				} else {
					searchQueryBuilder = QueryBuilders.boolQuery().should(searchQueryBuilder)
							.should(getQueryBuilders(wildcardSearchText));
				}
			}
			return searchQueryBuilder;
		}
		return QueryBuilders.matchAllQuery();
	}

	/**
	 * Method to frame the wildcard search query.
	 */
	private String frameWildCardQuery(String searchText) {
		return ElasticSearchConstants.WILDCARD + searchText.toLowerCase() + ElasticSearchConstants.WILDCARD;
	}

	/**
	 * Frame query builders.
	 */
	private QueryBuilder getQueryBuilders(String searchText) {
		return QueryBuilders.boolQuery().should(QueryBuilders.wildcardQuery(ProductServiceConstant.ID, searchText))
				.should(QueryBuilders.wildcardQuery(ProductServiceConstant.NAME, searchText))
				.should(QueryBuilders.wildcardQuery(ProductServiceConstant.STOCK, searchText))
				.should(QueryBuilders.wildcardQuery(ProductServiceConstant.BRAND, searchText));
	}

	/**
	 * Method to create the range query builder
	 */
	public QueryBuilder rangeQueryBuilder(RangeRequest rangeRequest) {
		return QueryBuilders.rangeQuery(rangeRequest.getField()).gt(rangeRequest.getStartvalue()).lt(rangeRequest.getEndvalue());
	}
	
}
