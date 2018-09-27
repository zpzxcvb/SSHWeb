package com.zhangpan.dao.sys;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysUserRole;

public interface SysUserRoleDao extends BaseDao<SysUserRole,Integer> {
    
    /**
     * 批量保存
     * @param params
     * @return
     */
    public int saveBatchUserRole(Map<String, Object> params);
}