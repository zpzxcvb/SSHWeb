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

    private String dictName;
    
    private String dictType;
    
    private Integer parentId;
    
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

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}