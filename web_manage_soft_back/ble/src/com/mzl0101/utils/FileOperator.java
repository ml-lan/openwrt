package com.mzl0101.utils;

import java.io.File;
import java.io.FileNotFoundException;

public class FileOperator {
	public boolean deletefile(String delpath) throws Exception {
		try {

			File file = new File(delpath);
			// ���ҽ����˳���·������ʾ���ļ������� ��һ��Ŀ¼ʱ������ true
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory()) {
						delfile.delete();
						System.out.println(delfile.getAbsolutePath() + "ɾ���ļ��ɹ�");
					} else if (delfile.isDirectory()) {
						deletefile(delpath + "\\" + filelist[i]);
						System.out.println(file + "ssss");
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("deletefile() Exception:" + e.getMessage());
		}
		return true;
	}
}
