<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/home/module/indexup.jsp"%>
<%=session.getAttribute("fastmodkey") %>
<c:forEach items="${key_list}" var="usr" varStatus="idx">
<div class="row">
<div class="col-md-3">
<p class="text-center">发布人：${usr.user} </p>
</div>
<div class="col-md-6">
<p class="text-center">求助：${usr.text} &nbsp;<i class="fa fa-warning"></i>状态：${usr.status}</p>
</div>
<div class="col-md-3">
<p class="text-center"><button data-toggle="modal" data-target="#myfastmod${usr.id}" type="button" class="btn btn-success"><i class="fa fa-coffee"></i>&nbsp;详 情</button></p>
</div>
</div>

	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="myfastmod${usr.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">求助详情</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="form-group">
      <div class="text-center">
      <p>求助人：${usr.user}</p>
      <p>求助：${usr.text}</p>
      <p>时间：${usr.time}</p>
      <p>联系方式：${usr.userphone}</p>
      <p>悬赏：${usr.money}元</p>
      <p>帮助者：${usr.helper}</p>
      <p>状态：${usr.status}</p>
      
    <!-- 连接数据库进行查找 -->
	<sql:setDataSource var="myfastmod" driver="com.mysql.jdbc.Driver" 
	url="jdbc:mysql://localhost:3306/helper"
	user="root"  password="root"/>
	<sql:query dataSource="${myfastmod}" var="result">
	SELECT * from user WHERE username = '${usr.helper}';
	</sql:query>
	<!-- 连接数据库进行查找 结束 -->
      <c:forEach var="row" items="${result.rows}">
      <p>帮助者联系方式：${row.phone}</p>
      </c:forEach>
      </div>
      </div>
      	<div class="modal-footer">

        </div>
      </div>
      </div>
    </div>
<!-- 查看信息用的模态框 结束 -->

</c:forEach>


<%@ include file="/home/module/indexdown.jsp"%>
