package com.zhangpan.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.thoughtworks.xstream.XStream;

/**
 * @author zhangpan
 * @date 2018年7月5日
 */
public class XMLUtils {
    
    /**
     * 根据xpath路径获取xml字符串根路径下单个元素的text值
     * @param xmlData 标准xml字符串，必须只有一个root节点
     * @param xpath xml元素节点路径 /root/class/className
     * @throws Exception 
     * return text
     */
    public static String getTextByXPath(String xmlData, String xpath) throws Exception {
        String text="";
        try {
            Document doc = DocumentHelper.parseText(xmlData);
            Node node = doc.selectSingleNode(xpath);
            text = node.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
    
    /**
     * 根据xpath路径获取xml字符串根路径下的元素集合key-value
     * @param xmlData 标准xml字符串，必须只有一个root节点
     * @param xpath xml元素节点路径 /root/students/student
     * @param attributeName 元素集合属性名
     * @throws Exception 
     * return map
     */
    public static List<Map<String, String>> getListByXPath(String xmlData, String xpath, String[] attributeNames) throws Exception {
        List<Map<String, String>> list =new ArrayList<Map<String, String>>();
        try {
            Document doc = DocumentHelper.parseText(xmlData);
            List<Node> nodes=doc.selectNodes(xpath);
            Iterator<Node> iterator = nodes.iterator();
            while(iterator.hasNext()) {
                Node node = iterator.next();
                Map<String, String> map=new HashMap<String, String>();
                for(String name : attributeNames) {
                    String text = "";
                    Node attr = node.selectSingleNode(name);
                    //如果属性名为空，忽略
                    if(attr != null) {
                        text = attr.getText();
                        map.put(name, text);
                    }
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static String createDom() {
        String xml="";
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        root.addElement("time").setText("12345");
        Element students = root.addElement("students");
        for (int i = 0; i < 3; i++) {
            Element child = students.addElement("student");
            Element name = child.addElement("name");name.setText("admin"+i);
            Element age = child.addElement("age");age.setText("1"+i);
            Element sex = child.addElement("sex");sex.setText("男"+i);
        }
        xml=doc.asXML();
        return xml;
    }
    
    /**
     * 根据参数创建xml字符串
     * @param rootName 根节点名称
     * @param tagName 子节点名称
     * @param list 子节点元素集合
     * @return
     */
    public static String createDom(String rootName, String tagName, List<Map<String, Object>> list) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement(rootName);
        
        for(Map<String, Object> elements : list) {
            Element child = root.addElement(tagName);
            for(Map.Entry<String, Object> ele : elements.entrySet()) {
                String key = ele.getKey();
                String value = ele.getValue().toString();
                child.addElement(key).setText(value);
            }
        }
        String xml=doc.asXML();
        return xml;
    }
    
    /**
     * 对象集合转xml
     * @param list 对象集合
     * @param clazz 对象类
     * @param rootName 根节点名
     * @param name 对象名
     * @return
     */
    public static String listToXML(List<?> list, Class<?> clazz, String rootName, String tagName) {
        StringBuffer sb = new StringBuffer();
        try {
            
            for(Object obj : list) {
                XStream x = new XStream();
                x.alias(rootName, List.class);
                x.alias(tagName, clazz);
                String xml = x.toXML(list);
                sb.append(xml);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            String xmlData = createDom();
            System.out.println(xmlData);
            
            String text = getTextByXPath(xmlData, "/root/time");
            System.out.println(text);
            List<Map<String, String>> list2 = getListByXPath(xmlData, "/root/students/student", new String[] {"name","age","sex","test"});
            System.out.println(list2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
