package com.zhangpan.ssh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name="test",propOrder={"sid","sname","sex","age"})
@XmlRootElement
public class Student implements Serializable{
	
	private int sid;
	private String sname;
	private String sex;
	private int age;
	private boolean is;
	
	private Grade grade;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public boolean isIs() {
		return is;
	}
	public void setIs(boolean is) {
		this.is = is;
	}
	
}
