package com.zhangpan.service.sys.rolePermission.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.sys.SysRolePermissionDao;
import com.zhangpan.model.SysRolePermission;
import com.zhangpan.service.sys.rolePermission.SysRolePermissionService;

/**
 * @author zhangpan
 * @date 2018年9月27日
 */
@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    
    @Resource
    private SysRolePermissionDao sysRolePermissionDao;

    @Override
    public int save(SysRolePermission model) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        return 0;
    }

    @Override
    public int update(SysRolePermission model) {
        return 0;
    }

    @Override
    public SysRolePermission findById(Integer id) {
        return null;
    }

    @Override
    public List<SysRolePermission> findList(Map<String, String> params) {
        return null;
    }

    @Override
    public Page<Object> findPage(Map<String, String> params) {
        return null;
    }

    @Override
    public int batchSave(Map<String, Object> params) {
        return sysRolePermissionDao.batchSave(params);
    }

}
