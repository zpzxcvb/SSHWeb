package com.zhangpan.ssh.util;

import java.util.List;


/**
 * 自定义json分页实体
 *
 */
public class Page {
	
	/**
	 * 默认每页显示的记录数
	 */
	private static final int DEFAULT_PAGE_SIZE = 10;  
	 
	/**
	 * 默认当前显示第一页
	 */
	private static final int DEFAULT_PAGE_NO = 1;  
	
	/**
	 * 当前是第几页
	 */
	private int pageNo;
	
	/**
	 * 总记录数
	 */
	private int totalRows;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 每页显示的记录数
	 */
	private int pageSize;
	
	/**
	 * 返回的结果集
	 */
	private List rows;
	
	/**
	 * 返回的Json格式的结果集
	 */
	private String rowsByJson;
	
	/**
	 * 排序的字段名
	 */
	private String sort;
	
	/**
	 * 排序方向
	 */
	private String direction;
	
	/**
	 * 初始化分页信息
	 * @param pageNo 当前页数
	 * @param pageSize 每页显示的记录数
	 * @return Page
	 */
	public Page(int pageNo, int pageSize) {  
        this.pageNo = pageNo;  
        this.pageSize = pageSize;  
    }  
  
	/**
	 * 初始化分页信息
	 * @param pageNo 当前页数
	 * @return Page
	 */
    public Page(int pageNo) {  
        this.pageNo = pageNo;  
        this.pageSize = DEFAULT_PAGE_SIZE;  
    }  
  
	/**
	 * 初始化分页信息，默认当前显示第一页，每页显示的记录数为默认值
	 * @return Page
	 */
    public Page() {  
        this.pageNo = DEFAULT_PAGE_NO;  
        this.pageSize = DEFAULT_PAGE_SIZE;  
    }  
    
    /**
     * 获取hibernate分页中 开始的索引数
     * @return
     */
    public int getFirstIndex() {  
        return pageSize * (pageNo - 1);  
    }  
  
    /**
     * 是否有前一页
     * @return
     */
    public boolean hasPrevious() {  
        return pageNo > 1;  
    }  
  
    /**
     * 是否有下一页
     * @return
     */
    public boolean hasNext() {  
        return pageNo < getTotalPage();  
    }   

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalRows() {
		if(null==this.getRows()){
			totalRows=0;
			
		}
		
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPage() {
		 long remainder = totalRows % this.getPageSize();  
		  
	        if (0 == remainder) {  
	            return totalRows / this.getPageSize();  
	        }  
	  
	        return totalRows / this.getPageSize() + 1;  
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public String getRowsByJson() {
		return rowsByJson;
	}

	public void setRowsByJson(String rowsByJson) {
		this.rowsByJson = rowsByJson;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
	
	
}
