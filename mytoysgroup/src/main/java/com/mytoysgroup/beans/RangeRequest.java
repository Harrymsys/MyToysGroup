package com.mytoysgroup.beans;

/**
 * This class is used for accepting the request for range based query.
 * @author hariprasanth.l
 */
public class RangeRequest {

	/**
	 * Field to be used for range query.
	 */
	private String field;
	
	/**
	 * Starting value of the range query.
	 */
	private double startvalue;
	
	/**
	 * Ending value of the range query.
	 */
	private double endvalue;
	
	/**
	 * scrollId - for pagination.
	 */
	private String scrollId;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public double getStartvalue() {
		return startvalue;
	}

	public void setStartvalue(double startvalue) {
		this.startvalue = startvalue;
	}

	public double getEndvalue() {
		return endvalue;
	}

	public void setEndvalue(double endvalue) {
		this.endvalue = endvalue;
	}
	
	public String getScrollId() {
		return scrollId;
	}

	public void setScrollId(String scrollId) {
		this.scrollId = scrollId;
	}

}
