package com.etc.flowershop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.flowershop.entity.Forder;
import com.etc.flowershop.service.UserService;
import com.etc.javademo.util.Log;

@WebServlet("/MoreServ")
public class MoreServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//分页显示多条件查询结果
		String page=request.getParameter("page");
		page=(page==null ? "1" :page);
		int p=Integer.parseInt(page);
		request.setAttribute("up", p-1);
		request.setAttribute("down", p+1);
		//多条件查询
		String name=request.getParameter("flowername");
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		Date st=null,en=null;
		try {
			if(start!=null&&!start.equals("")) st=sdf.parse(start);
			if(end!=null&&!end.equals("")) en=sdf.parse(end);
		} catch (ParseException e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}
		UserService service=new UserService();
		//获取一共有多少页
		int all=0;
		try {
			all = service.getpage(3);
		} catch (SQLException e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("all", all);
		List<Forder> list=new ArrayList<Forder>();
		try {
			list=service.quaryOrder(name,st,en);
			request.setAttribute("list", list);
			request.getRequestDispatcher("back/back.jsp").forward(request, response);
		} catch (SQLException e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}
		
		
	}

}
