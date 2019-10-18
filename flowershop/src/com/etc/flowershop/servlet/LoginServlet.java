package com.etc.flowershop.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.flowershop.entity.Tuser;
import com.etc.flowershop.service.UserService;

@WebServlet("/LoginServ")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("Userame");
		String pwd=request.getParameter("Password");
		String result=request.getParameter("result");
		UserService service=new UserService();
		HttpSession session=request.getSession();
		try {
			Tuser tuser=service.login(name,pwd);
			
			if(tuser==null){
				//Ê§°Ü
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				//³É¹¦
				session.setAttribute("tu", tuser);
				if (result==null||result.equals("")) {
					request.getRequestDispatcher("MainServ").forward(request, response);
				}else{
					request.getRequestDispatcher("CarServ?type=login").forward(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
