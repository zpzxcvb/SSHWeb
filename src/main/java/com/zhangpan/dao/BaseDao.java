package com.zhangpan.dao;

import java.util.List;

public interface BaseDao<T> {
	
	public int save(T model);
	
	public int delete(int id);
	
	public int deleteArray(int[] ids);
	
	public int update(T model);

    public T findById(int id);
    
    public List<T> findAll();
}
