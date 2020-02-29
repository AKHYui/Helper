<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>login</title>
    <link rel="stylesheet" href="<%=basePath %>assets/login_assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/login_assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/login_assets/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="<%=basePath %>assets/login_assets/css/styles.css">
</head>

<body>
    <div class="login-dark">
        <form action="<%=basePath %>GuestLogin" method="post">
            <h2 class="sr-only">Login Form</h2>
            <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
            <div class="form-group"><input class="form-control" type="text" name="username" placeholder="请输入用户名"></div>
            <div class="form-group"><input class="form-control" type="password" name="password" placeholder="请输入密码"></div>
            <div class="form-group"><button class="btn btn-primary btn-block" type="submit">登录</button></div>
    		<div class="form-group"><a class="forgot" href="<%=basePath %>pages/regist.jsp">没有账号？点此注册</a></div></form>
    </div>
    <script src="<%=basePath %>assets/js/jquery.min.js"></script>
    <script src="<%=basePath %>assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>