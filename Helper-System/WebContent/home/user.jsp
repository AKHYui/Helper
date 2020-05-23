<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String imgpath = request.getContextPath();
String imgbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<%@ include file="/home/module/indexup.jsp"%>
<%
		int begin_s = Integer.parseInt(session.getAttribute("begin").toString());
		int end_s = Integer.parseInt(session.getAttribute("end").toString());
		String ad = session.getAttribute("ad").toString();
		String button = "";
		if(ad.equals("admin") == true){
			button = "";
		}else if(ad.equals("registadmin") == true){
			button = "disabled= disabled ";
		}else{
			button = "disabled= disabled ";
		}
%>
<sql:setDataSource var="user" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${user}" var="res_u">
SELECT * FROM user order by id desc;
</sql:query>
 <div class="container-fluid">
                    <h3 class="text-dark mb-4">用户</h3>
                    <div class="card shadow">
                        <div class="card-header py-3">
                            <p class="text-primary m-0 font-weight-bold">用户管理  <button type="button" data-toggle="modal" data-target="#addadmin" <%=button %> class="btn btn-primary btn-sm">新增入驻</button></p>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                <table class="table dataTable my-0" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>用户名</th>
                                            <th>联系方式</th>
                                            <th>年龄</th>
                                            <th>权限</th>
                                            <th>详情</th>
                                        </tr>
                                    </thead>
                                    <tbody>
<c:forEach begin="<%= begin_s%>" end="<%= end_s%>" var="row" items="${res_u.rows}">  
                                        <tr>
                                            <td><img class="rounded-circle mr-2" width="30" height="30" src="<%=imgbasePath%>Helper/upload/icon/${row.icon}">${row.username}</td>
                                            <td>${row.phone}</td>
                                            <td>${row.age}</td>
                                            <td>${row.permit}</td>
                                            <td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#userinfo${row.id}">详情
</button></td>

<!-- Modal --> 
<div class="modal fade" id="userinfo${row.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  	<form action="<%=basePath%>UpdateUser" method="get" class="form-horizontal">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">用户信息</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    	ID：${row.id}<br/>
    	<div class="form-group">
    	<input type="hidden" class="form-control" id="recipient-name" name="id" value="${row.id}">
    	<label for="name" class="control-label col-sm-4 col-sm-offset-1">用户名：</label><br/>
    	<input type="text"  class="form-control" disabled="disabled" value="${row.username}" ><br/>	
      	<label for="email" class="control-label col-sm-4 col-sm-offset-1">邮&nbsp;箱：</label><br/>
      	<input type="text" class="form-control" name="email"  value="${row.email}"><br/>
      	<label for="phone" class="control-label col-sm-4 col-sm-offset-1">手机号码：</label><br/>
      	<input type="text" class="form-control" name="phone" value="${row.phone}"><br/>
      	<label for="age" class="control-label col-sm-4 col-sm-offset-1">年&nbsp;龄：</label><br/>
      	<input type="text" class="form-control" disabled="disabled" value="${row.age}"><br/>
      	<label for="age" class="control-label col-sm-4 col-sm-offset-1">出生年份：</label><br/>
      	<input type="text" class="form-control" disabled="disabled" value="${row.birth}年"><br/>
      	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <input type="submit" id="deleting" value="提交" class="btn btn-info"></input>
        <a href="<%=basePath%>DeleteUser?id=${row.id}"><button type="button" <%=button %> class="btn btn-danger">删除</button></a>
      </div>
    </div>
    </form>
  </div>
</div>

<!-- 修改信息用的模态框 结束 -->

                                        </tr>
</c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr></tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <nav class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
                                        <ul class="pagination">
                                            <li class="page-item"><a class="page-link" 
                                            href="<%=basePath %>PageDown?begin=<%=session.getAttribute("begin")%>&end=<%=session.getAttribute("end")%>&page=user"
                                             aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                            <li class="page-item"><a class="page-link" 
                                            href="<%=basePath %>PageUp?begin=<%=session.getAttribute("begin")%>&end=<%=session.getAttribute("end")%>&page=user" aria-label="Next">
                                            <span aria-hidden="true">»</span></a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
<!-- Modal --> 
<div class="modal fade" id="addadmin" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  	<form action="<%=basePath%>AddAdmin" method="post" class="form-horizontal">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">新增入驻</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    	<div class="form-group col-sm-12 text-left">
    	<label for="name" class="control-label col-sm-4 col-sm-offset-1">用户名：</label><br/>
    	<input type="text" name="username"  class="form-control" ><br/>	
    	<label for="name" class="control-label col-sm-4 col-sm-offset-1">密&nbsp;码：</label><br/>
    	<input type="password" name="password"  class="form-control" ><br/>	
      	<label for="phone" class="control-label col-sm-4 col-sm-offset-1">手机号码：</label><br/>
      	<input type="text" class="form-control" name="phone"><br/>
      	<label for="age" class="control-label col-sm-4 col-sm-offset-1">年&nbsp;龄：</label><br/>
      	<input type="text" name="age" class="form-control"><br/>
      	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <input type="submit" id="deleting" value="提交" class="btn btn-info"></input>
      </div>
    </div>
    </form>
  </div>
</div>

<!-- 增加管理员的模态框 结束 -->                
                
<%@ include file="/home/module/indexdown.jsp"%>
