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
    <script src="../js/jquery.js"></script>
    <script src="../js/jquery.validate.min.js"></script>
    <script src="../js/messages_zh.js"></script>
    <script type="text/javascript">
    $().ready(function() {
    	// 在键盘按下并释放及提交后验证提交表单
    	  $("#SignupForm").validate({
    	    rules: {
    	      username: {
    	        required: true,
    	        minlength: 4
    	      },
    	      password: {
    	        required: true,
    	        minlength: 10
    	      },
    	      email: {
    	        required: true,
    	        email: true
    	      },
    	      age: {
    	    	  number:true
    	      },
    	      birth: {
    	    	  dateISO:true
    	      },
    	      telephone:{
                  required: true,
                  maxlength:11,
                  maxlength:11,
                  isMobile:true
              }
    	    },
    	    messages: {
    	      username: {
    	        required: "请输入用户名",
    	        minlength: "用户名至少由4个字符组成"
    	      },
    	      password: {
    	        required: "请输入密码",
    	        minlength: "密码长度不能小于 10个字母"
    	      },
    	      email: "请输入一个正确的邮箱",
			  age:"请输入您的年龄",
			  birth:"请输入您的生日，例如：2009-06-23",
			  telephone:"请输入您的手机号码"
    	     }
    	    })
    	});
    </script>
</head>
<body>

<div>
	<h2 class="text-center">用户注册</h2>
	<form method="get" id="SignupForm" action="<%=basePath %>InsertUser" class="form-horizontal">
		<div class="form-group">
    		<label for="name" class="control-label col-sm-2 col-sm-offset-1">用户名：</label>
        	<div class="col-sm-6">
        		<input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名" >
        	</div>
		</div>
		<div class="form-group">
    		<label for="password" class="control-label col-sm-2 col-sm-offset-1">密&nbsp;码：</label>
        	<div class="col-sm-6">
        		<input type="password" class="form-control" name="password" id="password" placeholder="请输入密码" >
        	</div>
		</div>
		<div class="form-group">
    		<label for="email" class="control-label col-sm-2 col-sm-offset-1">邮&nbsp;箱：</label>
        	<div class="col-sm-6">
        		<input type="text" class="form-control" name="email" id="email" >
        	</div>
		</div>
		<div class="form-group">
    		<label for="age" class="control-label col-sm-2 col-sm-offset-1">年&nbsp;龄：</label>
        	<div class="col-sm-6">
        		<input type="text" class="form-control" name="age" id="age" >
        </div>
		</div>
		<div class="form-group">
    		<label for="birth" class="control-label col-sm-2 col-sm-offset-1">生&nbsp;日：</label>
        	<div class="col-sm-6">
        		<input type="text" class="form-control" name="birth" id="birth" placeholder="请输入您的生日，例如：2009-06-23">
        </div>
		</div>
		<div class="form-group">
    		<label for="phone" class="control-label col-sm-2 col-sm-offset-1">手机号码：</label>
        	<div class="col-sm-6">
        		<input type="text" class="form-control" name="phone" id="phone">
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