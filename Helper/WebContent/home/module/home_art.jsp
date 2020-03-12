<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String mpath = request.getContextPath();
String mbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+mpath+"/";
%>
<sql:setDataSource var="article" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${article}" var="result">
SELECT * from article order by id desc;
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
                        <p ><small>${row.user}·${row.time}·${row.addr}</small></p>
                        <p class="product">${row.text}</p>
                        <div class="row">
						</div>
                        <div><a href="<%=mbasePath %>ArticleServlet?id=${row.id}"><button type="button" class="btn btn-default btn-sm" ><i class="fa fa-location-arrow"></i>&nbsp;查看</button></a>
                        <a href="<%=mbasePath%>GuestAddFavorite?title=${row.title}"><button type="button" class="btn btn-default btn-sm"><i class="fa fa-star"></i>&nbsp;收藏</button></a></div>
                        </div>
                    </div>
                    <div class="col-md-3">
                    </div>
                </div>
                <hr/>
</c:forEach>