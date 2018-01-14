package com.zhangpan.model;

import java.io.Serializable;
import java.util.List;

/**
 * 字典类型表 "sys_dict_type"
 * @author 张攀
 * @ClassName : SysDictType
 * @ModifiedBy : 张攀
 * @date : 2017-3-22 下午5:35:43
 */
public class SysDictType implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String dictValue;

    private String dictCode;
    
    private List<SysDictItem> sysDictItems;
    
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

	public List<SysDictItem> getSysDictItems() {
		return sysDictItems;
	}

	public void setSysDictItems(List<SysDictItem> sysDictItems) {
		this.sysDictItems = sysDictItems;
	}

}