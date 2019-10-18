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
		//获取当前用户的id值
		Tuser tu=(Tuser)request.getSession().getAttribute("tu");
		//获取所选的鲜花Id集合
		String strs=(String)request.getSession().getAttribute("strs");
		//获取所需要的总价
		Double sum=(Double)request.getSession().getAttribute("sum");
		//获取所需鲜花id和价格所对的map集合
		Map<String, Integer> flowercount=(Map<String, Integer>) request.getSession().getAttribute("flowercount");
		//获取每一种花的总价
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
