/*
 * Jdbc–≈œ¢
 */
package com.jdbc.support;

public class JdbcUtil {
	public static String getDriver() {
		return "com.mysql.jdbc.Driver";
	}

	public static String getUrl() {
		return "jdbc:mysql://localhost:3306/helper?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
	}

	public static String getUser() {
		return "root";
	}

	public static String getPwd() {
		return "root";
	}
}
