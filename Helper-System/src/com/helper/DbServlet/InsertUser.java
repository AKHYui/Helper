package com.helper.DbServlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/InsertUser")
public class InsertUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = JdbcUtil.getDriver();
	static final String DB_URL = JdbcUtil.getUrl();
	static final String USER = JdbcUtil.getUser();
	static final String PASS = JdbcUtil.getPwd();
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String jieshao = request.getParameter("jieshao");
		int rs = 0;
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		Connection conn = null;
		Statement stmt = null;
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "INSERT INTO user (username,password,email,age,phone,sex,jieshao,birth,permit) VALUES ('"+username+"','"+password+"','"+email+"',"+age+",'"+phone+"','"+sex+"','"+jieshao+"','"+birth+"','�û�')";
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(sql);
			if (rs != 0) {
				try {
					response.getWriter().write("ע��ɹ�");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
