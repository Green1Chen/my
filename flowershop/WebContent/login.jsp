<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<!-- Head -->
<head>

<title>登录表单</title>
<script type="text/javascript">
	function loadXMLDoc() {
		$.ajax({
			type : "post",
			url : "JudeServ",
			data : "phone="+$("#phonenumber").val(),
			success : function(msg) {
				$("#show").html(msg)
			}
		});
	}
	//原生态的ajax
	//定义一个xmlhttprequest对象
	/* var xmlhttp

	function loadXMLDoc() {
		var no=document.getElementById("phonenumber")
		//alert(no.value)
		xmlhttp = null

		// 针对 Mozilla等浏览器的代码：
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest()
		}

		// 针对 IE 的代码：
		else if (window.ActiveXObject) {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")
		}
		if (xmlhttp != null) {
			xmlhttp.onreadystatechange = state_Change
			xmlhttp.open("post", "JudeServ?phone="+no.value, true)
			xmlhttp.send(null)
		} else {
			alert("您的浏览器不支持XMLHTTP")
		}
	}

	function state_Change() {
		// 如果 xmlhttp 显示为 "loaded"
		if (xmlhttp.readyState == 4) {
			// 如果为 "OK"
			if (xmlhttp.status == 200) {
				document.getElementById("show").innerHTML=xmlhttp.responseText
			} else {
				alert("似乎出了点问题")
			}
		}
	} */
</script>
<!-- Meta-Tags -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=basePath%>jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery.easyui.min.js"></script>
<script type="application/x-javascript">
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 


</script>
<!-- //Meta-Tags -->

<!-- Style -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">



</head>
<!-- //Head -->

<!-- Body -->
<body>

	<h1>登录表单</h1>

	<div class="container w3layouts agileits">

		<div class="login w3layouts agileits">
			<h4>${result}</h4>
			<h2>登 录</h2>
			<ul class="tick w3layouts agileits">
				<li><input type="checkbox" id="brand1" value=""></li>
			</ul>
			<div class="send-button w3layouts agileits">
				<form action="LoginServ?result=${result}" method="post">
					<input type="text" Name="Userame" placeholder="用户名" required="">
					<input type="password" Name="Password" placeholder="密码" required="">
					<input type="submit" value="登 录">
				</form>
			</div>
			<a href="main.html">返回主页</a>

			<div class="clear"></div>
		</div>
		<div class="copyrights">
			Collect from <a href=""></a>
		</div>
		<div class="register w3layouts agileits">
			<h2>注 册</h2>
			<form action="#" method="post">
				<input type="text" Name="Name" placeholder="用户名" required="">
				<input type="text" Name="Email" placeholder="邮箱" required="">
				<input type="password" Name="Password" placeholder="密码" required="">
				<input type="text" Name="Phone Number" onblur="loadXMLDoc()"
					placeholder="手机号码" required="" id="phonenumber">
				<div id="show"></div>
			</form>
			<div class="send-button w3layouts agileits">
				<form>
					<input type="submit" value="免费注册">
				</form>
			</div>
			<div class="clear"></div>
		</div>

		<div class="clear"></div>

	</div>



</body>
<!-- //Body -->

</html>