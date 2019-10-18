<%@ page language="java" 
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Floral Shop, Shopping Cart, Online Store</title>
<meta name="keywords" content="free template, shopping cart, floral, ecommerce, online, store" />
<meta name="description" content="Floral, Shopping Cart, free template for ecommerce websites." />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="css/orman.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />

<link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddsmoothmenu.js">

/***********************************************
* Smooth Navigational Menu- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<script type="text/javascript">
function changemoney(id){
	//动态获取花的单价
	var price=document.getElementById("price"+id).value;
	//动态获取花的数量
	var count=document.getElementById("count"+id).value;
	//计算当前总价
	var sum=count*price;
	document.getElementById("total"+id).value=sum;
}
function chooseBuy(id){ 
	var choice=document.getElementsByName("choice");
	var strs="";
	//获取到所有鲜花的总价
	//var sum=document.getElementsByName("sum");
	//document.getElementById("total"+id);
	for (var i = 0; i < choice.length; i++) {
		if(choice[i].checked){
			strs=strs+choice[i].value+",";
		}
	}
	//从js跳转到servlet的方法
	//location.href="CheckoutServ?strs="+strs;
	var myform=document.getElementById("myform");
	myform.action="CheckoutServ?strs="+strs;
	myform.method="post";
}
ddsmoothmenu.init({
	mainmenuid: "templatemo_menu", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

function clearText(field)
{
    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;
}

</script>


</head>

<body>

<div id="templatemo_wrapper_sp">
<div id="templatemo_header_wsp">
	<div id="templatemo_header" class="header_subpage">
    	<div id="site_title"><a href="" title=""></a></div>
        <div id="templatemo_menu" class="ddsmoothmenu">
            <jsp:include page="head.jsp" />
            <div id="templatemo_search">
                <form action="#" method="get">
                  	<input type="text" value="查找" name="keyword" id="keyword" title="keyword" 
                  			onfocus="clearText(this)" onblur="clearText(this)" class="txt_field" />
                  	<input type="submit" name="Search" value="" alt="Search" id="searchbutton" title="Search" class="sub_btn" />
                </form>
            </div>
            <br style="clear: left" />
        </div> 
        <a href="#" title="免费的图像"  class="site_repeat" ><img src="images/top_repeat.png" title="免费的图像"  /></a><!-- end of templatemo_menu -->

    </div> <!-- END of header -->
</div> <!-- END of header wrapper -->
<div id="templatemo_main_wrapper">
<div id="templatemo_main">
	<div id="sidebar" class="left">
    	<div class="sidebar_box"><span class="bottom"></span>
            <h3>种类</h3>   
            <div class="content"> 
                <ul class="sidebar_list">
                    <li><a href="#">玫瑰</a></li>
                    <li><a href="#">康乃馨</a></li>
                    <li><a href="#">百合</a></li>
                    <li><a href="#">满天星</a></li>
                    <li><a href="#">茉莉花</a></li>
                    <li><a href="#">薰衣草</a></li>
                    <li><a href="#">海棠</a></li>
                    <li><a href="#">月季</a></li>
                    <li><a href="#">花篮装饰</a></li>
                </ul>
            </div>
        </div>
        <div class="sidebar_box"><span class="bottom"></span>
            <h3>每周特价</h3>   
            <div class="content special"> 
                <img src="images/templatemo_image_01.jpg" alt="Flowers" />
                <a href="#">Citrus Burst Bouquet</a>
                <p>
                	Price:
                    <span class="price normal_price">$160</span>&nbsp;&nbsp;
                    <span class="price special_price">$100</span>
                </p>
            </div>
        </div>
    </div>
    
    <div id="content" class="right">
    	<h2>购物车</h2>
    	<form action="#" id="myform">
		<table width="700" border="0" cellpadding="5" cellspacing="0">
          	<tr bgcolor="#395015">
                <th width="168" align="left">选择</th> 
                <th width="168" align="left">鲜花图片</th> 
                <th width="188" align="left">花名</th> 
                <th width="60" align="center">数量</th> 
                <th width="80" align="right">单价</th> 
                <th width="80" align="right">总价</th> 
                <th width="64"> </th>
          	</tr>
          	<c:forEach items="${flower}" var="flower">
            <tr bgcolor="#41581B">
                <td><input type="checkbox" name="choice" value="${flower.f_id}"/></td>
                <td><img src="../images/${flower.pic}" alt="flower image 1" width="140px" height="140px"/></td> 
                <td>${flower.f_name}</td> 
                <td align="center"><input name="count${flower.f_id}" type="text" id="count${flower.f_id}" value="1" size="6" maxlength="2"
                onblur="changemoney(${flower.f_id})" autocomplete="off"/> </td>
                <td align="right"><input type="text" id="price${flower.f_id}" value="${flower.price}" readonly="readonly"/></td>  
                <td align="right" ><input type="text" id="total${flower.f_id}" name="${flower.f_id}" value="${flower.price}"/></td>
                <td align="center"> <a href="CarServ?flowerid=${flower.f_id}"><img src="images/remove.png" alt="remove" /><br />删除</a> </td>
              
            </tr>
           </c:forEach>
<tr bgcolor="#41581B">
                <td colspan="3">Have you modified item quantities? Please <a href="shoppingcart.html"><strong>Update</strong></a> the Cart.&nbsp;&nbsp;</td>
          
                <td> </td>
            </tr>
        </table>
		<div class="cleaner h20"></div>
        <div class="right"><input type="submit" value="结算" onclick="chooseBuy()" /></div>
        <div class="cleaner h20"></div>
        <div class="blank_box">
        	<a href="#"><img src="images/free_shipping.jpg" alt="Free Shipping" /></a>
        </div> 
        </form>   
    </div>
    <div class="cleaner"></div>
</div> <!-- END of main -->
</div> <!-- END of main wrapper -->


</div>

</body>
<script type='text/javascript' src='js/logging.js'></script>
</html>