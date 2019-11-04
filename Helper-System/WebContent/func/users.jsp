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
<title>用户管理</title>
<link rel="stylesheet" href="../js/bootstrap.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="row">
<div class="col-md-2">&nbsp;</div> <!-- 左边的空白 -->
<div class="col-md-8">
	<%
		String sessionusers = session.getAttribute("sessionusers").toString();
		int begin_s = Integer.parseInt(session.getAttribute("begin").toString());
		int end_s = Integer.parseInt(session.getAttribute("end").toString());
		int page_s = Integer.parseInt(session.getAttribute("page").toString());
		String error = new String(basePath+"login-error.jsp");
		if(sessionusers != "users"){
			response.setStatus(response.SC_MOVED_TEMPORARILY);
	    	response.setHeader("Location", error); 
		}
	%>
	<!-- 连接数据库进行全部用户的查找 -->
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${snapshot}" var="result">
SELECT * from user order by id;
</sql:query>
<table class="table"> <!-- 输出各用户信息的表格 -->
  		<thead>
    		<tr>
      		<th scope="col">ID</th>
      		<th scope="col">用户名</th>
      		<th scope="col">email</th>
      		<th scope="col">手机号码</th>
      		<th scope="col">身份权限</th>
      		<th scope="col">删除操作</th>
      		<th scope="col">修改信息</th>
    		</tr>
  		</thead><!-- 循环输出各个用户的信息 -->
		<c:forEach begin="<%= begin_s%>" end="<%= end_s%>" var="row" items="${result.rows}">
		<tbody>
    		<tr>
      			<td><c:out value="${row.id}"/></td>
      			<td><c:out value="${row.username}"/></td>
      			<td><c:out value="${row.email}"/></td>
      			<td><c:out value="${row.phone}"/></td>
      			<td><c:out value="${row.permit}"/></td>
      			<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#DeleteModal${row.id}" value="${row.id}">删除
</button></td>
				<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#InsertModal${row.id}" value="${row.id}">修改
</button></td>
</tr>
</tbody>
</c:forEach>
<!-- 查找总行数 -->
<sql:setDataSource var="cou" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${cou}" var="res">
SELECT COUNT(*) total FROM user;
</sql:query>
<tr>
<c:forEach var="row" items="${res.rows}">
<td colspan="2" align="left"><a href="<%= basePath%>PageDown?begin=<%= begin_s%>&end=<%= end_s%>">上一页</a></td>
<td colspan="2" align="center"><%=page_s %></td>
<td colspan="3" align="right"><a href="<%= basePath%>PageUp?begin=<%= begin_s%>&end=<%= end_s%>&all=${row.total}">下一页</a></td>
</c:forEach>
</tr>
</table>
</div>
<div class="col-md-2">&nbsp;</div> <!-- 右边的空白 -->
</div>
</body>
</html>