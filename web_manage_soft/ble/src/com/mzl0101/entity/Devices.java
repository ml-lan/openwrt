package com.mzl0101.entity;

public class Devices {
	private int id; //设备id
	private String uuid; //设备蓝牙mac地址
	private String device_name; //设备名称
	private String device_des; //设备描述
	private String device_status; //设备状态
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getDevice_des() {
		return device_des;
	}
	public void setDevice_des(String device_des) {
		this.device_des = device_des;
	}
	public String getDevice_status() {
		return device_status;
	}
	public void setDevice_status(String device_status) {
		this.device_status = device_status;
	}
	
	
}
