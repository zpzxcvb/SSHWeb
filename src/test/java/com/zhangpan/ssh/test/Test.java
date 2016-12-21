package com.zhangpan.ssh.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhangpan.ssh.model.Student;

public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println("1".equals(1+""));
		
		
	}

	private static List<Object[]> parseList2(List<WorkItemModel> list,String taxProcessStateCode){
		List<Object[]> sort_newList = new ArrayList<Object[]>();
		Map<String, WorkItemModel> map=new HashMap<String, WorkItemModel>();
		Map<String, Integer> mapid=new HashMap<String, Integer>();
		Map<String, Boolean> maptax=new HashMap<String, Boolean>();
		for(WorkItemModel workItem : list){
			String stepCode=workItem.getStateId();
			Integer workitemId=workItem.getWorkitemId();
			if(map.get(stepCode)==null){
				map.put(stepCode, workItem);
				mapid.put(stepCode, workitemId);
			}else{
				Integer old_id=map.get(stepCode).getWorkitemId();
				if(workitemId>old_id){
					map.put(stepCode, workItem);
					mapid.put(stepCode, workitemId);
				}
			}
		}
		System.err.println(map);
		System.out.println(JSONArray.toJSONString(mapid));
		Integer taxpid=mapid.get(taxProcessStateCode);//认证环节id
		for(Map.Entry<String, Integer> entry : mapid.entrySet()){
			Integer id=entry.getValue();
			if(taxpid==null||id<taxpid){
				maptax.put(entry.getKey(), false);
			}else{
				maptax.put(entry.getKey(), true);
			}
		}
		System.err.println(maptax);
		
		for(Map.Entry<String, WorkItemModel> entry : map.entrySet()){
			String stepCode=entry.getKey();
			WorkItemModel wim=entry.getValue();
			Object[] o=new Object[]{wim,maptax.get(stepCode)};
			sort_newList.add(o);
		}
		System.out.println(JSONArray.toJSONString(sort_newList));
		return sort_newList;
	}
	
	private static List<WorkItemModel> parseList(List<WorkItemModel> list,String taxProcessStateCode){
		List<WorkItemModel> sort_newList = new ArrayList<WorkItemModel>();
		Map<String, WorkItemModel> map=new HashMap<String, WorkItemModel>();
		for(WorkItemModel workItem : list){
			String stepCode=workItem.getStateId();
			Integer workitemId=workItem.getWorkitemId();
			if(map.get(stepCode)==null){
				map.put(stepCode, workItem);
			}else{
				Integer old_id=map.get(stepCode).getWorkitemId();
				if(workitemId>old_id){
					map.put(stepCode, workItem);
				}
			}
		}
		System.out.println(JSONArray.toJSONString(map));
		for(Map.Entry<String, WorkItemModel> entry : map.entrySet()){
			WorkItemModel wim=entry.getValue();
			sort_newList.add(wim);
		}
		return sort_newList;
	} 
	
	public static boolean beforeAuthStep(String taxProcessStateCode,List<String> list){
		boolean beforeAuthStep=false;
		int benchIndex=list.indexOf(taxProcessStateCode);
//		System.out.println(benchIndex);
		List<String> front = new ArrayList<String>(); //认证环节前
		List<String> end = new ArrayList<String>(); //认证环节后
		if(benchIndex!=-1){
			for (int i = 0; i < list.size(); i++) {
				String stepCode=list.get(i);
				if(i<benchIndex){
					front.add(stepCode);
					beforeAuthStep=true;
				}else{
					end.add(stepCode);
				}
			}
		}
		System.out.println(beforeAuthStep);
		return beforeAuthStep;
	}
	
	public static void o2x() throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(Student.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING,"gb2312");//编码格式
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//是否格式化生成的xml串 
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);//是否省略xml头信息（<?xml version="1.0" encoding="gb2312" standalone="yes"?>）
		Student stu=new Student();
		stu.setSid(1);stu.setSname("tom");stu.setAge(18);
		marshaller.marshal(stu, System.out);
	}
}

class WorkItemModel{
	protected Integer workitemId;
	protected String stateId;
	
	
	public WorkItemModel(Integer workitemId, String stateId) {
		super();
		this.workitemId = workitemId;
		this.stateId = stateId;
	}
	public Integer getWorkitemId() {
		return workitemId;
	}
	public void setWorkitemId(Integer workitemId) {
		this.workitemId = workitemId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
}