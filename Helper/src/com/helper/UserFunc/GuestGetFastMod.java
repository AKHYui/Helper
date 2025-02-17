/*
 * 负责进行接单
 */
package com.helper.UserFunc;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.support.UseJdbc;

@WebServlet("/GuestGetFastMod")
public class GuestGetFastMod extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		
		//设置响应内容之类型
		response.setContentType("text/html;charset=UTF-8");	
				
		String user = request.getParameter("user");
		int id = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		ResultSet prs = null;
		try {
			prs = UseJdbc.hphone(username);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String phone = "";
		try {
			while(prs.next()){
				phone = prs.getString("phone");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i_phone = phone.length();
		if(i_phone < 11){
			System.out.print("接单用户手机号码不完善");
			response.getWriter().write("请先完善您的手机号码，您的手机号码必须是11位的手机号码"
					+ "，请前往用户设置中设定");
			response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
		}else{
		if(user.equals(username) == true){
			System.out.print("您无法接受自己的求助");
			response.getWriter().write("您无法接受自己的求助,3秒后返回页面");
			response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
		}else{
		
		ResultSet rs = null;
		String status = "";
		String y_status = "未被接单";
		
		try {
			rs = UseJdbc.checkfastmod(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				status = rs.getString("status");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status.equals(y_status)==true){
			int i = 0;
			try {
				i = UseJdbc.dofastmod(id, username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i == 1){
				System.out.print("接单成功");
				response.getWriter().write("接单成功,3秒后返回页面");
				response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
			}else if(i == 2){
				System.out.print("数据库出错");
				response.getWriter().write("接单失败，数据库出错，3秒后返回页面");
				response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
			}else{
				System.out.print("未知错误0");
				response.getWriter().write("未知错误,3秒后返回页面");
				response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
			}
		}else{
			System.out.print("当前求助已被接单");
			response.getWriter().write("当前求助已被接单,3秒后返回页面");
			response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
		}
		}
		}
	}

}
