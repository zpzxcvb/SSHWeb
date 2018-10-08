package com.zhangpan.service.sys.dict.item;

import java.util.Map;

import com.zhangpan.model.SysDictItem;
import com.zhangpan.service.BaseService;

/**
 * @author zhangpan
 * @date 2018年9月18日
 */
public interface SysDictItemService extends BaseService<SysDictItem, Integer> {

    /**
     * 判断重复code
     * @return
     */
    public int checkItemCode(Map<String, Object> params);
}
