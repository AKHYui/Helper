package com.index.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import api.baidu.ip.IpCheck;
import api.baidu.ip.UseApi;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

@WebServlet("/IndexServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String login = request.getParameter("login");
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String se = (String) session.getAttribute("se");
		String ak = (String) session.getAttribute("ak");
		
		String userip = request.getRemoteAddr();//记得改回来
		String json_s = UseApi.UseApi(ak, userip);
		String addr = IpCheck.GetData(json_s);
		System.out.println(addr);
		session.setAttribute("nowaddress", addr);
		/*
		com.alibaba.fastjson.JSONObject jsonObject = 
				com.alibaba.fastjson.JSONObject.parseObject(json_s);
		String r = jsonObject.getString("status");
		System.out.println(r);
		
		
		String url = "http://api.map.baidu.com/location/ip?ak=LowbbhGX04PYPpSObHVZoCdTjzaHScjm&ip=192.168.31.12&coor=bd09ll";
		JSONObject json = IpCheck.doGetStr(url);
		String json_s = json.toString();
		System.out.println(json_s);
		*/
		if(se == null){
			try {
				request.getRequestDispatcher("pages/login.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				request.getRequestDispatcher("home/index.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
