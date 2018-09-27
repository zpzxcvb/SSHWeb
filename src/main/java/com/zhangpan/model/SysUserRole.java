package com.zhangpan.model;

import java.io.Serializable;

/**
 * 系统角色表 "sys_role"
 * @author zhangpan
 * @date 2018年9月17日
 */
public class SysUserRole implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer roleId;

    private String roleName;

    private String createTime;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}