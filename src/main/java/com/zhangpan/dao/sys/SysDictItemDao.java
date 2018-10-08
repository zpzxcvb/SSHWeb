package com.zhangpan.dao.sys;

import java.util.Map;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysDictItem;

public interface SysDictItemDao extends BaseDao<SysDictItem,Integer> {
    
    /**
     * 判断重复code
     * @return
     */
    public int checkItemCode(Map<String, Object> params);
}