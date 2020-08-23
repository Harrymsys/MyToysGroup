package com.mytoysgroup.productinterface;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.mytoysgroup.beans.ProductResponse;
import com.mytoysgroup.beans.RangeRequest;
import com.toysgroup.exception.MyToysDefinedException;

/**
 * This is an interface to handle the CRUD operations for product to data store.
 * @author hariprasanth.l
 *
 */
public interface IProductDao {

	/**
	 * Method to create the products as bulk.
	 */
	public void bulkCreate(List<CSVRecord> productRecords, String[] headers, String uniqueIdField) throws MyToysDefinedException;
	
	/**
	 * Method to get the products based on the search keyword, total size
	 */
	public ProductResponse getProducts(String search, int size, String sort) throws MyToysDefinedException;
	
	/**
	 * Method to get the products based on the scrollId
	 */
	public ProductResponse getProducts(String scrollId) throws MyToysDefinedException;
	
	/**
	 * Method to get the products based on the rangeRequest
	 */
	public ProductResponse getProducts(RangeRequest rangeRequest) throws MyToysDefinedException;
	
}
