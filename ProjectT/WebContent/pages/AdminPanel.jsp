<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
<!doctype html>	
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>管理员</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
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
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          更多功能
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="index.jsp">退出登录</a>
          <a class="dropdown-item" href="#">修改密码</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">关于</a>
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
<body>
<div class="row">
<div class="col-md-2">&nbsp;</div>
<div class="col-md-8">
<div>
<h4><%=info %>管理员</h4><br/>
<%
	SimpleDateFormat dy = new SimpleDateFormat("yyyy年MM月dd日");
%>
<h5>今天是<%=dy.format(date) %></h5>
</div>
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${snapshot}" var="result">
SELECT * from user;
</sql:query>
<nav><!-- 滑动门 -->
  <div class="nav nav-tabs" id="nav-tab" role="tablist">
    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">用户管理</a>
    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">发布管理</a>
    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">Contact</a>
  </div>
</nav>
<div class="tab-content" id="nav-tabContent">
  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
	<table class="table">
  		<thead>
    		<tr>
      		<th scope="col">ID</th>
      		<th scope="col">用户名</th>
      		<th scope="col">email</th>
      		<th scope="col">手机号码</th>
      		<th scope="col">身份权限</th>
      		<th scope="col">删除操作</th>
    		</tr>
  		</thead><!-- 循环输出各个用户的信息 -->
		<c:forEach var="row" items="${result.rows}">
		<tbody>
    		<tr>
      			<td><c:out value="${row.id}"/></td>
      			<td><c:out value="${row.username}"/></td>
      			<td><c:out value="${row.email}"/></td>
      			<td><c:out value="${row.phone}"/></td>
      			<td><c:out value="${row.permit}"/></td>
      			<td><button type="button" data-whatever="${row.id}" class="btn btn-primary" data-toggle="modal" data-target="#DeleteModal${row.id}" value="${row.id}">删除
</button></td>
<!--弹窗 --> <!-- 无法用js实现将button数据传给模态框的操作 所以直接将模态框写在循环体里 每个模态框的id为DeleteModal+用户ID
用户ID是独一无二的 所以每个模态框ID都是独一无二的 然后将row.id的数据交给隐藏的input以将id传送到删除操作-->
<!-- Modal -->
<div class="modal fade" id="DeleteModal${row.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">删除用户</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <input type="hidden" class="form-control" id="recipient-name" value="${row.id}">
        是否要删除用户${row.username}？这个操作是不可逆的！
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" id="deleting" class="btn btn-danger">删除</button>
      </div>
    </div>
  </div>
</div>
<!--弹窗 -->
    			</tr>
		</tbody>
		</c:forEach>
		</table>
  </div>
  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">...</div>
  <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">...</div>
</div>
</div>
<div class="col-md-2">&nbsp;</div>
</div>
</body>
</html>