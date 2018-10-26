package com.zhangpan.dao.sys;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysUserRole;

public interface SysUserRoleDao extends BaseDao<SysUserRole,Integer> {
    
    /**
     * 根据用户id删除角色关系
     * @return
     */
    public int deleteUserRoleByUserId(Integer userId);
}