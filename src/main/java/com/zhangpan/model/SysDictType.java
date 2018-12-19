package com.zhangpan.model;

import java.io.Serializable;

/**
 * 字典类型表 "sys_dict_type"
 * @author 张攀
 * @date : 2017-3-22 下午5:35:43
 */
public class SysDictType implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String dictName;

    private String dictCode;
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}