package com.zhangpan.ssh.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TestDB {
	private static Logger log=Logger.getLogger(TestDB.class);
	
	public static void excuteQuery(Connection con) throws SQLException{
		String sql="SELECT * FROM student where sid not in(select student_id from score where score<80 group by student_id)";
		PreparedStatement pst=con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString("sname")+"|"+rs.getString("age"));
		}
	}
			
	public static void main(String[] args) throws Exception {
		InputStream in=ClassLoader.getSystemResourceAsStream("db.properties");
		Properties p=new Properties();
		p.load(in);
		String driver=p.getProperty("jdbc.driverClass");
		String url=p.getProperty("jdbc.jdbcUrl");
		String user=p.getProperty("jdbc.user");
		String password=p.getProperty("jdbc.password");
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,password);
		log.info(con);
		excuteQuery(con);
	}
}
