package com.zhangpan.service.sys.menu;

import java.util.List;
import java.util.Map;

import com.zhangpan.model.SysMenu;
import com.zhangpan.service.BaseService;

/**
 * @author zhangpan
 * @date 2018年6月14日
 */
public interface SysMenuService extends BaseService<SysMenu,Integer>{
    
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
