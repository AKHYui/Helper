/*
 * 用户更新个人数据
 */
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
	 * 从usersetting.jsp以POST方式传过来的数据
	 * 在usersetting.jsp中，我把用户名和权限设定为了只读数据并且没有赋予name
	 * 目的是防止外部人员通过修改html源码来达到破坏账号数据或修改自身权限的目的
	 * 为了确定用户修改的确确实实是自己的账号我从session中获取用户名
	 * 然后直接在UseJDBC中对用户数据进行查重和更新操作
	 * 在查重阶段如果用户提交的数据与原数据一模一样则不修改直接返回
	 * 如果除用户名以外的数据有不一样则按照用户提交的数据进行更新提交给数据库
	 * 若返回值正常则说明数据更新完成
	 * 然后返回用户设定界面
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		//为了防止用户修改用户名，用户名信息从session里拿
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		String email = new String(request.getParameter("email").getBytes("ISO8859-1"),"UTF-8");
		String phone = new String(request.getParameter("phone").getBytes("ISO8859-1"),"UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8");
		String sage = new String(request.getParameter("age").getBytes("ISO8859-1"),"UTF-8");
		String sex = new String(request.getParameter("sex").getBytes("ISO8859-1"),"UTF-8");
		String jieshao = new String(request.getParameter("jieshao").getBytes("ISO8859-1"),"UTF-8");
		
		int age = Integer.parseInt(sage);
		
		System.out.println("传入的数据"+username+" "+email+" "+phone
				+" "+password+" "+age+" "+sex+" "+jieshao);
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		//查重
		int i = 0;
		try {
			i = UseJdbc.useronly(username, password, email, phone, sex, age, jieshao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1){
			System.out.print("提交的和原数据一样，请修改后再提交");
			response.getWriter().write("提交的和原数据一样，请修改后再提交");
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
				System.out.print("更新成功");
				response.getWriter().write("更新成功");
				response.setHeader("refresh", "1;url="+basePath+"UserSettingServlet");
			}else if(x == 2){
				System.out.print("更新失败，数据库错误");
				response.getWriter().write("更新失败，数据库错误");
			}else{
				System.out.print("更新失败，未知错误");
			}
			
		}else{
			System.out.print("未知错误0");
		}
	}
	
	//处理POST请求
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }

}
