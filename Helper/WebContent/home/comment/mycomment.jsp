<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/home/module/indexup.jsp"%>
<h4>我的评论：</h4>
<!-- 连接数据库进行全部帖子的查找 -->
	<sql:setDataSource var="myarticle" driver="com.mysql.jdbc.Driver" 
	url="jdbc:mysql://localhost:3306/helper"
	user="root"  password="root"/>
	<sql:query dataSource="${myarticle}" var="result">
	SELECT * from comment WHERE user="<%= session.getAttribute("username")%>";
	</sql:query>
<!-- 连接数据库进行全部帖子的查找 结束 -->
<c:forEach var="row" items="${result.rows}">
<div class="row">
	<div class="col-md-3">
	<p class="text-left">时间：${row.time}</p>
    </div>
    <div class="col-md-3">
    <p class="product text-left">主题：${row.atitle}</p>
    </div>
    <div class="col-md-3">
    <p class="product text-left">内容：${row.text}</p>
    </div>
    <div class="col-md-3">
	<a href="<%=basePath%>CommentServlet?title=${row.atitle}"><button type="button" class="btn btn-default btn-sm"><i class="fa fa-location-arrow"></i>&nbsp;查看</button></a>
	<button data-toggle="modal" data-target="#DelComment${row.id}" type="button" class="btn btn-default btn-sm"><i class="fa fa-times-circle"></i>&nbsp;删除</button>
	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="DelComment${row.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">删除应答</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="form-group">
      <p>确认删除这个应答？</p>
      </div>
      	<div class="modal-footer">
        <a href="<%=basePath%>GuestDeleteComment?id=${row.id}">确定</a>
        </div>
      </div>
      </div>
    </div>
<!-- 查看信息用的模态框 结束 -->
    </div>
</div>
<hr/>

</c:forEach>

<%@ include file="/home/module/indexdown.jsp"%>