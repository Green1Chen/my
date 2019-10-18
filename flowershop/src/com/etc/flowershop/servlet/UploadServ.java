package com.etc.flowershop.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.flowershop.entity.Flower;
import com.etc.flowershop.service.UserService;
import com.etc.javademo.util.Log;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;


@WebServlet("/UploadServ")
public class UploadServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建对象
		SmartUpload su=new SmartUpload();
		//此对象需要与当前的servlet进行关联，初始化操作
		su.initialize(this.getServletConfig(),request,response);
		//修改编码方式
		su.setCharset("utf-8");
		//创建上传的流
		try {
			su.upload();
		} catch (SmartUploadException e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}
		//获取页面信息
		Request re=su.getRequest();
		
		String price=re.getParameter("price");
		Double p=0.0;
		if(price!=null){
			p=Double.parseDouble(price);
		}
		String ftype=re.getParameter("ftype");
		int tid=0;
		if(ftype!=null){
			tid=Integer.parseInt(ftype);
		}
		
		String name=re.getParameter("name");
		//上传图片信息
		Files file=su.getFiles();
		File f=file.getFile(0);
		try {
			f.saveAs("E:/img/"+f.getFileName());
		} catch (SmartUploadException e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}
		
		String count=re.getParameter("count");
		int c=0;
		if(count!=null){
			c=Integer.parseInt(count);
		}
		//创建鲜花对象，将以上获取到的值存储到对象
		Flower flower=new Flower();
		flower.setCount(c);
		flower.setPrice(p);
		flower.setPic(f.getFileName());
		flower.setF_name(name);
		flower.setT_id(tid);
		UserService service=new UserService();
		try {
			service.uploadF(flower);
			request.getRequestDispatcher("back/back.jsp").forward(request, response);
		} catch (SQLException e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}
	}


}
