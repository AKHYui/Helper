<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>互帮圈管理面板</title>
    <link rel="stylesheet" href="<%=basePath %>assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=basePath %>assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/css/untitled-1.css">
    <link rel="stylesheet" href="<%=basePath %>assets/css/untitled.css">
</head>

<body id="page-top">
    <div id="wrapper">
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
            <div class="container-fluid d-flex flex-column p-0">
                <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                    <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-handshake"></i></div>
                    <div class="sidebar-brand-text mx-3"><span>互帮圈管理面板</span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="nav navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="<%=basePath%>IndexServlet"><i class="fas fa-tachometer-alt"></i><span>总览</span></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="<%=basePath%>UserServlet"><i class="fas fa-user"></i><span>用户管理</span></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="<%=basePath%>FastModServlet"><i class="fas fa-rocket"></i><span>求助管理</span></a></li>
                    <li class="nav-item" role="presentation"></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="<%=basePath%>ArticleServlet"><i class="fas fa-edit"></i><span>发布管理</span></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="<%=basePath%>CommentServlet"><i class="fas fa-comment"></i><span>应答管理</span></a></li>
                </ul>
                <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
            </div>
        </nav>
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                    <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                        <ul class="nav navbar-nav flex-nowrap ml-auto">
                            <li class="nav-item dropdown d-sm-none no-arrow"><a class="dropdown-toggle nav-link" aria-expanded="false" href="<%=basePath%>SessionDel?exit=1">退出登录</a>
                                
                            </li>
                            
                            
                            
                            <li class="nav-item dropdown no-arrow" role="presentation">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><span class="d-none d-lg-inline mr-2 text-gray-600 small">退出登录</span></a>
                                    <div class="dropdown-menu shadow dropdown-menu-right animated--grow-in"
                                        role="menu">
                                            <div class="dropdown-divider"></div><a class="dropdown-item" role="presentation" href="<%=basePath%>SessionDel?exit=1"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a></div>
                                </div>
                                
                            </li>
                        </ul>
                    </div>
                </nav>
                <div class="container-fluid">
                    <div class="d-sm-flex justify-content-between align-items-center mb-4">
                        <h3 class="text-dark mb-0">总览面板</h3>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-xl-3 mb-4">
                            <div class="card shadow border-left-primary py-2">
                                <div class="card-body">
                                    <div class="row align-items-center no-gutters">
                                        <div class="col mr-2">
<sql:setDataSource var="cou" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${cou}" var="res">
SELECT COUNT(*) total FROM user;
</sql:query>
<c:forEach var="row" items="${res.rows}">                                       
                                            <div class="text-uppercase text-primary font-weight-bold text-xs mb-1"><span>当前共有用户</span></div>
                                            <div class="text-dark font-weight-bold h5 mb-0"><span>${row.total}</span></div>
</c:forEach>
                                        </div>
                                        <div class="col-auto"><i class="fas fa-user fa-2x text-gray-300"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xl-3 mb-4">
                            <div class="card shadow border-left-success py-2">
                                <div class="card-body">
                                    <div class="row align-items-center no-gutters">
                                        <div class="col mr-2">
<sql:setDataSource var="art" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${art}" var="res_a">
SELECT COUNT(*) total FROM article;
</sql:query>
<c:forEach var="row_a" items="${res_a.rows}">
                                            <div class="text-uppercase text-success font-weight-bold text-xs mb-1"><span>当前共有发布</span></div>
                                            <div class="text-dark font-weight-bold h5 mb-0"><span>${row_a.total}</span></div>
</c:forEach>
                                        </div>
                                        <div class="col-auto"><i class="fas fa-comment fa-2x text-gray-300"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xl-3 mb-4">
                            <div class="card shadow border-left-info py-2">
                                <div class="card-body">
                                    <div class="row align-items-center no-gutters">
                                        <div class="col mr-2">
<sql:setDataSource var="fast" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${fast}" var="res_f">
SELECT COUNT(*) total FROM fastmod;
</sql:query>
<c:forEach var="row_f" items="${res_f.rows}">
                                            <div class="text-uppercase text-info font-weight-bold text-xs mb-1"><span><strong>当前共有求助</strong><br></span></div>
                                            <div class="text-dark font-weight-bold h5 mb-0"><span>${row_f.total}</span></div>
