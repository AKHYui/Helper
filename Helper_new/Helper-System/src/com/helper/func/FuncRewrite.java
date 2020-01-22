package com.helper.func;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FuncRewrite")
public class FuncRewrite extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		int id = Integer.parseInt(request.getParameter("id"));
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		if (id == 1){  //id=1时，进入user.jsp
			try {
				response.getWriter().write("成功");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String sessionusers = "users";
			String begin = "1";
			String end = "5";
			String page = "1";
			session.setAttribute("sessionusers", sessionusers);
			session.setAttribute("begin", begin);
			session.setAttribute("end", end);
			session.setAttribute("page", page);
			try {
				response.sendRedirect(basePath+"func/users.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String site = new String(basePath + "func/users.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}else if(id == 2){ //id=2时进入article.jsp
			String sessionusers = "article";
			String begin = "1";
			String end = "5";
			String page = "1";
			session.setAttribute("sessionusers", sessionusers);
			session.setAttribute("begin", begin);
			session.setAttribute("end", end);
			session.setAttribute("page", page);
			try {
				response.sendRedirect(basePath+"func/article.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String site = new String(basePath + "func/article.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}else if(id == 3){
			String sessionusers = "comment";
			String begin = "1";
			String end = "5";
			String page = "1";
			session.setAttribute("sessionusers", sessionusers);
			session.setAttribute("begin", begin);
			session.setAttribute("end", end);
			session.setAttribute("page", page);
			try {
				response.sendRedirect(basePath+"func/comment.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String site = new String(basePath + "func/comment.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}else if(id == 4){
			String sessionusers = "bullent";
			String begin = "1";
			String end = "5";
			String page = "1";
			session.setAttribute("sessionusers", sessionusers);
			session.setAttribute("begin", begin);
			session.setAttribute("end", end);
			session.setAttribute("page", page);
			try {
				response.sendRedirect(basePath+"func/bullent.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String site = new String(basePath + "func/bullent.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}else{
			try {
				response.getWriter().write("error");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
