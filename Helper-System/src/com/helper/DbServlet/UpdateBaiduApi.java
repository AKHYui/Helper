package com.helper.DbServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.UseJdbc;

@WebServlet("/UpdateBaiduApi")
public class UpdateBaiduApi extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String key = request.getParameter("key");
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username_se = (String) session.getAttribute("username");
		if(username_se.equals("admin") == true){
			int rs = 0;
			try {
				rs = UseJdbc.updateapi(key);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(rs!=0){
				response.getWriter().write("�޸ĳɹ�");
				response.setHeader("refresh", "3;url="+basePath+"IndexServlet");
			}else{
				response.getWriter().write("�޸�ʧ��");
				response.setHeader("refresh", "3;url="+basePath+"IndexServlet");
			}
		}else{
			response.getWriter().write("�޸�ʧ��,ֻ��admin����Ա�����޸�Ȩ��");
			response.setHeader("refresh", "3;url="+basePath+"IndexServlet");
		}
	}
}
