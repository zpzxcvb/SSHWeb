package com.zhangpan.dao.sys;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysRolePermission;

public interface SysRolePermissionDao extends BaseDao<SysRolePermission,Integer> {
    
    /**
     * 根据角色id删除角色权限关系
     * @return
     */
    public int deleteRolePermissionByRoleId(Integer roleId);
    
}