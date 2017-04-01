package com.zhangpan.ssh.test;

import java.math.BigDecimal;
import java.util.*;

import org.mybatis.generator.api.MyBatisGenerator;

import com.alibaba.fastjson.JSON;

public class Test {
	
	public static void main(String[] args) {
		Test t=new Test(); 
		String reg="a<html>123456</html>c";
		System.out.println(reg.matches("^a(.*)c$"));
		
		A b=new B();
		b.aa();
	}
}
class A {
	public void aa(){
		System.out.println("aa");
	}
}
class B extends A{
	public void aa(){
		System.out.println("bb");
	}
}