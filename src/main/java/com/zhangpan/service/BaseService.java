package com.zhangpan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zhangpan.config.PageInfo;

public interface BaseService<T,PK extends Serializable> {
	
	public int save(T model);
	
	public int deleteById(PK id);
	
	public int deleteByIds(PK[] ids);
	
	public int update(T model);

    public T findById(PK id);
    
    public List<T> findAll();
    
    public List<Map> findPage(Map<String, String> map);
	
}
