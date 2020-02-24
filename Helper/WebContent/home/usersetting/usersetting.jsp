<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/home/module/indexup.jsp"%>

<!-- 连接数据库进行头像的查找 -->
<sql:setDataSource var="user" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${user}" var="result">
SELECT * from user where username="<%= session.getAttribute("username")%>" ;
</sql:query>
<c:forEach var="row" items="${result.rows}">
<h4 class="text-center">用户设定</h4>
<br/>
<form action="#" method="post" class="form-horizontal">
<div class="row">
<div class="col-md-6">
<div class="pull-center">
<div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">用户名</span>
    </div>
    <input type="text" class="form-control" value="${row.username}">
  </div>
<br/>
<div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">邮箱</span>
    </div>
    <input type="text" class="form-control" value="${row.email}">
  </div>
<br/>
<div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">手机</span>
    </div>
    <input type="text" class="form-control" value="${row.phone}">
  </div>
<br/>
<div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">当前权限</span>
    </div>
    <input type="text" disabled="disabled" class="form-control" value="${row.permit}">
  </div>
</div>
</div>
<div class="col-md-6">
<div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">密码</span>
    </div>
    <input type="password" class="form-control" value="${row.password}">
  </div>
<br/>
<div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">年龄</span>
    </div>
    <input type="text" class="form-control" value="${row.age}">
    <div class="input-group-prepend">
      <span class="input-group-text">岁</span>
    </div>
  </div>  
<br/>
<div>
<div class="input-group mb-3">
<div class="input-group-prepend">
      <span class="input-group-text">性别</span>
    </div>
<select name="sex" class="custom-select-sm">
    <option selected>未知</option>
    <option value="男">男</option>
    <option value="女">女</option>
  </select>
<div class="input-group-prepend">
      <span class="input-group-text">当前性别：${row.sex}</span>
    </div>
</div>
<br/>
<button type="button" class="btn btn-primary btn-block">头像设定</button>

</div>
</div>
</div>
<br/>
<div class="form-group">
      <span class="input-group-text">自我介绍</span>
      <textarea cols="3" class="form-control">${row.jieshao}</textarea>
</div>
<div align="center">
<input type="submit" value="确认" class="btn btn-primary">
<input type="reset" value="重置" class="btn btn-primary">
</div>
</form>
</c:forEach>
<%@ include file="/home/module/indexdown.jsp"%>
