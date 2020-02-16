<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>注 册</title>
    <link rel="stylesheet" href="<%=basePath %>assets/regist_assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/regist_assets/css/Registration-Form-with-Photo.css">
    <link rel="stylesheet" href="<%=basePath %>assets/regist_assets/css/styles.css">
</head>

<body>
    <div class="register-photo">
        <div class="form-container">
            <div class="image-holder"></div>
            <form action="<%=basePath %>GuestRegist" method="post">
                <h2 class="text-center"><strong>注 册</strong></h2>
                <div class="form-group"><input class="form-control" type="username" name="username" placeholder="用户名"></div>
                <div class="form-group"><input class="form-control" type="password" name="password" placeholder="密 码"></div>
                <div class="form-group"><input class="form-control" type="email" name="email" placeholder="邮 箱"></div><button class="btn btn-primary btn-block" type="submit">注 册</button></form>
        </div>
    </div>
    <script src="<%=basePath %>assets/regist_assets/js/jquery.min.js"></script>
    <script src="<%=basePath %>assets/regist_assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>