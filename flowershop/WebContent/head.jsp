<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li><a href="MainServ" class="selected">主页</a></li>
		<c:if test="${tu==null }">
			<li><a href="login.jsp">登录注册</a></li>
		</c:if>
		<li><a href="products.html">产品</a></li>
		<li><a href="checkout.html">结账</a></li>
		<c:if test="${tu.role=='1'}">
			<li><a href="back/back.jsp">后台</a></li>
		</c:if>
		<c:if test="${tu!=null }">
		<li><a href="Logout">退出</a></li>
		</c:if>
		<li><a href="contact.html">关于我们</a></li>
		<li><a href="faqs.html">常见问题</a></li>
	</ul>
</body>
</html>