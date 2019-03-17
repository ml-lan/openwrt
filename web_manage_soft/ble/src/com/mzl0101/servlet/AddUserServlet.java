package com.mzl0101.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mzl0101.entity.Users;
import com.mzl0101.service.UsersService;
import com.mzl0101.utils.Md5Utils;
import com.mzl0101.utils.SendMail;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		String username = request.getParameter("username");

		String password = request.getParameter("password");
		String user_email = request.getParameter("user_email");
		
		String subjectStr = "�û�ע���ʼ�";
		String info = "��ϲ��ע��ɹ��������û�����"
				+ username
				+ ",�������룺"
				+ password
				+ "�������Ʊ��ܣ�������������ϵ";
		Users user = new Users();

		user.setUsername(username);
		user.setPassword(Md5Utils.md5(password));
		user.setEmail(user_email);
		UsersService us = new UsersService();
		try {
            String result = us.save(user);
            if(result.equals("success"))
            {
            	System.out.print("ע��ɹ�");
            	request.setAttribute("msg", "success");
            	 //�û�ע��ɹ�֮���ʹ���û�ע��ʱ��������û�����һ��Email  
                //�����ʼ���һ���ǳ���ʱ�����飬������￪������һ���߳���ר�ŷ����ʼ�  
    			SendMail send = new SendMail(user,subjectStr,info);  
    			 //�����̣߳��߳�����֮��ͻ�ִ��run�����������ʼ�  
                send.start();  
    			request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            else if(result.equals("failed")){
            	System.out.print("ע��ʧ��");
            	request.setAttribute("msg", "failed");
    			request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
