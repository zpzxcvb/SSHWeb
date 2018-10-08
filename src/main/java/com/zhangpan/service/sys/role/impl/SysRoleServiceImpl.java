package com.zhangpan.service.sys.role.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.sys.SysRoleDao;
import com.zhangpan.model.SysRole;
import com.zhangpan.service.sys.role.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	private SysRoleDao sysRoleDao;

	@Override
	public int save(SysRole model) {
		return sysRoleDao.save(model);
	}

	@Override
	public int deleteById(Integer id) {
		return sysRoleDao.deleteById(id);
	}

	@Override
	public int deleteByIds(Integer[] ids) {
		return sysRoleDao.deleteByIds(ids);
	}

	@Override
	public int update(SysRole model) {
		return sysRoleDao.update(model);
	}

	@Override
	public SysRole findById(Integer id) {
		return sysRoleDao.findById(id);
	}

	@Override
	public List<SysRole> findList(Map<String, Object> params) {
		return sysRoleDao.findList(params);
	}

	@Override
	public Page<Object> findPage(Map<String, Object> params) {
		return sysRoleDao.findPage(params);
	}

    @Override
    public Page<Object> findUserByRoleId(Integer roleId) {
        return sysRoleDao.findUserByRoleId(roleId);
    }

    @Override
    public int batchSave(Map<String, Object> params) {
        return 0;
    }

}
