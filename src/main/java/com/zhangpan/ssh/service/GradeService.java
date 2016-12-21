package com.zhangpan.ssh.service;

import java.util.List;

import com.zhangpan.ssh.dao.GradeDao;
import com.zhangpan.ssh.model.Grade;
import com.zhangpan.ssh.model.Student;

public class GradeService extends BaseService<Grade,Integer> {
	
	private GradeDao gradeDao;
	
	public Grade getGradeById(int id){
		Grade grade=gradeDao.get(id);
		return grade;
	}

	public List<Student> getStudents(){
		List<Student> stus=gradeDao.getStudents();
		return stus;
	}
	
	public void setGradeDao(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}
	
	
}
