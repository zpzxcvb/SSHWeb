package com.zhangpan.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
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
    
    public static String createWord1(Map dataMap, String templateName, String filePath, String fileName) {
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
        dataMap.put("name", "张三");
        dataMap.put("y", "2018");
        dataMap.put("m", "08");
        dataMap.put("d", "15");
        dataMap.put("a", "电脑");
        dataMap.put("b", "华硕电脑");
        dataMap.put("c", "1800");
//        dataMap.put("img", ImageUtil.imgToCode("E:/temp/b.jpg"));
        createWord1(dataMap, "test.xml", "E:/temp", "demo.doc");
    }

}
