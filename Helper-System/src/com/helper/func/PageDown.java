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
		
		String username = (String) session.getAttribute("username");
		if(username!=null){
		
		ResultSet drs = null;
		
		try {
			drs = UseJdbc.usermun();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String all = "";
		try {
			while(drs.next()){
				all = drs.getString("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int all_i = Integer.parseInt(all);
		
		if(begin_i == 0){
			begin_i = 0;
			end_i = 9;
			System.out.println("√ªºı");
			session.setAttribute("begin", begin_i);
			session.setAttribute("end", end_i);
			request.getRequestDispatcher("home/user.jsp").forward(request, response);
		}else{
			begin_i = begin_i - 10;
			end_i = end_i - 10;
			System.out.println(begin_i);
			System.out.println(end_i);
			System.out.println("ºı¡À");
			session.setAttribute("begin", begin_i);
			session.setAttribute("end", end_i);
			request.getRequestDispatcher("home/user.jsp").forward(request, response);
		}
		}else{
			String site = new String(basePath + "IndexServlet");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}
		
	}
}
