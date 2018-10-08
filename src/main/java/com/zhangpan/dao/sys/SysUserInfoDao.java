package com.zhangpan.dao.sys;

import java.util.Map;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysUserInfo;

public interface SysUserInfoDao extends BaseDao<SysUserInfo,Integer> {
    
    /**
     * 根据参数查询单个
     * 
     * @param id
     * @return
     */
    public SysUserInfo findByParams(Map<String, Object> params);
    
}