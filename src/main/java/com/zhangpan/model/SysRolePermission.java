package com.zhangpan.model;

import java.io.Serializable;

/**
 * 系统角色权限表表 "sys_role"
 * @author zhangpan
 * @date 2018年9月17日
 */
public class SysRolePermission implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer roleId;

    private String permissionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
    
}