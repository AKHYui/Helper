<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/home/module/indexup.jsp"%>

<%=session.getAttribute("orderkey") %>
<c:forEach items="${key_order}" var="order" varStatus="idx">
<div class="row">
<div class="col-md-3">
<p class="text-center">发布人：${order.user} </p>
</div>
<div class="col-md-6">
<p class="text-center">求助：${order.text} &nbsp;<i class="fa fa-warning"></i>状态：${order.status}</p>
</div>
<div class="col-md-3">
<p class="text-center"><button data-toggle="modal" data-target="#orderfastmod${order.id}" type="button" class="btn btn-success"><i class="fa fa-coffee"></i>&nbsp;详 情</button></p>
</div>
</div>

	<!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="orderfastmod${order.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
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
      <p>求助人：${order.user}</p>
      <p>求助：${order.text}</p>
      <p>时间：${order.time}</p>
      <p>联系方式：${order.userphone}</p>
      <p>悬赏：${order.money}元</p>
      <p>帮助者：${order.helper}</p>
      <p>状态：${order.status}</p>
      <p><i class="fa fa-lightbulb-o"></i> 请尽快与求助者取得联系并完成求助者的请求，完成后点击我已完成按钮</p>
      </div>
      </div>
      	<div class="modal-footer">
        <a href="<%=basePath %>GuestComplete?id=${order.id}"><button type="button" class="btn btn-success">我已完成</button></a>
        </div>
      </div>
      </div>
    </div>
<!-- 查看信息用的模态框 结束 -->

</c:forEach>

<%@ include file="/home/module/indexdown.jsp"%>
