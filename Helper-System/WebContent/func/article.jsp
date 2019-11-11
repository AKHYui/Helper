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
<title>Insert title here</title>
</head>
<%
		String sessionusers = session.getAttribute("sessionusers").toString();
		int begin_s = Integer.parseInt(session.getAttribute("begin").toString());
		int end_s = Integer.parseInt(session.getAttribute("end").toString());
		int page_s = Integer.parseInt(session.getAttribute("page").toString());
		String error = new String(basePath+"login-error.jsp");
		if(sessionusers != "article"){
			response.setStatus(response.SC_MOVED_TEMPORARILY);
	    	response.setHeader("Location", error); 
		}
	%>
<body>
article页面<%=begin_s %><%=end_s %><%=page_s %>
</body>
</html>