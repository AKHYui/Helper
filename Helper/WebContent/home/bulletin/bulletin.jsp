<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/home/module/indexup.jsp"%>
<%=session.getAttribute("bullkey") %>
<c:forEach items="${bull_list}" var="bull" varStatus="idx">
<div class="row">
<div class="col-md-3">
<p class="text-center">时间：${bull.time} </p>
</div>
<div class="col-md-6">
<p class="text-center">公告内容：${bull.text}</p>
</div>
<div class="col-md-3">
<p class="text-center"><button data-toggle="modal" data-target="#bullkey${bull.id}" type="button" class="btn btn-success"><i class="fa fa-coffee"></i>&nbsp;详 情</button></p>
</div>
	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="bullkey${bull.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">公  告</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="form-group">
      <div class="text-center">
      <p>内容：${bull.text}</p>
      <p>时间：${bull.time}</p>
      </div>
      </div>
      	<div class="modal-footer">
        </div>
      </div>
      </div>
    </div>
<!-- 查看信息用的模态框 结束 -->
</div>
</c:forEach>
<%@ include file="/home/module/indexdown.jsp"%>
