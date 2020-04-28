package com.jdbc.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.support.JdbcUtil;
/*
 * �����Ǹ�������ݿ�����Ĵ���
 */

public class UseJdbc {
	private static final long serialVersionUID = 1L;
	private static String JDBC_DRIVER = JdbcUtil.getDriver();
	private static String DB_URL = JdbcUtil.getUrl();
	private static String USER = JdbcUtil.getUser();
	private static String PASS = JdbcUtil.getPwd();
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
	public static int GuestLogin(String username, String password) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM user WHERE "
				+ "username = '"+username+"' and password = '"+password+"'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
			if(rs.next()){
				conn.close();
				return 1;
			}else {
				conn.close();
				return 0;
			}
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
			String sql1 = "INSERT INTO user (username,password,email,age,phone,sex,jieshao,birth,permit,icon) VALUES ('"+username+"','"+password+"','"+email+"',0,'0','δ֪','����˺�������ʱ��û�н���','δ֪','�û�','icon.jpg')";
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
	
	//����������ĵ�һ��
	public static int checktitle(String title) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM article WHERE title = '"+title+"'";
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
	
	//����һ�����⵽���ݿ�
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
	
	//ȷ������ȷʵ���ڸ��û�
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
	
	//�û�ɾ���Լ�������
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
	
	//���������������ȡ����ID
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
	
	//ȷ��Ӧ��ȷʵ���ڸ��û�
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
	
	//�û�ɾ���Լ���Ӧ��
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
	
	//��ѯ�����Ƿ��Ѿ����ղ�
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
	
	//����û��ղ�
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
	
	//��ѯ�Ƿ�Ϊ�����ղ�
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
	
	//ȡ���ղ�
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
	
	//��鴫����Ϣ�Ƿ������ݿ��е���Ϣһ��
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
	
	//�����û�����
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
	
	//�����û�ͷ��
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
	
	//��������Ƿ񱻽ӵ�
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
	
	//�ӵ�����
	public static int dofastmod(int id, String username) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "Update fastmod SET helper = '"+username+"', status = '"+"�ѱ��ӵ�"+"' WHERE id = "+id+"";
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
	
	//��ӿ�������
	public static int addfastmod(String username, String phone,
			int money, String text, String nowtime) throws SQLException{
		int rs = 0;
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "INSERT INTO fastmod (user, text, userphone, helper, status, money, time) "
				+ "VALUES ('"+username+"', '"+text+"', '"+phone+"', '��', 'δ���ӵ�', "+money+", '"+nowtime+"')";
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
	
	//��ѯ�涨�û����еĿ��ٷ���
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
	
	//��ѯ�û��ӵ�����������
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
	
	//��ѯ��ǰ��½�û����ֻ�����
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
	
	//ȷ���������ǰ����֤
	public static int cof(String username, int id) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String sql = "SELECT * FROM fastmod WHERE helper = '"+username+"' and id= "+id+" and status = '�ѱ��ӵ�'";
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
	
	//�������
	public static int dorder(int id) throws SQLException{
		int rs = 0;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "Update fastmod SET status = '�����' WHERE id = "+id+"";
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
	
	//�������й���
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
	
	//�����ҷ���������
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
	
	//�����ҵ�����
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
	
	//�����ҵ������ղ�
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
	
	//��ѯapi״̬
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
	
	//��ѯak
	public static ResultSet getak() throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT ak FROM baiduapi WHERE id = 1;";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//��ѯָ���û���Ϣ
	public static ResultSet getusermes(String username) throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "SELECT * FROM user WHERE username = '"+username+"'";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	//��ѯ�û���û�з�������
	public static int checkart(String username) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String sql = "SELECT * FROM article WHERE user = '"+username+"'";
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
}
