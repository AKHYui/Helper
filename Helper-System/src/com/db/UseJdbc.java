package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.helper.DbServlet.JdbcUtil;

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
	public static int upus(String id, String email, String phone){
		int rs = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "Update user SET email = '"+email+"', phone = '"+phone+"' WHERE id = "+id+"";
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
	
	//管理员登陆后台
	public static int adminlogin(String username, String password) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM user WHERE username = '"+username+"' and password = '"+password+"' and permit = '管理员'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			conn.close();
			return 1;
		}else{
			return 0;
		}
	}
	
	//入驻管理员登陆后台
	public static int readminlogin(String username, String password) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM user WHERE username = '"+username+"' and password = '"+password+"' and permit = '入驻管理员'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			conn.close();
			return 1;
		}else{
			return 0;
		}
		
	}
/*	
	//查询所有用户
	public static ResultSet alluser() throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM user order by id desc;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}*/
	
	//查询用户总数
	public static ResultSet usermun() throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT COUNT(*) total FROM user;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
		
	}
	
}
