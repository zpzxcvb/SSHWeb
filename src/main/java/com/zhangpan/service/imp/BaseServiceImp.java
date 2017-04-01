package com.zhangpan.service.imp;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.service.BaseService;

@Service
public class BaseServiceImp<T,PK extends Serializable> implements BaseService<T,PK> {

	@Autowired
	private BaseDao<T,PK> baseDao;
	
	@Override
	public int save(T model) {
		return baseDao.save(model);
	}

	@Override
	public int deleteById(PK id) {
		return baseDao.deleteById(id);
	}

	@Override
	public int deleteByIds(PK[] ids) {
		return baseDao.deleteByIds(ids);
	}

	@Override
	public int update(T model) {
		return baseDao.update(model);
	}

	@Override
	public T findById(PK id) {
		return baseDao.findById(id);
	}

	@Override
	public List<T> findAll() {
		return baseDao.findAll();
	}
	

}
