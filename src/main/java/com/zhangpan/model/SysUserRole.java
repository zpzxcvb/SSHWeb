package com.zhangpan.model;

import java.io.Serializable;

/**
 * 系统用户角色关系表 "sys_user_role"
 * @author zhangpan
 * @date 2018年9月17日
 */
public class SysUserRole implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer userId;

    private String roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
}