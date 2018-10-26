package com.zhangpan.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhangpan.filter.SessionFilter;
import com.zhangpan.intercepter.GlobalExceptionHandler;

/**
 * @author zhangpan
 * @date 2018年10月11日
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    
    /*@Bean
    public FilterRegistrationBean filterRegistrationBean() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注册过滤器
        registration.setFilter(new SessionFilter());
        //设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        return registration;
    }*/
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalExceptionHandler()).addPathPatterns("/mm");
        super.addInterceptors(registry);
    }
}
