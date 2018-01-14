package com.zhangpan.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zhangpan.config.PageInfo;

public interface BaseDao<T,PK extends Serializable> {
	
	public int save(T model);
	
	public int deleteById(PK id);
	
	public int deleteByIds(PK[] ids);
	
	public int update(T model);

    public T findById(PK id);
    
    public List<T> findAll();
    
    public List<PageInfo<T>> findByPage(Map<String, String> map);
}
