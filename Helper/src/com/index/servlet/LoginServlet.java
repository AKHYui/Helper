/*
 * 登录页面
 */
package com.index.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import api.baidu.ip.DoApi;
import api.baidu.ip.IpCheck;
import api.baidu.ip.Reverse;
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
			/*
			String ak = "";
			
			try {
				ak = UseApi.getak();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String userip = request.getRemoteAddr();
			
			//String userip = request.getRemoteAddr();//记得改回来
			String userip = "118.74.119.220";
			String json_s = UseApi.UseApiKey(ak, userip);
			String json_r = IpCheck.GetData(json_s);
			System.out.println(json_r);
			if(json_r.equals("0") == true){
				String addr = "API错误或管理员关闭了API";
				session.setAttribute("nowaddress", addr);
			}else{
				String addr = Reverse.Getaddr(json_r);
				session.setAttribute("nowaddress", addr);
			}
			
			DoApi doapi = new DoApi();
			String addr = doapi.doMapApi(ak,userip);
			session.setAttribute("nowaddress", addr);
			*/
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
