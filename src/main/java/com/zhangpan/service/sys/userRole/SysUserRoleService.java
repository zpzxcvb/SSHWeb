package com.zhangpan.service.sys.userRole;

import com.zhangpan.model.SysUserRole;
import com.zhangpan.service.BaseService;

/**
 * @author zhangpan
 * @date 2018年9月25日
 */
public interface SysUserRoleService extends BaseService<SysUserRole, Integer> {
    
    /**
     * 根据用户id删除角色关系
     * @return
     */
    public int deleteUserRoleByUserId(Integer userId);

}
