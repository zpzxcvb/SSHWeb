package com.zhangpan.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhangpan.dao.sys.SysLogDao;
import com.zhangpan.model.SysLog;
import com.zhangpan.util.DateUtil;

//@Aspect
//@Component
public class WebLogAop {
    
    private static final Logger log = LoggerFactory.getLogger(WebLogAop.class);
    
    @Autowired
    private SysLogDao sysLogDao;
	
	@Pointcut("execution(* com.zhangpan.service..*.save*(..))")
	public void insertLog() {}
	
	@Pointcut("execution(* com.zhangpan.service..*.update*(..))")
    public void updateLog() {}
	
	@Pointcut("execution(* com.zhangpan.service..*.delete*(..))")
    public void deleteLog() {}
	
	@Before("executeService()")
	public void doBefore(JoinPoint joinPoint) {
	    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //URL
        log.info("url={}", request.getRequestURI());
        //method
        log.info("method={}", request.getMethod());
        //ip
        log.info("ip={}",request.getRemoteAddr());
        //类方法
        log.info("class={} and method name = {}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        //参数
        log.info("参数={}",joinPoint.getArgs());
	}
	
	@After("executeService()")
	public void doAfter() {
	    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("url = {} end of execution", request.getRequestURL());
	}
	
	@AfterReturning(value = "executeService()", returning = "returnVal")
    public void doAfterReturn(Object returnVal) {
        log.info("AfterReturn:"+returnVal);
    }
	
	/**
     * 环绕通知
     * @param joinPoint 可用于执行切点的类
     * @return
     * @throws Throwable
     */
    @Around("executeService()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知前....");
        Object obj= (Object) joinPoint.proceed();
        System.out.println("环绕通知后...."+obj);
        return obj;
    }
	
	@AfterThrowing(value = "executeService()", throwing = "e")
	public void doException(Exception e) {
	    log.info("error:"+e.getMessage());
	}
	
	private void log(JoinPoint joinPoint, Object object, String operation) {
        try {
            if (joinPoint.getArgs() == null) {// 没有参数
                return;
            }
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
 
            String methodRoot = joinPoint.getSignature().getDeclaringTypeName();
            String method = methodRoot + "." + joinPoint.getSignature().getName();
            String parameter = Arrays.toString(joinPoint.getArgs());
 
            SysLog log = new SysLog();
//            log.setCreateUser(createUser);
            log.setCreateTime(DateUtil.currentDateTime());
//            log.setLogType(logType);
//            log.setLogDesc(logDesc);
            sysLogDao.save(log);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
