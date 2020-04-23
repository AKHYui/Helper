/*
 * 负责添加收藏
 */
package com.helper.UserFunc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.support.UseJdbc;

@WebServlet("/GuestAddFavorite")
public class GuestAddFavortie extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String atitle = request.getParameter("title");
		
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
				
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		int sefav = 0;
		try {
			sefav = UseJdbc.sefav(username, atitle);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(sefav == 1){
			System.out.print("已经收藏过了");
			response.getWriter().write("<h4 align = center>您已经收藏过了，稍后自动跳转</h4>");
			response.setHeader("refresh", "2;url="+basePath+"IndexServlet");
		}else{
			System.out.print("收藏可以被添加");
			int addfav = 0;
			try {
				addfav = UseJdbc.addfav(username, atitle);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(addfav == 1){
				System.out.print("收藏成功");
				response.getWriter().write("<h4 align = center>收藏成功，稍后自动跳转</h4>");
				response.setHeader("refresh", "2;url="+basePath+"IndexServlet");
				/*
				String site = new String(basePath+"IndexServlet");
	    		response.setStatus(response.SC_MOVED_TEMPORARILY);
	    		response.setHeader("Location", site);*/
			}else if(addfav == 2){
				System.out.print("数据库出错");
			}else{
				System.out.print("未知错误");
			}
		}
		
		
	}

}
