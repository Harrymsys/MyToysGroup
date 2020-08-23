package com.mytoysgroup.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mytoysgroup.constants.TestConstants;
import com.mytoysgroup.dao.elasticsearch.client.ElasticSearchClient;
import com.mytoysgroup.management.ProductManagement;
import com.mytoysgroup.util.PropertyUtil;
import com.toysgroup.exception.MyToysDefinedException;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { ProductService.class, ProductManagement.class })
@WebMvcTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestProductServiceIT {

	/**
	 * Logger object
	 */
	private final static Logger LOG = Logger.getLogger(TestProductServiceIT.class);

	/**
	 * Autowired to perform rest call.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Load the initiate phase.
	 */
	@BeforeAll
	public static void loadInitiatePhase() {
		try {
			PropertyUtil.loadProperties(TestConstants.MICROSERVICE_TEST_PROPERTY);
			ElasticSearchClient.getClient();
			ElasticSearchClient.createIndex();
		} catch (MyToysDefinedException e) {
			LOG.error(e);
			System.exit(1);
		}
	}

	/**
	 * This will be the first test to be executed (as it is an upload function which
	 * populates data)
	 */
	@Test
	@Order(1)
	public void testMyToysGroupUpload() throws Exception {
		// Preparing the file for the test
		MockMultipartFile jsonFile = new MockMultipartFile(TestConstants.UPLOAD_FILE_NAME,
				TestConstants.UPLOAD_FILE_NAME, TestConstants.MEDIA_TYPE, TestConstants.PRODUCTCSV.getBytes());
		// Upload it
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.multipart(TestConstants.PRODUCT_UPLOAD_URL).file(jsonFile))
				.andExpect(status().isOk()).andReturn();
		assertEquals(TestConstants.UPLOAD_RESPONSE, result.getResponse().getContentAsString());
	}

	/**
	 * This will be the first test to be executed (as it is an upload function which
	 * populates data)
	 */
	@Test
	public void testMyToysGroupExceptionForWrongMediaType() throws Exception {
		// Preparing the file for the test
		MockMultipartFile jsonFile = new MockMultipartFile(TestConstants.UPLOAD_FILE_NAME,
				TestConstants.UPLOAD_FILE_NAME, TestConstants.WRONG_MEDIA_TYPE, TestConstants.PRODUCTCSV.getBytes());
		mockMvc.perform(MockMvcRequestBuilders.multipart(TestConstants.PRODUCT_UPLOAD_URL).file(jsonFile))
				.andExpect(status().is(500)).andReturn();
	}

	/**
	 * This will be the first test to be executed (as it is an upload function which
	 * populates data)
	 */
	@Test
	public void testMyToysGroupExceptionForCSVWithoutData() throws Exception {
		// Preparing the file for the test
		MockMultipartFile jsonFile = new MockMultipartFile(TestConstants.UPLOAD_FILE_NAME,
				TestConstants.UPLOAD_FILE_NAME, TestConstants.MEDIA_TYPE, TestConstants.CSV_WITHOUT_DATA.getBytes());
		mockMvc.perform(MockMvcRequestBuilders.multipart(TestConstants.PRODUCT_UPLOAD_URL).file(jsonFile))
				.andExpect(status().is(500)).andReturn();
	}

	/**
	 * This will be the first test to be executed (as it is an upload function which
	 * populates data)
	 */
	@Test
	public void testMyToysGroupExceptionForCSVWithWrongHeader() throws Exception {
		// Preparing the file for the test
		MockMultipartFile jsonFile = new MockMultipartFile(TestConstants.UPLOAD_FILE_NAME,
				TestConstants.UPLOAD_FILE_NAME, TestConstants.MEDIA_TYPE,
				TestConstants.CSV_WITH_UNEXPECTED_HEADER.getBytes());
		mockMvc.perform(MockMvcRequestBuilders.multipart(TestConstants.PRODUCT_UPLOAD_URL).file(jsonFile))
				.andExpect(status().is(500)).andReturn();
	}

	@Test
	public void testGetAllProducts() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.PRODUCT_BASE_URL))
				.andExpect(status().isOk()).andReturn();
		String productDetails = result.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(productDetails);
		assertNotEquals(0, jsonObject.get(TestConstants.TOTAL_IN_CURRENT_SEARCH));
	}

	@Test
	public void testGetAllProductsBySize() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.PRODUCT_BASE_URL_WITH_SIZE))
				.andExpect(status().isOk()).andReturn();
		String productDetails = result.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(productDetails);
		assertEquals(20, jsonObject.get(TestConstants.TOTAL_IN_CURRENT_SEARCH));
	}

	@Test
	public void testGetAllProductsBySizeAndSearch() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.SIZE_WITH_SEARCH))
				.andExpect(status().isOk()).andReturn();
		String productDetails = result.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(productDetails);
		assertNotEquals(0, jsonObject.get(TestConstants.TOTAL_IN_CURRENT_SEARCH));
	}

	@Test
	public void testGetAllProductsBySizeSearchAndSortByName() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.SIZE_SEARCH_SORTBYNAME))
				.andExpect(status().isOk()).andReturn();
		String productDetails = result.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(productDetails);
		assertNotEquals(0, jsonObject.get(TestConstants.TOTAL_IN_CURRENT_SEARCH));
	}

	@Test
	public void testGetAllProductsByPagination() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.PRODUCT_BASE_URL))
				.andExpect(status().isOk()).andReturn();
		String productDetails = result.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(productDetails);
		String scrollId = (String) jsonObject.get(TestConstants.SCROLLID);
		result = mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.PRODUCT_BASE_URL_WITH_PAGINATION + scrollId))
				.andExpect(status().isOk()).andReturn();
		productDetails = result.getResponse().getContentAsString();
		jsonObject = new JSONObject(productDetails);
		assertNotEquals(0, jsonObject.get(TestConstants.TOTAL_IN_CURRENT_SEARCH));
	}

	@Test
	public void testGetAllProductsBySearchAndScrollId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.SEARCH_WITH_SCROLL)).andExpect(status().is(500))
				.andReturn();
	}

	@Test
	public void testMyToysGroupException500_WrongSortName() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.WRONG_SORT_NAME)).andExpect(status().is(500))
				.andReturn();
	}

	@Test
	public void testGetAllProductsByRangeQuery() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PRODUCT_BASE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.RANGE_QUERY))
				.andExpect(status().isOk()).andReturn();
		String productDetails = result.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(productDetails);
		assertNotEquals(0, jsonObject.get(TestConstants.TOTAL_IN_CURRENT_SEARCH));
	}

	@Test
	public void testGetAllProductsByPaginationInRangeQuery() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(TestConstants.PRODUCT_BASE_URL)
						.contentType(MediaType.APPLICATION_JSON).content(TestConstants.RANGE_QUERY))
				.andExpect(status().isOk()).andReturn();
		String productDetails = result.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(productDetails);
		String scrollId = (String) jsonObject.get(TestConstants.SCROLLID);
		result = mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.PRODUCT_BASE_URL)
				.contentType(MediaType.APPLICATION_JSON).content(TestConstants.getRangeQueryWithScrollId(scrollId)))
				.andExpect(status().isOk()).andReturn();
		productDetails = result.getResponse().getContentAsString();
		jsonObject = new JSONObject(productDetails);
		assertNotEquals(0, jsonObject.get(TestConstants.TOTAL_IN_CURRENT_SEARCH));
	}

	@Test
	public void testMyToysGroupsExceptionInRangeQuery() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.PRODUCT_BASE_URL)
				.contentType(MediaType.APPLICATION_JSON).content(TestConstants.WRONG_RANGE_QUERY))
				.andExpect(status().is(500)).andReturn();
	}

	@AfterAll
	public static void cleanup() {
		ElasticSearchClient.close();
	}

}
