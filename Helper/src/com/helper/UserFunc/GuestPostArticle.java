package com.helper.UserFunc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GuestPostArticle")
public class GuestPostArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public GuestPostArticle(){
		super();
	}
	
	/*
	 * GuestPostArticle������־��
	 * ����������������ʱ��������һ������
	 * �����ҵ�Ԥ�� ������������������Ҫ����һ��ͼƬ
	 * �������޷�ʵ��ͬʱ��ͼƬ��������POST�ķ�ʽ���͸�Servlet
	 * �������ҳ��͵�����
	 * ��myarticle.jsp����ʱ�Ȳ�����ͼƬ
	 * GuestPostArticle���ҳ�渺���myarticle.jsp�л�ȡ������Ϣ
	 * �����ַ��������ж� ����ϸ�����Щ���ַ���session��
	 * Ȼ�������һ��ҳ��home/module/send.jsp
	 * send.jsp���������ҳ���session���Ҹ���ͼƬ���ϴ�����
	 * �ύͼƬ�����ݽ��ᱻ����UploadServlet��
	 * UploadServlet�����ϴ���ɺ�
	 * ת��SendArticleServlet�� Ȼ���ͼƬ������ǰ׺�������ݿ�
	 * ����ת��home
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//������Ӧ����֮����
		response.setContentType("text/html;charset=UTF-8");	
		String arttitle = new String(request.getParameter("title").getBytes("ISO8859-1"),"UTF-8");
		String artaddr = new String(request.getParameter("addr").getBytes("ISO8859-1"),"UTF-8");
		String arttext = new String(request.getParameter("text").getBytes("ISO8859-1"),"UTF-8");
		/*
		response.getWriter().write(arttitle);
		response.getWriter().write(artaddr);
		response.getWriter().write(arttext);
		*/
		String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
    			+ "/";
		
		int t_length = arttitle.length();
		int a_lenth = artaddr.length();
		int at_lenth = arttext.length();
		
		if(t_length<=1 || a_lenth<=2 || at_lenth<15){
			response.getWriter().write("<p>���ķ��������Ϲ涨����������Ҫ��2���ַ���"
					+ "�ص�����Ҫ��3���ַ�����������15���ַ�</p>"
					+ "<p>3��󽫻ص��ϸ�ҳ��</p>");
			response.setHeader("refresh", "3;url="+basePath+"MyArticleServlet");
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("arttitle", arttitle);
			session.setAttribute("artaddr", artaddr);
			session.setAttribute("arttext", arttext);
			request.getRequestDispatcher("home/module/send.jsp").forward(request, response);
		}
		
	}
	
	//����POST����
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }

}
