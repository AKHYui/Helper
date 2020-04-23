/*
 * ����ȡ���ղ�
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

@WebServlet("/GuestDeleteFavorite")
public class GuestDeleteFavorite extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
				
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		int i = 0;
		try {
			i = UseJdbc.userfav(username, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1){
			int x = 0;
			try {
				x = UseJdbc.delfav(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(x != 0){
				System.out.print("ȡ���ɹ�");
				String site = new String(basePath+"MyFavoriteServlet");
	    		response.setStatus(response.SC_MOVED_TEMPORARILY);
	    		response.setHeader("Location", site);
			}else{
				System.out.print("���ݿ�����");
			}
		}else if(i == 2){
			response.getWriter().write("������ȡ�����˵��ղ�");
			System.out.print("������ȡ�����˵��ղ�");
		}else{
			System.out.print("δ֪����0");
		}
	}

}
