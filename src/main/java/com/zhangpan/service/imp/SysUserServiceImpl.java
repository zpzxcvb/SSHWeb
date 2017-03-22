package com.zhangpan.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangpan.dao.SysUserDao;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{

	@Resource
	private SysUserDao sysUserDao;

	@Override
	public int save(SysUser model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteArray(int[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SysUser model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SysUser findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysUser> findAll() {
		return sysUserDao.findAll();
	}

}
