package com.zhangpan.ssh.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zhangpan.model.Student;
import com.zhangpan.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestSpring {
	
	@Resource
	private StudentService studentService;
	
	@Test
	public void test1(){
		Student stu=studentService.findStudentById(1);
		System.out.println(JSON.toJSONString(stu));
	}
	
	@Test
	public void test2(){
		List list=studentService.findAllStudent();
		System.out.println(JSON.toJSONString(list));
	}
	
	@Test
	public void test3(){
		Student student=new Student();
		int i=studentService.saveStudent(student);
		System.out.println(i);
	}
	
}
