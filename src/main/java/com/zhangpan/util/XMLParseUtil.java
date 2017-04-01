package com.zhangpan.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLParseUtil {
	
	/**
	 * 获取属性值
	 * 单个xml元素转map
	 * @param root
	 * @return
	 * @throws Exception 
	 */
	public static Map<String, List> xmlParse(String filePath,String nodeName) throws Exception{
		File file=new File(filePath);
		SAXReader reader = new SAXReader();
		Document doc=reader.read(file);
		Element root = doc.getRootElement();
		System.out.println(root.attributeValue("name"));
		
		List<Element> childNodes=root.elements(nodeName);
		Map<String,List> maplist=new HashMap<String, List>();
		for(Element childNode : childNodes){
			List list=null;
			String key = childNode.getName();
			if(maplist.containsKey(key)){
				list=maplist.get(key);
			}else{
				list=new ArrayList();
			}
			
			Map childNodeAttrMap=new HashMap();
			List<Attribute> childNodeAttrs=childNode.attributes();
			for(Attribute childNodeAttr : childNodeAttrs){
				childNodeAttrMap.put(childNodeAttr.getName(), childNodeAttr.getValue());
			}
			
			list.add(childNodeAttrMap);
			maplist.put(key,list);
		}
//		System.out.println("当前节点："+nodeName+"的子节点："+maplist);
		return maplist;
	}
	
	public static void main(String[] args) throws Exception {
		String filePath="src/main/java/com/zhangpan/ssh/xml/Loc.xml";
//		xmlParse(filePath,"CountryRegion");
		
//		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
//		DocumentBuilder db=dbf.newDocumentBuilder();
//		org.w3c.dom.Document d=db.parse(file);
//		System.out.println(d.getElementsByTagName("CountryRegion").item(0).getAttributes());
		
	}
	/*public static void childNodes(Element element,String parentNodeAttr,String childNodeName){
		System.out.println(element.attributeValue(parentNodeAttr));
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		List<Element> childNodes=element.elements(childNodeName);
		for(Element e : childNodes){
			Map<String, Object> ele_attrs=xmlToMap(e);//子元素的属性值
			list.add(ele_attrs);
		}
		System.out.println(list);
	}*/
	/**
	 * 获取属性值
	 * xml元素集合转map
	 * @param elements
	 * @return
	 */
	/*public static Map<String,List> xmlToMap(List<Element> elements){
		Map<String,List> maplist=new HashMap<String, List>();
		
		for (Element element : elements) {//遍历元素
			List list=null;
			String key = element.getName();
			Map<String, Object> map=xmlToMap(element);//元素属性
			if(maplist.containsKey(key)){
				list=maplist.get(key);
			}else{
				list=new ArrayList();//所有元素属性集合
			}
			list.add(map);
			maplist.put(key,list);
		}
		System.out.println(maplist);
		return maplist;
	}*/
	/*public static void formatNodeXMLData(Map<String,List> nodeMap,String key){
		List<Map<String, String>> nodeDataArray=new ArrayList<Map<String,String>>();
		List<Map<String, String>> linkDataArray=new ArrayList<Map<String,String>>();
//	{"from":"1", "to":"3","points":[200,309,245,309]},
//		{start-state=[{ylocation=310, sid=step1, stateType=1, xlocation=170, modelType=1, sequence=1, name=流程开始}]}
//		<start-state name="流程开始" sid="step1" xlocation="170" ylocation="310" sequence="1" modelType="1" stateType="1"> 
//        <transition  name="起草人" to="step10" returnBack="false" rejectBack="false" modelType="1" points="200,309,245,309,"/> 
//      </start-state> 
		List<Map<String, String>> list=nodeMap.get(key);
		for(Map<String, String> nodeAttrs : list){
			Map<String, String> node=new HashMap<String,String>();
			node.put("key", nodeAttrs.get("sid"));
			node.put("text", nodeAttrs.get("name"));
			node.put("loc", nodeAttrs.get("xlocation")+" "+nodeAttrs.get("ylocation"));
			node.put("figure", (nodeAttrs.get("sid").matches("step(1|0)"))?"Circle":"");
			node.put("fill", ("step1".equals(nodeAttrs.get("sid")))?"green":"red");
			nodeDataArray.add(node);
		}
		Map<String, List> a=new HashMap<String,List>();
		a.put("nodeDataArray", nodeDataArray);
		System.out.println("格式化后："+a);
	}*/
}
