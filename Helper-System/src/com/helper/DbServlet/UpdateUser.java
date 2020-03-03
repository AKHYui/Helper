package com.helper.DbServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.UseJdbc;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {  //编辑用户
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int i = Integer.parseInt(id);
		int rs = 0;
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username_se = (String) session.getAttribute("username");
		if(username_se.equals("admin")==true || username_se.equals("registadmin")==true){
		rs = UseJdbc.upus(id, email, phone);
			if (rs != 0) {
				String info = "OK";
				session.setAttribute("info", info);
				String sessionusers = "users";
				String begin = "1";
				String end = "5";
				String page = "1";
				session.setAttribute("sessionusers", sessionusers);
				session.setAttribute("begin", begin);
				session.setAttribute("end", end);
				session.setAttribute("page", page);
				try {
					response.sendRedirect(basePath+"UserServlet");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String site = new String(basePath + "UserServlet");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
			}
	}else{
		response.getWriter().write("您当前没有此权限");
		response.setHeader("refresh", "3;url="+basePath+"UserServlet");
	}
	}
}
