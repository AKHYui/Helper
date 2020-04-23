/*
 * 负责取消收藏
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

@WebServlet("/GuestDeleteFavorite")
public class GuestDeleteFavorite extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
				
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		int i = 0;
		try {
			i = UseJdbc.userfav(username, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1){
			int x = 0;
			try {
				x = UseJdbc.delfav(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(x != 0){
				System.out.print("取消成功");
				String site = new String(basePath+"MyFavoriteServlet");
	    		response.setStatus(response.SC_MOVED_TEMPORARILY);
	    		response.setHeader("Location", site);
			}else{
				System.out.print("数据库层出错");
			}
		}else if(i == 2){
			response.getWriter().write("您不能取消别人的收藏");
			System.out.print("您不能取消别人的收藏");
		}else{
			System.out.print("未知错误0");
		}
	}

}
