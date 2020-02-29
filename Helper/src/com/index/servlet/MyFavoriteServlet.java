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

@WebServlet("/MyFavoriteServlet")
public class MyFavoriteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		
		if(username != null){
			ResultSet myfav = null;
			try {
				myfav = UseJdbc.myfav(username);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<Map> list =new ArrayList<Map>();//����list�������ڴ���map�ļ�ֵ�Լ���
			try {
				while(myfav.next()){
					String id = myfav.getString("id");
					String user = myfav.getString("user");
					String atitle = myfav.getString("atitle");
					//��ȡ��ѭ���������ݿ�ı����Ϣ
					Map map = new HashMap();
					map.put("id", id);
					map.put("user", user);
					map.put("atitle", atitle);
					//�ü�ֵ�Դ��뵽map������
					list.add(map);
					for (Map map_1 :list){
						System.out.println(map_1);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(list.toString());
			
			if(list.toString().equals("[]") == false){
				String myfavkey = "<h4>�ҵ��ղأ�</h4>";
				request.setAttribute("key_myfav",list);
				session.setAttribute("myfavkey", myfavkey);
				request.getRequestDispatcher("home/favorite/myfavorite.jsp").forward(request, response);
			}else{
				String myfavkey = "<h4 class= text-center>����ʱ��û���ղ�</h4>";
				request.setAttribute("key_myfav",list);
				session.setAttribute("myfavkey", myfavkey);
				request.getRequestDispatcher("home/favorite/myfavorite.jsp").forward(request, response);
			}
			
		}else{
			String site = new String(basePath + "IndexServlet");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}
	}
	}

