package com.zhangpan.model;

public class Student {
    private Integer sid;

    private String sname;

    private Integer sex1;

    private Integer age;

    public Student() {}

	public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public Integer getSex1() {
        return sex1;
    }

    public void setSex1(Integer sex) {
        this.sex1 = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}