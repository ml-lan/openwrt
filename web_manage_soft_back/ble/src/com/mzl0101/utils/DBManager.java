package com.mzl0101.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBManager {
	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://104.224.163.27:3306/ble?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	private static final String NAME = "mzl";
	private static final String PASS = "MySqlPass123...";

	private Connection conn;

	static {
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, NAME, PASS);
	}

	/**
	 * 完成数据的更新操作
	 */
	public void update(String sql) {

		try {
			conn = getConnection();
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConn();
		}

	}

	/**
	 * 完成数据的查询操作
	 */
	public ResultSet query(String sql) {
		try {
			conn = getConnection();
			return conn.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 此处connection不可以关闭，因为resultset的解析要求connection处于连接状态
		return null;

	}
	/*
	 * 读取二进制图片
	 */
	
	public ResultSet select_pic(String sql) {
		try {
			conn = getConnection();
			return conn.prepareStatement(sql).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 此处connection不可以关闭，因为resultset的解析要求connection处于连接状态
		return null;

	}

	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
