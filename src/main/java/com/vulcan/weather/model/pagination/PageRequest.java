package com.vulcan.weather.model.pagination;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PageRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private final Integer pageNumber;
	
	private final Integer pageSize;
	
	private final Integer limit;
	
	private final Integer offset;
	
	 private boolean sortOrder;

	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	
	private static final Integer DEFAULT_PAGE_SIZE = 20;
	
	@JsonCreator
	public PageRequest(@JsonProperty("pageNumber") final Integer pageNumber, @JsonProperty("pageSize") final Integer pageSize) {
	        this.pageNumber = (pageNumber == null) ? DEFAULT_PAGE_NUMBER : pageNumber;
	        this.pageSize = (pageSize == null) ? DEFAULT_PAGE_SIZE : pageSize;
	        this.offset = this.pageSize * this.pageNumber;
	        this.limit = this.offset+this.pageSize+1;
	}

	public boolean isSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(boolean sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getOffset() {
		return offset;
	}	
}
