<%@ page language="java" pageEncoding="utf-8"%>
<!--绝对路径的定义  -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="../themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../themes/icon.css">
<link rel="stylesheet" type="text/css" href="../demo.css">
<script type="text/javascript" src="../jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../jquery.easyui.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
     <h3>多条件查询</h3>
     <table  align="center">
     <tr><td>
	 <form action="<%=basePath%>MoreServ" method="post">
	 鲜花名：<input type="text" name="flowername"/>
	 起始日期：<input type="text" name="start"class="easyui-datebox"/>
	 结束日期：<input type="text" name="end" class="easyui-datebox"/>
	<input type="submit" value="按条件查询"/>
	</form>
	</td></tr> 
	 </table>
	 <c:forEach items="${list}" var="f">
	      用户id:<input type="text" value="${f.u_id}" readonly="readonly">
	      购物日期:<input type="text" value="${f.o_date}" readonly="readonly">
	      消费金额:<input type="text" value="${f.price}" readonly="readonly">
	      <br><br>
	 </c:forEach>
	 <c:if test="${page>1}">
	 <a  href="MoreServ?page=${up}" >上一页</a>
	 </c:if>
	 <c:if test="${page<all}">
	 <a  href="MoreServ?page=${down}" >下一页</a>
	 </c:if>
	 <br>
	 <br>
	 <br>
	<!--如果当前的路径不在根目录下，而是在某个文件夹下，如果不使用绝对路径，系统会在当前路径下寻找  -->
	<form action="<%=basePath%>UploadServ" enctype="multipart/form-data"
		method="post" >
	<table align="center" border="5" width="50%" height="50px" cellpadding="0"
			cellspacing="0" />
	
		<h3>上传信息表</h3>
		<tr>
			<th>鲜花的类别：<select name="ftype" >
					<option value="1">玫瑰花</option>
					<option value="2">康乃馨</option>
					<option value="3">百合</option>
					<option value="4">满天星</option>
					<option value="5">茉莉花</option>
					<option value="6">熏衣草</option>
					<option value="7">海棠</option>
					<option value="8">月季</option>
			</select>
			</th>
		</tr>
		<tr>
			<td>鲜花名:<input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>鲜花的单价:<input type="text" name="price" /></td>
		</tr>
		<tr>
			<td>鲜花图片：<input type="file" name="pic" /></td>

		</tr>
		<tr>
			<td>鲜花数量：<input type="text" name="count" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="上传" align="left"></td>
		</tr>
	</table>
	</form>

</body>
</html>