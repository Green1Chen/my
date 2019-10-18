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
	 
	//������
	public void openTran() throws SQLException {
		//����򿪼�Ϊȡ���Զ��ύ���ܣ���con�������Զ��ύ��ȡ�������Դ�����ĵ�һ�����飬��Ҫ�����ݿ�
		getConn() ;
		//ȡ���Զ��ύ����
		con.setAutoCommit(false);
	}
	
	//�ύ����
	public void commitTran() throws SQLException {
		if(con != null) {
			con.commit();
		}
	}
	
	//�ع�����
	public void rollbackTran() throws SQLException {
		if(con != null)
			con.rollback();
	}
	
    //�������ݿ�
	public void getConn() throws SQLException {
		// �������ݿ������
				// �������
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					Log.logger.debug(e.getMessage());
					e.printStackTrace();
				}
				// �������ݿ�
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flower", "root", "321");

	}
	//�ر����ݿ�
	public void closeResource() throws SQLException {
		// �ر���Դ
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
