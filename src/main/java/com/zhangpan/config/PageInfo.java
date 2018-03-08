package com.zhangpan.config;

import java.io.Serializable;
import java.util.List;

public class PageInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer pageNum = 1;//当前页
	
	private Integer pageSize = 10;//每页记录数
	
	private Integer totalNum;//总记录数
	
	private Integer totalPage;//总页数
	
	private Integer startIndex;//开始索引
	
	private List data;//分页结果

	public PageInfo() {}

	public PageInfo(Integer pageNum, Integer pageSize, Integer totalNum) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalNum = totalNum;
		this.totalPage = (this.totalNum + this.pageSize-1)/this.pageSize;
		this.startIndex = (pageNum - 1)*pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
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

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}
	
}
