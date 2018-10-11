package com.zhangpan.service.sys.rolePermission;

import com.zhangpan.model.SysRolePermission;
import com.zhangpan.service.BaseService;

/**
 * @author zhangpan
 * @date 2018年9月27日
 */
public interface SysRolePermissionService extends BaseService<SysRolePermission, Integer> {
    
    /**
     * 根据角色id删除角色权限关系
     * @return
     */
    public int deleteRolePermissionByRoleId(Integer roleId);

}
