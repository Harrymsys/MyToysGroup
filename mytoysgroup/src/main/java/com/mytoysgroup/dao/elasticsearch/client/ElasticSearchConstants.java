package com.mytoysgroup.dao.elasticsearch.client;

import com.mytoysgroup.util.PropertyUtil;

/**
 * @author hariprasanth.l This class is used to hold the elastic search
 *         constants.
 */
public class ElasticSearchConstants {

	/**
	 * CLUSTER_NAME_KEY
	 */
	public final static String CLUSTER_KEY = "cluster.name";

	/**
	 * CLUSTER_NAME
	 */
	public final static String CLUSTER_NAME = "docker-cluster";

	/**
	 * XPACK
	 */
	public static final String XPACK = "xpack.security.user";

	/**
	 * DESTINATION_HOST_USERNAME
	 */
	public static final String SOURCE_HOST_USERNAME = PropertyUtil.getProperty("SOURCE_HOST_USERNAME", "elastic");
	
	/**
	 * DESTINATION_HOST_PASSWORD
	 */
	public static final String SOURCE_HOST_PASSWORD = PropertyUtil.getProperty("SOURCE_HOST_PASSWORD", "changeme");
	
	/**
	 * USERNAME
	 */
	public static final String USERNAME_PASSWORD = SOURCE_HOST_USERNAME
			+ ":" + SOURCE_HOST_PASSWORD;

	/**
	 * TRANSPORT_PORT
	 */
	public static final int TRANSPORT_PORT = Integer
			.parseInt(PropertyUtil.getProperty("SOURCE_PORT", "9300"));

	/**
	 * IPADDRESS
	 */
	public static final String IPADDRESS = PropertyUtil.getProperty("SOURCE_HOST", "127.0.0.1");
	
	/**
	 * SOURCE_SCHEMA
	 */
	public static final String SOURCE_SCHEMA = PropertyUtil.getProperty("SOURCE_SCHEMA");
	
	/**
	 * Connection timeout value.
	 */
	public static final String TIMEOUT_VALUE = "10s";
	
	/**
	 * Connection timeout 
	 */
	public static final String TRANSPORT_CONNECTION_TIMEOUT = "transport.connect_timeout";

	/**
	 * Timeout for reindex
	 */
	public static final long TIMEOUT = 5;
	
	/**
	 * BATCH_SIZE
	 */
	public static final int BATCH_SIZE = 1000;

	/**
	 * SCROLL_STORAGE_TIME
	 */
	public static final long SCROLL_STORAGE_TIME = 120000;

	/**
	 * RESOURCE_ALREADY_EXISTS
	 */
	public static final String RESOURCE_ALREADY_EXISTS = "ResourceAlreadyExistsException";

	/**
	 * WILDCARD
	 */
	public static final String WILDCARD = "*";

	/**
	 * SPACE
	 */
	public static final String SPACE = " ";

	/**
	 * RETRY_COUNT
	 */
	public static final int RETRY_COUNT = Integer.parseInt(PropertyUtil.getProperty("RETRY_COUNT", "3"));
	
}
