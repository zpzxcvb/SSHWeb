package com.zhangpan.ssh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhangpan.ssh.dao.BaseDao;
import com.zhangpan.ssh.util.Page;

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	private Class<?> entityClass;
	
	/**
	 * 在构造函数中利用反射机制获得参数T的具体类
	 */
	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = pt.getActualTypeArguments();
		entityClass = (Class) types[0];
		System.out.println("实体名称："+entityClass.getName()+"====>"+entityClass.getSimpleName());
	}
	
	@Autowired  
    private SessionFactory sessionfactory;  
	
	public Session getSession(){  
        return sessionfactory.getCurrentSession();    
    }
	
	@Override
	public T get(PK id) {
		return (T) this.getSession().get(entityClass, id);
	}

	@Override
	public T load(PK id) {
		return (T) this.getSession().load(entityClass, id);
	}

	@Override
	public void save(T t) {
		this.getSession().save(t);
	}

	@Override
	public void update(T t) {
		this.getSession().update(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delObject(T t) {
		this.getSession().delete(t);
	}

	@Override
	public void del(PK id) {
		T t = load(id);
		delObject(t);
	}

	@Override
	public void delObjects(PK[] ids) {
		for (PK id : ids) {
			del(id);
		}
	}
	
	@Override
	public List getAll() {
		String hql="select t from "+entityClass.getSimpleName();
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public List getAllCache() {
		return getAll();
	}

	@Override
	public Page getPage() {
		String hql="select t from "+entityClass.getSimpleName();
		return searchPaginated(hql);
	}

	@Override
	public Page searchPaginated(String hql) {
		return searchPaginated(hql, new Object[] {});
	}

	@Override
	public Page searchPaginated(String hql, Object param) {
		return searchPaginated(hql, new Object[] { param });
	}

	@Override
	public Page searchPaginated(String hql, List params) {
		String countHql = getCountQuery(hql);
		Query query = getSession().createQuery(countHql);
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		int total = ((Long) query.uniqueResult()).intValue();

		query = getSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		
		
//		int startIndex=Page.
//		
//		query.setFirstResult(SystemContext.getOffset());
//		query.setMaxResults(SystemContext.getPagesize());
//
//		// 或缺数据集合
//		List list = query.list();
//		Page page = new Page();
//		page.setRows(list);
//		page.setTotalRows(total);
//		page.setPageSize(SystemContext.getPagesize());
//		page.setPageNo(SystemContext.getPageNo());
//		page.setSort(SystemContext.getSort());
//		page.setDirection(SystemContext.getDirection());

		return null;
	}

	@Override
	public Page searchPaginated(String hql, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page searchPaginated(String hql, Map params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static String getCountQuery(String hql) {
		int orderIndex = hql.lastIndexOf(" order ");
		if (orderIndex != -1) {

			hql = hql.substring(0, orderIndex);
		}
		hql="select count(*) "+hql.substring(hql.indexOf("from "));
		return hql;
	}
	
	public static void main(String[] args) {
		String hql="select name,id,age from user order by id desc";
		String s=getCountQuery(hql);
		System.out.println(s);
	}

	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}
	
	
}
