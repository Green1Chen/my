package com.etc.flowershop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.etc.javademo.util.Log;
 
 

public class BaseDao {
	Connection con ;
	PreparedStatement ps ;
	ResultSet rs ;
	Statement st ;
	 
	//打开事务
	public void openTran() throws SQLException {
		//事务打开即为取消自动提交功能，由con来进行自动提交的取消，所以打开事务的第一件事情，先要打开数据库
		getConn() ;
		//取消自动提交功能
		con.setAutoCommit(false);
	}
	
	//提交事务
	public void commitTran() throws SQLException {
		if(con != null) {
			con.commit();
		}
	}
	
	//回滚事务
	public void rollbackTran() throws SQLException {
		if(con != null)
			con.rollback();
	}
	
    //连接数据库
	public void getConn() throws SQLException {
		// 连接数据库分两步
				// 添加驱动
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					Log.logger.debug(e.getMessage());
					e.printStackTrace();
				}
				// 连接数据库
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flower", "root", "321");

	}
	//关闭数据库
	public void closeResource() throws SQLException {
		// 关闭资源
		if (con != null) {
			con.close();
		}
		if( st != null) {
			st.close();
		}
		if (ps != null) {
			ps.close();
		}
		if(rs != null) {
			rs.close();
		}
	}
}
