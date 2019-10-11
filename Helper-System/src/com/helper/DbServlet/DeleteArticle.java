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

@WebServlet("/DeleteArticle")
public class DeleteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = JdbcUtil.getDriver();
	static final String DB_URL = JdbcUtil.getUrl();
	static final String USER = JdbcUtil.getUser();
	static final String PASS = JdbcUtil.getPwd();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");  //这里接收的ID是发布的文章的ID
		int i = Integer.parseInt(id);
		int rs = 0;
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		Connection conn = null;
		Statement stmt = null;
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "DELETE FROM article WHERE Id = '" + id + "'";
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(sql);
			if (rs != 0) {
				String info = "OK";
				session.setAttribute("info", info);
				String site = new String(basePath + "pages/AdminPanel.jsp");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
