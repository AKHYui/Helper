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

@WebServlet("/MyArticleServlet")
public class MyArticleServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
			ResultSet myart = null;
			try {
				myart = UseJdbc.myart(username);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(myart);
			try {
				while(myart.next()){
					String id = myart.getString("id");
					String title = myart.getString("title");
					String text = myart.getString("text");
					String time = myart.getString("time");
					String user = myart.getString("user");
					String addr = myart.getString("addr");
					Map map = new HashMap();
					map.put("id", id);
					map.put("title", title);
					map.put("text", text);
					map.put("time", time);
					map.put("user", user);
					map.put("addr", addr);
					//用键值对存入到map集合中
					list.add(map);
					//放入集合
					for (Map map_1 :list){
						System.out.println(map_1);
					}
					System.out.println(list.toString());
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(list.toString().equals("[]") == false){
				String myartkey = "<h4>我发过的：</h4>";
				request.setAttribute("myart_key",list);
				session.setAttribute("myartkey", myartkey);
				request.getRequestDispatcher("home/article/myarticle.jsp").forward(request, response);
			}else{
				String myartkey = "<h4 class= text-center>您暂时还没有发过哦</h4>";
				request.setAttribute("myart_key",list);
				session.setAttribute("myartkey", myartkey);
				request.getRequestDispatcher("home/article/myarticle.jsp").forward(request, response);
			}
		}else{
			String site = new String(basePath + "IndexServlet");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}
	}

}
