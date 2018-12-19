package com.zhangpan.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志管理 "sys_log"
 * @author zhangpan
 * @date 2018年10月29日
 */
public class SysLog implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String createUser;
    
    private String createTime;
    
    private String logType;
    
    private String logDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogDesc() {
        return logDesc;
    }

    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc;
    }
    
}
