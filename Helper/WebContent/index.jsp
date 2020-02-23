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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>helper</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/Dark-NavBar-1.css">
    <link rel="stylesheet" href="assets/css/Dark-NavBar-2.css">
    <link rel="stylesheet" href="assets/css/Dark-NavBar.css">
    <link rel="stylesheet" href="assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="assets/css/Header-Blue.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="assets/css/Projects-Horizontal.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="assets/css/untitled.css">
</head>
<body>
    <div></div>
    <div>
        <div data-aos="fade-up" data-aos-duration="400" class="header-blue">
            <nav class="navbar navbar-light navbar-expand-md navigation-clean-search">
                <div class="container-fluid"><a class="navbar-brand" href="#">互帮圈</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse"
                        id="navcol-1">
                        <ul class="nav navbar-nav">
                            <li class="nav-item" role="presentation"><a class="nav-link" href="#">主页</a></li>
                            <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-th-list"></i>&nbsp;菜单</a>
                                <div class="dropdown-menu" role="menu"><a class="dropdown-item" role="presentation" href="<%=basePath %>IndexServlet"><i class="fa fa-home"></i>&nbsp;首页面</a>
                                <a class="dropdown-item" role="presentation" href="<%=basePath%>MyArticleServlet"><i class="fa fa-send"></i> &nbsp;我的发布</a>
                                <a class="dropdown-item" role="presentation" href="#"><i class="fa fa-list"></i> &nbsp;我的应答</a>
                                <a class="dropdown-item" role="presentation" href="#"><i class="fa fa-user"></i> &nbsp;用户设置</a>
                                </div>
                            </li>
                        </ul>
                        <form class="form-inline mr-auto" target="_self">
                           
                        </form><!-- <span class="navbar-text"> <a class="login" href="pages/login.jsp">登陆</a></span><a class="btn btn-light action-button" role="button" href="pages/register.jsp">注册</a></div> -->	
                </div>
            </nav>
            <div class="container hero">
                <div class="row">
                    <div class="col-12 col-lg-6 col-xl-5 offset-xl-1">
                        <h1>信院互帮圈</h1>
                        <p>Mutual aid platform, A Completely free online platform, You can submit your request and you can also help callers.</p><a href="IndexServlet">
                        <button class="btn btn-light btn-lg action-button" type="button">快速加入&nbsp;<i class="fa fa-angle-double-right"></i></button></a></div>
                    <div
                        class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-lg-block phone-holder">
                        <div class="iphone-mockup"></div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <div class="projects-horizontal">
        <div class="container">
            <div class="intro">
                <h2 class="text-center">我们的优势</h2>
            </div>
            <div class="row projects">
                <div class="col-sm-6 item" data-bs-hover-animate="bounce">
                    <div class="row">
                        <div class="col-md-12 col-lg-5"><a><img class="img-fluid" src="assets/img/desk.jpg"></a></div>
                        <div class="col">
                            <h3 class="name">简单的界面</h3>
                            <p class="description">简单的界面将会帮助您快速掌握互帮圈的使用方法，这将极大地节省您的时间。</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 item" data-bs-hover-animate="bounce">
                    <div class="row">
                        <div class="col-md-12 col-lg-5"><a><img class="img-fluid" src="assets/img/building.jpg"></a></div>
                        <div class="col">
                            <h3 class="name">快速的响应</h3>
                            <p class="description">您发布的求助将会被第一时间推送到平台上，帮助者会准确的找到您的求助。</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 item" data-bs-hover-animate="bounce">
                    <div class="row">
                        <div class="col-md-12 col-lg-5"><a><img class="img-fluid" src="assets/img/loft.jpg"></a></div>
                        <div class="col">
                            <h3 class="name">多平台支持</h3>
                            <p class="description">互帮圈的页面会支持大部分不同分辨率屏幕的设备，让您即使在没有电脑的情况下也能正常使用。</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 item" data-bs-hover-animate="bounce">
                    <div class="row">
                        <div class="col-md-12 col-lg-5"><img class="img-fluid" src="assets/img/minibus.jpeg"></div>
                        <div class="col">
                            <h3 class="name">永久的免费政策</h3>
                            <p class="description">平台不会向求助者和帮助者收取任何金钱，求助者与帮助者之间可以通过交流的方式以确定酬金。</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<!-- <div class="footer-basic">
        <footer>
            <div class="social"><a href="#"><i class="icon ion-social-instagram"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-facebook"></i></a></div>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="#">主页</a></li>
                <li class="list-inline-item"><a href="#">联系</a></li>
                <li class="list-inline-item"><a href="#">关于</a></li>
                <li class="list-inline-item"><a href="#">协议</a></li>
                <li class="list-inline-item"><a href="#"></a></li>
            </ul>
            <p class="copyright">Company Name © 2020</p>
        </footer>
    </div> -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-animation.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
</body>
</html>