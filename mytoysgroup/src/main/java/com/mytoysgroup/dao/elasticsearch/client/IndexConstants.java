package com.mytoysgroup.dao.elasticsearch.client;

/**
 * This class is used to hold the constants for the indexes in ElasticSearch.
 * @author hariprasanth.l
 *
 */
public class IndexConstants {

	/**
	 * PRODUCTS
	 */
	public final static String PRODUCTS = "products";

	/**
	 * PRODUCT_INDEX_MAPPING
	 */
	public final static String PRODUCT_INDEX_MAPPING = " {\r\n" + 
			"    \"mappings\": {\r\n" + 
			"      \"mappings\": {\r\n" + 
			"        \"properties\": {\r\n" + 
			"          \"ID\": {\r\n" + 
			"            \"type\": \"text\",\r\n" + 
			"            \"fields\": {\r\n" + 
			"              \"keyword\": {\r\n" + 
			"                \"type\": \"keyword\",\r\n" + 
			"                \"ignore_above\": 256\r\n" + 
			"              }\r\n" + 
			"            }\r\n" + 
			"          },\r\n" + 
			"          \"NAME\": {\r\n" + 
			"            \"type\": \"text\",\r\n" + 
			"            \"fields\": {\r\n" + 
			"              \"keyword\": {\r\n" + 
			"                \"type\": \"keyword\",\r\n" + 
			"                \"ignore_above\": 256\r\n" + 
			"              }\r\n" + 
			"            }\r\n" + 
			"          },\r\n" + 
			"          \"BRAND\": {\r\n" + 
			"            \"type\": \"text\",\r\n" + 
			"            \"fields\": {\r\n" + 
			"              \"keyword\": {\r\n" + 
			"                \"type\": \"keyword\",\r\n" + 
			"                \"ignore_above\": 256\r\n" + 
			"              }\r\n" + 
			"            }\r\n" + 
			"          },\r\n" + 
			"          \"STOCK\": {\r\n" + 
			"            \"type\": \"text\",\r\n" + 
			"            \"fields\": {\r\n" + 
			"              \"keyword\": {\r\n" + 
			"                \"type\": \"keyword\",\r\n" + 
			"                \"ignore_above\": 256\r\n" + 
			"              }\r\n" + 
			"            }\r\n" + 
			"          },\r\n" + 
			"          \"PRICE\": {\r\n" + 
			"            \"type\": \"double\"\r\n" + 
			"          },\r\n" + 
			"          \"OLD_PRICE\": {\r\n" + 
			"            \"type\": \"double\"\r\n" + 
			"          }\r\n" + 
			"        }\r\n" + 
			"      }\r\n" + 
			"    }\r\n" + 
			"  }";
	
}

