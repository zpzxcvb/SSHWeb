package com.zhangpan.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangpan.controller.BaseController;

@RestController("demo")
public class TestController extends BaseController {
    
    @Autowired
    private StringRedisTemplate template;
	
	@RequestMapping("/setValue")
    public Object setValue() {
	    String str="";
	    if(!template.hasKey("shabao")){
	        template.opsForValue().append("shabao", "我是傻宝");
	        str="使用redis缓存保存数据成功";
	    }else {
	        template.delete("shabao");
	        str="key已存在";
	    }
        return str;
    }
	
	@RequestMapping("/getValue")
    public String getValue(){
        if(!template.hasKey("shabao")){
            return "key不存在，请先保存数据";
        }else{
            String shabao = template.opsForValue().get("shabao");
            return "获取到缓存中的数据：shabao="+shabao;
        }
    }
	
}
