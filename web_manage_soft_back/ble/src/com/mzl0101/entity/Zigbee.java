package com.mzl0101.entity;

public class Zigbee {
	private int id; 
	private String terminal_id;
	private String temperature; 
	private String humidity;
	private String all_light_status; 
	private String data_time; 

	
	public String getAll_light_status() {
		return all_light_status;
	}
	public void setAll_light_status(String all_light_status) {
		this.all_light_status = all_light_status;
	}

	public String getData_time() {
		return data_time;
	}
	public void setData_time(String data_time) {
		this.data_time = data_time;
	}
	public int getId() {
		return id;
	}
	public String getTerminal_id() {
		return terminal_id;
	}
	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

}
