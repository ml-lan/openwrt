package com.mzl0101.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mzl0101.utils.SendMail;

/**
 * Servlet implementation class FindPassServlet
 */
@WebServlet("/FindPassServlet")
public class FindPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPassServlet() {
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
		
		PrintWriter out =response.getWriter();
       
		String uuid = UUID.randomUUID().toString();
		String user_email = request.getParameter("user_email");
		ServletContext sc = getServletContext(); 
		sc.setAttribute("uuid", uuid);  
		sc.setAttribute("user_email", user_email);  
		String subjectStr = "找回密码邮件";
		String info = "下面是找回密码的来链接，请点击进入后重置密码。"
					+"http://104.224.163.27:8080/ble/modify_pass.jsp?uuid="
					+uuid;
	
		
		try {
			SendMail send = new SendMail(user_email, subjectStr, info);
			send.start();
			request.setAttribute("msg", "success");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("msg", "failed");
		}  

	}

}
