package com.etc.flowershop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.flowershop.entity.Tuser;
import com.etc.flowershop.entity.UAddress;
import com.etc.flowershop.service.UserService;
import com.etc.javademo.util.Log;

@WebServlet("/CheckoutServ")
public class CheckoutServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取想要支付的鲜花的id
		String strs=request.getParameter("strs");
		request.getSession().setAttribute("strs", strs);
		//System.out.println(strs.equals(""));
		//动态获取每个鲜花的总价
		String[] str=strs.split(",");
		/*System.out.println(str.length);
		for (String string : str) {
			System.out.println("string="+string);
			System.out.println(string.equals(""));
			System.out.println(string.equals(" "));
		}*/
		Double sum=0.0;
		Map<String,Integer> countmap=new HashMap<String,Integer>();
		Map<String,Double> pricemap=new HashMap<String,Double>();
		if(strs!=null&&!strs.equals("")){
			for (String id: str) {
			//获取每一种花的总价	
			Double sumone =Double.parseDouble(request.getParameter(id));
			//获取所有花的总价
			sum +=sumone;
			//获取花的数量
			String strcount=request.getParameter("count"+id);
			System.out.println(strcount);
			int count=0;
			if(strcount!=null){
				count=Integer.parseInt(strcount);	
			}
			countmap.put(id, count);
			pricemap.put(id, sumone);
			}
		}
		request.getSession().setAttribute("flowercount", countmap);
		request.getSession().setAttribute("flowersum", pricemap);
		request.getSession().setAttribute("sum", sum);
		//将当前用户的默认地址取到结算页面 
		UAddress address=new UAddress();
		UserService service=new UserService();
		Tuser tu=(Tuser)request.getSession().getAttribute("tu");
		String uaddress=null;
		try {
			uaddress=service.getAddress(tu);
		} catch (SQLException e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("address",uaddress);
		request.getRequestDispatcher("checkout.jsp").forward(request, response);
		
	}


}
