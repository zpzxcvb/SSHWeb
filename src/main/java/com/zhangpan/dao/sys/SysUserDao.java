package com.zhangpan.dao.sys;

import java.util.List;
import java.util.Map;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysRole;
import com.zhangpan.model.SysUser;

public interface SysUserDao extends BaseDao<SysUser,Integer> {
    
    public SysUser findUserByName(String name);

    /**
     * 根据用户查角色
     * @return
     */
    public List<SysRole> findRoleByUserName(String userName);
    
    /**
     * 根据用户查权限
     * @return
     */
    public List<Map<String, Object>> findPermissionByUserId(Integer userId);
}