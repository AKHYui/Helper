package com.helper.UserFunc;

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

import com.jdbc.support.UseJdbc;

@WebServlet("/GuestUpdateSetting")
public class GuestUpdateSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public GuestUpdateSetting(){
		super();
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * ��usersetting.jsp��POST��ʽ������������
	 * ��usersetting.jsp�У��Ұ��û�����Ȩ���趨Ϊ��ֻ�����ݲ���û�и���name
	 * Ŀ���Ƿ�ֹ�ⲿ��Աͨ���޸�htmlԴ�����ﵽ�ƻ��˺����ݻ��޸�����Ȩ�޵�Ŀ��
	 * Ϊ��ȷ���û��޸ĵ�ȷȷʵʵ���Լ����˺��Ҵ�session�л�ȡ�û���
	 * Ȼ��ֱ����UseJDBC�ж��û����ݽ��в��غ͸��²���
	 * �ڲ��ؽ׶�����û��ύ��������ԭ����һģһ�����޸�ֱ�ӷ���
	 * ������û�������������в�һ�������û��ύ�����ݽ��и����ύ�����ݿ�
	 * ������ֵ������˵�����ݸ������
	 * Ȼ�󷵻��û��趨����
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		//Ϊ�˷�ֹ�û��޸��û������û�����Ϣ��session����
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		String email = new String(request.getParameter("email").getBytes("ISO8859-1"),"UTF-8");
		String phone = new String(request.getParameter("phone").getBytes("ISO8859-1"),"UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8");
		String sage = new String(request.getParameter("age").getBytes("ISO8859-1"),"UTF-8");
		String sex = new String(request.getParameter("sex").getBytes("ISO8859-1"),"UTF-8");
		String jieshao = new String(request.getParameter("jieshao").getBytes("ISO8859-1"),"UTF-8");
		
		int age = Integer.parseInt(sage);
		
		System.out.println("���������"+username+" "+email+" "+phone
				+" "+password+" "+age+" "+sex+" "+jieshao);
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		//����
		int i = 0;
		try {
			i = UseJdbc.useronly(username, password, email, phone, sex, age, jieshao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1){
			System.out.print("�ύ�ĺ�ԭ����һ�������޸ĺ����ύ");
			response.getWriter().write("�ύ�ĺ�ԭ����һ�������޸ĺ����ύ");
			response.setHeader("refresh", "2;url="+basePath+"UserSettingServlet");
		}else if(i == 2){
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy");
			String time = sf.format(date);
			int nowtime = Integer.parseInt(time);
			int birth = nowtime - age;
			
			int x = 0;
			try {
				x = UseJdbc.updateuser(username, password, email, phone, sex, age, jieshao, birth);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(x == 1){
				System.out.print("���³ɹ�");
				response.getWriter().write("���³ɹ�");
				response.setHeader("refresh", "1;url="+basePath+"UserSettingServlet");
			}else if(x == 2){
				System.out.print("����ʧ�ܣ����ݿ����");
				response.getWriter().write("����ʧ�ܣ����ݿ����");
			}else{
				System.out.print("����ʧ�ܣ�δ֪����");
			}
			
		}else{
			System.out.print("δ֪����0");
		}
	}
	
	//����POST����
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }

}
