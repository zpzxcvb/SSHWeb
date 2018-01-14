package com.zhangpan.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TestDB {
	private static Logger log=Logger.getLogger(TestDB.class);
	
	public static void excuteQuery(Connection con) throws SQLException{
		String sql="SELECT * FROM user_ ";
		PreparedStatement pst=con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData data = rs.getMetaData();
		int count=data.getColumnCount();
		while(rs.next()){
			for (int i = 1; i <= count; i++) {
				System.out.print(data.getColumnName(i)+" ");
				System.out.print(rs.getString(i)+" ");
			}
			System.out.println();
		}
	}
			
	public static void main(String[] args) throws Exception {
		InputStream in=ClassLoader.getSystemResourceAsStream("config/db.properties");
		Properties p=new Properties();
		p.load(in);
		String driver=p.getProperty("jdbc.driverClass");
		String url=p.getProperty("jdbc.jdbcUrl");
		String user=p.getProperty("jdbc.user");
		String password=p.getProperty("jdbc.password");
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,password);
		log.info(con);
//		excuteQuery(con);
	}
}
