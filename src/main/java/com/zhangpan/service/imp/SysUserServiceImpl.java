package com.zhangpan.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.SysUserDao;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserDao sysUserDao;

	@Override
	public int save(SysUser model) {
		return sysUserDao.save(model);
	}

	@Override
	public int deleteById(Integer id) {
		return sysUserDao.deleteById(id);
	}

	@Override
	public int deleteByIds(Integer[] ids) {
		return sysUserDao.deleteByIds(ids);
	}

	@Override
	public int update(SysUser model) {
		return sysUserDao.update(model);
	}

	@Override
	public SysUser findById(Integer id) {
		return sysUserDao.findById(id);
	}

	@Override
	public List<SysUser> findAll(Map<String, String> params) {
		return sysUserDao.findAll(params);
	}

	@Override
	public Page<SysUser> findPage(Map<String, String> params) {
		return sysUserDao.findPage(params);
	}

    @Override
    public SysUser userAuth(Map<String, String> params) {
        return sysUserDao.userAuth(params);
    }

}
