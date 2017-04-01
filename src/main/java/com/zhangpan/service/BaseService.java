package com.zhangpan.service;

import java.io.Serializable;

import com.zhangpan.dao.BaseDao;

public interface BaseService<T,PK extends Serializable> extends BaseDao<T,PK>{
	
}
