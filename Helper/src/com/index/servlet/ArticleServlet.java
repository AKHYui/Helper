package com.index.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.support.UseJdbc;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		int id = Integer.parseInt(request.getParameter("id"));
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		String title = "";
		String text = "";
		String time = "";
		String user = "";
		String addr = "";
		ResultSet rs = null;
		try {
			rs = UseJdbc.rss(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				title = rs.getString("title");
				text = rs.getString("text");
				time = rs.getString("time");
				user = rs.getString("user");
				addr = rs.getString("addr");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(title+","+text+","+time+","+user+","+addr );
	}
}
