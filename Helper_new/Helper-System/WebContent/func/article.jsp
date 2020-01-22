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
<title>发布管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="./js/bootstrap.min.css">
<script src="./js/jquery-3.3.1.slim.min.js.下载"></script>
<script src="./js/popper.min.js.下载"></script>
<script src="./js/bootstrap.min.js.下载"></script>
<script type="text/javascript">
	$('#myModal').on('shown.bs.modal', function () {  //提示框必要使用
	  	$('#myInput').trigger('focus')
		});
</script>
</head>
<%@ include file="../module/header.jsp"%>
<%
		String sessionusers = session.getAttribute("sessionusers").toString();
		int begin_s = Integer.parseInt(session.getAttribute("begin").toString());
		int end_s = Integer.parseInt(session.getAttribute("end").toString());
		int page_s = Integer.parseInt(session.getAttribute("page").toString());
		String error = new String(basePath+"login-error.jsp");
		if(sessionusers != "article"){
			response.setStatus(response.SC_MOVED_TEMPORARILY);
	    	response.setHeader("Location", error); 
		}
	%>
<body>
<div class="row">
<div class="col-md-2">&nbsp;</div> <!-- 左边的空白 -->
<div class="col-md-8">
	<!-- 连接数据库进行全部发布的查找 -->
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${snapshot}" var="result">
SELECT * from article order by id;
</sql:query>
<table class="table"> <!-- 输出各用户信息的表格 -->
  		<thead>
    		<tr>
      		<th scope="col">ID</th>
      		<th scope="col">标题</th>
      		<th scope="col">正文</th>
      		<th scope="col">时间</th>
      		<th scope="col">发布人</th>
      		<th scope="col">发布地点</th>
      		<th scope="col">详细查看</th>
      		<th scope="col">删除操作</th>
    		</tr>
  		</thead><!-- 循环输出各个用户的信息 -->
		<c:forEach begin="<%= begin_s%>" end="<%= end_s%>" var="row" items="${result.rows}">
		<tbody>
    		<tr>
      			<td><c:out value="${row.id}"/></td>
      			<td><c:out value="${row.title}"/></td>
      			<td><c:out value="${row.text}"/></td>
      			<td><c:out value="${row.time}"/></td>
      			<td><c:out value="${row.user}"/></td>
      			<td><c:out value="${row.addr}"/></td>
      			<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#Aritcle${row.id}" value="${row.id}">查看
</button></td>
      			<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#DeleteArticle${row.id}" value="${row.id}">删除
</button></td>
	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="Aritcle${row.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  	<form action="" method="get" class="form-horizontal">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">查看详情</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		ID：${row.id}<br/>
		题目：${row.title}<br/>
		内容：${row.text}<br/>
		发布时间：${row.time}<br/>
		发布人：${row.user}<br/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
      </div>
    </div>
    </form>
  </div>
</div>
<!-- 查看信息用的模态框 结束 -->
<!-- 删除信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="DeleteArticle${row.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  	<form action="../DeleteArticle" method="get" class="form-horizontal">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">删除信息</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <input type="hidden" class="form-control" id="recipient-name" name="id" value="${row.id}">
        是否要删除用户${row.user}发布的${row.title}？这个操作是不可逆的！<br/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <input type="submit" id="deleting" value="删除" class="btn btn-danger"></input>
      </div>
    </div>
    </form>
  </div>
</div>
<!-- 删除信息用的模态框 结束 -->
</tr>
</tbody>
</c:forEach>
<!-- 查找总行数 -->
<sql:setDataSource var="cou" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${cou}" var="res">
SELECT COUNT(*) total FROM article;
</sql:query>
<tr>
<c:forEach var="row" items="${res.rows}">
<td colspan="2" align="left"><a href="<%= basePath%>PageDown?begin=<%= begin_s%>&end=<%= end_s%>&uid=2">上一页</a></td>
<td colspan="2" align="center">第<%=page_s %>页</td>
<td colspan="3" align="right"><a href="<%= basePath%>PageUp?begin=<%= begin_s%>&end=<%= end_s%>&all=${row.total}&uid=2">下一页</a></td>
</c:forEach>
</tr>
</table>
</div>
<div class="col-md-2">&nbsp;</div> <!-- 右边的空白 -->
</div>
</body>
</html>