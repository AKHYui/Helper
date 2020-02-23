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
    <title>提交检查</title>
    <link rel="stylesheet" href="<%=basePath %>assets/send_assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath %>assets/send_assets/css/Contact-Form-Clean.css">
    <link rel="stylesheet" href="<%=basePath %>assets/send_assets/css/styles.css">
</head>

<body>
    <div class="contact-clean">
        <form action="<%=basePath %>UploadServlet" enctype="multipart/form-data" method="post">
            <h2 class="text-center">提 交</h2>
            <div class="form-group"><input class="form-control"  readonly="readonly" type="text" value="<%= session.getAttribute("arttitle")%>"></div>
            <div class="form-group"><input class="form-control"  readonly="readonly" type="text" value="<%= session.getAttribute("artaddr")%>"></div>
            <div class="form-group"><textarea class="form-control" readonly="readonly" rows="14"><%= session.getAttribute("arttext")%></textarea></div>
            <label for="img" class="control-label col-sm-4 col-sm-offset-1">图片(只支持jpg,png,gif类型图片):</label>
      	<div class="form-group">
    		<label class="sr-only" for="inputfile">文件输入</label>
    		<input type="file" name="uploadFile">
 		 </div>
            <div class="form-group"><button class="btn btn-primary" type="submit">确定 </button></div>
        </form>
    </div>
    <script src="<%=basePath %>assets/send_assets/js/jquery.min.js"></script>
    <script src="<%=basePath %>assets/send_assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>