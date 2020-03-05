package com.helper.DbServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.UseJdbc;

@WebServlet("/SwitchApi")
public class SwitchApi extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";;
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String nowstatus = request.getParameter("nowstatus");
		if(nowstatus.equals("on") == true){
			int srs = 0;
			try {
				srs = UseJdbc.statuson();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(srs!=0){
				String site = new String(basePath + "IndexServlet");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
			}else{
				response.getWriter().write("数据库错误");
				response.setHeader("refresh", "3;url="+basePath+"IndexServlet");
			}
		}else if(nowstatus.equals("off") == true){
			int srs = 0;
			try {
				srs = UseJdbc.statusoff();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(srs!=0){
				String site = new String(basePath + "IndexServlet");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
			}else{
				response.getWriter().write("数据库错误");
				response.setHeader("refresh", "3;url="+basePath+"IndexServlet");
			}
		}
	}
}
