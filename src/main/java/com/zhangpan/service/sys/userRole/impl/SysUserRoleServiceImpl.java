package com.zhangpan.service.sys.userRole.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.sys.SysUserRoleDao;
import com.zhangpan.model.SysUserRole;
import com.zhangpan.service.sys.userRole.SysUserRoleService;

/**
 * @author zhangpan
 * @date 2018年9月25日
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleDao sysUserRoleDao;
    
    @Override
    public int save(SysUserRole model) {
        return sysUserRoleDao.save(model);
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
    public int update(SysUserRole model) {
        return 0;
    }

    @Override
    public SysUserRole findById(Integer id) {
        return null;
    }

    @Override
    public List<SysUserRole> findList(Map<String, Object> params) {
        return sysUserRoleDao.findList(params);
    }

    @Override
    public Page<Object> findPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public int batchSave(Map<String, Object> params) {
        return sysUserRoleDao.batchSave(params);
    }

    @Override
    public int deleteUserRoleByUserId(Integer userId) {
        return sysUserRoleDao.deleteUserRoleByUserId(userId);
    }

}
