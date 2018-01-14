package com.zhangpan.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataBaseConfiguration implements EnvironmentAware {

	 private static final Logger logger = LoggerFactory.getLogger(DataBaseConfiguration.class);
	
	private RelaxedPropertyResolver propertyResolver;
	
	@Override
	public void setEnvironment(Environment env) {
		logger.info(env.getProperty("JAVA_HOME"));
		this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
	}
	
	@Bean(name = "dataSource")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
        dataSource.setUrl(propertyResolver.getProperty("url"));
        dataSource.setUsername(propertyResolver.getProperty("username"));
        dataSource.setPassword(propertyResolver.getProperty("password"));
        
        return dataSource;
    }

}
