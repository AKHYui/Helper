<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
</head>
<body>
<%
	//以下全是字符转换
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	int i = Integer.parseInt(id);
	int rs = 0;
%>
<%
	if(i<2){  //如果ID<1就拒绝删除
		String warn;
		warn = "You can't Delet Admin!";
		session.setAttribute("warn", warn);
		response.sendRedirect("AdminPanel.jsp");
	}else{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/helper";  
		String root = "root";
		String pwd = "root";
		Connection conn = DriverManager.getConnection(url,root,pwd); //连接数据库
		if(conn != null){
			String sql = "DELETE FROM user WHERE Id = '"+id+"'";
			Statement stmt = conn.createStatement();  //将sql语句发送到数据库中
			rs=stmt.executeUpdate(sql);  //对数据库的查询操作，一般需要返回查询结果
			if(rs!=0){
				String info = "OK";
				session.setAttribute("info", info);
				response.sendRedirect("AdminPanel.jsp");
			} 
		}
	}
%>

<div class="text-center">
<h3>删除成功</h3>
</div>
</body>
</html>