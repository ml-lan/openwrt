package com.mzl0101.utils;



import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	private static DataSource dataSource = new ComboPooledDataSource();

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * ��DBUtils��Ҫ�ֶ���������ʱ�����ø÷������һ������
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		Connection con = tl.get();

		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}

		return con;
	}

	// ��������
	public static void startTransaction() throws SQLException {

		getConnection().setAutoCommit(false);

	}

	// ����ع�
	public static void rollback() throws SQLException {
		getConnection().rollback();
	}

	// �����ύ
	public static void commitAndReleased() throws SQLException {

		getConnection().commit(); // �����ύ
		getConnection().close();// �ͷ�connection���ǽ���Żص����ӳ�.
		tl.remove();
	}

}
