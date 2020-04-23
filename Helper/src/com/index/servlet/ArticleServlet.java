/*
 * ����ҳ��
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

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String sid = request.getParameter("id");
		int id = Integer.parseInt(request.getParameter("id"));
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String title = "";
		String text = "";
		String time = "";
		String user = "";
		String addr = "";
		String img = "";
		ResultSet rs = null;
		ResultSet rst = null;
		try {
			rs = UseJdbc.rss(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				title = rs.getString("title");
				text = rs.getString("text");
				time = rs.getString("time");
				user = rs.getString("user");
				addr = rs.getString("addr");
				img = rs.getString("img");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.getWriter().write(title+","+text+","+time+","+user+","+addr );
		session.setAttribute("title", title);
		session.setAttribute("text", text);
		session.setAttribute("time", time);
		session.setAttribute("user", user);
		session.setAttribute("addr", addr);
		session.setAttribute("img", img);
		session.setAttribute("sid", sid);
		
		List<Map> list =new ArrayList<Map>();//����list�������ڴ���map�ļ�ֵ�Լ���
		String c_title = title.toString();
		try {
			rst = UseJdbc.rst(c_title);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		System.out.println(rst);
		try {
			while (rst.next()){
				String ctext = rst.getString("text");
				String cuser = rst.getString("user");
				String ctime = rst.getString("time");
				System.out.println(ctext);
				System.out.println(cuser);
				System.out.println(ctime);
				//��ȡ��ѭ���������ݿ�ı����Ϣ
				Map map = new HashMap();
				map.put("ctext", ctext);
				map.put("cuser", cuser);
				map.put("ctime", ctime);
				//�ü�ֵ�Դ��뵽map������
				list.add(map);
				//���뼯��
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
			String artkey = "<h4>Ӧ��</h4>";
			session.setAttribute("artkey", artkey);
			request.setAttribute("key_list",list);
			request.getRequestDispatcher("home/article/article.jsp").forward(request, response);
		}else{
			String artkey = "<h4 class= text-center>��ǰ��û��Ӧ��Ŷ</h4>";
			session.setAttribute("artkey", artkey);
			request.setAttribute("key_list",list);
			request.getRequestDispatcher("home/article/article.jsp").forward(request, response);
		}
	}
}
