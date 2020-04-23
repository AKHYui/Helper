/*
 * 公告页面
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

import com.jdbc.support.UseJdbc;

@WebServlet("/BulletinServlet")
public class BulletinServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		List<Map> list =new ArrayList<Map>();//创建list集合用于存入map的键值对集合
		if(username != null){
			ResultSet bull = null;
			try {
				bull = UseJdbc.bull();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(bull);
			try {
				while (bull.next()){
				String id = bull.getString("id");
				String text = bull.getString("text");
				String time = bull.getString("time");
				System.out.println(text);
				System.out.println(time);
				//获取用循环接收数据库的表格信息
				Map map = new HashMap();
				map.put("id", id);
				map.put("text", text);
				map.put("time", time);
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
			if(list.equals("[]") == false){
			String bullkey = "<h4>当前公告：</h4>";
			session.setAttribute("bullkey", bullkey);
			request.setAttribute("bull_list",list);
			request.getRequestDispatcher("home/bulletin/bulletin.jsp").forward(request, response);
			}else{
			String bullkey = "<h4 class= text-center>当前没有公告</h4>";
			session.setAttribute("bullkey", bullkey);
			request.setAttribute("bull_list",list);
			request.getRequestDispatcher("home/bulletin/bulletin.jsp").forward(request, response);
			}
		}else{
			String site = new String(basePath + "IndexServlet");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}
	}

}
