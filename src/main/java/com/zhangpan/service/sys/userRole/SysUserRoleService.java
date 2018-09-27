package com.zhangpan.service.sys.userRole;

import java.util.Map;

import com.zhangpan.model.SysUserRole;
import com.zhangpan.service.BaseService;

/**
 * @author zhangpan
 * @date 2018年9月25日
 */
public interface SysUserRoleService extends BaseService<SysUserRole, Integer> {
    /**
     * 批量保存
     * @param params
     * @return
     */
    public int saveBatchUserRole(Map<String, Object> params);
}
