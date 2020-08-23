package com.mytoysgroup.management;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mytoysgroup.beans.ProductResponse;
import com.mytoysgroup.beans.RangeRequest;
import com.mytoysgroup.constants.CommonConstants;
import com.mytoysgroup.constants.ProductServiceConstant;
import com.mytoysgroup.dao.elasticsearch.ProductDao;
import com.mytoysgroup.productinterface.IProductDao;
import com.mytoysgroup.util.CSVUtil;
import com.mytoysgroup.util.CommonUtils;
import com.toysgroup.exception.MyToysDefinedException;

/**
 * This class contains the business logic for the product module.
 * 
 * @author hariprasanth.l
 *
 */
@Component("productManagement")
public class ProductManagement {

	/**
	 * Logger object
	 */
	private final static Logger LOG = Logger.getLogger(ProductManagement.class);

	/**
	 * Loading the csv content to data store.
	 * 
	 * @throws MyToysDefinedException - If the file type is not valid - If the file
	 *                                content is not valid - If the file is not able
	 *                                to successfully added to the data store. - If
	 *                                there is no CSV record. - If there is an
	 *                                exception in the datastore write operation.
	 */
	public int loadCSVtoDataStore(MultipartFile productcsv) throws MyToysDefinedException {
		String fileName = productcsv.getOriginalFilename();
		// 1. Validation
		LOG.info("Validating the file : " + fileName);
		CSVUtil.isValidFile(productcsv);

		// 2. Validate Content
		LOG.info("Validating the content in the file : " + fileName);
		List<CSVRecord> productRecords = CSVUtil.convertFileToObject(productcsv);
		validateContent(productRecords);

		// 3. Loading the data to data store
		LOG.info("Loading the data from the file : " + fileName + " into the datastore...");
		IProductDao productDao = new ProductDao();
		productDao.bulkCreate(productRecords, ProductServiceConstant.HEADERS, ProductServiceConstant.UNIQUE_KEY);

		// 4. Framing the response
		int totalRecords = productRecords.size();
		LOG.info(String.format("All the %s records in the file : %s are updated successfully to the datastore.",
				totalRecords, fileName));
		return totalRecords;
	}

	/**
	 * Method to validate the content.
	 * 
	 * @param csvRecords holds the rows as interable content.
	 * @throws MyToysDefinedException - validating is data available - validating
	 *                                the content
	 */
	private void validateContent(List<CSVRecord> productRecords) throws MyToysDefinedException {
		try {
			if (productRecords.size() <= 1) {
				throw new MyToysDefinedException("There is no record found in the uploaded CSV file!");
			}
			CSVRecord headerRecord = productRecords.get(CommonConstants.HEADER_INDEX);
			for (int index = 0; index < ProductServiceConstant.HEADERS.length; index++) {
				if (!ProductServiceConstant.HEADERS[index].equalsIgnoreCase(headerRecord.get(index))) {
					throw new MyToysDefinedException("There are missing headers in the uploaded CSV file !");
				}
			}
		} catch (Exception e) {
			throw new MyToysDefinedException(e);
		}
	}

	/**
	 * This service is used to get all the products based on the following criteria
	 * 		- Search Keyword on the field like ID, NAME, BRAND, STOCK
	 * 		- Sort on all the fields 
	 * 		- Limit by Size
	 * 		- Pagination
	 * 
	 * @return all the products available based on the above matching criteria.
	 *
	 * @throws MyToysDefinedException - If the client is not available / not able to
	 *                                connect. - If there is an exception in the
	 *                                datastore layer
	 */
	public ProductResponse getProducts(String search, int size, String sort, String scrollId) throws MyToysDefinedException {
		try {
			validateSearchInput(scrollId, search, size, sort);
			LOG.info(logMessage(scrollId, search, size));
			IProductDao productDao = new ProductDao();
			if(!CommonUtils.isEmpty(scrollId)) {
				return productDao.getProducts(scrollId);
			} else {
				return productDao.getProducts(search, size, sort);
			}
		} catch (MyToysDefinedException e) {
			throw new MyToysDefinedException(e);
		}
	}

	/**
	 * Method to frame the input message.
	 */
	private String logMessage(String scrollId, String search, int size) {
		String message = "Get the products from the datastore ";
		if(!CommonUtils.isEmpty(scrollId)) {
			message = " ScrollId : " + scrollId;
		}
		if(!CommonUtils.isEmpty(search)) {
			message = " Search on ID, NAME, BRAND, PRICE : " + search;
		}
		return message;
	}

	/**
	 * Validate the input for search.
	 * @throws MyToysDefinedException 
	 * 		throws the validation error for search input.
	 */
	private void validateSearchInput(String scrollId, String search, int size, String sort) throws MyToysDefinedException {
		if(!CommonUtils.isEmpty(scrollId) && !CommonUtils.isEmpty(search)) {
			throw new MyToysDefinedException("Both the scrollId and search cannot be provided as input!, Only either of one can be provided for search - Please refer API documentation!");
		}
		if(!CommonUtils.isEmpty(sort)) {
			validateSortField(sort);
		}
	}

	/**
	 * Method to validate the sort field.
	 */
	private void validateSortField(String sort) throws MyToysDefinedException {
		boolean isSortEligible = false;
		for(String header : ProductServiceConstant.HEADERS) {
			if(header.equals(sort)) {
				isSortEligible = true;
			}
		}
		if(!isSortEligible) {
			throw new MyToysDefinedException(String.format("This is not an accepted sort field : '%s', \nOnly these fields are accepted for sorting : %s", sort, Arrays.toString(ProductServiceConstant.HEADERS)));
		}
	}

	/**
	 * This service is used to get all the products based on the following criteria
	 * 		- Range - Say field price from 50 euros to 100 euros
	 * 
	 * @return all the products based on the above matching criteria.
	 * @throws MyToysDefinedException 
	 * 		- validation failure
	 * 		- query failure
	 */
	public ProductResponse getProducts(RangeRequest rangeRequest) throws MyToysDefinedException {
		try {
			validateRangeRequest(rangeRequest);
			LOG.info(logMessage(rangeRequest));
			IProductDao productDao = new ProductDao();
			String scrollId = rangeRequest.getScrollId();
			if(!CommonUtils.isEmpty(scrollId)) {
				return productDao.getProducts(scrollId);
			} else {
				return productDao.getProducts(rangeRequest);
			}
		} catch (MyToysDefinedException e) {
			throw new MyToysDefinedException(e);
		}
	}

	/**
	 * log message for range request.
	 */
	private Object logMessage(RangeRequest rangeRequest) {
		if(CommonUtils.isEmpty(rangeRequest.getScrollId())) {
			return String.format("Range Request - Field : %s, From : %s, To : %s", rangeRequest.getField(), rangeRequest.getStartvalue(), rangeRequest.getEndvalue());
		} else {
			return "Pagination search for the scrollId : " + rangeRequest.getScrollId();
		}
	}

	/**
	 * Method to validate the range request.
	 */
	private void 	validateRangeRequest(RangeRequest rangeRequest) throws MyToysDefinedException {
		String field = rangeRequest.getField();
		if(CommonUtils.isEmpty(rangeRequest.getScrollId()) && !ProductServiceConstant.PRICE.equals(field) && !ProductServiceConstant.OLD_PRICE.equals(field)) {
			throw new MyToysDefinedException(String.format("This is not an accepted field for range query : '%s', \nOnly these fields are accepted for range query : %s and %s", field, ProductServiceConstant.PRICE, ProductServiceConstant.OLD_PRICE));
		}
	}
	
}
