package com.zhangpan.ssh.test;

import java.math.BigDecimal;
import java.util.*;

import org.mybatis.generator.api.MyBatisGenerator;

import com.alibaba.fastjson.JSON;

public class Test {
	public static void main(String[] args) {
		Test t=new Test();
		BigDecimal t1=new BigDecimal(1.00);
		BigDecimal t2=new BigDecimal(1.0);
		System.out.println(t1.equals(t2));
		System.out.println(t1.compareTo(t2));
	}

}
