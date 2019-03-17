package com.mzl0101.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mzl0101.utils.DBManager;

public class DevicesDao {
	private DBManager db = new DBManager();
	
	/**
	 *查询所有设备的状态
	 *提供API接口 存放在一个String 中。
	 *@param username
	 *@return password
	 */
	public String queryDevices(){
		String result = null;
		StringBuffer sb = new StringBuffer();
       
		try {
			String sql = "select * from devices";
			ResultSet rs = db.query(sql);

			while (rs.next()) {
				  sb.append(rs.getInt(1)); // 读出每一列的数据
                  sb.append("-"); 			  // 在每列数据后面做标记，将来便于做拆分
                  sb.append(rs.getString(2));
                  sb.append("-");
                  sb.append(rs.getString(3));
                  sb.append("-");
                  sb.append(rs.getString(4));
                  sb.append("-");
                  sb.append(rs.getString(5));
                  sb.append("%");
			}
			
			result = sb.toString();// 将数据由StringBuffer类型转化成String类型
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return result;
	}
	
	/**
	 * 修改设备状态
	 * @param id change_sta
	 */
	
	
	public void updateDeviceStatus(String id, String change_sta) 
	{
		String sql = "update devices set device_status ="+change_sta+" where id=" +id;
		db.update(sql);
	}
	
	/**
	 * 修改设备名称 描述
	 * @param id device_name device_des
	 *
	 */
	public void modifyDevice(String id, String device_name, String device_des) 
	{
		String sql = "update devices set device_name = '" 
					+device_name 
					+"', device_des='"
					+device_des
					+"' where id=" 
					+id;
		db.update(sql);
	}
	
	
	

	

}
