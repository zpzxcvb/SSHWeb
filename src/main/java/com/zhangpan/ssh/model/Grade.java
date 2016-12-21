package com.zhangpan.ssh.model;

import java.io.Serializable;
import java.util.Set;

public class Grade implements Serializable{
	
	private int id;
	private String name;
	
	private Set students;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getStudents() {
		return students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", name=" + name + ", students=" + students
				+ "]";
	}

}
