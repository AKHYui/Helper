/*
 * 发布主题页面
 */
package com.index.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.support.UseJdbc;

@WebServlet("/SendArticleServlet")
public class SendArticleServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String arttitle = (String) session.getAttribute("arttitle");
		String artaddr = (String) session.getAttribute("artaddr");
		String arttext = (String) session.getAttribute("arttext");
		String filename = (String) session.getAttribute("filename");
		
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
    			+ "/";
    	//String file_basePath = path+"/";
    	//filename = file_basePath+"upload/image/"+filename;
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowtime = sf.format(date);
		
		int artadd = 0;
		try {
			artadd = UseJdbc.artadd(username, arttitle, artaddr, arttext, filename, nowtime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(artadd == 1){
			String site = new String(basePath+"IndexServlet");
    		response.setStatus(response.SC_MOVED_TEMPORARILY);
    		response.setHeader("Location", site);
		}else{
			response.getWriter().write("失败");
		}
		
		System.out.print(username+"发送出了一条求助");
		System.out.print("主题"+arttitle);
		System.out.print("地点"+artaddr);
		System.out.print("内容"+arttext);
		System.out.print("图片路径"+filename);
		/*
		response.getWriter().write(arttitle);
		response.getWriter().write(arttext);
		response.getWriter().write(artaddr);
		response.getWriter().write(filename);
		response.getWriter().write(username);
		*/
		
	}

}
