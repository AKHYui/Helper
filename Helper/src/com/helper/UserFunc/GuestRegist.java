package com.helper.UserFunc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.support.UseJdbc;

@WebServlet("/GuestRegist")
public class GuestRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public GuestRegist(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		//设置响应内容之类型
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		String username = new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8");
		String email = new String(request.getParameter("email").getBytes("ISO8859-1"),"UTF-8");
		//response.getWriter().write(username+"+"+password+"+"+repassword+"+"+email);
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
		int i = 100;
		try {
			i = UseJdbc.reg(username, password, email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1){
			response.getWriter().write("注册成功，3秒后进入登陆页面");
			response.setHeader("refresh", "3;url="+basePath+"Helper/IndexServlet");
		}else if(i == 0){
			response.getWriter().write("该用户名已被注册");
		}else if(i == 2){
			response.getWriter().write("注册出错");
		}
	}
	//处理POST请求
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       doGet(request, response);
	}
}
