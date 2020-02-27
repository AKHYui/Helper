package com.helper.UserFunc;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.support.UseJdbc;

@WebServlet("/GuestGetFastMod")
public class GuestGetFastMod extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		
		//������Ӧ����֮����
		response.setContentType("text/html;charset=UTF-8");	
				
		int id = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		ResultSet rs = null;
		String status = "";
		String y_status = "δ���ӵ�";
		
		try {
			rs = UseJdbc.checkfastmod(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				status = rs.getString("status");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status.equals(y_status)==true){
			int i = 0;
			try {
				i = UseJdbc.dofastmod(id, username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i == 1){
				System.out.print("�ӵ��ɹ�");
				response.getWriter().write("�ӵ��ɹ�,3��󷵻�ҳ��");
				response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
			}else if(i == 2){
				System.out.print("���ݿ����");
				response.getWriter().write("�ӵ�ʧ�ܣ����ݿ����3��󷵻�ҳ��");
				response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
			}else{
				System.out.print("δ֪����0");
				response.getWriter().write("δ֪����,3��󷵻�ҳ��");
				response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
			}
		}else{
			System.out.print("��ǰ�����ѱ��ӵ�");
			response.getWriter().write("��ǰ�����ѱ��ӵ�,3��󷵻�ҳ��");
			response.setHeader("refresh", "3;url="+basePath+"FastModServlet");
		}
	}

}
