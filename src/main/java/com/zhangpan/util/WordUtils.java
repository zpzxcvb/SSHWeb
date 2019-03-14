package com.zhangpan.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author zhangpan
 * @date 2018年8月15日
 */
public class WordUtils {
    
    /**
     * freemaker 方式
     * @param dataMap
     * @param templateName
     * @param filePath
     * @param fileName
     * @return
     */
    public static String createWordForFreemaker(Map dataMap, String templateName, String filePath, String fileName) {
        String fileOnlyName = null;
        try {
            // 创建配置实例
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);

            // 设置编码
            configuration.setDefaultEncoding("UTF-8");

            // ftl模板文件统一放至 template 包下面
            configuration.setClassForTemplateLoading(WordUtils.class, "./");
//            configuration.setDirectoryForTemplateLoading(new File(filePath));

            // 获取模板
            Template template = configuration.getTemplate(templateName, "UTF-8");
            
            // 输出文件
            File outFile = new File(filePath+File.separator+fileName);

            // 将模板和数据模型合并生成文件
            Writer out = new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8");
            // 生成文件
            template.process(dataMap, out);
            // 关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileOnlyName;
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 创建数据
        Map<String,Object> dataMap = new HashMap<String, Object>();
        dataMap.put("title", "demo新厂年度");
        dataMap.put("content", "sdfsfdsfsdfdfsdfdsfdsfdsfdsfs");
        dataMap.put("address", "西安雁塔区");
        dataMap.put("date", "2018nian");
        dataMap.put("userName", "张三");
        dataMap.put("phone", "17691125766");
        List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
        for (int i = 1; i <= 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sname", "张三" + i);
            map.put("sex", "男" + i);
            map.put("sage", (i * 2));
            newsList.add(map);
        }
        dataMap.put("list", newsList);
//        dataMap.put("img", ImageUtil.imgToCode("E:/temp/b.jpg"));
        createWordForFreemaker(dataMap, "test.xml", "C:\\Users\\SV924LB\\Works", "demo.doc");
    }

}
class Student{
	private String sname;
	private String sage;
	private String sex;
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSage() {
		return sage;
	}
	public void setSage(String sage) {
		this.sage = sage;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}