package com.zhangpan.webservice.imp;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.zhangpan.webservice.HelloWorldService;

@Service
@WebService(serviceName = "HelloWorldService", endpointInterface = "com.zhangpan.webservice.HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService{

	public String sayHello(String name) {
		return "Hello "+name;
	}

}
