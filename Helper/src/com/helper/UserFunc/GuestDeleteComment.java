/*
 * 负责用户删除应答
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

@WebServlet("/GuestDeleteComment")
public class GuestDeleteComment extends HttpServlet {
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
			i = UseJdbc.comuser(username, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1){
			int x = 0;
			try {
				x = UseJdbc.comdel(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(x == 1){
				String site = new String(basePath+"MyCommentServlet");
	    		response.setStatus(response.SC_MOVED_TEMPORARILY);
	    		response.setHeader("Location", site);
			}else{
				System.out.print("数据库处理出错");
			}
		}else if(i==2){
			response.getWriter().write("删除失败，不可以删除非本人的应答");
		}else{
			System.out.print("未知错误0");
		}
	}

}
