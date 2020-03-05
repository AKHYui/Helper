package com.helper.PageServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.UseJdbc;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";;
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String ad = (String) session.getAttribute("ad");
		String username = (String) session.getAttribute("username");
		
		if(username!=null){
			int apistatus = 0;
			try {
				apistatus = UseJdbc.checkstatus();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(apistatus == 1){
				String button = "已开启";
				String nowstatus = "on";
				session.setAttribute("button", button);
				session.setAttribute("nowstatus", nowstatus);
				request.getRequestDispatcher("home/index.jsp").forward(request, response);
			}else if(apistatus == 2){
				String button = "未开启";
				String nowstatus = "off";
				session.setAttribute("button", button);
				session.setAttribute("nowstatus", nowstatus);
				request.getRequestDispatcher("home/index.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
