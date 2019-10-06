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
<title>warning</title>
<link rel="stylesheet" href="js/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="text-center">
<h2>发生了什么？</h2>
<h5>可能是您的管理员账号密码错误或者权限不够</h5>
<a href="<%=basePath+"index.jsp" %>"><button class="btn btn-primary">回到首页</button></a>
</div>
</body>
</html>