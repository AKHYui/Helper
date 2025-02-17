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
@WebServlet("/DeleteBullent")
public class DeleteBullent extends HttpServlet {  //删除公告
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");  //这里接收的ID是发布的公告的ID
		int i = Integer.parseInt(id);
		int rs = 0;
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username_se = (String) session.getAttribute("username");
		String ad = (String) session.getAttribute("ad");
		if(username_se.equals("admin")==true || ad.equals("registadmin")==true){
		try {
			rs = UseJdbc.debu(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if (rs != 0) {
				String info = "OK";
				session.setAttribute("info", info);
				String site = new String(basePath + "IndexServlet");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
				//conn.close();
			}
	}else{
		response.getWriter().write("您当前没有此权限");
		response.setHeader("refresh", "3;url="+basePath+"IndexServlet");
	}
	}
}
