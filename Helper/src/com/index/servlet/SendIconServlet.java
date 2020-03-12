package com.index.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.support.UseJdbc;

@WebServlet("/SendIconServlet")
public class SendIconServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String iconname = (String) session.getAttribute("iconName");
		
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
    			+ "/";
    	//String icon_basePath = path+"/";
    	//iconname = icon_basePath+"upload/icon/"+iconname;
    	
    	System.out.println("图片名"+iconname);
    	
    	int i = 0;
    	try {
			i = UseJdbc.updateicon(username, iconname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(i == 1){
    		System.out.println("成功放入数据库");
    		response.getWriter().write("上传成功");
    		response.setHeader("refresh", "2;url="+basePath+"UserSettingServlet");
    	}else if(i == 2){
    		System.out.println("数据库出错");
    		response.getWriter().write("上传失败");
    		response.setHeader("refresh", "4;url="+basePath+"UserSettingServlet");
    	}else{
    		response.getWriter().write("未知错误0");
    		response.setHeader("refresh", "4;url="+basePath+"UserSettingServlet");
    	}
	}
}
