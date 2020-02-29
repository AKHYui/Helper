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

@WebServlet("/MyCommentServlet")
public class MyCommentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		
		if(username != null){
			ResultSet mycom = null;
			try {
				mycom = UseJdbc.mycom(username);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<Map> list =new ArrayList<Map>();//创建list集合用于存入map的键值对集合
			try {
				while(mycom.next()){
					String id = mycom.getString("id");
					String text = mycom.getString("text");
					String time = mycom.getString("time");
					String user = mycom.getString("user");
					String atitle = mycom.getString("atitle");
					//获取用循环接收数据库的表格信息
					Map map = new HashMap();
					map.put("id", id);
					map.put("text", text);
					map.put("time", time);
					map.put("user", user);
					map.put("atitle", atitle);
					//用键值对存入到map集合中
					list.add(map);
					for (Map map_1 :list){
						System.out.println(map_1);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(list.toString());
			if(list.toString().equals("[]") == false){
				String mycomkey = "<h4>我的应答：</h4>";
				request.setAttribute("key_mycom",list);
				session.setAttribute("mycomkey", mycomkey);
				request.getRequestDispatcher("home/comment/mycomment.jsp").forward(request, response);
			}else{
				String mycomkey = "<h4 class= text-center>您暂时还没有应答</h4>";
				request.setAttribute("key_mycom",list);
				session.setAttribute("mycomkey", mycomkey);
				request.getRequestDispatcher("home/comment/mycomment.jsp").forward(request, response);
			}
		}else{
			String site = new String(basePath + "IndexServlet");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}
	}

}
