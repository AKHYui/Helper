<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/home/module/indexup.jsp"%>

<div class="row">
	<div class="col-md-4">
	<p class="text-center">
	<button type="button" data-toggle="modal" data-target="#sendfastmod" class="btn btn-primary btn-lg btn-block"><i class="fa fa-paper-plane-o"></i>&nbsp;快速求助</button>
	</p>
	<%@ include file="/home/module/sendfastmod.jsp"%>
    </div>
    <div class="col-md-4">
	<p class="text-center">
	<a href="<%=basePath%>MyFastModServlet"><button type="button" class="btn btn-primary btn-lg btn-block"><i class="fa fa-envelope-o"></i>&nbsp;我的求助</button></a>
	</p>
    </div>
    <div class="col-md-4">
	<p class="text-center">
	<button type="button" class="btn btn-primary btn-lg btn-block"><i class="fa fa-rmb"></i>&nbsp;我的接单</button>
	</p>
    </div>
</div>
<hr/>

<!-- 连接数据库进行全部的查找 -->
	<sql:setDataSource var="fasthelp" driver="com.mysql.jdbc.Driver" 
	url="jdbc:mysql://localhost:3306/helper"
	user="root"  password="root"/>
	<sql:query dataSource="${fasthelp}" var="result">
	SELECT * from fastmod order by id desc;
	</sql:query>
<!-- 连接数据库进行全部的查找 结束 -->

<c:forEach var="row" items="${result.rows}">
<div class="row">
<div class="col-md-3">
<p class="text-center">发布人：${row.user} </p>
</div>
<div class="col-md-6">
<p class="text-center">求助：${row.text} &nbsp;<i class="fa fa-warning"></i>状态：${row.status}</p>
</div>
<div class="col-md-3">
<p class="text-center"><button data-toggle="modal" data-target="#fastmod${row.id}" type="button" class="btn btn-success"><i class="fa fa-coffee"></i>&nbsp;详 情</button></p>
</div>
</div>

	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="fastmod${row.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
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
      <p>求助人：${row.user}</p>
      <p>求助：${row.text}</p>
      <p>时间：${row.time}</p>
      <p>联系方式：${row.userphone}</p>
      <p>悬赏：${row.money}元</p>
      <p>帮助者：${row.helper}</p>
      <p>状态：${row.status}</p>
      </div>
      </div>
      	<div class="modal-footer">
        <a href="<%=basePath %>GuestGetFastMod?id=${row.id}"><button type="button" class="btn btn-success">接 单</button></a>
        </div>
      </div>
      </div>
    </div>
<!-- 查看信息用的模态框 结束 -->

</c:forEach>

<%@ include file="/home/module/indexdown.jsp"%>