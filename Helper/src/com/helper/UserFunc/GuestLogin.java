/*
 * 负责用户登录
 */
package com.helper.UserFunc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.support.UseJdbc;

@WebServlet("/GuestLogin")

public class GuestLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public GuestLogin(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		//设置响应内容之类型
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		String username = new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8");
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
		String se = "1";
		HttpSession session = request.getSession();
		
		int i = 0;
		try {
			i = UseJdbc.GuestLogin(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1){
			response.getWriter().write("登录成功,3秒后进入页面");
			response.setHeader("refresh", "3;url="+basePath+"Helper/");
			session.setAttribute("username", username);
			session.setAttribute("se", se);
		}else{
			response.getWriter().write("登录失败,3秒后回到登录");
			response.setHeader("refresh", "3;url="+basePath+"Helper/IndexServlet");
			session.setAttribute("0", username);
		}
		
	}
	//处理POST请求
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
