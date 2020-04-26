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
    <title>信院互帮圈助手平台</title>
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
                <div id="icon"><img class="rounded-circle mr-2" width="30" height="30" src="<%=basePath %>upload/icon/${row_i.icon}"></div>
                </c:forEach>
				<div id="user"><a href="#"><%= session.getAttribute("username")%> </a></div>
				</div></li>
                <li> <a href="<%=basePath%>IndexServlet"><i class="fa fa-home"></i> 首 页</a></li>
                <li> <a href="<%=basePath%>MyArticleServlet"><i class="fa fa-send"></i> 我的发布</a></li>
                <li> <a href="<%=basePath%>MyCommentServlet"><i class="fa fa-list"></i> 我的应答</a></li>
                <li> <a href="<%=basePath%>MyFavoriteServlet"><i class="fa fa-star"></i> 我的收藏</a></li>
                <li> <a href="<%=basePath%>FastModServlet"><i class="fa fa-rocket"></i> 快速求助</a></li>
                <li> <a href="<%=basePath%>BulletinServlet"><i class="fa fa-calendar-o"></i> 公告查看</a></li>
                <li> <a href="<%=basePath%>UserSettingServlet"><i class="fa fa-user"></i> 用户设置</a></li>
                <li> <a href="<%= basePath %>SessionDel?exit=1"><i class="fa fa-sign-out"></i> 退出登录 </a></li>
            </ul>
        </div>
        <div class="page-content-wrapper">
            <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i class="fa fa-bars"></i></a>