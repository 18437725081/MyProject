<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="nyist.web.jstl.domain.Product" %>
<%@page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Product product = new Product();
		product.setPname("外星人电脑");
		product.setDate(new Date());
		pageContext.setAttribute("product", product);
		
		Product product1 = new Product();
		product1.setPname("苹果电脑");
		product1.setDate(new Date());
		
		Product product2 = new Product();
		product2.setPname("机器人");
		product2.setDate(new Date());
		
		Product product3 = new Product();
		product3.setPname("乐视手机");
		product3.setDate(new Date());
		
		List<Product> list = new ArrayList<Product>();
		list.add(product1);
		list.add(product2);
		list.add(product3);
		request.setAttribute("list", list);		
		
	%>
	<h1>获取对象中的数据</h1>
	${product.pname }<br/>
	${product.date }<br/>
	<h1>=====================</h1>
	<h1>获取list集合中的数据</h1>
	${list[1].pname }<br/>
	${list[1].date }<br/>
	
	${list[0].pname }<br/>
	${list[0].date }<br/>
	
</body>
</html>