package com.zhangpan.service.sys.role;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zhangpan.model.SysRole;
import com.zhangpan.service.BaseService;

/**
 * @author zhangpan
 * @date 2018年6月14日
 */
public interface SysRoleService extends BaseService<SysRole,Integer>{
    
    /**
     * 根据角色查用户
     * @return
     */
    public Page<Object> findUserByRoleId(Integer roleId);
    
    /**
     * 根据角色查权限
     * @return
     */
    public List<Map<String, Object>> findPermissionByRoleId(Integer roleId);
    
    /**
     * 根据角色查已选权限
     * @return
     */
    public List<Map<String, Object>> findCheckedPermissionByRoleId(Integer roleId);
}
