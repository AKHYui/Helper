<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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
    <title>互帮圈</title>
    <link rel="stylesheet" href="<%=basePath %>assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/css/Sidebar-Menu-1.css">
    <link rel="stylesheet" href="<%=basePath %>assets/css/Sidebar-Menu.css">
    <link rel="stylesheet" href="<%=basePath %>assets/myarticle_assets/css/Article-List.css">
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
                <li> <a href="<%=basePath%>IndexServlet"><i class="fa fa-home"></i> 首 页</a></li>
                <li> <a href="<%=basePath%>MyArticleServlet"><i class="fa fa-send"></i> 我的发布</a></li>
                <li> <a href="<%=basePath%>MyCommentServlet"><i class="fa fa-list"></i> 我的应答</a></li>
                <li> <a href="<%=basePath%>MyFavoriteServlet"><i class="fa fa-star"></i> 我的收藏</a></li>
                <li> <a href="<%=basePath%>FastModServlet"><i class="fa fa-rocket"></i> 快速模式</a></li>
                <li> <a href="<%=basePath%>BulletinServlet"><i class="fa fa-calendar-o"></i> 公告查看</a></li>
                <li> <a href="<%=basePath%>UserSettingServlet"><i class="fa fa-user"></i> 用户设置</a></li>
                <li> <a href="<%= basePath %>SessionDel?exit=1"><i class="fa fa-sign-out"></i> 退出登录 </a></li>
            </ul>
        </div>
        <div class="page-content-wrapper">
            <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i class="fa fa-bars"></i></a>
            
                <button type="button" data-toggle="modal" data-target="#Aritcle" class="btn btn-primary btn-lg btn-block"><i class="fa fa-send"></i> 发布求助</button>
                <%=session.getAttribute("myartkey") %>
                <%@ include file="/home/module/myarticle_mod.jsp"%>
<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="Aritcle" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">发布求助</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <!-- enctype="multipart/form-data" -->
		<form action="<%=basePath %>GuestPostArticle" method="post" class="form-horizontal">
		<div class="form-group">
		
    	<label for="title" class="control-label col-sm-4 col-sm-offset-1"><i class="fa fa-tag"></i>&nbsp;主&nbsp;题:</label>
    	<input type="text" class="form-control" name="title" placeholder="至少2个字符" id="title" value="" ><br/>
    	<label for="addr" class="control-label col-sm-4 col-sm-offset-1"><i class="fa fa-flag"></i>&nbsp;地&nbsp;点:</label>
    	<input type="text" class="form-control" name="addr" placeholder="至少3个字符" id="addr" value=""><br/>
      	<label for="text" class="control-label col-sm-4 col-sm-offset-1"><i class="fa fa-pencil"></i>&nbsp;求助内容:</label>
      	<textarea class="form-control" rows="10" id="text" placeholder="至少15个字符" name="text"></textarea>
      	 <!-- 
      	<label for="img" class="control-label col-sm-4 col-sm-offset-1">图片:</label>
      	<div class="form-group">
    		<label class="sr-only" for="inputfile">文件输入</label>
    		<input type="file" name="uploadFile">
 		 </div>
 		  -->
      	</div>
      	<div class="modal-footer">
      <input type="submit" value="提交" class="btn btn-primary"></input>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        </div>
		</form>
      </div>
      </div>
    </div>
  </div>
</div>
<!-- 查看信息用的模态框 结束 -->

            </div>
        </div>
        </div>
    <script src="<%=basePath %>assets/js/jquery.min.js"></script>
    <script src="<%=basePath %>assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basePath %>assets/js/Sidebar-Menu.js"></script>
</body>
</html>