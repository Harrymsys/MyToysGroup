package com.mytoysgroup.beans;

import java.util.List;
import java.util.Map;

/**
 * This class is used to provide the search response.
 * @author hariprasanth.l
 *
 */
public class ProductResponse {

	/**
	 * scrollId used to get the paginated data.
	 */
	private String scrollId;
	
	/**
	 * List of response for the search / get all query.
	 */
	private List<Map<String, Object>> data;
	
	/**
	 * Total number of data.
	 */
	private long total;
	
	/**
	 * totalRecordInCurrentSearch 
	 */
	private int totalRecordInCurrentSearch;
	
	public String getScrollId() {
		return scrollId;
	}

	public void setScrollId(String scrollId) {
		this.scrollId = scrollId;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getTotalRecordInCurrentSearch() {
		return totalRecordInCurrentSearch;
	}

	public void setTotalRecordInCurrentSearch(int totalRecordInCurrentSearch) {
		this.totalRecordInCurrentSearch = totalRecordInCurrentSearch;
	}

}
