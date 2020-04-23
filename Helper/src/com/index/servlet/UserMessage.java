/*
 * 用户信息页面
 */
package com.index.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JInternalFrame;

import com.jdbc.support.UseJdbc;

@WebServlet("/UserMessage")
public class UserMessage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String user = request.getParameter("user");
		if(username != null){
		ResultSet rs = null;
		String meusername = "";
		String meage = "";
		String mesex = "";
		String mejieshao = "";
		String meicon = "";
		List<Map> list =new ArrayList<Map>();//创建list集合用于存入map的键值对集合
		try {
			rs = UseJdbc.getusermes(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				meusername = rs.getString("username");
				meage = rs.getString("age");
				mesex = rs.getString("sex");
				mejieshao = rs.getString("jieshao");
				meicon = rs.getString("icon");
				
				//获取用循环接收数据库的表格信息
				Map map = new HashMap();
				map.put("meusername", meusername);
				map.put("meage", meage);
				map.put("mesex", mesex);
				map.put("mejieshao", mejieshao);
				map.put("meicon", meicon);
				//用键值对存入到map集合中
				list.add(map);
				//放入集合
				for (Map map_1 :list){
					System.out.println(map_1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.toString());
		
		int checkart = 0;
		try {
			checkart = UseJdbc.checkart(meusername);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(checkart == 1){
			String userart = "<h4>TA发布的主题：</h4>";
			session.setAttribute("userart", userart);
		}else if(checkart == 2){
			String userart = "<h4 class= text-center>TA暂时还没有发布主题</h4>";
			session.setAttribute("userart", userart);
		}
		session.setAttribute("meusername", meusername);
		request.setAttribute("key_list",list);
		request.getRequestDispatcher("home/usersetting/usermessage.jsp").forward(request, response);
	}else{
		String site = new String(basePath + "IndexServlet");
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}
	}
}
