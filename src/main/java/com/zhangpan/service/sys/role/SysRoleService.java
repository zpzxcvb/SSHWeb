package com.zhangpan.service.sys.role;

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
}
