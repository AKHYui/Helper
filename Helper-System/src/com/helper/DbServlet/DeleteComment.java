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

@WebServlet("/DeleteComment")
public class DeleteComment extends HttpServlet{  //这个是删除评论的功能
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");  //这里接收的ID是发布的评论的ID
		int i = Integer.parseInt(id);
		int rs = 0;
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username_se = (String) session.getAttribute("username");
		if(username_se.equals("admin")==true){
		rs = UseJdbc.deco(id);
			if (rs != 0) {
				String info = "OK";
				session.setAttribute("info", info);
				String site = new String(basePath + "CommentServlet");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
				//conn.close();
			}
	}else{
		response.getWriter().write("您当前没有此权限");
		response.setHeader("refresh", "3;url="+basePath+"CommentServlet");
	}
	}
}
