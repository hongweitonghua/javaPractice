package com.hongwei.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JdbcUtils {
	
	private static Properties config = new Properties();
	//静态代码块，只执行一次，类加载时执行
	static{	
		try {
			config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties")); 
			System.out.println(config.getProperty("driver"));
			Class.forName(config.getProperty("driver"));
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{		
		return DriverManager.getConnection(config.getProperty("url"),config.getProperty("username"),config.getProperty("password"));
	}
	
	public static void release(ResultSet rs,Statement st,Connection conn){
		if(rs!=null){
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			rs = null;
		}
		if(st!=null){
			try{
				st.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			st = null;
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
