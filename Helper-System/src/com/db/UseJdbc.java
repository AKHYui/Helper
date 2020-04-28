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
	private static final String JDBC_DRIVER = JdbcUtil.getDriver();
	private static final String DB_URL = JdbcUtil.getUrl();
	private static final String USER = JdbcUtil.getUser();
	private static final String PASS = JdbcUtil.getPwd();
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
	public static int debu(String id) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "DELETE FROM bulletin WHERE Id = '" + id + "'";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		conn.close();
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
	public static int inbu(String text, String time) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "INSERT INTO bulletin (text,time) VALUES ('"+text+"','"+time+"') ";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		conn.close();
		return rs;
	}
	//�༭�û�
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
	
	//����Ա��½��̨
	public static int adminlogin(String username, String password) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM user WHERE username = '"+username+"' and password = '"+password+"' and permit = '����Ա'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			conn.close();
			return 1;
		}else{
			return 0;
		}
	}
	
	//��פ����Ա��½��̨
	public static int readminlogin(String username, String password) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM user WHERE username = '"+username+"' and password = '"+password+"' and permit = '��פ����Ա'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			conn.close();
			return 1;
		}else{
			conn.close();
			return 0;
		}
		
	}
/*	
	//��ѯ�����û�
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
	
	//��ѯ�û�����
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
	
	//��ѯ��������
	public static ResultSet artmun() throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT COUNT(*) total FROM article;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//ɾ������
	public static int fastdel(int id) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "DELETE FROM fastmod WHERE Id = "+id+"";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		return 1;
	}
	
	//��ѯ��������
	public static ResultSet fastmun() throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT COUNT(*) total FROM fastmod;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	//��ѯ��������
	public static ResultSet commun() throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT COUNT(*) total FROM comment;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	//��ѯ���ݿ����Ƿ����ظ��û���
	public static int checkuser(String username) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM user WHERE username = '"+username+"'";
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
	//�����פ����Ա
	public static int addadmin(String username, String password, String phone, int age, int birth) throws SQLException{
		int rs = 0;
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "INSERT INTO user (username,password,email,age,phone,sex,jieshao,birth,permit,icon) "
				+ "VALUES ('"+username+"','"+password+"','0',"+age+",'"+phone+"','δ֪','����˺�������ʱ��û�н���',"+birth+",'��פ����Ա','icon.jpg')";
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		if(rs!=0){
			conn.close();
			return 1;
		}else {
			conn.close();
			return 2;
		}
		
	}
	//�޸İٶ�api
	public static int updateapi(String ak) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "Update baiduapi set ak = '"+ak+"' WHERE id = 1";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		conn.close();
		return rs;
	}
	//��鼤��״̬
	public static int checkstatus() throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM baiduapi WHERE id = 1 and status = 'on'; ";
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
	//ת��status on
	public static int statuson() throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "Update baiduapi set status = 'off' WHERE id = 1";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		conn.close();
		return rs;
	}
	//ת��status off
	public static int statusoff() throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "Update baiduapi set status = 'on' WHERE id = 1";
		Statement stmt = null;
		stmt = conn.createStatement();
		rs = stmt.executeUpdate(sql);
		conn.close();
		return rs;
	}
}
