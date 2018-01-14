package com.zhangpan.config;

import java.util.List;

public class PageInfo<T> {
	
	private Integer currentPage = 1;//当前页
	
	private Integer pageSize = 10;//每页记录数
	
	private Integer totalNum;//总记录数
	
	private Integer totalPage;//总页数
	
	private Integer startIndex;//开始索引
	
	private List<T> items;//分页结果

	public PageInfo() {}

	public PageInfo(Integer currentPage, Integer pageSize, Integer totalNum) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalNum = totalNum;
		this.totalPage = (this.totalNum + this.pageSize-1)/this.pageSize;
		this.startIndex = (currentPage - 1)*pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	
}
