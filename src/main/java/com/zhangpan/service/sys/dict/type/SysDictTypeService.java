package com.zhangpan.service.sys.dict.type;

import java.util.List;
import java.util.Map;

import com.zhangpan.model.SysDictType;
import com.zhangpan.service.BaseService;

/**
 * @author zhangpan
 * @date 2018年9月18日
 */
public interface SysDictTypeService extends BaseService<SysDictType, Integer> {
    
    public List<Map<String, Object>> findDictTypes(Map<String, String> params);

}
