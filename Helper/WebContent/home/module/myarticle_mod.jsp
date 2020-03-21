<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String mpath = request.getContextPath();
String mbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+mpath+"/";
%>
<c:forEach items="${myart_key}" var="myart" varStatus="idx">
<div class="row">
                    <div class="col-md-3">
                    </div>
                    <div class="col-md-6">
                        <div>
                            <h3 class="text-center">${myart.title}</h3>
                        </div>
                        <div class="text-center">
                        <p ><small><a class="text-muted" href="#">${myart.user}</a>·${myart.time}·${myart.addr}</small></p>
                        <p class="product">${myart.text}</p>
                        <div class="row">
						</div>
                        <div><a href="<%=mbasePath %>ArticleServlet?id=${myart.id}"><button type="button" class="btn btn-default btn-sm" ><i class="fa fa-location-arrow"></i>&nbsp;查看</button></a>
                        <button type="button" data-toggle="modal" data-target="#DelAritcle${myart.id}" class="btn btn-default btn-sm"><i class="fa fa-times-circle"></i>&nbsp;删除</button></div>
                        
                        <!--弹窗 -->
<!-- 模态框 -->	
<!-- Modal -->
<div class="modal fade" id="DelAritcle${myart.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">删除主题</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="form-group">
      <p>确认删除这个主题？</p>
      </div>
      	<div class="modal-footer">
        <a href="<%=mbasePath%>GuestDeleteArticle?id=${myart.id}">确定</a>
        </div>
      </div>
      </div>
    </div>
  </div>
</div>
<!-- 模态框 结束 -->
                        
                        </div>
                    <div class="col-md-3">
                    </div>
                <hr/>
</c:forEach>
