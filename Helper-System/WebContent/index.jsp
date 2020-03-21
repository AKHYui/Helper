<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
		if(session.getAttribute("username")!=null){
			System.out.println("已登录过");
			String site = new String(basePath + "IndexServlet");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}else{
			System.out.println("需要登录");
		}
		
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>管理系统登录</title>
    <link rel="stylesheet" href="<%=basePath %>assets/login_assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/login_assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/login_assets/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="<%=basePath %>assets/login_assets/css/styles.css">
</head>

<body>
    <div class="login-dark">
        <form method="post" action="<%=basePath %>AdminLoginServlet">
            <h2 class="sr-only">管理员登录</h2>
            <div class="illustration"><i class="icon-login"></i></div>
            <div class="form-group"><input class="form-control" type="text" name="username" placeholder="用户名"></div>
            <div class="form-group"><input class="form-control" type="password" name="password" placeholder="密码"></div>
            <div class="form-group"><button class="btn btn-primary btn-block" type="submit">登&nbsp; 录</button></div>
            <a class="forgot" data-target="#cantlogin" href="#">无法登录请向管理员索取入驻资格</a></form>
            
    </div>
    <script src="<%=basePath %>assets/login_assets/js/jquery.min.js"></script>
    <script src="<%=basePath %>assets/login_assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>