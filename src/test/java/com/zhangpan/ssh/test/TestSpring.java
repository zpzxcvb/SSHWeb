package com.zhangpan.ssh.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zhangpan.service.SysUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestSpring {
	
	@Resource
	private SysUserService sysUserService;
	
	@Test
	public void test(){
		List list=sysUserService.findAll();
		System.out.println(JSON.toJSONString(list));
	}
	
}
