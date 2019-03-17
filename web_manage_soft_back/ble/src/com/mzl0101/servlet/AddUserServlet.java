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

		Md5Utils md5 = new Md5Utils();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		String username = request.getParameter("username");

		String password = request.getParameter("password");
		String user_email = request.getParameter("user_email");
		
		String subjectStr = "用户注册邮件";
		String info = "恭喜您注册成功，您的用户名："
				+ username
				+ ",您的密码："
				+ password
				+ "，请妥善保管，如有问题请联系";
		Users user = new Users();

		user.setUsername(username);
		user.setPassword(md5.md5(password));
		user.setEmail(user_email);
		UsersService us = new UsersService();
		try {
            String result = us.save(user);
            if(result.equals("success"))
            {
            	System.out.print("注册成功");
            	request.setAttribute("msg", "success");
            	 //用户注册成功之后就使用用户注册时的邮箱给用户发送一封Email  
                //发送邮件是一件非常耗时的事情，因此这里开辟了另一个线程来专门发送邮件  
    			SendMail send = new SendMail(user,subjectStr,info);  
    			 //启动线程，线程启动之后就会执行run方法来发送邮件  
                send.start();  
    			request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            else if(result.equals("failed")){
            	System.out.print("注册失败");
            	request.setAttribute("msg", "failed");
    			request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
