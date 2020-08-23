package com.mytoysgroup.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.toysgroup.exception.MyToysDefinedException;

/**
 * @author hariprasanth.l This class is used to load the property files.
 */
public class PropertyUtil {

	/**
	 * Logger object
	 */
	private final static Logger LOG = Logger.getLogger(PropertyUtil.class);

	/**
	 * MicroService property.
	 */
	private static Properties microservicePropertyFile;

	/**
	 * MicroService Property stream
	 */
	private static FileInputStream microservicePropertyStream = null;

	/**
	 * Method to load the properties.
	 * 
	 * @throws MyToysDefinedException
	 */
	public static void loadProperties(String[] args) throws MyToysDefinedException {
		loadMicroServiceProperties(processArgs(args));
	}

	/**
	 * Method to get the micro service properties.
	 */
	public static String getProperty(String key) {
		return microservicePropertyFile.getProperty(key);
	}

	/**
	 * Method to get the available property / default value.
	 */
	public static String getProperty(String key, String defaultValue) {
		String propertyValue = getProperty(key);
		if (!CommonUtils.isEmpty(propertyValue)) {
			return propertyValue;
		}
		return defaultValue;
	}

	/**
	 * Method to load the microservice properties.
	 * 
	 * @param microservicePropertyFile holds the data of the mytoysgroup
	 *                                 microservice property file.
	 * @throws MyToysDefinedException
	 */
	private static void loadMicroServiceProperties(String microServicePropertyFile) throws MyToysDefinedException {
		try {
			if (microservicePropertyFile == null) {
				LOG.info("Loading the property file : " + microServicePropertyFile);
				microservicePropertyStream = new FileInputStream(microServicePropertyFile);
				microservicePropertyFile = new Properties();
				microservicePropertyFile.load(microservicePropertyStream);
				LOG.info("Loaded the property file successfuly : " + microServicePropertyFile);
			}
		} catch (Exception e) {
			LOG.error(e);
			throw new MyToysDefinedException(e);
		} finally {
			try {
				microservicePropertyStream.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}
	}
	
	/**
	 * Method to process the args
	 */
	private static String processArgs(String[] args) {
		return args[1];
	}

}
