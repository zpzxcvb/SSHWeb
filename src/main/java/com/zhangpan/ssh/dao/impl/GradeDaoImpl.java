package com.zhangpan.ssh.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.zhangpan.ssh.dao.GradeDao;
import com.zhangpan.ssh.model.Grade;
import com.zhangpan.ssh.model.Student;

public class GradeDaoImpl extends BaseDaoImpl<Grade, Integer> implements GradeDao {

	@Override
	public List<Student> getStudents() {
		String sql="select * from Student";
		Query query=this.getSession().createQuery(sql);
		List<Student> stus=query.list();
		return stus;
	}

}
