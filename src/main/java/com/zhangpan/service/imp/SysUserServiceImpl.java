package com.zhangpan.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangpan.dao.SysUserDao;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.SysUserService;

@Service
public class SysUserServiceImpl extends BaseServiceImp<SysUser,Integer> implements SysUserService{

	@Resource
	private SysUserDao sysUserDao;

}
