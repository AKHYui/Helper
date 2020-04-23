/*
 * 通过应答进入主题
 */
package com.index.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.support.UseJdbc;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		
		String id = "";
		String atitle = request.getParameter("title");
		String page = request.getParameter("page");
		
		System.out.println(atitle);
		
		ResultSet rs = null;
		try {
			rs = UseJdbc.tid(atitle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				id = rs.getString("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(id);
		if(id.toString().equals("") == false){
		String site = new String(basePath+"ArticleServlet?id="+id);
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
		}else{
			if(page.equals("1") == true){
			response.getWriter().write("<h4 align = center>404 Not Found，当前求助主题已经不存在了</h4>");
			response.setHeader("refresh", "3;url="+basePath+"MyCommentServlet");
			}else if(page.equals("2") == true){
			response.getWriter().write("<h4 align = center>404 Not Found，当前求助主题已经不存在了</h4>");
			response.setHeader("refresh", "3;url="+basePath+"MyFavoriteServlet");	
			}else{
				response.getWriter().write("<h4 align = center>404 Not Found，当前求助主题已经不存在了</h4>");
				response.setHeader("refresh", "3;url="+basePath+"IndexServlet");
			}
		}
	}

}
