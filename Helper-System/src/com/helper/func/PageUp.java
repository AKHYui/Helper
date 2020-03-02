	package com.helper.func;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.UseJdbc;
import com.helper.DbServlet.JdbcUtil;
@WebServlet("/PageUp")
public class PageUp extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		int begin_i = Integer.parseInt(request.getParameter("begin"));
		int end_i = Integer.parseInt(request.getParameter("end"));
		String page = request.getParameter("page");
		
		String username = (String) session.getAttribute("username");
		if(username!=null){
			if(page.equals("user") == true){
				int userp = 0;
				try {
					userp = PageTools.userpageup(begin_i, end_i);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(userp == 1){
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/user.jsp").forward(request, response);
				}else if(userp == 2){
					begin_i = begin_i + 10;
					end_i = end_i + 10;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/user.jsp").forward(request, response);
				}
		
			}else if(page.equals("article") == true){
				int artup = 0;
				try {
					artup = PageTools.articlepageup(begin_i, end_i);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(artup == 1){
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/article.jsp").forward(request, response);
				}else if(artup == 2){
					begin_i = begin_i + 10;
					end_i = end_i + 10;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/article.jsp").forward(request, response);
				}
			}else if(page.equals("fastmod") == true){
				int fastup = 0;
				try {
					fastup = PageTools.fastpageup(begin_i, end_i);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(fastup == 1){
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/fastmod.jsp").forward(request, response);
				}else if(fastup == 2){
					begin_i = begin_i + 10;
					end_i = end_i + 10;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/fastmod.jsp").forward(request, response);
				}
			}else if(page.equals("comment") == true){
				int comup = 0;
				try {
					comup = PageTools.comup(begin_i, end_i);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(comup == 1){
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/comment.jsp").forward(request, response);
				}else if(comup == 2){
					begin_i = begin_i + 10;
					end_i = end_i + 10;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/comment.jsp").forward(request, response);
				}
			}
		}else{
			String site = new String(basePath + "IndexServlet");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}

	}


}
