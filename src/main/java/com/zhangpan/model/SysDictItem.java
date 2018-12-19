package com.zhangpan.model;

import java.io.Serializable;

/**
 * 字典数据表 "sys_dict_item"
 * @author 张攀
 * @date : 2017-3-22 下午5:36:29
 */
public class SysDictItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;

    private String dictName;

    private String dictCode;
    
    private String dictType;
    
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}