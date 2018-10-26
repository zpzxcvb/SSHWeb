package com.zhangpan.service.sys.user;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zhangpan.model.SysRole;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.BaseService;

/**
 * @author zhangpan
 * @date 2018年6月14日
 */
public interface SysUserService extends BaseService<SysUser,Integer>{
    
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
