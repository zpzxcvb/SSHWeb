package com.zhangpan.dao.sys;

import java.util.List;
import java.util.Map;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.SysMenu;

public interface SysMenuDao extends BaseDao<SysMenu,Integer> {
    
    /**
     * 查询树节点
     * @return
     */
    public List<Map<String, Object>> menuList();
    
    /**
     * 是否有子节点
     * @return
     */
    public int hasChild(int id);
}