package com.helper.DbServlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.UseJdbc;

@WebServlet("/InsertBulletin")
public class InsertBulletin extends HttpServlet {  //��ӹ���
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = request.getParameter("text");
		Date date = new Date();
		SimpleDateFormat dy = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dy.format(date);
		int rs = 0;
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String username_se = (String) session.getAttribute("username");
		String ad = (String) session.getAttribute("ad");
		if(username_se.equals("admin")==true || ad.equals("registadmin")==true){
		try {
			rs = UseJdbc.inbu(text, time);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if (rs != 0) {
				String info = "OK";
				session.setAttribute("info", info);
				String site = new String(basePath + "IndexServlet");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
				//conn.close();
			}
	}else{
		response.getWriter().write("���ʧ�ܣ�����ǰû�д�Ȩ��");
		response.setHeader("refresh", "3;url="+basePath+"IndexServlet");
	}
	}
}