</c:forEach>
                                        </div>
                                        <div class="col-auto"><i class="fas fa-clipboard-list fa-2x text-gray-300"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xl-3 mb-4">
                            <div class="card shadow border-left-warning py-2">
                                <div class="card-body">
                                    <div class="row align-items-center no-gutters">
                                        <div class="col mr-2">
<sql:setDataSource var="com" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${com}" var="res_c">
SELECT COUNT(*) total FROM comment;
</sql:query>
<c:forEach var="row_c" items="${res_c.rows}">
                                            <div class="text-uppercase text-warning font-weight-bold text-xs mb-1"><span><strong>当前共有评论</strong><br></span></div>
                                            <div class="text-dark font-weight-bold h5 mb-0"><span>${row_c.total}</span></div>
</c:forEach>
                                        </div>
                                        <div class="col-auto"><i class="fas fa-comments fa-2x text-gray-300"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                    <div class="col">
                    <h4>定位设置</h4>
                    <button type="button" data-toggle="modal" data-target="#baiduapi" class="btn btn-primary btn-xs">修改key</button>
                    <a href="<%=basePath%>SwitchApi?nowstatus=<%=session.getAttribute("nowstatus")%>"><button type="button" class="btn btn-info btn-xs"><%=session.getAttribute("button") %></button></a>
                    
<!-- Modal --> 
<div class="modal fade" id="baiduapi" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  <form action="<%=basePath %>UpdateBaiduApi" method="get" class="form-horizontal">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">修改BaiduMap API Key</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    	<div class="form-group col-md-12">
    	<label for="text" class="control-label col-sm-4 col-sm-offset-1">Key：</label><br/>
    	<textarea name="key" cols="3" class="form-control"></textarea>
      	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <input type="submit" value="确认" class="btn btn-primary">
      </div>
    </div>
    </form>
  </div>
</div>

<!-- 修改信息用的模态框 结束 -->
                    
                    </div>
                    </div>
                    <div class="row">
                        <div class="col">
                        <h4>公告</h4>
                        <button data-toggle="modal" data-target="#bulletin" class="btn btn-info btn-xs">发送公告</button>
                        
                        <!-- Modal --> 
<div class="modal fade" id="bulletin" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  <form action="<%=basePath %>InsertBulletin" method="get" class="form-horizontal">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="titleModalLabel">发布公告</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    	<div class="form-group">
    	<label for="text" class="control-label col-sm-4 col-sm-offset-1">公告内容：</label><br/>
    	<textarea name="text" cols="3" class="form-control"></textarea>
      	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <input type="submit" value="确认" class="btn btn-primary">
      </div>
    </div>
    </form>
  </div>
</div>

<!-- 修改信息用的模态框 结束 -->
                        
<sql:setDataSource var="bull" driver="com.mysql.jdbc.Driver" 
     url="jdbc:mysql://localhost:3306/helper"
     user="root"  password="root"/>
<sql:query dataSource="${bull}" var="res_b">
SELECT * FROM bulletin;
</sql:query>                       
<c:forEach var="row_b" items="${res_b.rows}">
                            <div class="row">
                                <div class="col-lg-12 mb-4">
                                    <div class="card text-white bg-secondary shadow">
                                        <div class="card-body">
                                            <p class="m-0">${row_b.text}</p>
                                            <p class="text-white-50 small m-0">${row_b.time}</p>
                                            <p><button data-toggle="modal" data-target="#delbulletin${row_b.id}" class="btn btn-danger btn-xs">删除此公告</button></p>
                                            
<!-- Modal --> 
<div class="modal fade" id="delbulletin${row_b.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title text-danger" id="titleModalLabel">删除公告</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    	<div class="form-group">
    	<p class="text-danger text-center">确定删除？</p>
      	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <a href="<%=basePath%>DeleteBullent?id=${row_b.id}"><button type="button" class="btn btn-danger">删除</button></a>
      </div>
    </div>
  </div>
</div>

<!-- 修改信息用的模态框 结束 -->
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
</c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="bg-white sticky-footer">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
    <script src="<%=basePath %>assets/js/jquery.min.js"></script>
    <script src="<%=basePath %>assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="<%=basePath %>assets/js/theme.js"></script>
</body>

</html>