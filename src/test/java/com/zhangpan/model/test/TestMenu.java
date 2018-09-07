package com.zhangpan.model.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.zhangpan.model.SysMenu;
import com.zhangpan.service.sys.menu.SysMenuService;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages="com.zhangpan.dao.*")
public class TestMenu {

    private Map map=new HashMap();
    
    @Autowired
    private SysMenuService sysMenuService;
	
	@Test
    public void testMenuList() {
	    map.put("pid", 1);
	    List<SysMenu> list=sysMenuService.findList(map);
        System.err.println(JSON.toJSONString(list));
    }
	
	@Test
    public void testDeleteMenu() {
        Integer[] ids= {8,};
        int count=sysMenuService.deleteByIds(ids);
        System.err.println(count);
    }
}
