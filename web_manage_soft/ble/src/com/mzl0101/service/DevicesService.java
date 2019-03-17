package com.mzl0101.service;

import com.mzl0101.dao.DevicesDao;

public class DevicesService {
	DevicesDao dd = new DevicesDao();
	public void update(String id, String change_sta) 
	{
		dd.updateDeviceStatus(id, change_sta);
	}
	public void modify(String id, String device_name, String device_des) 
	{
		dd.modifyDevice(id, device_name,device_des);
	}

	public void timingDeviceTask(String id, String set_time, String change_sta) 
	{
		int setting_time = Integer.valueOf(set_time);
		try {
			Thread.currentThread();
			Thread.sleep(setting_time*60*1000);
			dd.updateDeviceStatus(id, change_sta);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//毫秒  
		
	}
}
