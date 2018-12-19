package com.zhangpan.model.article;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * 接口配置表 "article"
 * @author zhangpan
 * @date 2018年11月6日
 */
public class Article implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String title;

    private String type;

    private String content;
    
    private String fileUrl;
    
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}