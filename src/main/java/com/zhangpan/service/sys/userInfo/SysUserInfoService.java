package com.zhangpan.service.sys.userInfo;

import java.util.Map;

import com.zhangpan.model.SysUserInfo;
import com.zhangpan.service.BaseService;

/**
 * @author zhangpan
 * @date 2018年6月14日
 */
public interface SysUserInfoService extends BaseService<SysUserInfo,Integer>{
    
    /**
     * 根据参数查询单个
     * 
     * @param id
     * @return
     */
    public SysUserInfo findByParams(Map<String, Object> params);
    
}
