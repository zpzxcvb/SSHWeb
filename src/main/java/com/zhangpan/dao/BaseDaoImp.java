package com.zhangpan.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BaseDaoImp<T,PK extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T,PK> {

	private Class<?> clazz;
	
	public static final String SQLNAME_SEPARATOR = "."; 

	public static final String SQL_SAVE = "save";   
	public static final String SQL_DELETEBYID = "deleteById"; 
	public static final String SQL_DELETEBYIDS = "deleteByIds"; 
	public static final String SQL_UPDATE = "update";   
	public static final String SQL_FINDBYID = "findById"; 
	public static final String SQL_FINDALL = "findAll"; 
	public static final String SQL_FINDPAGEBY = "findPageBy";   
	public static final String SQL_FINDLISTBY = "findListBy"; 
	public static final String SQL_GETCOUNTBY = "getCountBy"; 

	private static final String SORT_NAME = "SORT"; 

	private static final String DIR_NAME = "DIR"; 
	/** 
	* 获取默认SqlMapping命名空间。 
	* 使用泛型参数中业务实体类型的全限定名作为默认的命名空间。 
	* 如果实际应用中需要特殊的命名空间，可由子类重写该方法实现自己的命名空间规则。 
	* @return 返回命名空间字符串 
	*/ 
	protected String getDefaultSqlNamespace() { 
		clazz = getClassGenricType(getClass());  
		String nameSpace = clazz.getName(); 
		return nameSpace; 
	}
	
	public static Class<?> getClassGenricType(Class<?> clazz){  
        //返回表示此Class所表示的实体（类、接口、基本类型或 void）的直接超类的Type。如果超类是参数化类型，则返回的Type对象必须准确反映源代码中所使用的实际类型参数。  
        Type type = clazz.getGenericSuperclass();  
        //强制转化为参数化类型：Collection<String>  
        ParameterizedType pt = (ParameterizedType) type;  
        //返回表示此类型的实际类型参数的 Type 对象的数组。   
        Type[] types = pt.getActualTypeArguments();  
        return (Class<?>) types[0];  
    }
	
	protected String getSqlName(String sqlName) { 
		return getDefaultSqlNamespace() + SQLNAME_SEPARATOR + sqlName; 
	} 
	
	@Override
	public int save(T model) {
		return this.getSqlSession().insert(getSqlName(SQL_SAVE), model);
	}

	@Override
	public int deleteById(PK id) {
		return this.getSqlSession().delete(getSqlName(SQL_DELETEBYID), id);
	}

	@Override
	public int deleteByIds(PK[] ids) {
		return this.getSqlSession().delete(getSqlName(SQL_DELETEBYIDS), ids);
	}

	@Override
	public int update(T model) {
		return this.getSqlSession().update(getSqlName(SQL_UPDATE), model);
	}

	@Override
	public T findById(PK id) {
		return (T)this.getSqlSession().selectOne(getSqlName(SQL_FINDBYID), id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>)this.getSqlSession().selectList(getSqlName(SQL_FINDALL));
	}

}
