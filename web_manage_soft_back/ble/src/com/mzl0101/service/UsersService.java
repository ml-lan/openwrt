package com.mzl0101.service;

import com.mzl0101.dao.UsersDao;
import com.mzl0101.entity.Users;

public class UsersService {
	private UsersDao userdao = new UsersDao();
	
	public String save(Users s) {
		// ����Ҫ�������ݵ��ж�

		if (userdao.queryByUserName(s.getUsername()) == true) {
			
			return "failed";
		}
		if (userdao.queryByUserEmail(s.getEmail()) == true)
		{
			return "failed";
		}
		userdao.saveUser(s);
		return "success";
	}
	
	public Users userLogin(Users user) {

		Users u = userdao.queryUserByUserName(user.getUsername());
		if (u == null) {
			throw new RuntimeException("���޴���");
		}

		if (!u.getPassword().equals(user.getPassword())) {
			throw new RuntimeException("�û������������");
		}

		return u;
	}

	public void updatePass(String email, String password) 
	{
		userdao.updateUserPass(email, password);
	}

}
