package com.zhangpan.ssh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 张攀
 * @ClassName : AuthFilter
 * @ModifiedBy : 张攀
 * @date : 2016-10-20 下午4:52:52
 */
public class AuthFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentURL = request.getRequestURI();
		System.out.println("request.getContextPath()=" + request.getContextPath());  
		System.out.println("request.getServletPath()=" + request.getServletPath()); 
		System.out.println("request.getRequestURL()=" + request.getRequestURL()); 
        // 截取到当前文件名用于比较  
        HttpSession session = request.getSession(false); 
        if (!(currentURL.equals(request.getContextPath()+"/")||currentURL.endsWith("login.jsp"))) {// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环  
            if (session == null || session.getAttribute("user") == null) {  
                // *用户登录以后需手动添加session  
                response.sendRedirect(request.getContextPath()+"/login.jsp");  
                // 如果session为空表示用户没有登录就重定向到login.jsp页面  
                return;  
            }  
        }  
        // 加入filter链继续向下执行  
        filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
