package com.zhangpan.service.sys.user;

import java.util.Map;

import com.zhangpan.model.SysUser;
import com.zhangpan.service.BaseService;

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
    public int userAuth(Map<String, Object> params);
}
