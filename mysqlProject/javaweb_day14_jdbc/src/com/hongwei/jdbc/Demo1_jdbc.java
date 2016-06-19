package com.hongwei.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo1_jdbc {

	/**准备好数据库
		create database mytest;
		use mytest;
		create table users
		(
			id int primary key,
			name varchar(40),
			password varchar(40),
			email varchar(60),
			birthday date			
		);
		insert into users(id,name,password,email,birthday) values ('1','zhang','123456','zhang@qq.com','1980-06-21');
		insert into users(id,name,password,email,birthday) values ('2','li','123456','li@qq.com','1981-07-22');
		insert into users(id,name,password,email,birthday) values ('3','zhou','123456','zhou@qq.com','1982-08-23');
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("123");
		String url = "jdbc:mysql://localhost:3306/mytest?useUnicode=true&characterEncoding=UTF-8";
		//写上useUnicode=true&characterEncoding=UTF-8更清爽明朗一些
		
		//或者也可以省略主机和端口，默认是本机和3306端口
		//String url ="jdbc:mysql:///mytest"; 
		String username = "root";
		String password = "root";
		
		//1.加载驱动
		//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Class.forName("com.mysql.jdbc.Driver"); //推荐此种！
		
		//2.链接数据库 
		//Connection conn = DriverManager.getConnection(url,username,password);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest?user=root&password=root");
		
		//3.获取向数据库发送sql语句的statement对象
		Statement st = conn.createStatement();
		
		//4.发送sql语句，接收返回的结果集
		ResultSet rs = st.executeQuery("select * from users");
		
		//5.从结果集中获取数据
		while(rs.next()){
			System.out.println("id="+rs.getObject("id"));
			System.out.println("name="+rs.getObject("name"));
			System.out.println("password="+rs.getObject("password"));
			System.out.println("email="+rs.getObject("email"));
			System.out.println("birthday="+rs.getObject("birthday"));
		}
		
		//6.释放资源（释放链接）
		rs.close();
		st.close();
		conn.close();
	}

}
