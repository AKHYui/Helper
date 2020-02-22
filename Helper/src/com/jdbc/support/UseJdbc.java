package com.jdbc.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.support.JdbcUtil;

public class UseJdbc {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = JdbcUtil.getDriver();
	static final String DB_URL = JdbcUtil.getUrl();
	static final String USER = JdbcUtil.getUser();
	static final String PASS = JdbcUtil.getPwd();
	//ɾ������
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
	//ɾ���û�
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
	//ɾ������
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
	//ɾ������
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
	//��ӹ���
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
	//�༭�û�
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
	//��ͨ�û���½
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
	
	//ע���û�
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
		stmt = conn.createStatement();  //��sql��䷢�͵����ݿ���
		rs1=stmt.executeQuery(sql);  //�����ݿ�Ĳ�ѯ������һ����Ҫ���ز�ѯ���
		if(rs1.next()){
			return 0;
		}else {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql1 = "INSERT INTO user (username,password,email,age,phone,sex,jieshao,birth,permit,icon) VALUES ('"+username+"','"+password+"','"+email+"',0,'0','δ֪','����˺�������ʱ��û�н���','λ��','�û�','/Helper-System/img/icon/icon.jpg')";
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
	
	//��ѯָ������
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
	
	//����������Ŀ��ѯ��Ӧ
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
	
	//Ӧ�����
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
	
	//����һ�����������ݿ�
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

}
