<%@page import="java.net.URLDecoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String se = session.getAttribute("username").toString();  //接收session 如果session错误
	String error = new String(basePath+"login-error.jsp");	  //则返回到错误页面
	if(se != "admin"){
		response.setStatus(response.SC_MOVED_TEMPORARILY);
    	response.setHeader("Location", error); 
	}
%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!doctype html>	
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>管理员</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
<script type="text/javascript">
	$('#myModal').on('shown.bs.modal', function () {  //提示框必要使用
	  	$('#myInput').trigger('focus')
		});
</script>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	%>
<%
            Date date = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("HH");
			String hour = ft.format(date);
			int hour_i = Integer.parseInt(hour);
			String info = "";
			if(hour_i<12){
				info = "早上好！";
			}else if(hour_i>12 && hour_i<14){
				info = "中午好！";
			}else if(hour_i>14 && hour_i<18){
				info = "下午好！";
			}else{
				info = "晚上好！";
			}
        %>
<header id="header"> <!-- 状态栏 -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">管理面板</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="<%= basePath%>">Home<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">关于</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          完整功能
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%= basePath%>FuncRewrite?id=1">用户管理</a>
          <a class="dropdown-item" href="#">发布管理</a>
          <a class="dropdown-item" href="#">评论管理</a>
          <a class="dropdown-item" href="#">公告管理</a>
          
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          更多功能
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="index.jsp">退出登录</a>
          <a class="dropdown-item" href="#">修改密码</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="<%=basePath %>">回到主页</a>
        </div>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
</header>
 <!-- 状态栏 结束 -->
<body>
<div class="row">
<div class="col-md-2">&nbsp;</div> <!-- 左边的空白 -->
<div class="col-md-8">
<div>
<h4><%=info %>管理员</h4><br/>
<%
	SimpleDateFormat dy = new SimpleDateFormat("yyyy年MM月dd日");
%>
<h5>今天是<%=dy.format(date) %></h5>
</div>
<nav><!-- 滑动门 -->
  <div class="nav nav-tabs" id="nav-tab" role="tablist">
    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">最近注册</a>
    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">最近发布</a>
    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">最近评论</a>
    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-fourth" role="tab" aria-controls="nav-contact" aria-selected="false">最近公告</a>
  </div>
</nav>

<div class="tab-content" id="nav-tabContent">
<!-- 滑动门的第一个模块 -->
	<%@ include file="../module/user_table.jsp"%>
  </div>
  <!-- 滑动门的第一个模块 结束 -->
  <!-- 滑动门第二个模块 -->
  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
	<%@ include file="../module/article_table.jsp"%>
  </div>
  <!-- 滑动门第二个模块 结束 -->
  <!-- 滑动门第三个模块 -->
  <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
	<%@ include file="../module/comment_table.jsp"%>
  </div>
  <!-- 滑动门第三个模块 结束 -->
  <!-- 滑动门第四个模块 -->
  <div class="tab-pane fade" id="nav-fourth" role="tabpanel" aria-labelledby="nav-fourth-tab">
  	<%@ include file="../module/bulletin_table.jsp"%>
  </div>
  <!-- 滑动门第四个模块 结束 -->
</div>
</div>
<div class="col-md-2">&nbsp;</div><!-- 右边的空白 -->
</div>
</body>
</html>