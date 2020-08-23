package com.mytoysgroup.constants;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

import com.mytoysgroup.beans.ProductResponse;
import com.mytoysgroup.beans.RangeRequest;

/**
 * This class is used to store the test constants.
 * 
 * @author hariprasanth.l
 *
 */
public class TestConstants {

	/**
	 * MICROSERVICE_TEST_PROPERTY
	 */
	public final static String[] MICROSERVICE_TEST_PROPERTY = { "",
			"D:\\base\\Workspace\\eclipse_back\\mytoys_group\\mytoys_group_project\\mytoysgroup\\src\\test\\java\\com\\mytoysgroup\\rest\\microservicetest.properties" };

	/**
	 * PRODUCT_BASE_URL
	 */
	public final static String PRODUCT_BASE_URL = "/product";

	/**
	 * PRODUCT_BASE_URL_WITH_SIZE
	 */
	public final static String PRODUCT_BASE_URL_WITH_SIZE = "/product?size=20";

	/**
	 * SIZE_WITH_SEARCH
	 */
	public final static String SIZE_WITH_SEARCH = "/product?size=20&search=JOY TOY";

	/**
	 * SIZE_SEARCH_SORTBYNAME
	 */
	public final static String SIZE_SEARCH_SORTBYNAME = "/product?size=20&search=A&sort=NAME";

	/**
	 * WRONG_SORT_NAME
	 */
	public final static String WRONG_SORT_NAME = "/product?sort=TEST";

	/**
	 * TOTAL_IN_CURRENT_SEARCH
	 */
	public final static String TOTAL_IN_CURRENT_SEARCH = "totalRecordInCurrentSearch";

	/**
	 * PRODUCT_BASE_URL_WITH_PAGINATION
	 */
	public final static String PRODUCT_BASE_URL_WITH_PAGINATION = "/product?scrollId=";
	

	/**
	 * SEARCH_WITH_SCROLL
	 */
	public static final String SEARCH_WITH_SCROLL = "/product?scrollId=data&search=data";

	/**
	 * Range Query
	 */
	public static final String RANGE_QUERY = "{\r\n" + "	\"field\" : \"PRICE\",\r\n" + "	\"startvalue\" : 10,\r\n"
			+ "	\"endvalue\" : 20\r\n" + "}";

	/**
	 * Frame range query with scrollId.
	 */
	public static final String getRangeQueryWithScrollId(String scrollId) {
		String rangeQuery = "{\"scrollId\":\"" + scrollId + "\"}";
		return rangeQuery;
	}

	/**
	 * Range Query
	 */
	public static final String WRONG_RANGE_QUERY = "{\r\n" + "	\"field\" : \"TEST\",\r\n"
			+ "	\"startvalue\" : 10,\r\n" + "	\"endvalue\" : 20\r\n" + "}";

	/**
	 * SCROLLID
	 */
	public static final String SCROLLID = "scrollId";

	/**
	 * UPLOAD_FILE_NAME
	 */
	public static final String UPLOAD_FILE_NAME = "productcsv";

