package com.helper.func;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.helper.DbServlet.JdbcUtil;

@WebServlet("/PageDown")
public class PageDown extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String begin;
		String end;
		HttpSession session = request.getSession();
		
		int begin_i = Integer.parseInt(request.getParameter("begin"));
		int end_i = Integer.parseInt(request.getParameter("end"));
		if (begin_i == 0 && end_i == 4){
			begin = "0";
			end = "4";
			session.setAttribute("begin", begin);
			session.setAttribute("end", end);
			try {
				response.sendRedirect(basePath+"func/users.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String site = new String(basePath + "func/users.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}else{
			begin_i = begin_i-5;
			end_i = end_i-5;
			begin = String.valueOf(begin_i);
			end = String.valueOf(end_i);
			session.setAttribute("begin", begin);
			session.setAttribute("end", end);
			try {
				response.sendRedirect(basePath+"func/users.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String site = new String(basePath + "func/users.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}
	}
}
