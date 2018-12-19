package com.zhangpan.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhangpan.model.SysDictItem;

public class SysConstant {
    
    public static Map<String, List<SysDictItem>> map;
	
    public static List<Map<String, Object>> getSysDictValue(String key) {
        List<Map<String, Object>> types = new ArrayList<>();
        List<SysDictItem> items = map.get(key);
        if(items != null) {
            for(SysDictItem item : items) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", item.getDictName());
                map.put("value", item.getDictCode());
                types.add(map);
            }
        }
        return types;
    }

    public static void putSysDicts(Map<String, List<SysDictItem>> sysDicts) {
        map = sysDicts;
    }
}
