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
%>
<%
	String title = session.getAttribute("title").toString();  
	String text = session.getAttribute("text").toString();  
	String time = session.getAttribute("time").toString();  
	String user = session.getAttribute("user").toString();  
	String addr = session.getAttribute("addr").toString();  
	String img = session.getAttribute("img").toString();  
	String username = session.getAttribute("username").toString();
	String sid = session.getAttribute("sid").toString();  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<body>
    <div data-aos="fade-up">
        <nav class="navbar navbar-light navbar-expand-md sticky-top navigation-clean-button" style="height:80px;background-color:#37434d;color:#ffffff;">
            <div class="container-fluid"><a class="navbar-brand" href="<%=basePath%>IndexServlet"><i class="fa fa-chevron-left"></i>&nbsp;返回</a>
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
                        <p class="text-center"><span class="by">by</span> <a href="<%=basePath%>UserMessage?user=<%=user%>"><%=user%></a><span class="date"><%=time %> </span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="fa fa-flag"></i>${addr}</span></p><img class="img-fluid" src="<%=basePath %>upload/image/<%=img %>"></div>
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
                    <form method="get" action="<%=basePath %>CommentSubmitServlet" class="form-horizontal">
                    <div class="form-group">
                    <c:forEach var="row_i" items="${result.rows}">
                    <div id="icon"><img width="25px" src="<%=basePath %>upload/icon/${row_i.icon}"></div>
                    </c:forEach>
        			<div id="text"><%=username %>&nbsp;<i class="fa fa-comment"></i></div><textarea class="form-control" name="answer" placeholder="赶快回应TA吧"></textarea>
        			<input type="hidden" class="form-control"  name="username" value="<%=username %>">
        			<input type="hidden" class="form-control"  name="atitle" value="<%=title %>">
        			<input type="hidden" class="form-control"  name="sid" value="<%=sid%>">
        			</div>
        			<div align="center">
        			<input class="btn btn-default" type="submit" value="提交">
        			<input type="reset" value="重置" class="btn btn-default">
        			</div>
        			</form>
        			<hr/>
        			<div>
        			<%=session.getAttribute("artkey") %>
        			<p><c:forEach items="${key_list}" var="usr" varStatus="idx">
        			<div>
        			<!-- 之前发现的一个问题
        			如果我用sql标签进行查找，无法做到同时查找用户表和回应表
        			所以我将回应表的sql标签去掉，将查找回应集放在了ArticleServlet里
        			ArticleServlet会送过来一个list形式的数据集
        			将这个数据集用c标签拆分成多个单个的数据并循环显示
        			最后从每个循环显示出的数据中取username
        			用sql标签对用户表进行查找，从而得出对应头像路径 -->
        			
        			<sql:setDataSource var="article" driver="com.mysql.jdbc.Driver" 
     				url="jdbc:mysql://localhost:3306/helper"
     				user="root"  password="root"/>
					<sql:query dataSource="${article}" var="result">
					SELECT icon from user WHERE username = "${usr.cuser}";
					</sql:query>
        			<c:forEach var="row" items="${result.rows}">
        			<div id="icon"><img width="30px" src="<%=basePath %>upload/icon/${row.icon}"></div>
        			</c:forEach>
        			<div id="text"><p class="font-weight-bold">${usr.cuser}</p></div>&nbsp;&nbsp;&nbsp;&nbsp;
        			<div id="time"><p>${usr.ctime}</p></div>
        			<div><p class="lead">${usr.ctext}</p></div>
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