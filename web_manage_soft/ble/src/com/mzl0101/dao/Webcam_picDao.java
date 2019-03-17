package com.mzl0101.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mzl0101.utils.DBManager;

public class Webcam_picDao {
	public static final String separator = File.separator;
	private DBManager db = new DBManager();

	InputStream in = null;
	File file = null;
	byte[] b = null;
	int i = 0;

	/**
	 * 读取数据库webcam_pic表数据并将图片存在项目目录中
	 */
	public List selectWebcam_pic() {
		List<String> Webcam_data_time_list = new ArrayList();
		try {

			String sql = "select * from webcam_pic";
			ResultSet rs = db.select_pic(sql);
			OutputStream out = null;
			while (rs.next()) {

				in = rs.getBinaryStream("data");

				b = new byte[in.available()]; // 新建保存图片数据的byte数组
				in.read(b);
				/*
				 * file = new
				 * File("F:"+separator+"eclipse_workspace"+separator+"ble"+
				 * separator+"WebContent"+separator+"img"+separator+"webcam"+
				 * separator+i+".jpg"); if (!file.exists()) { //
				 * 先得到文件的上级目录，并创建上级目录，在创建文件 file.getParentFile().mkdir();
				 * file.createNewFile(); } out = new FileOutputStream(file);
				 * out.write(b); out.flush(); out.close(); i++;
				 */

				file = new File("/usr" + separator + "local" + separator + "tomcat" + separator
						+ "webapps" + separator + "ble" + separator + "img" + separator + "webcam" + separator + i
						+ ".jpg");
				file.setWritable(true, false);
				if (!file.exists()) {
					// 先得到文件的上级目录，并创建上级目录，在创建文件
					file.getParentFile().mkdir();
					file.createNewFile();
				}
				out = new FileOutputStream(file);
				out.write(b);
				out.flush();
				out.close();
				i++;

				Webcam_data_time_list.add(rs.getString("data_time").substring(0, 19));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return Webcam_data_time_list;
	}

}
