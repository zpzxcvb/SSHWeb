package com.zhangpan.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhangpan.model.SysUser;
import com.zhangpan.service.SysUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ComponentScan(basePackages="com.zhangpan.dao")
@MapperScan(basePackages="com.zhangpan.dao")
public class DemoApplicationTests {

	@Autowired
	private SysUserService users;
	
//	@Test
	public void testAdd() {
		SysUser user=new SysUser();
		user.setUserName("张三");
		user.setPassword("123");
		users.save(user);
	}

	@Test
	public void testSelect() {
		List<SysUser> userlist = users.findAll();
		System.err.println(userlist.get(0).getUserName());
	}
}
