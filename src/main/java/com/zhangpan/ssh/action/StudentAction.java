package com.zhangpan.ssh.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.zhangpan.ssh.model.Grade;
import com.zhangpan.ssh.service.GradeService;
import com.zhangpan.ssh.util.ExcelUtil;
import com.zhangpan.ssh.vo.UserVo;

public class StudentAction implements ModelDriven{
	
	private UserVo user=new UserVo();
	
	private GradeService gradeService;
	
	public String execute(){
		System.out.println(user.getUsername());
		HttpServletRequest req=ServletActionContext.getRequest();
		Grade grade=gradeService.getGradeById(1);
		System.out.println(grade);
		boolean bool=true;//userService.login(userVo.getUsername(),userVo.getPassword());
		if(bool){
			System.out.println("登录成功");
		}
		return "success";
	}

	@Override
	public Object getModel() {
		return user;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

}
