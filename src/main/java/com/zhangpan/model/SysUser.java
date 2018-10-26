package com.zhangpan.model;

import java.io.Serializable;
import java.util.List;

/**
 * 系统用户表 "sys_user"
 * @author 张攀
 * @ClassName : SysUser
 * @ModifiedBy : 张攀
 * @date : 2017-3-22 下午5:34:48
 */
public class SysUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer userId;

    private String userName;

    private String password;

    private String createTime;
    
    private String updateTime;
    
    private Integer status;
    
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}