<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理系统</title>
	<link rel="stylesheet" href="js/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<h2 class="text-center">互帮圈管理系统</h2>
<div class="text-center">
<a href="<%=basePath%>pages/index.jsp"><button class="btn btn-primary">管理员登陆</button></a><br/><hr>
<a href="<%=basePath%>pages/index.jsp"><button class="btn btn-primary">用户登陆</button></a><br/><hr>
<a href="<%=basePath%>pages/register.jsp"><button class="btn btn-primary">用户注册</button></a><br/><hr>
</div>
</body>
</html>