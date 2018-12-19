package com.zhangpan.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口配置表 "sys_interface"
 * @author zhangpan
 * @date 2018年11月6日
 */
public class SysInterface implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private String url;

    private String description;
    
    private Integer status;
    
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}