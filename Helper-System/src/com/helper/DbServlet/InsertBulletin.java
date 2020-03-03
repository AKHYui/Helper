package com.helper.DbServlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.UseJdbc;

@WebServlet("/InsertBulletin")
public class InsertBulletin extends HttpServlet {  //添加公告
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = request.getParameter("text");
		Date date = new Date();
		SimpleDateFormat dy = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dy.format(date);
		int rs = 0;
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username_se = (String) session.getAttribute("username");
		if(username_se.equals("admin")==true || username_se.equals("registadmin")==true){
		rs = UseJdbc.inbu(text, time);
			if (rs != 0) {
				String info = "OK";
				session.setAttribute("info", info);
				String site = new String(basePath + "IndexServlet");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
				//conn.close();
			}
	}else{
		response.getWriter().write("添加失败，您当前没有此权限");
		response.setHeader("refresh", "3;url="+basePath+"IndexServlet");
	}
	}
}
