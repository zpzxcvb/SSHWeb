package com.zhangpan.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class DbUtil {
    
    // 定义数据库的链接  
    private static Connection connection;
  
    // 定义sql语句的执行对象  
    private static PreparedStatement pstmt;
  
    // 定义查询返回的结果集合  
    private static ResultSet resultSet;
      
    static{  
        try {
            //获取数据库连接 
            Class.forName(SysConfig.getProperty("jdbc.driverClass")); // 注册驱动  
            connection = DriverManager.getConnection(SysConfig.getProperty("jdbc.jdbcUrl"),
                    SysConfig.getProperty("jdbc.user"), SysConfig.getProperty("jdbc.password")); // 获取连接 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** 
     * 执行更新操作 
     *  
     * @param sql 
     *            sql语句 
     * @param params 
     *            执行参数 
     * @return 执行结果 
     * @throws SQLException 
     */  
    public static boolean update(String sql, List<?> params)  
            throws SQLException {  
        boolean flag = false;  
        int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数  
        pstmt = connection.prepareStatement(sql);  
        int index = 1;  
        // 填充sql语句中的占位符  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        result = pstmt.executeUpdate();  
        flag = result > 0 ? true : false;  
        return flag;  
    }
    
    /** 
     * 执行查询操作 
     *  
     * @param sql 
     *            sql语句 
     * @param params 
     *            执行参数 
     * @return 
     * @throws SQLException 
     */  
    public static List<Map<String, Object>> findList(String sql, List<?> params)  
            throws SQLException {  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        int index = 1;  
        pstmt = connection.prepareStatement(sql);  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        resultSet = pstmt.executeQuery();  
        ResultSetMetaData metaData = resultSet.getMetaData();  
        int cols_len = metaData.getColumnCount();  
        while (resultSet.next()) {  
            Map<String, Object> map = new HashMap<String, Object>();  
            for (int i = 0; i < cols_len; i++) {  
                String cols_name = metaData.getColumnName(i + 1);  
                Object cols_value = resultSet.getObject(cols_name);  
                if (cols_value == null) {  
                    cols_value = "";  
                }  
                map.put(cols_name, cols_value);  
            }  
            list.add(map);  
        }  
        return list;
    }
    
    /** 
     * 释放资源 
     */  
    public static void releaseConn() {  
        if (resultSet != null) {  
            try {  
                resultSet.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (pstmt != null) {  
            try {  
                pstmt.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (connection != null) {  
            try {  
                connection.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("connection : "+connection);
            
            /*String updatesql="insert into student(name,age,birthday,test) values(?,?,?,?)";
//            updatesql="delete from student where sid=?";
            List<Object> params = new ArrayList<Object>();
            params.add("王五");
            params.add(17);params.add(new Date());params.add(new Date());
//            params.add(3);
            update(updatesql, params);*/
            String username="";
            String sql="select * from stu ";
            List<Map<String, Object>> result = findList(sql, null);
            for (Map<String, Object> m : result) {  
                System.out.println(m);  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            releaseConn();
        }
    }
    
}
