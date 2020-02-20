<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String title = session.getAttribute("title").toString();  
	String text = session.getAttribute("text").toString();  
	String time = session.getAttribute("time").toString();  
	String user = session.getAttribute("user").toString();  
	String addr = session.getAttribute("addr").toString();  
	String img = session.getAttribute("img").toString();  
	String username = session.getAttribute("username").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title><%=title %></title>
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
</head>
<body>
    <div data-aos="fade-up">
        <nav class="navbar navbar-light navbar-expand-md sticky-top navigation-clean-button" style="height:80px;background-color:#37434d;color:#ffffff;">
            <div class="container-fluid"><a class="navbar-brand" href="<%=basePath%>IndexServlet"><i class="fa fa-chevron-left"></i>&nbsp;返回</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
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
    <div class="article-clean">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 col-xl-8 offset-lg-1 offset-xl-2">
                    <div class="intro">
                        <h1 class="text-center"><%=title %></h1>
                        <p class="text-center"><span class="by">by</span> <a href="#"><%=user%></a><span class="date"><%=time %> </span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="fa fa-flag"></i>${addr}</span></p><img class="img-fluid" src="<%=img %>"></div>
                    <div class="text">
                        <p><%=text %></p>
                    </div>
                    <hr/>
                    <div>
                    <sql:setDataSource var="icon" driver="com.mysql.jdbc.Driver" 
     				url="jdbc:mysql://localhost:3306/helper"
     				user="root"  password="root"/>
					<sql:query dataSource="${icon}" var="result">
					SELECT icon from user where username="<%=username%>" ;
					</sql:query>
                    <form method="post" action="#" class="form-horizontal">
                    <div class="form-group">
                    <c:forEach var="row_i" items="${result.rows}">
                    <div id="icon"><img width="25px" src="${row_i.icon}"></div>
                    </c:forEach>
        			<div id="text"><%=username %></div><textarea class="form-control" name="jieshao" placeholder="赶快回应TA吧"></textarea>
        			</div>
        			<div align="center">
        			<input class="btn btn-default" type="submit" value="提交">
        			<input type="reset" value="重置" class="btn btn-default">
        			</div>
        			</form>
        			<hr/>
        			<sql:setDataSource var="comment" driver="com.mysql.jdbc.Driver" 
     				url="jdbc:mysql://localhost:3306/helper"
     				user="root"  password="root"/>
					<sql:query dataSource="${comment}" var="result">
					SELECT * from comment WHERE atitle="<%=title %>";
					</sql:query>
        			<div>
        			<h4>应答:</h4>
        			<c:forEach var="row" items="${result.rows}">
        			<div>
        			<!-- 这里的头像部分暂时有无法完全实现 -->
        			<div id="icon"><img width="30px" src="/Helper-System/img/icon/${row.user}icon.jpg"></div>
        			<div id="text"><p class="font-weight-bold">${row.user}</p></div>&nbsp;&nbsp;&nbsp;&nbsp;
        			<div id="time"><p>${row.time}</p></div>
        			<div><p class="lead">${row.text}</p></div>
        			</div>
        			<hr/>
        			</c:forEach>
        			</div>
        			</div>
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