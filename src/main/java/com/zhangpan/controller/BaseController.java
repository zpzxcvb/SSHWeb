package com.zhangpan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhangpan.constant.Constant;
import com.zhangpan.constant.ResponseResult;

@Controller
public class BaseController {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected Map<String, String> result = new HashMap<String, String>();
	// protected ResponseResult result = new ResponseResult();

	@RequestMapping("/")
	public String login(){
		return "user/login";
	}
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	protected String getResponseCode(int count) {
		String code = "";

		if (count > 0) {
			code = Constant.SUCCESS;
		} else {
			code = Constant.FAILURE;
		}
		
		return code;
	}
}
