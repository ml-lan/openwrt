package com.mzl0101.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mzl0101.entity.Users;
import com.mzl0101.utils.DBManager;
import com.mzl0101.utils.DataSourceUtils;

public class UsersDao {
	private DBManager db = new DBManager();
	
	/**
	 * �����û�����
	 * 
	 * @param user
	 * @return void
	 */
	public void saveUser(Users user) {

		String sql = "insert into users (username,passwd,user_email)"
				+" values('"
				+user.getUsername()
				+"','"
				+user.getPassword()
				+"','"
				+user.getEmail()
				+"')";
		db.update(sql);

	}
	
	/**
	 * ͨ���û��������Ƿ���ڸ��û�
	 * ����true�����
	 * @param username
	 * @return boolean
	 */
	public boolean queryByUserName(String username) {
		try {
			String sql = "select * from users where username= '"+username+"'";
			//System.out.println(">>>>>>>>>>>>>"+sql);
			ResultSet rs = db.query(sql);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return false;
	}
	
	/**
	 *�����Ѵ��ڵ��û������Ҹ��û������롣 
	 *@param username
	 *@return password
	 */
	public Users queryUserByUserName(String username){
		Users u = null;
		try {
			String sql = "select * from users where username= '" + username +"'";
			ResultSet rs = db.query(sql);

			while (rs.next()) {
				u = new Users();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("passwd"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return u;
	}
	
	/**
	 * �޸��û�����
	 * @param email password
	 */
	
	
	public void updateUserPass(String email, String password) 
	{
		String sql = "update users set passwd ='"+password+"' where user_email='"+email+"'";
		db.update(sql);
	}
	
	/**
	 *�����Ѵ��ڵ�������Ҹ��û��� 
	 *@param emial
	 *@return boolean
	 */
	
	public boolean queryByUserEmail(String user_email) {
		
		// 1.����QueryRunner
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from users where user_email=?";
		try {
			if(runner.query(sql,new BeanHandler<Users>(Users.class), user_email) != null){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
