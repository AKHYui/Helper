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

@WebServlet("/DeleteUser")

public class DeleteUser extends HttpServlet {
	/*private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = JdbcUtil.getDriver();
	static final String DB_URL = JdbcUtil.getUrl();
	static final String USER = JdbcUtil.getUser();
	static final String PASS = JdbcUtil.getPwd();*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int i = Integer.parseInt(id);
		int rs = 0;
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		//Connection conn = null;
		//Statement stmt = null;
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		if (i < 2) {
			String warn;
			warn = "You can't Delet Admin!";
			session.setAttribute("warn", warn);
			String site = new String(basePath + "pages/AdminPanel.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		} else {
			
				rs = UseJdbc.delu(id);
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
						response.sendRedirect(basePath+"func/users.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String site = new String(basePath + "func/users.jsp");
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location", site);
					
				}
			
				
		}
	}
}
