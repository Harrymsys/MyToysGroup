package com.mytoysgroup;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mytoysgroup.constants.CommonConstants;
import com.mytoysgroup.dao.elasticsearch.client.ElasticSearchClient;
import com.mytoysgroup.dao.elasticsearch.client.ElasticSearchConstants;
import com.mytoysgroup.util.CommonUtils;
import com.mytoysgroup.util.PropertyUtil;
import com.toysgroup.exception.MyToysDefinedException;

/**
 * @author hariprasanth.l This class will be the initializer for this micro
 *         service.
 */
@SpringBootApplication
public class MyToysGroupApplication {

	/**
	 * Logger object
	 */
	private final static Logger LOG = Logger.getLogger(MyToysGroupApplication.class);

	/**
	 * This method will be the initializer for this micro service.
	 * 
	 * @param args args[0] - It accepts the property file
	 *             (product_microservice.properties) for this project.
	 */
	public static void main(String[] args) {
		try {
			validateInput(args);
			PropertyUtil.loadProperties(args);
			SpringApplication.run(MyToysGroupApplication.class, args);
			initiate();
		} catch (MyToysDefinedException e) {
			LOG.error("MyToysGroup Application is shutdown unexpectedly due to the error :" + e.toString());
			System.exit(1);
		} finally {
			LOG.info(CommonConstants.HIGHLIGHTER);
			LOG.info("MyToysGroup Application is initialized successfully, product service is ready to use...");
			LOG.info(CommonConstants.HIGHLIGHTER);
		}
	}

	/**
	 * Method to validate the input.
	 */
	private static void validateInput(String[] args) throws MyToysDefinedException {
		if (args.length != 2) {
			throw new MyToysDefinedException(String
					.format("Invalid input : %s, Please provide the config path as input!", Arrays.toString(args)));
		}
	}

	/**
	 * Initialization for the datastore and other services.
	 * 
	 * @throws MyToysDefinedException throws the error If there is an issue in data
	 *                                store connection creation.
	 */
	private static void initiate() throws MyToysDefinedException {
		int retryCount = ElasticSearchConstants.RETRY_COUNT;
		while (retryCount >= 1) {
			try {
				ElasticSearchClient.getClient();
				ElasticSearchClient.createIndex();
				return;
			} catch (MyToysDefinedException e) {
				retryCount = retryCount - 1;
				waitForStartUp(retryCount);
			}
		}
		LOG.info(CommonConstants.RETRY_FAILURE_MESSAGE);
		throw new MyToysDefinedException(CommonConstants.RETRY_FAILURE_MESSAGE);
	}

	/**
	 * wait for the start up.
	 */
	private static void waitForStartUp(int retryCount) {
		try {
			LOG.info(CommonConstants.HIGHLIGHTER);
			LOG.info("wait for the init phase startup..." + retryCount);
			Thread.sleep(CommonConstants.WAIT_TIME);
			LOG.info(CommonConstants.HIGHLIGHTER);
			LOG.info("Retrying the init phase..." + retryCount);
		} catch (InterruptedException e) {
			LOG.error(e);
		}
	}

}
