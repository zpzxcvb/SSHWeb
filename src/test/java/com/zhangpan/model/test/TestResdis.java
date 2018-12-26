package com.zhangpan.model.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangpan.model.SysRole;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.sys.user.SysUserService;
import com.zhangpan.util.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestResdis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        System.out.println(redisTemplate.opsForValue().get("aaa"));
    }
}
