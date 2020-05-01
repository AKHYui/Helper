/*
 * �ҵ�����ҳ��
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

import api.baidu.ip.DoApi;
import api.baidu.ip.UseApi;

@WebServlet("/MyArticleServlet")
public class MyArticleServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		List<Map> list =new ArrayList<Map>();//����list�������ڴ���map�ļ�ֵ�Լ���
		if(username != null){
			String ak = "";
			try {
				ak = UseApi.getak();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String userip = request.getRemoteAddr();
			DoApi doapi = new DoApi();
			String nowaddr = doapi.doMapApi(ak,userip);
			session.setAttribute("nowaddress", nowaddr);
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
					//�ü�ֵ�Դ��뵽map������
					list.add(map);
					//���뼯��
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
				String myartkey = "<h4>�ҷ����ģ�</h4>";
				request.setAttribute("myart_key",list);
				session.setAttribute("myartkey", myartkey);
				request.getRequestDispatcher("home/article/myarticle.jsp").forward(request, response);
			}else{
				String myartkey = "<h4 class= text-center>����ʱ��û�з���Ŷ</h4>";
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
