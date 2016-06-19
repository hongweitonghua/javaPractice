package com.hongwei.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hongwei.bean.User;

public class Demo2_jdbc_try {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn=null;
		Statement st = null;
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");		
			String url = "jdbc:mysql://localhost:3306/mytest?useUnicode=true&characterEncoding=UTF-8&user=root&password=root";
			conn = DriverManager.getConnection(url);			
			st = conn.createStatement();
			rs = st.executeQuery("select * from users");			
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setDate(rs.getDate("birthday"));
				System.out.println(user);
			}
		}finally{
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
			
			/*if(rs!=null){
				try{
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(st!=null){
						try{
							st.close();
						}catch(Exception e){
							e.printStackTrace();
						}finally{
							if(conn!=null){
								try{
									conn.close();
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}
				}
			}*/			
		}		
	}
}
