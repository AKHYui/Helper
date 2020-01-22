package com.helper.Session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionDel")
public class SessionDel extends HttpServlet  {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
	String exit = request.getParameter("exit");
	int e = Integer.parseInt(exit);
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
			+ "/";
	// 设置响应内容类型
	response.setContentType("text/html;charset=UTF-8");
	HttpSession session = request.getSession();
	if (e == 1){
		session.invalidate();
		String site = new String(basePath);
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}
	}
}
