<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <!-- 连接数据库进行全部帖子的查找 -->
<sql:setDataSource var="article" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${article}" var="result">
SELECT * from article order by id desc;
</sql:query>
<!-- 连接数据库进行全部帖子的查找 结束 -->
  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
	<table class="table"> <!-- 输出各用户信息的表格 -->
  		<thead>
    		<tr>
      		<th scope="col">ID</th>
      		<th scope="col">标题</th>
      		<!--  <th scope="col">正文</th>-->
      		<th scope="col">时间</th>
      		<th scope="col">发布人</th>
      		<!-- <th scope="col">发布地点</th> -->
      		<th scope="col">详细查看</th>
      		<th scope="col">删除操作</th>
    		</tr>
  		</thead><!-- 循环输出各个用户的信息 -->
		<c:forEach begin="1" end="5" var="row" items="${result.rows}">
		<tbody>
    		<tr>
      			<td><c:out value="${row.id}"/></td>
      			<td><c:out value="${row.title}"/></td>
      			<!-- <td><c:out value="${row.text}"/></td> -->
      			<td><c:out value="${row.time}"/></td>
      			<td><c:out value="${row.user}"/></td>
      			<!--  <td><c:out value="${row.addr}"/></td>-->
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
		</c:forEach>
</table>
</div>