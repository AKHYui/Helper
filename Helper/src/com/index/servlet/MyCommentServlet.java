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
		// ������Ӧ��������
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
			List<Map> list =new ArrayList<Map>();//����list�������ڴ���map�ļ�ֵ�Լ���
			try {
				while(mycom.next()){
					String id = mycom.getString("id");
					String text = mycom.getString("text");
					String time = mycom.getString("time");
					String user = mycom.getString("user");
					String atitle = mycom.getString("atitle");
					//��ȡ��ѭ���������ݿ�ı����Ϣ
					Map map = new HashMap();
					map.put("id", id);
					map.put("text", text);
					map.put("time", time);
					map.put("user", user);
					map.put("atitle", atitle);
					//�ü�ֵ�Դ��뵽map������
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
				String mycomkey = "<h4>�ҵ�Ӧ��</h4>";
				request.setAttribute("key_mycom",list);
				session.setAttribute("mycomkey", mycomkey);
				request.getRequestDispatcher("home/comment/mycomment.jsp").forward(request, response);
			}else{
				String mycomkey = "<h4 class= text-center>����ʱ��û��Ӧ��</h4>";
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
