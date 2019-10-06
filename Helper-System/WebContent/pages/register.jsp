<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账号注册</title>
	<link rel="stylesheet" href="../js/bootstrap.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>

<div>
	<h2 class="text-center">用户注册</h2>
	<form method="get" action="register-success.jsp" class="form-horizontal">
		<div class="form-group">
    		<label for="name" class="control-label col-sm-2 col-sm-offset-1">用户名：</label>
        	<div class="col-sm-6">
        		<input type="text" class="form-control" name="username" placeholder="请输入用户名" >
        	</div>
		</div>
		<div class="form-group">
    		<label for="password" class="control-label col-sm-2 col-sm-offset-1">密&nbsp;码：</label>
        	<div class="col-sm-6">
        		<input type="password" class="form-control" name="password" placeholder="请输入密码" >
        	</div>
		</div>
		<div class="form-group">
    		<label for="email" class="control-label col-sm-2 col-sm-offset-1">邮&nbsp;箱：</label>
        	<div class="col-sm-6">
        		<input type="text" class="form-control" name="email" placeholder="请输入邮箱" >
        	</div>
		</div>
		<div class="form-group">
    		<label for="age" class="control-label col-sm-2 col-sm-offset-1">年&nbsp;龄：</label>
        	<div class="col-sm-6">
        		<input type="text" class="form-control" name="age" placeholder="请输入年龄" >
        </div>
		</div>
		<div class="form-group">
    		<label for="birth" class="control-label col-sm-2 col-sm-offset-1">生&nbsp;日：</label>
        	<div class="col-sm-6">
        		<input type="text" class="form-control" name="birth" placeholder="YYYY-MM-DD" >
        </div>
		</div>
		<div class="form-group">
    		<label for="phone" class="control-label col-sm-2 col-sm-offset-1">手机号码：</label>
        	<div class="col-sm-6">
        		<input type="text" class="form-control" name="phone" placeholder="请输入手机号码" >
        	</div>
		</div>
		<div class="form-group">
    		<label for="sex" class="control-label col-sm-2 col-sm-offset-1">性别：</label>
        	<div class="col-sm-6">
        		<input type="radio" value="1" id="boy"  name="sex" >男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		<input type="radio" value="0" id="girl" name="sex" >女&nbsp;
        	</div>
		</div>
		<div class="form-group">
    		<label for="jieshao" class="control-label col-sm-2 col-sm-offset-1">自我介绍：</label>
        	<div class="col-sm-6">
        		<textarea class="form-control" name="jieshao"><%out.print("这个人很懒，暂时还没有介绍"); %></textarea>
        	</div>
		</div>
		<div class="text-center">
			<input type="submit" value="注册" class="btn btn-primary">
			<input type="reset" value="重置" class="btn btn-primary">
		</div>
</form>
</div>
</body>
</html>