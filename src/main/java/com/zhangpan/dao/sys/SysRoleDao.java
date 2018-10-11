package com.zhangpan.dao.sys;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysRole;

public interface SysRoleDao extends BaseDao<SysRole,Integer> {
    
    /**
     * 根据角色查用户
     * @return
     */
    public Page<Object> findUserByRoleId(Integer roleId);
    
    /**
     * 根据角色查权限
     * @return
     */
    public List<Map<String, Object>> findPermissionByRoleId(Integer roleId);
    
    /**
     * 根据角色查已选权限
     * @return
     */
    public List<Map<String, Object>> findCheckedPermissionByRoleId(Integer roleId);
}