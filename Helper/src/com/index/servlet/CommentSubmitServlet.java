package com.index.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.support.UseJdbc;

@WebServlet("/CommentSubmitServlet")
public class CommentSubmitServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String answer = request.getParameter("answer");
		String username = request.getParameter("username");  //应答者的用户名
		String atitle = request.getParameter("atitle");
		String sid = request.getParameter("sid");
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowtime = sf.format(date);
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(answer);
		if (answer !=""){
		int css = 0;
		try {
			css = UseJdbc.css(answer, username, atitle, nowtime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(css == 1){
			System.out.println("成功,转到"+basePath + "ArticleServlet?id=" + sid);
			String site = new String(basePath + "ArticleServlet?id=" + sid);
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}else{
			try {
				response.getWriter().write("失败");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}else{
		try {
			response.getWriter().write("应答不能为空");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

}
