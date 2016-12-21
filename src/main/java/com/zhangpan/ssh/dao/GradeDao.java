package com.zhangpan.ssh.dao;

import java.util.List;

import com.zhangpan.ssh.model.Grade;
import com.zhangpan.ssh.model.Student;

public interface GradeDao extends BaseDao<Grade, Integer> {
	public List<Student> getStudents();
}
