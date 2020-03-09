<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String iconbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>查看用户</title>
    <link rel="stylesheet" href="<%=basePath %>assets/article_assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lora">
    <link rel="stylesheet" href="<%=basePath %>assets/article_assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/article_assets/css/Article-Clean.css">
    <link rel="stylesheet" href="<%=basePath %>assets/article_assets/css/Dark-NavBar-1.css">
    <link rel="stylesheet" href="<%=basePath %>assets/article_assets/css/Dark-NavBar-2.css">
    <link rel="stylesheet" href="<%=basePath %>assets/article_assets/css/Dark-NavBar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="<%=basePath %>assets/article_assets/css/styles.css">
</head>
<body>
    <div data-aos="fade-up">
        <nav class="navbar navbar-light navbar-expand-md sticky-top navigation-clean-button" style="height:80px;background-color:#37434d;color:#ffffff;">
            <div class="container-fluid"><a class="navbar-brand" href="<%= request.getHeader("Referer")%>"><i class="fa fa-chevron-left"></i>&nbsp;返回</a>
                <div
                    class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item" role="presentation"></li>
                        <li class="nav-item" role="presentation"></li>
                        <li class="nav-item" role="presentation"></li>
                        <li class="nav-item" role="presentation"></li>
                        <li class="nav-item" role="presentation"></li>
                    </ul>
            </div>
    </div>
    </nav>
    </div>
    <br>
    <br>
        <div class="article-clean">
        <div class="container">
        <p><c:forEach items="${key_list}" var="usr" varStatus="idx">
            <div class="row">
            <div class="col-md-12 text-center">
    <img class="rounded-circle mr-2" width="90" height="90" src="<%=iconbasePath%>${usr.meicon}">
    <br>
    <br>
    <h4>${usr.meusername}</h4>
    <br>
    <p>${usr.meage}岁|${usr.mesex}生</p>
    <br>
    <h5>${usr.mejieshao}</h5>
    </div>
    </div>
    </c:forEach>
    <hr/>
    <div class="row">
    <div class="col-md-12">
    <%=session.getAttribute("userart") %>
    <br/>
<!-- 连接数据库进行全部主题的查找 -->
<sql:setDataSource var="article" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${article}" var="result">
SELECT * from article WHERE user = '<%=session.getAttribute("meusername") %>' order by id desc;
</sql:query>
<!-- 连接数据库进行全部主题的查找 结束 -->
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
    						<!-- <div class="col-sm-12 col-md-12">
        						<a href="#" class="thumbnail">
            					<img width="300px" src="${row.img}"
                 					alt="缩略图">
        						</a> 
    					</div> -->
						</div>
                        <div><a href="<%=basePath %>ArticleServlet?id=${row.id}"><button type="button" class="btn btn-default btn-sm" ><i class="fa fa-location-arrow"></i>&nbsp;查看</button></a>
                        <a href="<%=basePath%>GuestAddFavorite?title=${row.title}"><button type="button" class="btn btn-default btn-sm"><i class="fa fa-star"></i>&nbsp;收藏</button></a></div>
                        </div>
                    </div>
                    <div class="col-md-3">
                    </div>
                </div>
</c:forEach>
    </div>
    </div>
    </div>
    </div>
    <script src="<%=basePath %>assets/article_assets/js/jquery.min.js"></script>
    <script src="<%=basePath %>assets/article_assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basePath %>assets/article_assets/js/bs-init.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
    </body>
    </html>