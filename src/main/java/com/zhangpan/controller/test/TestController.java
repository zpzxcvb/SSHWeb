package com.zhangpan.controller.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangpan.controller.BaseController;

@RestController("demo")
public class TestController extends BaseController {
	
	@RequestMapping("/test")
    public int test(int a, int b) {
        return a/b;
    }
	
}
