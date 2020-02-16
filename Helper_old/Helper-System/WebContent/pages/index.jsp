<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
	<link rel="stylesheet" href="../js/bootstrap.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
        <h2 class="text-center">管理员登陆</h2>
        <form action="<%=basePath %>pages/paneltop.jsp" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="username" class="control-label col-sm-2 col-sm-offset-1">用户名：</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="control-label col-sm-2 col-sm-offset-1">密 码：</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                </div>
            </div>
            <div class="form-group text-center">
                <input type="submit" value="登录" class="btn btn-primary">
                <input type="reset" value="重置" class="btn btn-primary">
            </div>
            
        </form>
    </div>
</body>
</html>