package com.zhangpan.ssh.test;

public class Test {
	
	public static void main(String[] args) {
		Test t=new Test(); 
		String reg="a<html>123456</html>c";
		System.out.println(reg.matches("^a(.*)c$"));
	}
}
