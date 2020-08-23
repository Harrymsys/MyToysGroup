package com.mytoysgroup.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mytoysgroup.beans.ProductResponse;
import com.mytoysgroup.beans.RangeRequest;
import com.mytoysgroup.constants.CommonConstants;
import com.mytoysgroup.constants.ProductServiceConstant;
import com.mytoysgroup.management.ProductManagement;
import com.toysgroup.exception.MyToysDefinedException;

/**
 * @author hariprasanth.l
 * 
 *         This is the controller class which exposes the rest service for the
 *         product module in MyToysGroup. This specifically hold the services
 *         for CRUD operations on product module - load / create / fetch / fetch
 *         with search etc.,
 */

@RestController
public class ProductService {

	/**
	 * Logger object
	 */
	private final static Logger LOG = Logger.getLogger(ProductService.class);

	/**
	 * This class contains the business logic for the product module.
	 */
	@Autowired
	ProductManagement productManagement;

	/**
	 * This service is used to upload the csv file, which gets validated, then
	 * uploaded into the datastore.
	 * 
	 * @param productcsv holds the csv file which contains the product informations.
	 * @return the success message after the upload is successful.
	 */
	@PostMapping("/product/upload")
	public ResponseEntity<String> upload(@RequestParam("productcsv") MultipartFile productcsv) {
		try {
			int totalRecords = productManagement.loadCSVtoDataStore(productcsv);
			return ResponseEntity.status(HttpStatus.OK)
					.body(String.format(
							"All the %s records in the file : %s are updated successfully to the datastore.",
							totalRecords, productcsv.getOriginalFilename()));
		} catch (MyToysDefinedException e) {
			LOG.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data loading from the file : "
					+ productcsv.getOriginalFilename() + " is failed due to the error : " + e);
		}
	}

	/**
	 * This service is used to get all the products based on the following criteria
	 * 		- Search Keyword on the field like ID, NAME, BRAND, STOCK
	 * 		- Sort on all the fields 
	 * 		- Limit by Size
	 * 		- Pagination
	 * 
	 * @return all the products based on the above matching criteria.
	 */
	@GetMapping("/product")
	public ResponseEntity<Object> getProducts(@RequestParam(defaultValue = CommonConstants.EMPTY) String search,
			@RequestParam(defaultValue = ProductServiceConstant.DEFAULT_RECORDS) int size,
			@RequestParam(defaultValue = ProductServiceConstant.ID) String sort,
			@RequestParam(defaultValue = CommonConstants.EMPTY) String scrollId) {
		try {
			ProductResponse searchResponse = productManagement.getProducts(search, size, sort, scrollId);
			return ResponseEntity.status(HttpStatus.OK).body(searchResponse);
		} catch (Exception e) {
			LOG.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(String.format("Error in getting the product details : %s", e));
		}
	}

	/**
	 * This service is used to get all the products based on the following criteria
	 * 		- Range - Say field price from 50 euros to 100 euros
	 * 
	 * @return all the products based on the above matching criteria.
	 */
	@PostMapping("/product")
	public ResponseEntity<Object> getProducts(@RequestBody RangeRequest rangeRequest) {
		try {
			ProductResponse searchResponse = productManagement.getProducts(rangeRequest);
			return ResponseEntity.status(HttpStatus.OK).body(searchResponse);
		} catch (Exception e) {
			LOG.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(String.format("Error in getting the product details : %s", e));
		}
	}

	
}
