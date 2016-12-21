package com.zhangpan.ssh.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zhangpan.ssh.util.Page;


public interface BaseDao<T, PK extends Serializable> {
	/**
	 * 根据id查询对象
	 * @param id 
	 * @return 泛型对象
	 */
	public T get(PK id);
	
	/**
	 *  根据id查询对象
	 * @param id 
	 * @return 泛型对象
	 */
	public T load(PK id);
	
	/**
	 * 保存对象
	 * @param object
	 */
	public void save(T object);
	
	/**
	 * 更新对象
	 * @param object
	 */
	public void update(T object);
	

	/**
	 * 保存或更新对象
	 * @param object
	 */
	public void saveOrUpdate(T object);
     
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void del(PK id);
	 
	/**
	 * 删除对象
	 * @param object
	 */
    public void delObject(T object);
    
    /**
     * 批量删除
     * @param ids id数组
     */
    public void delObjects(PK[] ids);
    
    /**
     * 查询T所有对象，默认按 id逆序排序
     * @return
     */
    public List<T> getAll();
    
    /**
     * 查询缓存中T所有对象，默认按 id逆序排序
     * @return
     */
    public List<T> getAllCache();
    
    /**
     * 查询T的分页，按 id逆序排序
     * @return 分页对象
     */
    public Page getPage();
    
    /**
     * 分页查询，无参数
     * @param hql 查询语句 
     * @return 分页对象
     */
	public Page searchPaginated(String hql);
	
	/**
	 * 分页查询，1个参数
	 * @param hql 查询语句 
	 * @param param 查询语句中参数
	 * @return 分页对象
	 */
	public Page searchPaginated(String hql,Object param);
	
	/**
	 * 分页查询，多个参数
	 * @param hql 查询语句 
	 * @param params 查询语句中参数
	 * @return 分页对象
	 */
	public Page searchPaginated(String hql,List params);
	
	/**
	 * 分页查询，多个参数
	 * @param hql 查询语句 
	 * @param params 查询语句中参数
	 * @return 分页对象
	 */
	public Page searchPaginated(String hql,Object[] params);
	
	/**
	 * 分页查询，多个参数
	 * @param hql 查询语句 
	 * @param params 参数名的方式查询，key=变量名，value=变量值（Object 或 Collection或Object[]）
	 * @return 分页对象
	 */
	public Page searchPaginated(String hql,Map params);
}
