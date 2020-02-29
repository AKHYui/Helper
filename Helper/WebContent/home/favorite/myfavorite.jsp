<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/home/module/indexup.jsp"%>

<%=session.getAttribute("myfavkey") %>

<c:forEach items="${key_myfav}" var="fav" varStatus="idx">
<div class="row">
	<div class="col-md-4">
	<p class="text-left">收藏ID：${fav.id}</p>
    </div>
    <div class="col-md-4">
    <p class="product text-left">主题：${fav.atitle}</p>
    </div>
    <div class="col-md-4">
	<a href="<%=basePath%>CommentServlet?title=${fav.atitle}&page=2"><button type="button" class="btn btn-default btn-sm"><i class="fa fa-location-arrow"></i>&nbsp;查看</button></a>
	<button data-toggle="modal" data-target="#Delfavorite${fav.id}" type="button" class="btn btn-default btn-sm"><i class="fa fa-times-circle"></i>&nbsp;取消收藏</button>
	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="Delfavorite${fav.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">取消收藏</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="form-group">
      <p>确认取消收藏？</p>
      </div>
      	<div class="modal-footer">
        <a href="<%=basePath%>GuestDeleteFavorite?id=${fav.id}">确定</a>
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
