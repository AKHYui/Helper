package com.helper.DbServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.UseJdbc;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AdminLoginServlet(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		//设置响应内容之类型
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		String username = new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8");
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		HttpSession session = request.getSession();
		
		int al = 0;
		try {
			al = UseJdbc.adminlogin(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(al == 1){
			String ad = "admin";
			System.out.println("管理员登陆成功");
			session.setAttribute("username", username);
			session.setAttribute("ad", ad);
			String site = new String(basePath + "IndexServlet");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}else if(al == 0){
			int ral = 0;
			try {
				ral = UseJdbc.readminlogin(username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(ral == 1){
				String ad = "registadmin";
				System.out.println("入驻管理员登陆成功");
				session.setAttribute("username", username);
				session.setAttribute("ad", ad);
				String site = new String(basePath + "IndexServlet");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
			}else{
				System.out.println("登陆失败");
				String site = new String(basePath + "IndexServlet");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
			}
		}else{
			System.out.println("登陆失败");
			String site = new String(basePath + "IndexServlet");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}
	}
	
	//处理POST请求
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
