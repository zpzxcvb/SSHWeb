package com.zhangpan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

public interface BaseService<T, PK extends Serializable> {

    /**
     * 添加
     * 
     * @param model
     * @return
     */
    public int save(T model);

    /**
     * 批量添加
     * 
     * @param params
     * @return
     */
    public int batchSave(Map<String, Object> params);

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    public int deleteById(PK id);

    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    public int deleteByIds(PK[] ids);

    /**
     * 修改
     * 
     * @param model
     * @return
     */
    public int update(T model);

    /**
     * 根据id查询
     * 
     * @param id
     * @return
     */
    public T findById(PK id);

    /**
     * 查询
     * 
     * @param params
     * @return
     */
    public List<T> findList(Map<String, String> params);

    /**
     * 带分页查询
     * 
     * @param params
     * @return
     */
    public Page<Object> findPage(Map<String, String> params);

}
