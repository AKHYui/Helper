<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- 连接数据库进行全部公告的查找 -->
<sql:setDataSource var="bulletin" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${bulletin}" var="result">
SELECT * from bulletin order by id;
</sql:query>
<!-- 连接数据库进行全部公告的查找 结束 -->
  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
	<table class="table"> <!-- 输出各用户信息的表格 -->
  		<thead>
    		<tr>
      		<th scope="col">ID</th>
      		<th scope="col">正文</th>
      		<th scope="col">时间</th>
      		<th scope="col">详细查看</th>
      		<th scope="col">删除操作</th>
    		</tr>
  		</thead><!-- 循环输出各个用户的信息 -->
		<c:forEach begin="1" end="5" var="row" items="${result.rows}">
		<tbody>
    		<tr>
      			<td><c:out value="${row.id}"/></td>
      			<td><c:out value="${row.text}"/></td>
      			<td><c:out value="${row.time}"/></td>
      			<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#Bulletin${row.id}" value="${row.id}">查看
</button></td>
      			<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#DeleteBulletin${row.id}" value="${row.id}">删除
</button></td>
	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="Bulletin${row.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
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
		内容：${row.text}<br/>
		发布时间：${row.time}<br/>
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
<div class="modal fade" id="DeleteBulletin${row.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  	<form action="../DeleteBullent" method="get" class="form-horizontal">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">删除信息</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <input type="hidden" class="form-control" id="recipient-name" name="id" value="${row.id}">
        是否要删除公告？这个操作是不可逆的！<br/>
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
<div class="text-right">
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#bulletin">添加公告</button>
</div>
<div class="modal fade" id="bulletin" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  	<form action="../InsertBulletin" method="get" class="form-horizontal"">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">修改用户</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    	<div class="form-group">
    	<input type="hidden" class="form-control" id="recipient-name" name="id" value="${row.id}">
    	<label for="text" class="control-label col-sm-4 col-sm-offset-1">公告正文：</label><br/>
    	<textarea type="text" class="form-control" name="text" ></textarea><br/>
      	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <input type="submit"  value="提交" class="btn btn-primary"></input>
      </div>
    </div>
    </form>
  </div>
</div>
</table>
</div>