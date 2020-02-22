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
	 * GuestPostArticle开发日志：
	 * 我在做求助发布的时候遇到了一个问题
	 * 根据我的预期 发布出的求助必须需要附带一张图片
	 * 但是我无法实现同时将图片和文字用POST的方式发送给Servlet
	 * 于是这个页面就诞生了
	 * 在myarticle.jsp中暂时先不发送图片
	 * GuestPostArticle这个页面负责从myarticle.jsp中获取文字信息
	 * 经过字符数量的判定 如果合格会把这些文字放入session中
	 * 然后进入下一个页面home/module/send.jsp
	 * send.jsp接收了这个页面的session并且负责图片的上传界面
	 * 提交图片后数据将会被送入UploadServlet中
	 * UploadServlet进行上传完成后
	 * 转到SendArticleServlet中 然后给图片加链接前缀传入数据库
	 * 结束转到home
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//设置响应内容之类型
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
			response.getWriter().write("<p>您的发布不符合规定，标题至少要有2个字符，"
					+ "地点至少要有3个字符，内容至少15个字符</p>"
					+ "<p>3秒后将回到上个页面</p>");
			response.setHeader("refresh", "3;url="+basePath+"MyArticleServlet");
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("arttitle", arttitle);
			session.setAttribute("artaddr", artaddr);
			session.setAttribute("arttext", arttext);
			request.getRequestDispatcher("home/module/send.jsp").forward(request, response);
		}
		
	}
	
	//处理POST请求
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }

}
