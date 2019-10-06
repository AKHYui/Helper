<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	request.setCharacterEncoding("utf-8");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	%>
<%		
		boolean i;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            i = true;
        } catch (Exception e) {
            i = false;
        }
        if(i=true){  //判定数据库驱动是否完善
        	
        }else{
        	out.print("数据库驱动错误");
        }
%>
<%
	Class.forName("com.mysql.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/helper";  
	String root = "root";
	String pwd = "root";
	Connection conn = DriverManager.getConnection(url,root,pwd);
	if(conn != null){
		String sql = "select * from user where username='"+username+"' and password='"+ password + "' and permit='管理员'";
		Statement stmt = conn.createStatement();  //将sql语句发送到数据库中
		ResultSet rs=stmt.executeQuery(sql);  //对数据库的查询操作，一般需要返回查询结果
		boolean a;
		if(rs.next()){  
			a = true;
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			response.sendRedirect("AdminPanel.jsp");
        }else{  
        	a = false;
        	String site = new String(basePath+"login-error.jsp");  //密码错误，重定向到指定页面
        	response.setStatus(response.SC_MOVED_TEMPORARILY);
        	response.setHeader("Location", site); 
        }             
	}
	conn.close();  
%>
<%
            Date date = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("HH");
			String hour = ft.format(date);
			int hour_i = Integer.parseInt(hour);
			String info = "";
			if(hour_i<12){
				info = "早上好！";
			}else if(hour_i>12 && hour_i<14){
				info = "中午好！";
			}else if(hour_i>14 && hour_i<18){
				info = "下午好！";
			}else{
				info = "晚上好！";
			}
        %>
<div>
	<h2><%=info %><%=username %></h2>
</div>

</body>
</html>