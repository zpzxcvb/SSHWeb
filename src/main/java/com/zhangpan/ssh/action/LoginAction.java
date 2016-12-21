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

public class LoginAction implements ModelDriven{
	
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
	
	public String export(){
		Map<String,String> headMap=new HashMap<String,String>();
		headMap.put("name", "姓名");
		headMap.put("age", "年龄");
		headMap.put("sex", "性别");
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for (int i = 1; i <= 700; i++) {
			Map<String,String> map=new HashMap<String,String>();
			map.put("name", "张三"+i);
			map.put("age", "0000"+i);
			map.put("sex", "M");
			list.add(map);
		}
		ExcelUtil.downloadExcelFile("testExport", headMap, list, 655, ServletActionContext.getResponse());
		return null;
	}

	@Override
	public Object getModel() {
		return user;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

}
