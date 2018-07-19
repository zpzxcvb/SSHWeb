package com.zhangpan.service;

import java.util.Map;

import com.zhangpan.model.SysUser;

/**
 * @author zhangpan
 * @date 2018年6月14日
 */
public interface SysUserService extends BaseService<SysUser,Integer>{
    /**
     * 验证用户名和密码
     * @param params
     * @return
     */
    public SysUser userAuth(Map<String, String> params);
}
