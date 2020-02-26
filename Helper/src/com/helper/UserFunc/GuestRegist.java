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
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
    			+ "/";
		
		int l_username = username.length();
		int l_password = password.length();
		int l_email = email.length();
		
		if(l_username<=6 || l_password<=5 || l_email<=5){
			response.getWriter().write("<p>您的注册不符合规定，用户名至少需要7个字符，"
					+ "密码至少要有6个字符，email至少要有6个字符</p>"
					+ "<p>3秒后将回到上个页面</p>");
			response.setHeader("refresh", "3;url="+basePath+"pages/regist.jsp");
		}else{
		
		int i = 100;
		try {
			i = UseJdbc.reg(username, password, email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1){
			response.getWriter().write("注册成功，3秒后进入登陆页面");
			response.setHeader("refresh", "3;url="+basePath+"IndexServlet");
		}else if(i == 0){
			response.getWriter().write("该用户名已被注册");
		}else if(i == 2){
			response.getWriter().write("注册出错");
		}
		}
	}
	//处理POST请求
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       doGet(request, response);
	}
}
