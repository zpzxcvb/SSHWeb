package com.zhangpan.dao.sys;

import java.util.List;
import java.util.Map;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysDictType;

public interface SysDictTypeDao extends BaseDao<SysDictType,Integer> {
    
    public List<Map<String, Object>> findDictTypes(Map<String, String> params);
    
    public int checkTypeCode(Map<String, String> params);
}