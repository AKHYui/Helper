package com.helper.UserFunc;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.support.UseJdbc;

@WebServlet("/GuestSendFastMod")
public class GuestSendFastMod extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		//设置响应内容之类型
		response.setContentType("text/html;charset=UTF-8");	
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		String text = request.getParameter("text");
		String phone = request.getParameter("phone");
		String smoney = request.getParameter("money");
		
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowtime = sf.format(date);
		
		int l_text = text.length();
		int l_phone = phone.length();
		int l_smoney = smoney.length();
		if(l_text<=4 || l_phone<=10 || l_smoney<=0){
			response.getWriter().write("请按照平台规定发布快速求助");
			System.out.println("没有遵循规定");
			response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
		}else{
			int money = Integer.parseInt(smoney);
		int i = 0;
		try {
			i = UseJdbc.addfastmod(username, phone, money, text, nowtime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if(i == 1){
				System.out.println("发布成功");
				response.getWriter().write("发布成功");
				response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
			}else if(i == 2){
				System.out.println("发布失败，数据库错误");
				response.getWriter().write("发布失败，数据库错误");
				response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
			}else{
				System.out.println("未知错误");
				System.out.println("发布失败，未知错误");
				response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
			}
		}
	}

}
