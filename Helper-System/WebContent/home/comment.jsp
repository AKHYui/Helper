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

<sql:setDataSource var="comment" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${comment}" var="res_c">
SELECT * FROM comment order by id desc;
</sql:query>
 <div class="container-fluid">
                    <h3 class="text-dark mb-4">评论</h3>
                    <div class="card shadow">
                        <div class="card-header py-3">
                            <p class="text-primary m-0 font-weight-bold">评论管理</p>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                <table class="table dataTable my-0" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>评论主题</th>
                                            <th>评论人</th>
                                            <th>评论内容</th>
                                            <th>时间</th>
                                            <th>删除</th>
                                        </tr>
                                    </thead>
<c:forEach begin="<%= begin_s%>" end="<%= end_s%>" var="row" items="${res_c.rows}">  
                                    <tbody>

                                        <tr>
                                            <td>${row.id}</td>
                                            <td>${row.atitle}</td>
                                            <td>${row.user}</td>
                                            <td>${row.text}</td>
                                            <td>${row.time}</td>
                                            <td><a href="<%=basePath%>DeleteComment?id=${row.id}"><button type="button" <%=button %> class="btn btn-info">删除
</button></a></td>

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
                                            href="<%=basePath %>PageDown?begin=<%=session.getAttribute("begin")%>&end=<%=session.getAttribute("end")%>&page=comment"
                                             aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                            <li class="page-item"><a class="page-link" 
                                            href="<%=basePath %>PageUp?begin=<%=session.getAttribute("begin")%>&end=<%=session.getAttribute("end")%>&page=comment" aria-label="Next">
                                            <span aria-hidden="true">»</span></a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
<%@ include file="/home/module/indexdown.jsp"%>