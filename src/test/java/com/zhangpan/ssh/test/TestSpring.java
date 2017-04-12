package com.zhangpan.ssh.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zhangpan.model.SysDictType;
import com.zhangpan.service.SysDictService;
import com.zhangpan.service.SysUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestSpring {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysDictService sysDictService;
	
	@Test
	public void test(){
//		List list=sysUserService.findAll();
		/*SysDictType dicType=new SysDictType(); 1
		dicType.setId(4);
		dicType.setDictName("省市");
		dicType.setDictValue("area");
		sysDictService.save(dicType);*/
		List list1=sysDictService.findAll();
		System.out.println(JSON.toJSONString(list1));
	}
	
}
