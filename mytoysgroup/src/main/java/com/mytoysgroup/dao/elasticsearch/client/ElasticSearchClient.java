package com.mytoysgroup.dao.elasticsearch.client;

import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import com.mytoysgroup.util.CommonUtils;
import com.toysgroup.exception.MyToysDefinedException;

/**
 * @author hariprasanth.l This class is used to create the instance for elastic
 *         search eswrapper.
 */
@SuppressWarnings("deprecation")
public class ElasticSearchClient {

	/**
	 * Client used to communicate to the elastic search.
	 */
	private static Client client = null;
	/**
	 * Initialization to the logger class.
	 */
	private static final Logger LOG = Logger.getLogger(ElasticSearchClient.class.getName());

	/**
	 * Method creates the instance for the elastic search eswrapper.
	 * 
	 * @return the elastic search eswrapper.
	 * @throws MyToysDefinedException holds the client connection exception.
	 */
	@SuppressWarnings({ "resource" })
	public static Client getClient() throws MyToysDefinedException {
		try {
			if (client == null) {
				LOG.info("Creating the transport client connection...");
				Settings settings = Settings.builder()
						.put(ElasticSearchConstants.CLUSTER_KEY, ElasticSearchConstants.CLUSTER_NAME)
						.put(ElasticSearchConstants.XPACK, ElasticSearchConstants.USERNAME_PASSWORD)
						.put(ElasticSearchConstants.TRANSPORT_CONNECTION_TIMEOUT, ElasticSearchConstants.TIMEOUT_VALUE)
						.build();
				client = new PreBuiltXPackTransportClient(settings).addTransportAddress(
						new TransportAddress(InetAddress.getByName(ElasticSearchConstants.IPADDRESS),
								ElasticSearchConstants.TRANSPORT_PORT));
				LOG.info("Client connection created successfully...");
			}
		} catch (Exception e) {
			throw new MyToysDefinedException(e);
		}
		return client;
	}

	/**
	 * Method to create index
	 */
	public static void createIndex() throws MyToysDefinedException {
		try {
			LOG.info("Initiating the table / index creation process for the index named : " + IndexConstants.PRODUCTS);
			CreateIndexRequest request = new CreateIndexRequest(IndexConstants.PRODUCTS);
			request.source(IndexConstants.PRODUCT_INDEX_MAPPING, XContentType.JSON);
			CreateIndexResponse createIndexResponse = client.admin().indices().create(request).get();
			LOG.info(String.format("Created the index successfully : %s and the Response from the data store success : %s",
					IndexConstants.PRODUCTS, createIndexResponse.isAcknowledged()));
		} catch (Exception e) {
			if (!CommonUtils.isEmptyObject(e)
					&& e.toString().contains(ElasticSearchConstants.RESOURCE_ALREADY_EXISTS)) {
				LOG.info("Table / Index already exists : " + IndexConstants.PRODUCTS);
			} else {
				throw new MyToysDefinedException(e);
			}
		}
	}

	/**
	 * Method to close the client connection.
	 */
	public static void close() {
		if (client != null) {
			client.close();
		}
	}

}
