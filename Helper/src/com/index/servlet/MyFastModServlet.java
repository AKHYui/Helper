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

@WebServlet("/MyFastModServlet")
public class MyFastModServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
		

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		ResultSet rs = null;
		try {
			rs = UseJdbc.mfms(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rs);
		List<Map> list =new ArrayList<Map>();//����list�������ڴ���map�ļ�ֵ�Լ���
		try {
			while(rs.next()){
				String user = rs.getString("user");
				String text = rs.getString("text");
				String helper = rs.getString("helper");
				String time = rs.getString("time");
				String status = rs.getString("status");
				String money = rs.getString("money");
				String userphone = rs.getString("userphone");
				//��ȡ��ѭ���������ݿ�ı����Ϣ
				Map map = new HashMap();
				map.put("user", user);
				map.put("text", text);
				map.put("helper", helper);
				map.put("time", time);
				map.put("status", status);
				map.put("money", money);
				map.put("userphone", userphone);
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
			String fastmodkey = "<h4>�ҵ�������</h4>";
			request.setAttribute("key_list",list);
			session.setAttribute("fastmodkey", fastmodkey);
		try {
			request.getRequestDispatcher("home/fastmod/myfastmod.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
			String fastmodkey = "<h4 class= text-center>����ʱû�з���</h4>";
			request.setAttribute("key_list",list);
			session.setAttribute("fastmodkey", fastmodkey);
			try {
				request.getRequestDispatcher("home/fastmod/myfastmod.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
