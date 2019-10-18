package com.etc.flowershop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JudeServ")
public class JudeServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String phone=request.getParameter("phone");
		
		System.out.println(phone);
		PrintWriter writer = response.getWriter();
		boolean flag=false;
		if(flag){
			writer.print("此电话已注册");
		}else{
			writer.print("可以使用此电话注册");
		}
		
	}

}
