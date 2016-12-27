package com.zhangpan.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangpan.dao.StudentDao;
import com.zhangpan.model.Student;
import com.zhangpan.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Resource
	private StudentDao studentDao;
	
	@Override
	public int saveStudent(Student student) {
		int i=studentDao.saveStudent(student);
		return i;
	}

	@Override
	public int deleteStudent(int id) {
		int i=studentDao.deleteStudent(id);
		return i;
	}

	@Override
	public int updateStudent(Student student) {
		int i=studentDao.updateStudent(student);
		return i;
	}
	
	@Override
	public Student findStudentById(int id) {
		Student stu=studentDao.findStudentById(id);
		return stu;
	}

	@Override
	public List<Student> findAllStudent() {
		return studentDao.findAllStudent();
	}

}
