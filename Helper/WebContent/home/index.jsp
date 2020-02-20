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
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Helper·HOME</title>
    <link rel="stylesheet" href="<%=basePath %>assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/css/Sidebar-Menu-1.css">
    <link rel="stylesheet" href="<%=basePath %>assets/css/Sidebar-Menu.css">
    <link rel="stylesheet" href="<%=basePath %>assets/css/styles.css">
    
	
</head>
<body>
<!-- 连接数据库进行头像的查找 -->
<sql:setDataSource var="icon" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${icon}" var="result">
SELECT icon from user where username="<%= session.getAttribute("username")%>" ;
</sql:query>
<!-- 连接数据库进行头像的查找 结束 -->

    <div id="wrapper">
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                <div id="wrap">
                <c:forEach var="row_i" items="${result.rows}">
                <div id="icon"><img width="30px" src="${row_i.icon}"></div>
                </c:forEach>
				<div id="user"><a href="#"><%= session.getAttribute("username")%> </a></div>
				</div></li>
                <li> <a href="<%=basePath%>IndexServlet">首 页</a></li>
                <li> <a href="#">用户设置 </a></li>
                <li> <a href="#">我的发布 </a></li>
                <li> <a href="#">我的评论 </a></li>
                <li> <a href="<%= basePath %>SessionDel?exit=1">退出登录 </a></li>
            </ul>
        </div>
        <div class="page-content-wrapper">
            <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i class="fa fa-bars"></i></a>
                <%@ include file="/home/module/home_art.jsp"%>
            </div>
        </div>
    </div>
    <script src="<%=basePath %>assets/js/jquery.min.js"></script>
    <script src="<%=basePath %>assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basePath %>assets/js/Sidebar-Menu.js"></script>
</body>
</html>