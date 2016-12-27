package com.zhangpan.dao;

import java.util.List;

import com.zhangpan.model.Student;

public interface StudentDao {
	
	public int saveStudent(Student student);
	
	public int deleteStudent(int id);
	
	public int updateStudent(Student student);

    public Student findStudentById(int id);
    
    public List<Student> findAllStudent();
    
    
}