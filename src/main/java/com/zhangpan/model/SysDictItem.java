package com.zhangpan.model;

import java.io.Serializable;

/**
 * 字典数据表 "sys_dict_item"
 * @author 张攀
 * @ClassName : SysDictItem
 * @ModifiedBy : 张攀
 * @date : 2017-3-22 下午5:36:29
 */
public class SysDictItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;

    private String dictValue;

    private String dictCode;
    
    private Integer orderNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
    
}