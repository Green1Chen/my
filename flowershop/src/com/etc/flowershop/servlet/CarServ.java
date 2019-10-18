package com.etc.flowershop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.flowershop.entity.Flower;
import com.etc.flowershop.entity.Tuser;
import com.etc.flowershop.service.UserService;
import com.etc.javademo.util.Log;

@WebServlet("/CarServ")
public class CarServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int fid=0;
    	//从删除进入
		String flowerid = request.getParameter("flowerid");
		//不同的地方进入购物车要有标示
		String type=request.getParameter("type");
		if (type==null||type.equals("")) {
			//正常进入
			String id=request.getParameter("id");
			if(id!=null&&!id.equals("")){
				fid=Integer.parseInt(id);
			}
			request.getSession().setAttribute("id", fid);
		}else{
			fid=(int) request.getSession().getAttribute("id");
		}
		
		
		UserService us=new UserService();
		Tuser tu=(Tuser)request.getSession().getAttribute("tu");
		if (tu==null) {
			request.setAttribute("result", "请先登录，再进入购物车");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
				List<Flower> f = new ArrayList<Flower>();
				if (fid!=0) {
					try {
						f = us.addCar(fid, tu);
						request.setAttribute("flower", f);
						request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
					} catch (SQLException e) {
						Log.logger.debug(e.getMessage());
						e.printStackTrace();
					} 
				}else{
					//删除购物车的信息
					int flowerid1=0;
					if (flowerid != null && !flowerid.equals("")) {
						flowerid1 = Integer.parseInt(flowerid);
						us.deleteCar(tu, flowerid1);
					}
					try {
						f = us.getCar(tu);
						request.setAttribute("flower", f);
						request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
					} catch (SQLException e) {
						Log.logger.debug(e.getMessage());
						e.printStackTrace();
					}
				}
				
				
			
		}
	}


}
