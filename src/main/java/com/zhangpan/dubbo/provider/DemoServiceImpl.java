package com.zhangpan.dubbo.provider;

import com.zhangpan.dubbo.DemoService;

public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		return "Hello" + name;
	}

}
