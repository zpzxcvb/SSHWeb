package com.zhangpan.dao.sys;

import java.util.Map;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysUser;

public interface SysUserDao extends BaseDao<SysUser,Integer> {
    
    public SysUser userAuth(Map<String, String> params);

}