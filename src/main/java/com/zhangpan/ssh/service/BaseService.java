package com.zhangpan.ssh.service;

import java.io.Serializable;

import com.zhangpan.ssh.dao.BaseDao;

public class BaseService<T,PK extends Serializable> {
	
	private BaseDao<T, PK> baseDao;

	public T get(PK id) {
		return baseDao.get(id);
	}

	public T load(PK id) {
		return baseDao.load(id);
	}

	public void save(T t) {
		baseDao.saveOrUpdate(t);
		
	}

}
