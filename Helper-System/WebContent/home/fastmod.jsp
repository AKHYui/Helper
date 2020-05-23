<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/home/module/indexup.jsp"%>
<%
		int begin_s = Integer.parseInt(session.getAttribute("begin").toString());
		int end_s = Integer.parseInt(session.getAttribute("end").toString());
		String ad = session.getAttribute("ad").toString();
		String button = "";
		if(ad.equals("admin") == true){
			button = "";
		}else if(ad.equals("registadmin") == true){
			button = "disabled= disabled ";
		}else{
			button = "disabled= disabled ";
		}
%>
<sql:setDataSource var="fastmod" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${fastmod}" var="res_f">
SELECT * FROM fastmod order by id desc;
</sql:query>
 <div class="container-fluid">
                    <h3 class="text-dark mb-4">求助</h3>
                    <div class="card shadow">
                        <div class="card-header py-3">
                            <p class="text-primary m-0 font-weight-bold">求助管理</p>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                <table class="table dataTable my-0" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>求助人</th>
                                            <th>接单人</th>
                                            <th>时间</th>
                                            <th>状态</th>
                                            <th>求助人联系方式</th>
                                            <th>详情</th>
                                        </tr>
                                    </thead>
<c:forEach begin="<%= begin_s%>" end="<%= end_s%>" var="row" items="${res_f.rows}">  
                                    <tbody>

                                        <tr>
                                            <td>${row.user}</td>
                                            <td>${row.helper}</td>
                                            <td>${row.time}</td>
                                            <td>${row.status}</td>
                                            <td>${row.userphone}</td>
                                            <td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#fastmodinfo${row.id}">详情
</button></td>

<!-- Modal --> 
<div class="modal fade" id="fastmodinfo${row.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">求助信息</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    	<div class="form-group">
    	<p class="text-center">求助人：${row.user}</p>
    	<p class="text-center">求助时间：${row.time}</p>
    	<p class="text-center">求助内容：${row.text}</p>
    	<p class="text-center">求助状态：${row.status}</p>
    	<p class="text-center">帮助人：${row.helper}</p>
      	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <a href="<%=basePath%>DeleteFastmod?id=${row.id}"><button type="button" <%=button %> class="btn btn-danger">删除</button></a>
      </div>
    </div>
    </form>
  </div>
</div>

<!-- 修改信息用的模态框 结束 -->

                                        </tr>
                                    </tbody>
</c:forEach>
                                    <tfoot>
                                        <tr></tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <nav class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
                                        <ul class="pagination">
                                            <li class="page-item"><a class="page-link" 
                                            href="<%=basePath %>PageDown?begin=<%=session.getAttribute("begin")%>&end=<%=session.getAttribute("end")%>&page=fastmod"
                                             aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                            <li class="page-item"><a class="page-link" 
                                            href="<%=basePath %>PageUp?begin=<%=session.getAttribute("begin")%>&end=<%=session.getAttribute("end")%>&page=fastmod" aria-label="Next">
                                            <span aria-hidden="true">»</span></a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

<%@ include file="/home/module/indexdown.jsp"%>