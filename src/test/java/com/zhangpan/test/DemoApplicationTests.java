package com.zhangpan.test;

import java.util.Date;
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
//@ComponentScan(basePackages="com.zhangpan.dao")
@MapperScan(basePackages="com.zhangpan.dao")
public class DemoApplicationTests {

	@Autowired
	private SysUserService userService;
	
	@Test
	public void testAdd() {
		SysUser user=new SysUser();
		user.setUserName("zp");
		user.setPassword("123");
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
        List<?> list = userService.findAll();
        System.err.println(JSON.toJSONString(list));
    }
	
	@After
	public void findAll() {
		List<SysUser> list = userService.findAll();
		System.err.println("不带分页信息："+JSON.toJSONString(list));
		
		PageHelper.startPage(1, 2);
		List<Map> pageList = userService.findPage(null);
		System.err.println("包含分页信息："+JSON.toJSONString(pageList));
		PageInfo page = new PageInfo(list);
		System.err.println("包含分页信息："+JSON.toJSONString(page));
	}
}
