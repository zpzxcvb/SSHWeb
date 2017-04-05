package com.zhangpan.ssh.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zhangpan.dubbo.DemoService;
import com.zhangpan.service.SysUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:provider.xml"})
public class TestDubbo {
	
	private DemoService demoService;
	
	@Test
	public void test1(){
		System.out.println(demoService);
	}

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}
	
}
