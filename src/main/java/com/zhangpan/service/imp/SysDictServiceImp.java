package com.zhangpan.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangpan.dao.SysDictDao;
import com.zhangpan.model.SysDictType;
import com.zhangpan.service.SysDictService;

@Service
public class SysDictServiceImp extends BaseServiceImp<SysDictType,Integer> implements SysDictService {
	
	@Resource
	private SysDictDao sysDictDao;

}
