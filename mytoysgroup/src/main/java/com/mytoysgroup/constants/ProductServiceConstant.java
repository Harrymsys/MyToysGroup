package com.mytoysgroup.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to hold the constants for the complete product service.
 * @author hariprasanth.l
 *
 */
public class ProductServiceConstant {

	/**
	 * HEADERS
	 */
	public final static String[] HEADERS = {"ID", "NAME", "PRICE", "OLD_PRICE", "STOCK", "BRAND"};

	/**
	 * UNIQUE_KEY
	 */
	public final static String UNIQUE_KEY = "ID";
	
	/**
	 * ID
	 */
	public final static String ID = "ID";
	
	/**
	 * KEYWORD
	 */
	public final static String KEYWORD = ".keyword";
	
	/**
	 * NAME
	 */
	public final static String NAME = "NAME";
	
	/**
	 * PRICE
	 */
	public final static String PRICE = "PRICE";
	
	/**
	 * OLD_PRICE
	 */
	public final static String OLD_PRICE = "OLD_PRICE";
	
	/**
	 * STOCK
	 */
	public final static String STOCK = "STOCK";
	
	/**
	 * BRAND
	 */
	public final static String BRAND = "BRAND";
	
	/**
	 * SORTMAP
	 */
	private final static Map<String, String> SORTMAP = new HashMap<>();

	/**
	 * DEFAULT_RECORDS
	 */
	public static final String DEFAULT_RECORDS = "20";
	
	/**
	 * Sort Mapping for query.
	 * @return 
	 */
	public final static Map<String, String> getSortMap() {
		if(SORTMAP.isEmpty()) {
			SORTMAP.put(ID, ID+KEYWORD);
			SORTMAP.put(NAME, NAME+KEYWORD);
			SORTMAP.put(STOCK, STOCK+KEYWORD);
			SORTMAP.put(BRAND, BRAND+KEYWORD);
			SORTMAP.put(PRICE, PRICE);
			SORTMAP.put(OLD_PRICE, OLD_PRICE);
		}
		return SORTMAP;
	}
	
}
