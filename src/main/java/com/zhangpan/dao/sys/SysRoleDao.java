package com.zhangpan.dao.sys;

import com.github.pagehelper.Page;
import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysRole;

public interface SysRoleDao extends BaseDao<SysRole,Integer> {
    
    /**
     * 根据角色查用户
     * @return
     */
    public Page<Object> findUserByRoleId(Integer roleId);
}