package com.helper.UserFunc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.support.UseJdbc;

@WebServlet("/GuestComplete")
public class GuestComplete extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id"));
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
				
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		int i = 0;
		try {
			i = UseJdbc.cof(username, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1){
			int x = 0;
			try {
				x = UseJdbc.dorder(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(x == 1){
				System.out.print("�ɹ�");
				response.getWriter().write("����ɽӵ�,3��󷵻�ҳ��");
				response.setHeader("refresh", "3;url="+basePath+"MyOrderServlet");
			}else if(x == 2){
				System.out.print("���ݿ�2����");
				response.getWriter().write("��ɽӵ�ʧ�ܣ����ݿ����3��󷵻�ҳ��");
				response.setHeader("refresh", "3;url="+basePath+"MyOrderServlet");
			}else{
				System.out.print("δ֪����");
			}
		}else if(i == 2){
			System.out.print("���ݿ�1����");
			response.getWriter().write("��ɽӵ�ʧ�ܣ�����ӵ��������������Ѿ���ɴ˵���3��󷵻�ҳ��");
			response.setHeader("refresh", "3;url="+basePath+"MyOrderServlet");
		}else{
			System.out.print("δ֪����");
		}
	}

}
