package com.mzl0101.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mzl0101.service.ZigbeeService;

/**
 * Servlet implementation class ModifyZigbeeServlet
 */
@WebServlet("/ModifyZigbeeServlet")
public class ModifyZigbeeLightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyZigbeeLightServlet() {
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
		String terminal_id = request.getParameter("terminal_id");
		String change_sta = request.getParameter("change_sta");
		
		try {
			ZigbeeService zs = new ZigbeeService();
			zs.updateById(terminal_id, change_sta);
			response.getWriter().print("success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
		}
	}

}
