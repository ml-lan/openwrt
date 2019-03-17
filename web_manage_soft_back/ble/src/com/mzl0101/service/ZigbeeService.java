package com.mzl0101.service;

import java.util.ArrayList;
import java.util.List;

import com.mzl0101.dao.ZigbeeDao;
import com.mzl0101.entity.Zigbee;

public class ZigbeeService {

	ZigbeeDao zd = new ZigbeeDao();
	
	/*
	 * 根据终端id查询所有的操作记录
	 */
	public String queryZigbee(String terminal_id) 
	{
		return zd.queryZigbee(terminal_id);
	}
	/*
	 * 根据终端id查询终端最新的状态
	 */
	public Zigbee queryZigbeeByLately(String terminal_id) 
	{
		return zd.queryZigbeeByLately(terminal_id);
	}
	/*
	 * 更新zigbee light 状态
	 */
	public void updateById(String terminal_id, String change_status) 
	{   
		zd.updateZigbeeStatus(terminal_id, change_status);	
	}
	
	/*
	 * 查询所有终端的最新状态。
	 */
	public  ArrayList queryZigbeeByLatelyALL() 
	{
		List terminal_id_list = zd.queryZigbeeTerminal_id_all();
		ArrayList<Zigbee> zigbee_list=new ArrayList<Zigbee>();  
		for(int i=0;i<terminal_id_list.size();i++)
		{
			zigbee_list.add(zd.queryZigbeeByLately((String) terminal_id_list.get(i)));
		}
		
		return zigbee_list;
	}
}
