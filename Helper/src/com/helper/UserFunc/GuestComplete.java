package com.helper.UserFunc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.support.UseJdbc;

@WebServlet("/GuestComplete")
public class GuestComplete extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id"));
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
				
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		int i = 0;
		try {
			i = UseJdbc.cof(username, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1){
			int x = 0;
			try {
				x = UseJdbc.dorder(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(x == 1){
				System.out.print("成功");
				response.getWriter().write("已完成接单,3秒后返回页面");
				response.setHeader("refresh", "3;url="+basePath+"MyOrderServlet");
			}else if(x == 2){
				System.out.print("数据库2错误");
				response.getWriter().write("完成接单失败，数据库错误，3秒后返回页面");
				response.setHeader("refresh", "3;url="+basePath+"MyOrderServlet");
			}else{
				System.out.print("未知错误");
			}
		}else if(i == 2){
			System.out.print("数据库1错误");
			response.getWriter().write("完成接单失败，这个接单不属于您或您已经完成此单，3秒后返回页面");
			response.setHeader("refresh", "3;url="+basePath+"MyOrderServlet");
		}else{
			System.out.print("未知错误");
		}
	}

}
