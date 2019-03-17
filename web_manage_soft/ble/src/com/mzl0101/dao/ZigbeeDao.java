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
	 *根据终端id查询所有的操作记录
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
				  sb.append(rs.getInt(1)); // 读出每一列的数据
                  sb.append("_"); 			  // 在每列数据后面做标记，将来便于做拆分
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
			
			result = sb.toString();// 将数据由StringBuffer类型转化成String类型
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return result;
	}
	
	
	
	/**
	 *
	 *查询最新一条数据
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
	 * 修改终端一设备状态 
	 * @param terminal_id change_sta 
	 * @return void
	 */
	
	
	public void updateZigbeeStatus(String terminal_id, String change_status) 
	{   
		String sql = "update zigbee set all_light_status='"+change_status+"' where  terminal_id ='" +terminal_id+ "'and id ORDER BY data_time desc LIMIT 1";
		
		db.update(sql);
	}
	
	
	/**
	 * 查询所有的终端id
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