	/**
	 * PRODUCTCSV
	 */
	public static final String PRODUCTCSV = "ID,NAME,PRICE,OLD_PRICE,STOCK,BRAND\r\n"
			+ "43664,LEGO #14362905,24.99,29.99,0,LEGO\r\n" + "18450,s.Oliver #1574690,35.99,35.99,12,s.Oliver\r\n"
			+ "15348,ESPRIT #796418,48.99,69.99,0,ESPRIT\r\n" + "16113,Friboo #62442,29.99,34.99,0,Friboo\r\n"
			+ "9893,NIKE #1283807,24.99,24.99,6,NIKE\r\n" + "14541,s.Oliver #2119423,25.99,25.99,3,s.Oliver\r\n"
			+ "22704,s.Oliver #3253651,29.99,29.99,5,s.Oliver\r\n" + "19302,Sony #1798446,69.99,69.99,0,Sony\r\n"
			+ "42878,LEGO #9929190,30.99,39.95,0,LEGO\r\n" + "40755,s.Oliver #7538143,8.99,8.99,8,s.Oliver\r\n"
			+ "4361,LEGO #168389,29.99,29.99,0,LEGO\r\n" + "467,s.Oliver #5687,22.99,22.99,0,s.Oliver\r\n"
			+ "10920,s.Oliver #744328,23.99,23.99,0,s.Oliver\r\n" + "9848,VANS #912582,74.95,74.95,5,VANS\r\n"
			+ "35022,SIKU #6797897,36.99,39.99,68,SIKU\r\n" + "16520,LEGO #1086696,33.99,36.99,340,LEGO\r\n"
			+ "24525,LEGO #8545040,59.99,69.99,2,LEGO\r\n" + "2044,LEGO #269858,26.99,29.99,95,LEGO\r\n"
			+ "28348,PLAYMOBIL #6962213,16.99,17.99,86,PLAYMOBIL\r\n" + "44074,LEGO #3138861,29.99,29.99,0,LEGO\r\n"
			+ "37596,LEGO #11751436,29.99,29.99,0,LEGO\r\n" + "314,PLAYMOBIL #86252,18.99,19.99,94,PLAYMOBIL\r\n"
			+ "30821,JOY TOY #5656921,7.99,7.99,183,JOY TOY\r\n" + "18171,SIKU #4143756,18.99,19.99,43,SIKU\r\n"
			+ "48738,UNDERCOVER #2462931,4.99,7.99,37,UNDERCOVER\r\n"
			+ "28398,PLAYMOBIL #4161522,18.99,23.99,34,PLAYMOBIL\r\n"
			+ "34074,PLAYMOBIL #6358024,41.99,59.99,361,PLAYMOBIL\r\n"
			+ "15436,LEGO #1196398,221.99,299.99,177,LEGO\r\n"
			+ "43787,PLAYMOBIL #5860360,63.99,82.99,500,PLAYMOBIL\r\n"
			+ "312,PLAYMOBIL #20059,4.29,3.99,184,PLAYMOBIL\r\n"
			+ "45802,PLAYMOBIL #12865528,23.99,29.99,328,PLAYMOBIL\r\n"
			+ "13726,PLAYMOBIL #700159,27.99,34.99,30,PLAYMOBIL\r\n" + "32269,LEGO #10847620,34.99,49.99,281,LEGO\r\n"
			+ "19132,PLAYMOBIL #4073444,8.49,9.99,75,PLAYMOBIL\r\n"
			+ "1968,PLAYMOBIL #56567,76.99,104.99,131,PLAYMOBIL\r\n"
			+ "47433,PLAYMOBIL #14841456,14.99,16.99,236,PLAYMOBIL\r\n"
			+ "47271,PLAYMOBIL #3755351,28.99,37.99,114,PLAYMOBIL\r\n"
			+ "13395,PLAYMOBIL #2781318,25.99,33.99,150,PLAYMOBIL\r\n"
			+ "28105,PLAYMOBIL #2361956,7.49,7.99,71,PLAYMOBIL\r\n" + "20347,LEGO #6429784,9.49,9.99,261,LEGO\r\n"
			+ "30185,SIKU #8498723,33.99,39.99,30,SIKU\r\n" + "41078,PLAYMOBIL #9528919,43.99,56.99,394,PLAYMOBIL\r\n"
			+ "31255,PLAYMOBIL #10892898,83.99,114.99,123,PLAYMOBIL\r\n"
			+ "49135,PLAYMOBIL #1628099,8.49,9.99,136,PLAYMOBIL\r\n" + "44262,LEGO #10858542,18.99,19.99,377,LEGO\r\n"
			+ "830,PLAYMOBIL #30044,17.99,19.99,64,PLAYMOBIL\r\n" + "48455,JOY TOY #5286205,8.49,4.99,3,JOY TOY\r\n"
			+ "36044,LEGO #5938952,14.99,14.99,1097,LEGO\r\n" + "32521,LEGO #8940928,14.99,14.99,229,LEGO\r\n"
			+ "25289,Universal #1823891,9.99,9.99,0,Universal\r\n"
			+ "33065,PLAYMOBIL #5503230,17.99,19.99,22,PLAYMOBIL\r\n" + "13497,LEGO #2156688,53.99,59.99,123,LEGO\r\n"
			+ "14492,LEGO #1066907,90.99,99.99,210,LEGO\r\n" + "1397,PLAYMOBIL #337649,9.49,9.99,113,PLAYMOBIL\r\n"
			+ "30604,PLAYMOBIL #4973903,11.99,13.99,199,PLAYMOBIL\r\n"
			+ "49101,PLAYMOBIL #2155560,57.99,77.99,198,PLAYMOBIL\r\n"
			+ "42422,PLAYMOBIL #698556,11.99,13.99,52,PLAYMOBIL\r\n" + "11919,SIKU #2148864,19.99,22.99,5,SIKU\r\n"
			+ "12114,SIKU #833906,19.99,19.99,143,SIKU\r\n" + "41916,LEGO #13760358,87.99,119.99,286,LEGO\r\n"
			+ "44751,PLAYMOBIL #14639658,25.99,34.99,129,PLAYMOBIL\r\n"
			+ "35192,UNDERCOVER #1812325,15.99,19.99,107,UNDERCOVER\r\n"
			+ "29674,PLAYMOBIL #2166675,60.99,72.99,29,PLAYMOBIL\r\n"
			+ "42418,PLAYMOBIL #2543072,35.99,47.99,136,PLAYMOBIL\r\n" + "10617,LEGO #3572427,88.99,89.99,10,LEGO\r\n"
			+ "4347,SIKU #818043,306.99,339.0,6,SIKU\r\n" + "18514,PLAYMOBIL #5190980,6.49,8.49,130,PLAYMOBIL\r\n"
			+ "15320,SIKU #4695620,20.99,22.99,35,SIKU\r\n" + "30803,UNDERCOVER #7301690,9.49,9.99,32,UNDERCOVER\r\n"
			+ "37635,JOY TOY #3009101,11.99,12.99,49,JOY TOY\r\n" + "42964,JOY TOY #3279311,4.79,7.99,17,JOY TOY\r\n"
			+ "5461,LEGO #1895535,39.99,49.99,43,LEGO\r\n" + "24701,JOY TOY #7452765,11.99,16.99,4,JOY TOY\r\n"
			+ "13637,UNDERCOVER #3898797,6.49,9.99,57,UNDERCOVER\r\n"
			+ "9706,PLAYMOBIL #537028,18.99,19.99,58,PLAYMOBIL\r\n" + "9485,SIKU #1800538,24.99,27.99,26,SIKU\r\n"
			+ "1048,LEGO #217157,29.99,29.99,0,LEGO\r\n" + "49715,s.Oliver #2513935,25.99,29.99,5,s.Oliver\r\n"
			+ "10416,EB KIDS #1930809,31.99,39.99,0,EB KIDS\r\n" + "30883,LEGO #4798152,16.99,16.99,0,LEGO\r\n"
			+ "48023,s.Oliver #1716176,25.99,25.99,0,s.Oliver\r\n"
			+ "29760,s.Oliver #9430226,49.99,49.99,2,s.Oliver\r\n" + "1909,ESPRIT #430635,22.99,29.99,4,ESPRIT\r\n"
			+ "16437,s.Oliver #873028,48.95,69.95,4,s.Oliver\r\n" + "8891,s.Oliver #1359471,13.99,13.99,0,s.Oliver\r\n"
			+ "15883,s.Oliver #5056602,19.99,19.99,1,s.Oliver\r\n"
			+ "44611,s.Oliver #11189509,17.99,17.99,4,s.Oliver\r\n"
			+ "12900,s.Oliver #1573233,13.99,13.99,0,s.Oliver\r\n" + "7999,ESPRIT #1391009,35.0,49.95,0,ESPRIT\r\n"
			+ "34066,LEGO #5624811,9.99,9.99,0,LEGO\r\n" + "32877,LEGO #3713508,29.99,29.99,0,LEGO\r\n"
			+ "36447,LEGO #9390396,16.99,16.99,0,LEGO\r\n" + "10764,s.Oliver #668722,19.99,19.99,1,s.Oliver\r\n"
			+ "37004,NIKE #9759569,33.0,33.0,5,NIKE\r\n" + "24960,NIKE #6371917,25.0,25.0,9,NIKE\r\n"
			+ "2289,s.Oliver #102352,25.99,25.99,8,s.Oliver\r\n" + "45280,s.Oliver #12981015,15.99,15.99,0,s.Oliver\r\n"
			+ "20046,LEGO #4896500,9.99,9.99,0,LEGO\r\n" + "9180,LEGO #2265408,9.99,9.99,0,LEGO\r\n"
			+ "34034,s.Oliver #9595654,36.99,39.99,11,s.Oliver\r\n"
			+ "27219,s.Oliver #421816,10.99,11.99,0,s.Oliver\r\n" + "1394,s.Oliver #103894,11.99,11.99,0,s.Oliver\r\n"
			+ "48619,s.Oliver #15869774,17.99,17.99,0,s.Oliver\r\n"
			+ "1552,TOMMY HILFIGER #208032,27.99,27.99,19,TOMMY HILFIGER\r\n"
			+ "30718,s.Oliver #8948362,29.99,29.99,0,s.Oliver\r\n" + "20940,NIKE #801238,39.99,39.99,1,NIKE\r\n"
			+ "24974,PLAYMOBIL #4830667,44.99,59.99,0,PLAYMOBIL\r\n"
			+ "10475,TOMMY HILFIGER #803396,15.99,49.99,0,TOMMY HILFIGER\r\n"
			+ "25872,s.Oliver #8176000,29.99,29.99,0,s.Oliver\r\n"
			+ "47260,s.Oliver #14308414,39.99,39.99,1,s.Oliver\r\n" + "14225,ESPRIT #4499729,29.99,29.99,5,ESPRIT\r\n"
			+ "6637,Friboo #740609,34.99,34.99,0,Friboo\r\n" + "41471,LEGO #12717505,12.99,14.99,144,LEGO\r\n"
			+ "44686,LEGO #8352461,24.99,32.99,788,LEGO\r\n" + "42142,LEGO #14155874,32.99,39.99,260,LEGO\r\n"
			+ "13197,LEGO #3262515,32.99,39.99,204,LEGO\r\n" + "18542,LEGO #1699204,17.99,19.99,328,LEGO\r\n"
			+ "10830,LEGO #2183969,30.99,32.99,45,LEGO\r\n" + "36095,PLAYMOBIL #2128686,9.99,9.99,71,PLAYMOBIL\r\n"
			+ "22737,LEGO #3219424,17.99,19.99,314,LEGO\r\n" + "24103,LEGO #737391,16.99,19.99,301,LEGO\r\n"
			+ "48891,LEGO #9851310,18.99,19.99,2841,LEGO\r\n" + "23084,LEGO #6375429,23.99,29.99,844,LEGO\r\n"
			+ "5464,LEGO #814752,35.99,39.99,89,LEGO\r\n" + "27770,LEGO #6789119,14.99,14.99,222,LEGO\r\n"
			+ "23302,LEGO #6335150,23.99,29.99,576,LEGO\r\n" + "18149,LEGO #3226541,45.99,49.99,628,LEGO\r\n"
			+ "10649,LEGO #1611515,9.99,9.99,99,LEGO\r\n" + "39915,LEGO #13422305,9.99,9.99,324,LEGO\r\n"
			+ "36004,LEGO #11249816,25.99,29.99,481,LEGO\r\n" + "686,PLAYMOBIL #136969,23.99,29.99,325,PLAYMOBIL\r\n"
			+ "13966,PLAYMOBIL #3182811,18.99,19.99,232,PLAYMOBIL\r\n" + "11169,LEGO #2403428,35.99,49.99,190,LEGO\r\n"
			+ "15868,LEGO #5236899,25.99,29.99,165,LEGO\r\n" + "30917,LEGO #6776161,18.99,19.99,307,LEGO\r\n"
			+ "893,LEGO #227603,44.99,59.99,93,LEGO\r\n" + "49470,LEGO #17301292,23.99,29.99,316,LEGO\r\n"
			+ "3314,UNDERCOVER #1026005,9.99,9.99,18,UNDERCOVER\r\n" + "32042,LEGO #1935889,14.99,14.99,648,LEGO\r\n"
			+ "15794,LEGO #4561840,6.99,4.99,490,LEGO\r\n" + "33952,UNDERCOVER #3337812,9.99,9.99,7,UNDERCOVER\r\n"
			+ "12903,UNDERCOVER #4255599,9.99,9.99,21,UNDERCOVER\r\n" + "7300,LEGO #2325904,17.99,19.99,344,LEGO\r\n"
			+ "20955,LEGO #5103994,20.99,24.99,176,LEGO\r\n" + "10485,LEGO #1338034,73.99,99.99,132,LEGO";

	/**
	 * CSV_WITHOUT_DATA
	 */
	public static final String CSV_WITHOUT_DATA = "ID,NAME,PRICE,OLD_PRICE,STOCK,BRAND";
	
	/**
	 * CSV_WITH_UNEXPECTED_HEADER
	 */
	public static final String CSV_WITH_UNEXPECTED_HEADER = "ID,WRONG_NAME,PRICE,OLD_PRICE,STOCK,BRAND\r\n" + 
			"43664,LEGO #14362905,24.99,29.99,0,LEGO";
	
	/**
	 * MEDIA_TYPE
	 */
	public static final String MEDIA_TYPE = "text/csv";
	
	/**
	 * MEDIA_TYPE
	 */
	public static final String WRONG_MEDIA_TYPE = "test";

	/**
	 * PRODUCT_UPLOAD_URL
	 */
	public static final String PRODUCT_UPLOAD_URL = "/product/upload";

	/**
	 * UPLOAD_RESPONSE
	 */
	public static final String UPLOAD_RESPONSE = "All the 146 records in the file : productcsv are updated successfully to the datastore.";

}
