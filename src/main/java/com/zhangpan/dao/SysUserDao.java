package com.zhangpan.dao;

import java.util.Map;

import com.zhangpan.model.SysUser;

public interface SysUserDao extends BaseDao<SysUser,Integer> {
    
    public SysUser userAuth(Map<String, String> params);

}