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

@WebServlet("/PageDown")
public class PageDown extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		int begin_i = Integer.parseInt(request.getParameter("begin"));
		int end_i = Integer.parseInt(request.getParameter("end"));
		String page = request.getParameter("page");
		int down = 0;
		
		String username = (String) session.getAttribute("username");
		if(username!=null){
			if(page.equals("user") == true){
				down = PageTools.down(begin_i, end_i);
				if(down == 1){
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/user.jsp").forward(request, response);
				}else if(down == 2){
					begin_i = begin_i - 10;
					end_i = end_i - 10;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/user.jsp").forward(request, response);
				}else if(down == 3){
					begin_i = 0;
					end_i = 9;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/user.jsp").forward(request, response);
				}
			}else if(page.equals("fastmod") == true){
				down = PageTools.down(begin_i, end_i);
				if(down == 1){
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/fastmod.jsp").forward(request, response);
				}else if(down == 2){
					begin_i = begin_i - 10;
					end_i = end_i - 10;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/fastmod.jsp").forward(request, response);
				}else if(down == 3){
					begin_i = 0;
					end_i = 9;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/fastmod.jsp").forward(request, response);
				}
			}else if(page.equals("article") == true){
				down = PageTools.down(begin_i, end_i);
				if(down == 1){
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/article.jsp").forward(request, response);
				}else if(down == 2){
					begin_i = begin_i - 10;
					end_i = end_i - 10;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/article.jsp").forward(request, response);
				}else if(down == 3){
					begin_i = 0;
					end_i = 9;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/article.jsp").forward(request, response);
				}
			}else if(page.equals("comment") == true){
				down = PageTools.down(begin_i, end_i);
				if(down == 1){
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/comment.jsp").forward(request, response);
				}else if(down == 2){
					begin_i = begin_i - 10;
					end_i = end_i - 10;
					session.setAttribute("begin", begin_i);
					session.setAttribute("end", end_i);
					request.getRequestDispatcher("home/comment.jsp").forward(request, response);
				}else if(down == 3){
					begin_i = 0;
					end_i = 9;
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
