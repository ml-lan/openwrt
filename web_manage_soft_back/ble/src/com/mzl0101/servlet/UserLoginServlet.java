package com.mzl0101.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mzl0101.entity.Users;
import com.mzl0101.service.UsersService;
import com.mzl0101.utils.Md5Utils;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginServlet() {
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

		Users user = new Users();
		user.setUsername(username);
		user.setPassword(md5.md5(password));

		UsersService us = new UsersService();
		try {
			Users check_u = us.userLogin(user);
			request.setAttribute("check_u", check_u);
			request.setAttribute("msg", "success");
			Cookie c1 = new Cookie("islogin", "true");
			Cookie c2 = new Cookie("username", username);
			c1.setMaxAge(60 * 60 * 24 * 30 * 4);
			c1.setPath("/");

			c2.setMaxAge(60 * 60 * 24 * 30 * 4);
			c2.setPath("/");

			response.addCookie(c1);
			response.addCookie(c2);
			response.sendRedirect("../manage.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "failed");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

}
