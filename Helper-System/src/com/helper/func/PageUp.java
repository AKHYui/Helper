package com.helper.func;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.helper.DbServlet.JdbcUtil;
@WebServlet("/PageUp")
public class PageUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = JdbcUtil.getDriver();
	static final String DB_URL = JdbcUtil.getUrl();
	static final String USER = JdbcUtil.getUser();
	static final String PASS = JdbcUtil.getPwd();
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		int begin_i = Integer.parseInt(request.getParameter("begin"));
		int end_i = Integer.parseInt(request.getParameter("end"));
		

		begin_i = begin_i+5;
		end_i = end_i+5;
		String begin = String.valueOf(begin_i);
		String end = String.valueOf(end_i);
		session.setAttribute("begin", begin);
		session.setAttribute("end", end);
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
