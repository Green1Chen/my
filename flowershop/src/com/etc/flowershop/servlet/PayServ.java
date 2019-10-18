package com.etc.flowershop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.flowershop.entity.Tuser;
import com.etc.flowershop.service.UserService;
import com.etc.javademo.util.Log;


@WebServlet("/PayServ")
public class PayServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		UserService us=new UserService();
		//��ȡ��ǰ�û���idֵ
		Tuser tu=(Tuser)request.getSession().getAttribute("tu");
		//��ȡ��ѡ���ʻ�Id����
		String strs=(String)request.getSession().getAttribute("strs");
		//��ȡ����Ҫ���ܼ�
		Double sum=(Double)request.getSession().getAttribute("sum");
		//��ȡ�����ʻ�id�ͼ۸����Ե�map����
		Map<String, Integer> flowercount=(Map<String, Integer>) request.getSession().getAttribute("flowercount");
		//��ȡÿһ�ֻ����ܼ�
		Map<String, Double> flowersum=(Map<String, Double>) request.getSession().getAttribute("flowersum");
		try {
			us.buy(tu,strs,sum,flowercount,flowersum);
		} catch (SQLException e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}finally{
			request.getRequestDispatcher("CarServ").forward(request, response);
		}
		
	}

}
