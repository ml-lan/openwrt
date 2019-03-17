package com.mzl0101.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mzl0101.service.UsersService;
import com.mzl0101.utils.Md5Utils;

/**
 * Servlet implementation class ModifyPassServlet
 */
@WebServlet("/ModifyPassServlet")
public class ModifyPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyPassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		String password = request.getParameter("password");
		String page_uuid = request.getParameter("uuid");
		ServletContext sc = getServletContext();  
	    String uuid = (String)sc.getAttribute("uuid");
	    String user_email = (String)sc.getAttribute("user_email");
	    UsersService us = new UsersService();
	    if(page_uuid.equals(uuid)){
	    	try {
				us.updatePass(user_email, Md5Utils.md5(password));
				request.setAttribute("msg", "success");
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				request.setAttribute("msg", "failed");
			}
	    }
	    
		
	}

}
