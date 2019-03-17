package com.mzl0101.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mzl0101.entity.Users;
import com.mzl0101.entity.Zigbee;
import com.mzl0101.utils.DBManager;

public class ZigbeeDao {
	private DBManager db = new DBManager();
	/**
	 *
	 *�����ն�id��ѯ���еĲ�����¼
	 *@param terminal_id
	 *@return String
	 */
	public String queryZigbee(String terminal_id){
		String result = null;
		StringBuffer sb = new StringBuffer();
		String sql = null;
		try {
			if(terminal_id==null||terminal_id.length()<=0)
			{
				sql = "select * from zigbee";
			}
			else{
				sql = "select * from zigbee where terminal_id='"+terminal_id+"'";
			}
			
			ResultSet rs = db.query(sql);

			while (rs.next()) {
				  sb.append(rs.getInt(1)); // ����ÿһ�е�����
                  sb.append("_"); 			  // ��ÿ�����ݺ�������ǣ��������������
                  sb.append(rs.getString(2));
                  sb.append("_");
                  sb.append(rs.getString(3));
                  sb.append("_");
                  sb.append(rs.getString(4));
                  sb.append("_");
                  sb.append(rs.getString(5));
                  sb.append("_");
                  sb.append(rs.getString(6));
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
	 *
	 *��ѯ����һ������
	 *@param terminal_id
	 *@return Zigbee
	 */
	
	public Zigbee queryZigbeeByLately(String terminal_id){

		Zigbee z = null;
		String sql = null;
		
		try {
			if(terminal_id==null||terminal_id.length()<=0)
			{
				sql =  "select * from zigbee id ORDER BY data_time DESC LIMIT 1";
			}
			else{
				sql =  "select * from zigbee where terminal_id='"+terminal_id+"' ORDER BY data_time DESC LIMIT 1";
			}
			
			ResultSet rs = db.query(sql);

			while (rs.next()) {
				z = new Zigbee();
				z.setId(rs.getInt("id"));
				z.setTerminal_id(rs.getString("terminal_id"));
				z.setTemperature(rs.getString("temperature"));	
				z.setHumidity(rs.getString("humidity"));
				z.setAll_light_status(rs.getString("all_light_status"));
				z.setData_time(rs.getString("data_time"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return z;
	}
	
	/**
	 * �޸��ն�һ�豸״̬ 
	 * @param terminal_id change_sta 
	 * @return void
	 */
	
	
	public void updateZigbeeStatus(String terminal_id, String change_status) 
	{   
		String sql = "update zigbee set all_light_status='"+change_status+"' where  terminal_id ='" +terminal_id+ "'and id ORDER BY data_time desc LIMIT 1";
		
		db.update(sql);
	}
	
	
	/**
	 * ��ѯ���е��ն�id
	 * @param void
	 * @return List
	 */
	
	public List queryZigbeeTerminal_id_all(){
		List<String> Terminal_id_all_list =  new ArrayList();
		String sql = null;
		
		try {
			
			sql =  "select distinct terminal_id from zigbee";
			
			ResultSet rs = db.query(sql);

			while (rs.next()) {
				Terminal_id_all_list.add(rs.getString("terminal_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return Terminal_id_all_list;
	}
	
	
}
