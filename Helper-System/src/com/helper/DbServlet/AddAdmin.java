package com.helper.DbServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.UseJdbc;

@WebServlet("/AddAdmin")
public class AddAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AddAdmin(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		//������Ӧ����֮����
		response.setContentType("text/html;charset=UTF-8");	
		String username = new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8");
		String phone = new String(request.getParameter("phone").getBytes("ISO8859-1"),"UTF-8");
		String age_s = new String(request.getParameter("age").getBytes("ISO8859-1"),"UTF-8");
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		int age = Integer.parseInt(age_s);
		HttpSession session = request.getSession();
		String username_se = (String) session.getAttribute("username");
		
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		String time = sf.format(date);
		int nowtime = Integer.parseInt(time);
		int birth = nowtime - age;
		
		int username_l = username.length();
		int password_l = password.length();
		int phone_l = phone.length();
		
		if(username_se.equals("admin") == true){
		
		if(username_l<7 || password_l<6 || phone_l<11 || age<0){
			response.getWriter().write("<p>����ע�᲻���Ϲ涨���û���������Ҫ7���ַ���"
					+ "��������Ҫ��6���ַ����ֻ���������11���ַ����������Ϊ����</p>"
					+ "<p>3��󽫻ص��ϸ�ҳ��</p>");
			response.setHeader("refresh", "3;url="+basePath+"UserServlet");
		}else{
		
		int check = 0;
		try {
			check = UseJdbc.checkuser(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check == 1){
			response.getWriter().write("���ʧ�ܣ�����û����ѱ�ע��");
			response.setHeader("refresh", "3;url="+basePath+"UserServlet");
		}else if(check == 2){
			int add = 0;
			try {
				add = UseJdbc.addadmin(username, password, phone, age, birth);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(add == 1){
				response.getWriter().write("��ӳɹ�");
				response.setHeader("refresh", "3;url="+basePath+"UserServlet");
			}else if(add == 2){
				response.getWriter().write("���ʧ�ܣ����ݿ����");
				response.setHeader("refresh", "3;url="+basePath+"UserServlet");
			}else{
				response.getWriter().write("���ʧ�ܣ�δ֪����");
				response.setHeader("refresh", "3;url="+basePath+"UserServlet");
			}
		}else{
			response.getWriter().write("���ʧ�ܣ�δ֪����");
			response.setHeader("refresh", "3;url="+basePath+"UserServlet");
		}
		}
		}else{
			response.getWriter().write("���ʧ�ܣ�����ǰû�д�Ȩ��");
			response.setHeader("refresh", "3;url="+basePath+"UserServlet");
		}
	}
	
	//����POST����
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request, response);
	}

}
