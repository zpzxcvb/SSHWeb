package com.zhangpan.model.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.SysUserService;
import com.zhangpan.util.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages="com.zhangpan.dao")
public class UserTest {

	@Autowired
	private SysUserService userService;
	
	@Test
	public void testAdd() {
		SysUser user=new SysUser();
		user.setUserName("admin");
		user.setPassword("1");
		user.setCreateTime(DateUtil.currentDate());
		int i = userService.save(user);
		System.out.println("----------"+i);
	}
	
	@Test
	public void testDelete() {
//		int i = userService.deleteById(4);
		Integer[] ids = {3,4,5,6};
		int i=userService.deleteByIds(ids);
		System.out.println(i);
	}

	@Test
	public void testfindById() {
		SysUser user = userService.findById(1);
		System.err.println(JSON.toJSONString(user));
	}
	
	@Test
    public void testfindAll() {
	    Map<String, String> map=new HashMap<String, String>();
	    map.put("userName", "admin");
	    map.put("password", "1");
        List<?> list = userService.findAll(map);
        System.err.println(JSON.toJSONString(list));
    }
	
	@Test
    public void testUserAuth() {
        Map<String, String> map=new HashMap<String, String>();
        map.put("userName", "admin");
        map.put("password", "1");
        SysUser user = userService.userAuth(map);
        System.err.println(JSON.toJSONString(user));
    }
	
	@After
	public void findAll() {
		List<SysUser> list = userService.findAll(null);
		System.err.println("不带分页信息："+JSON.toJSONString(list));
		
//		PageHelper.startPage(1, 2);
//		List<Map<String, String>> pageList = userService.findPage(null);
//		System.err.println("包含分页信息："+JSON.toJSONString(pageList));
//		PageInfo page = new PageInfo(list);
//		System.err.println("包含分页信息："+JSON.toJSONString(page));
	}
}
