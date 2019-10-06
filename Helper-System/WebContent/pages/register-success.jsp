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
<title>Insert title here</title>
<link rel="stylesheet" href="../js/bootstrap.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</head>
<body>
<%
	//以下全是字符转换
	request.setCharacterEncoding("utf-8");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	int age = Integer.parseInt(request.getParameter("age"));
	String email = request.getParameter("email");
	String birth = request.getParameter("birth");
	String phone = request.getParameter("phone");
	String sex = request.getParameter("sex");
	String jieshao = request.getParameter("jieshao");
%>
<%		
		boolean i;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            i = true;
        } catch (Exception e) {
            i = false;
        }
        if(i=true){  //判定数据库驱动是否完善
        	
        }else{
        	out.print("数据库驱动错误");
        }
%>
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:update dataSource="${snapshot}" var="result">
INSERT INTO user (username,password,email,age,phone,sex,jieshao,birth,permit) VALUES ('<%=username %>','<%=password %>','<%=email %>',<%=age %>,'<%=phone %>','<%=sex %>','<%=jieshao %>','<%=birth %>','用户')
</sql:update>

<form class="form-horizontal">
<div class="text-center">
	<h2>注册成功</h2>
	<div class="form-group">
		<label class="control-label">用户名：<%=username %></label><br/>
	</div>
	<div class="form-group">
		<label class="control-label">密  码：<%=password %></label><br/>
	</div>
	<div class="form-group">
		<label class="control-label">年龄：<%=age %></label><br/>
	</div>
	<div class="form-group">
		<label class="control-label">邮箱：<%=email %></label><br/>
	</div>
	<div class="form-group">
		<label class="control-label">手机号码：<%=phone %></label><br/>
	</div>
	<div class="form-group">
		<label class="control-label">性别：<%=sex %></label><br/>
	</div>
	<div class="form-group">
		<label class="control-label">个人介绍：<%=jieshao %></label><br/>
	</div>
</div>
</form>
</body>
</html>