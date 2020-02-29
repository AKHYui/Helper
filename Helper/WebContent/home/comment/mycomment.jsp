<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/home/module/indexup.jsp"%>
<%=session.getAttribute("mycomkey") %>

<c:forEach items="${key_mycom}" var="com" varStatus="idx">
<div class="row">
	<div class="col-md-3">
	<p class="text-left">时间：${com.time}</p>
    </div>
    <div class="col-md-3">
    <p class="product text-left">主题：${com.atitle}</p>
    </div>
    <div class="col-md-3">
    <p class="product text-left">内容：${com.text}</p>
    </div>
    <div class="col-md-3">
	<a href="<%=basePath%>CommentServlet?title=${com.atitle}&page=1"><button type="button" class="btn btn-default btn-sm"><i class="fa fa-location-arrow"></i>&nbsp;查看</button></a>
	<button data-toggle="modal" data-target="#DelComment${com.id}" type="button" class="btn btn-default btn-sm"><i class="fa fa-times-circle"></i>&nbsp;删除</button>
	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="DelComment${com.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
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
        <a href="<%=basePath%>GuestDeleteComment?id=${com.id}">确定</a>
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