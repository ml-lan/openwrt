package com.mzl0101.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mzl0101.utils.DBManager;

public class DevicesDao {
	private DBManager db = new DBManager();
	
	/**
	 *��ѯ�����豸��״̬
	 *�ṩAPI�ӿ� �����һ��String �С�
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
				  sb.append(rs.getInt(1)); // ����ÿһ�е�����
                  sb.append("-"); 			  // ��ÿ�����ݺ�������ǣ��������������
                  sb.append(rs.getString(2));
                  sb.append("-");
                  sb.append(rs.getString(3));
                  sb.append("-");
                  sb.append(rs.getString(4));
                  sb.append("-");
                  sb.append(rs.getString(5));
                  sb.append("%");
			}
			
			result = sb.toString();// ��������StringBuffer����ת����String����
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return result;
	}
	
	/**
	 * �޸��豸״̬
	 * @param id change_sta
	 */
	
	
	public void updateDeviceStatus(String id, String change_sta) 
	{
		String sql = "update devices set device_status ="+change_sta+" where id=" +id;
		db.update(sql);
	}
	
	/**
	 * �޸��豸���� ����
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
