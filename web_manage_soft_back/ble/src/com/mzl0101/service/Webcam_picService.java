package com.mzl0101.service;

import java.util.List;

import com.mzl0101.dao.Webcam_picDao;

public class Webcam_picService {
	Webcam_picDao wpd = new Webcam_picDao();

	/**
	 * ��ѯ���ݿ�ͼƬ����
	 * 
	 */
	public List selectWebcam_pic() 
	{
		return wpd.selectWebcam_pic();
	}
	
}
