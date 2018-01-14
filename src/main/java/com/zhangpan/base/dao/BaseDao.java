package com.zhangpan.base.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T,PK extends Serializable> {
	
	public int save(T model);
	
	public int deleteById(PK id);
	
	public int deleteByIds(PK[] ids);
	
	public int update(T model);

    public T findById(PK id);
    
    public List<T> findAll();
}
