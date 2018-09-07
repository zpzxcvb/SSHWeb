/**
 * 
 */
package com.zhangpan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangpan
 * @date 2018年7月24日
 */
public class SysConfig {
    
    // 用于存于配置参数
    private static Map<String, String> map;
    
    static{  
        //加载数据库配置信息，并给相关的属性赋值  
        Properties prop = new Properties();
        InputStream in = null;
        try {
            map = new ConcurrentHashMap<String, String>();
            in = Object.class.getResourceAsStream("/config/db.properties");
            prop.load(in);
            for (Object key : prop.keySet()) {
                map.put(key.toString(), prop.getProperty(key.toString()).trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }              
            }
        }
    }
    
    public static String getProperty(String key) {
        return map.get(key);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(map);
        System.out.println(getProperty("jdbc.user"));
    }

}
