<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 连接数据库进行全部帖子的查找 -->
<sql:setDataSource var="article" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${article}" var="result">
SELECT * from article order by id desc;
</sql:query>
<!-- 连接数据库进行全部帖子的查找 结束 -->

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
                        <div><a href="/Helper/ArticleServlet?id=${row.id}"><button type="button" class="btn btn-default btn-sm" >查看</button></a>
                        <button type="button" class="btn btn-default btn-sm">收藏</button></div>
                        </div>
                    </div>
                    <div class="col-md-3">
                    </div>
                </div>
                <hr/>
</c:forEach>