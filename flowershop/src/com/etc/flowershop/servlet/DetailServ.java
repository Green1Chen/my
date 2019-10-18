package com.etc.flowershop.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.flowershop.entity.Flower;
import com.etc.flowershop.service.FlowerService;
import com.etc.javademo.util.Log;


@WebServlet("/DetailServ")
public class DetailServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fid=request.getParameter("flowerid");
		int id=0;
		if(fid!=null&&!fid.equals("")){
			id=Integer.parseInt(fid);
		}
		FlowerService fs=new FlowerService();
		try { 
			Flower f=fs.getFlower(id);
			request.setAttribute("flower", f);
			request.getRequestDispatcher("productdetail.jsp").forward(request, response);
		} catch (SQLException e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}
	}

}
