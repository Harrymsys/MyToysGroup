package com.mytoysgroup.constants;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytoysgroup.util.PropertyUtil;

/**
 * This class is used to hold the constants which is common for all the modules.
 * 
 * @author hariprasanth.l
 *
 */
public class CommonConstants {

	/**
	 * ACCEPTED_FILE_TYPES
	 */
	public static String[] ACCEPTED_FILE_TYPES = { "csv", "excel", "text" };

	/**
	 * ENCODING_FORMAT
	 */
	public static final String ENCODING_FORMAT = "UTF-8";

	/**
	 * HEADER_INDEX
	 */
	public static final int HEADER_INDEX = 0;

	/**
	 * EMPTY
	 */
	public static final String EMPTY = "";

	/**
	 * WAIT_TIME
	 */
	public static final long WAIT_TIME = Long.parseLong(PropertyUtil.getProperty("WAIT_TIME", "30000"));

	/**
	 * RETRY_FAILURE_MESSAGE
	 */
	public static final String RETRY_FAILURE_MESSAGE = "Init phase is not successfull and all retries exceeded!";
	
	/**
	 * HIGHLITER
	 */
	public static final String HIGHLIGHTER = "##################################################################";

	
}
