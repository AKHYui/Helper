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
<form action="<%=basePath %>GuestUpdateSetting" method="post" class="form-horizontal">
<div class="row">
<div class="col-md-6">
<div class="pull-center">
<div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">用户名</span>
    </div>
    <input type="text" disabled="disabled" class="form-control" value="${row.username}">
  </div>
<br/>
<div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">邮箱</span>
    </div>
    <input type="text" name="email" class="form-control" value="${row.email}">
  </div>
<br/>
<div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">手机</span>
    </div>
    <input type="text" name="phone" class="form-control" value="${row.phone}">
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
    <input type="password" name="password" class="form-control" value="${row.password}">
  </div>
<br/>
<div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">年龄</span>
    </div>
    <input type="text" name="age" class="form-control" value="${row.age}">
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
    <option selected value="保密">保密</option>
    <option value="男">男</option>
    <option value="女">女</option>
  </select>
<div class="input-group-prepend">
      <span class="input-group-text">当前性别：${row.sex}</span>
    </div>
</div>
<br/>
<button type="button" data-toggle="modal" data-target="#Icon" class="btn btn-primary btn-block">头像设定</button>

</div>
</div>
</div>
<br/>
<div class="input-group mb-3">
<div class="input-group-prepend">
      <span class="input-group-text">当前所在地</span>
    </div>
    <input type="text" disabled="disabled" class="form-control" value="<%=session.getAttribute("nowaddress")%>">
  </div>
<br/>
<div class="form-group">
      <span class="input-group-text">自我介绍</span>
      <textarea name="jieshao" cols="3" class="form-control">${row.jieshao}</textarea>
</div>
<div align="center">
<input type="submit" value="确认" class="btn btn-primary">
<input type="reset" value="重置" class="btn btn-primary">
</div>
</form>
	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="Icon" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">修改头像</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="<%=basePath %>IconUpload" enctype="multipart/form-data" method="post">
      <div class="form-group">
      <div class="row">
    <div class="col-sm-6 col-md-3">
        <a href="#" class="thumbnail">
            <img width="130px" src="<%=basePath %>upload/icon/${row.icon}"
                 alt="通用的占位符缩略图">
        </a>
    </div>
     <div class="col-sm-6 col-md-9">
      	<div class="form-group">
    		<label class="sr-only" for="inputfile">文件输入</label>
    		<input type="file" name="uploadFile">
 		 </div>
     </div>
    </div>
      </div>
      	<div class="modal-footer">
 		 </div>
            <div class="form-group"><button class="btn btn-primary" type="submit">确定 </button></div>
        </form>
        </div>
      </div>
      </div>
<!-- 查看信息用的模态框 结束 -->
</c:forEach>
<%@ include file="/home/module/indexdown.jsp"%>
