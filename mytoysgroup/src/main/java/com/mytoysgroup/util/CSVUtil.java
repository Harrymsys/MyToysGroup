package com.mytoysgroup.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.mytoysgroup.constants.CommonConstants;
import com.mytoysgroup.constants.ProductServiceConstant;
import com.toysgroup.exception.MyToysDefinedException;

/**
 * This class is used to handle the CSV related functionalities.
 * 
 * @author hariprasanth.l
 *
 */
public class CSVUtil {

	/**
	 * This method is used to check the CSV file format.
	 * 
	 * @throws MyToysDefinedException - If the file type is not valid.
	 */
	public static boolean isValidFile(MultipartFile file) throws MyToysDefinedException {
		String contentType = file.getContentType();
		for (String acceptedFileType : CommonConstants.ACCEPTED_FILE_TYPES) {
			if (contentType.contains(acceptedFileType)) {
				return true;
			}
		}
		throw new MyToysDefinedException("This file type is not accepted : " + contentType);
	}

	/**
	 * This method is used to convert the file as stream to the object
	 * 
	 * @return the iterable object of CSV Record.
	 * @throws MyToysDefinedException - If there is an exception in converting the
	 *                                file as stream to the object.
	 */
	@SuppressWarnings("resource")
	public static List<CSVRecord> convertFileToObject(MultipartFile file) throws MyToysDefinedException {
		try {
			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(file.getInputStream(), CommonConstants.ENCODING_FORMAT));
			return new CSVParser(fileReader,
					CSVFormat.DEFAULT.withHeader(ProductServiceConstant.HEADERS).withIgnoreHeaderCase().withTrim())
							.getRecords();
		} catch (Exception e) {
			throw new MyToysDefinedException(e);
		}
	}

	/**
	 * Method to convert the csv record to hashmap.
	 */
	public static Map<String, Object> csvRecordToMap(CSVRecord csvRecord, String[] headers) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		for (int index = 0; index < headers.length; index++) {
			String header = headers[index];
			String value = csvRecord.get(index);
			dataMap.put(header, value);
		}
		return dataMap;
	}

}
