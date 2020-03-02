package com.jdbc.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.support.JdbcUtil;
/*
 * 这里是JavaBean部分
 */
import com.sun.xml.internal.ws.message.stream.StreamHeader11;
public class UseJdbc {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = JdbcUtil.getDriver();
	static final String DB_URL = JdbcUtil.getUrl();
	static final String USER = JdbcUtil.getUser();
	static final String PASS = JdbcUtil.getPwd();
	//删除文章
	public static int delar(String id){
		int rs = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "DELETE FROM article WHERE Id = '" + id + "'";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//删除用户
	public static int delu(String id){
		int rs = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "DELETE FROM user WHERE Id = '" + id + "'";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	//删除公告
	public static int debu(String id){
		int rs = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "DELETE FROM bulletin WHERE Id = '" + id + "'";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//删除评论
	public static int deco(String id){
		int rs = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "DELETE FROM comment WHERE Id = '" + id + "'";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//添加公告
	public static int inbu(String text, String time){
		int rs = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "INSERT INTO bulletin (text,time) VALUES ('"+text+"','"+time+"') ";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//编辑用户
	public static int upus(String id, String username, String password, String email, String phone){
		int rs = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "Update user SET username = '"+username+"', password = '"+password+"', email = '"+email+"', phone = '"+phone+"' WHERE id = "+id+"";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//普通用户登陆
	public static int GuestLogin(String username, String password){
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		int a = 1;
		int b = 0;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT * FROM user WHERE username = '"+username+"' and password = '"+password+"'";
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()){
				return a;
			}else {
				return b;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//注册用户
	public static int reg(String username, String password, String email) throws SQLException{
		int rs = 0;
		ResultSet rs1 = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "select * from user where username='"+username+"'";
		stmt = conn.createStatement();  //将sql语句发送到数据库中
		rs1=stmt.executeQuery(sql);  //对数据库的查询操作，一般需要返回查询结果
		if(rs1.next()){
			return 0;
		}else {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql1 = "INSERT INTO user (username,password,email,age,phone,sex,jieshao,birth,permit,icon) VALUES ('"+username+"','"+password+"','"+email+"',0,'0','未知','这个人很懒，暂时还没有介绍','位置','用户','/Helper/upload/icon/icon.jpg')";
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(sql1);
			if(rs!=0){
				conn.close();
				return 1;
			}else {
				conn.close();
				return 2;
			}
		}
		
	}
	
	//查询指定求助
	public static ResultSet rss(int id) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM article WHERE id="+id+"";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//根据求助题目查询回应
	public static ResultSet rst(String title) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM comment WHERE atitle='"+title+"'";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//应答入库
	public static int css(String answer, String username, String atitle, String nowtime) throws SQLException{
		int rs = 0;
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "INSERT INTO comment (text,time,user,atitle) VALUES ('"+answer+"','"+nowtime+"','"+username+"','"+atitle+"')";
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		if (rs!=0){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
	}
	
	//发送一条求助到数据库
	public static int artadd(String username, String arttitle, String artaddr, String arttext, String filename, String nowtime) throws SQLException{
		int rs = 0;
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "INSERT INTO article (title,addr,user,text,img,time) "
				+ "VALUES ('"+arttitle+"','"+artaddr+"','"+username+"','"+arttext+"','"+filename+"','"+nowtime+"')";
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		if (rs!=0){
			conn.close();
			return 1;
		}else {
			conn.close();
			return 2;
		}
		
	}
	
	//确定求助确实属于该用户
	public static int artuser(String username, int id) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String sql = "SELECT * FROM article WHERE user = '"+username+"' and id="+id+"";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
		
	}
	
	//用户删除自己的求助
	public static int artdel(int id) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "DELETE FROM article WHERE Id = "+id+"";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		conn.close();
		return 1;
	}
	
	//根据求助主题来获取求助ID
	public static ResultSet tid(String atitle) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM article WHERE title='"+atitle+"'";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//确定应答确实属于该用户
	public static int comuser(String username, int id) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String sql = "SELECT * FROM comment WHERE user = '"+username+"' and id="+id+"";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
		
	}
	
	//用户删除自己的应答
	public static int comdel(int id) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "DELETE FROM comment WHERE Id = "+id+"";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		conn.close();
		return 1;
	}
	
	//查询求助是否已经被收藏
	public static int sefav(String username, String atitle) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM favorite WHERE user = '"+username+"' and atitle = '"+atitle+"'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
		
	}
	
	//添加用户收藏
	public static int addfav(String username, String atitle) throws SQLException{
		
		int rs = 0;
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "INSERT INTO favorite (user, atitle) "
				+ "VALUES ('"+username+"', '"+atitle+"')";
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		if(rs != 0){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
		
	}
	
	//查询是否为本人收藏
	public static int userfav(String username, int id) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String sql = "SELECT * FROM favorite WHERE user = '"+username+"' and id="+id+"";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		if(rs.next()){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
		
	}
	
	//取消收藏
	public static int delfav(int id) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "DELETE FROM favorite WHERE Id = "+id+"";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		conn.close();
		return rs;
		
	}
	
	//检查传入信息是否与数据库中的信息一致
	public static int useronly(String username, String password, String email, 
			String phone, String sex, int age, String jieshao) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String sql = "SELECT * FROM user WHERE username ='"+username+"' and password = '"+password+"' and email = '"+email+"' and age = "+age+" and phone = '"+phone+"' and sex = '"+sex+"' and jieshao = '"+jieshao+"'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		if(rs.next()){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
		
	}
	
	//更新用户数据
	public static int updateuser(String username, String password, String email, 
			String phone, String sex, int age, String jieshao, int birth) throws SQLException{
	int rs = 0;
	Connection conn = null;
	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	String sql = "Update user SET password = '"+password+"', email = '"+email+"', phone = '"+phone+"', sex = '"+sex+"', age = "+age+", jieshao = '"+jieshao+"', birth = '"+birth+"' WHERE username = '"+username+"'";
	Statement stmt = null;
	stmt = conn.createStatement();
	rs = stmt.executeUpdate(sql);
	conn.close();
	if(rs != 0){
		conn.close();
		return 1;
	}else{
		conn.close();
		return 2;
	}
	}
	
	//更新用户头像
	public static int updateicon(String username, String iconname) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "Update user SET icon = '"+iconname+"' "
				+ "WHERE username = '"+username+"'";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		if(rs != 0){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
		
	}
	
	//检查求助是否被接单
	public static ResultSet checkfastmod(int id) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT status FROM fastmod WHERE id="+id+"";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//接单求助
	public static int dofastmod(int id, String username) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "Update fastmod SET helper = '"+username+"', status = '"+"已被接单"+"' WHERE id = "+id+"";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		if(rs != 0){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
	}
	
	//添加快速求助
	public static int addfastmod(String username, String phone,
			int money, String text, String nowtime) throws SQLException{
		int rs = 0;
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "INSERT INTO fastmod (user, text, userphone, helper, status, money, time) "
				+ "VALUES ('"+username+"', '"+text+"', '"+phone+"', '无', '未被接单', "+money+", '"+nowtime+"')";
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		if(rs != 0){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
	}
	
	//查询规定用户所有的快速发布
	public static ResultSet mfms(String username) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM fastmod WHERE user = '"+username+"'";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//查询用户接单的所有求助
	public static ResultSet cufm(String username) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM fastmod WHERE helper = '"+username+"'";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//查询当前登陆用户的手机号码
	public static ResultSet hphone(String username) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT phone FROM user WHERE username = '"+username+"'";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//确认完成求助前的认证
	public static int cof(String username, int id) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String sql = "SELECT * FROM fastmod WHERE helper = '"+username+"' and id= "+id+" and status = '已被接单'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
	}
	
	//完成求助
	public static int dorder(int id) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "Update fastmod SET status = '已完成' WHERE id = "+id+"";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		if(rs != 0){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 2;
		}
	}
	
	//查找所有公告
	public static ResultSet bull() throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM bulletin order by id desc;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//查找我发布的主题
	public static ResultSet myart(String username) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM article WHERE user = '"+username+"' order by id desc;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//查找我的评论
	public static ResultSet mycom(String username) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM comment WHERE user = '"+username+"' order by id desc;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//查找我的所有收藏
	public static ResultSet myfav(String username) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM favorite WHERE user = '"+username+"' order by id desc;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
}
