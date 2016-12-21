package com.zhangpan.ssh.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangpan.ssh.model.Grade;
import com.zhangpan.ssh.model.Student;
import com.zhangpan.ssh.service.GradeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {
	
	private GradeService gradeService; 
	
	@Test
    public void testQueryStudent(){  
		List<Student> list=gradeService.getStudents();
		System.out.println(list);
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}
	
	
}
