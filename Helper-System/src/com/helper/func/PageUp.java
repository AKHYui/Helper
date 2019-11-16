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
@WebServlet("/PageUp")
public class PageUp extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		int begin_i = Integer.parseInt(request.getParameter("begin"));
		int end_i = Integer.parseInt(request.getParameter("end"));
		int all_i = Integer.parseInt(request.getParameter("all"));
		String uid = request.getParameter("uid");
		String base;
		int page_i = 1;
		if(all_i<end_i){
			String begin = String.valueOf(begin_i);
			String end = String.valueOf(end_i);
			page_i = end_i/5;
			String page = String.valueOf(page_i);
			session.setAttribute("begin", begin);
			session.setAttribute("end", end);
			session.setAttribute("page", page);
			base = PageDet.pagefun(uid);
			try {
				response.sendRedirect(basePath+base);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String site = new String(basePath + base);
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}else if(all_i==end_i+1){
			page_i = end_i/5;
			String page = String.valueOf(page_i);
			String begin = String.valueOf(begin_i);
			String end = String.valueOf(end_i);
			session.setAttribute("begin", begin);
			session.setAttribute("end", end);
			session.setAttribute("page", page);
			base = PageDet.pagefun(uid);
			try {
				response.sendRedirect(basePath+base);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String site = new String(basePath + base);
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}else{
		begin_i = begin_i+5;
		end_i = end_i+5;
		page_i = end_i/5;
		String page = String.valueOf(page_i);
		String begin = String.valueOf(begin_i);
		String end = String.valueOf(end_i);
		session.setAttribute("begin", begin);
		session.setAttribute("end", end);
		session.setAttribute("page", page);
		base = PageDet.pagefun(uid);
		try {
			response.sendRedirect(basePath+base);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String site = new String(basePath + base);
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}
	}


}
