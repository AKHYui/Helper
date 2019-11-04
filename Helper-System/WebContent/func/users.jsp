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

<!--弹窗 --> <!-- 无法用js实现将button数据传给模态框的操作 所以直接将模态框写在循环体里 每个模态框的id为DeleteModal+用户ID
用户ID是独一无二的 所以每个模态框ID都是独一无二的 然后将row.id的数据交给隐藏的input以将id传送到删除操作-->
<!-- 删除信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="DeleteModal${row.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  	<form action="../DeleteUser" method="get" class="form-horizontal"">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">删除用户</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <input type="hidden" class="form-control" id="recipient-name" name="id" value="${row.id}">
        是否要删除用户${row.username}？这个操作是不可逆的！<br/>
    ID：${row.id}<br/>
        用户名：${row.username}
      </div>
      <div class="modal-footer">
      <input type="hidden" class="form-control"  name="userset" value="1">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <input type="submit" id="deleting" value="删除" class="btn btn-danger"></input>
      </div>
    </div>
    </form>
  </div>
</div>
<!-- 删除信息用的模态框 结束 -->
<!-- 下面是修改信息用的模态框 -->
<!-- Modal --> 
<div class="modal fade" id="InsertModal${row.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  	<form action="../UpdateUser" method="get" class="form-horizontal"">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">修改用户</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    	ID：${row.id}<br/>
    	<div class="form-group">
    	<input type="hidden" class="form-control" id="recipient-name" name="id" value="${row.id}">
    	<label for="name" class="control-label col-sm-4 col-sm-offset-1">用户名：</label><br/>
    	<input type="text" class="form-control" name="username" value="${row.username}" ><br/>
    	<label for="password" class="control-label col-sm-4 col-sm-offset-1">密&nbsp;码：</label><br/>
    	<input type="password" class="form-control" name="password" value="${row.password}"><br/>
      	<label for="email" class="control-label col-sm-4 col-sm-offset-1">邮&nbsp;箱：</label><br/>
      	<input type="text" class="form-control" name="email"  value="${row.email}"><br/>
      	<label for="phone" class="control-label col-sm-4 col-sm-offset-1">手机号码：</label><br/>
      	<input type="text" class="form-control" name="phone" value="${row.phone}"><br/>
      	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <input type="submit" id="deleting" value="提交" class="btn btn-secondary"></input>
      </div>
    </div>
    </form>
  </div>
</div>

<!-- 修改信息用的模态框 结束 -->
<!--弹窗 -->

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
<td colspan="2" align="center">第<%=page_s %>页</td>
<td colspan="3" align="right"><a href="<%= basePath%>PageUp?begin=<%= begin_s%>&end=<%= end_s%>&all=${row.total}">下一页</a></td>
</c:forEach>
</tr>
</table>
</div>
<div class="col-md-2">&nbsp;</div> <!-- 右边的空白 -->
</div>
</body>
</html>