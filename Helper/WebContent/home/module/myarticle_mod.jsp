<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String mpath = request.getContextPath();
String mbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+mpath+"/";
%>
	<sql:setDataSource var="myarticle" driver="com.mysql.jdbc.Driver" 
	url="jdbc:mysql://localhost:3306/helper"
	user="root"  password="root"/>
	<sql:query dataSource="${myarticle}" var="result">
	SELECT * from article WHERE user="<%= session.getAttribute("username")%>";
	</sql:query>
<c:forEach var="row" items="${result.rows}">
<div class="row">
                    <div class="col-md-3">
                    </div>
                    <div class="col-md-6">
                        <div>
                            <h3 class="text-center">${row.title}</h3>
                        </div>
                        <div class="text-center">
                        <p ><small><a class="text-muted" href="#">${row.user}</a>·${row.time}·${row.addr}</small></p>
                        <p class="product">${row.text}</p>
                        <div class="row">
    						<!-- <div class="col-sm-12 col-md-12">
        						<a href="#" class="thumbnail">
            					<img width="300px" src="${row.img}"
                 					alt="缩略图">
        						</a> 
    					</div> -->
						</div>
                        <div><a href="<%=mbasePath %>ArticleServlet?id=${row.id}"><button type="button" class="btn btn-default btn-sm" ><i class="fa fa-location-arrow"></i>&nbsp;查看</button></a>
                        <button type="button" data-toggle="modal" data-target="#DelAritcle${row.id}" class="btn btn-default btn-sm"><i class="fa fa-times-circle"></i>&nbsp;删除</button></div>
                        
                        <!--弹窗 -->
<!-- 查看信息用的模态框 -->
<!-- Modal -->
<div class="modal fade" id="DelAritcle${row.id}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">删除求助</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="form-group">
      <p>确认删除这个求助？</p>
      </div>
      	<div class="modal-footer">
        <a href="<%=mbasePath%>GuestDeleteArticle?id=${row.id}">确定</a>
        </div>
      </div>
      </div>
    </div>
  </div>
</div>
<!-- 查看信息用的模态框 结束 -->
                        
                        </div>
                    <div class="col-md-3">
                    </div>
                <hr/>
</c:forEach>
