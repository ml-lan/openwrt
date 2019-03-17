package com.mzl0101.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.mzl0101.dao.DevicesDao;

/**
 * Servlet implementation class QueryAllDevices
 */
@WebServlet("/QueryAllDevices")
public class QueryDevicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryDevicesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");  
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		try {
			DevicesDao dd = new DevicesDao();
			String Devices = dd.queryDevices();
			String[] params = Devices.split("%");
			JSONObject devicesJson = new JSONObject();
			for (int i = 0; i < params.length; i++) {

				String[] param = params[i].split("-");
				JSONObject deviceJson = new JSONObject();
				deviceJson.put("id", param[0]);
				deviceJson.put("uuid", param[1]);
				deviceJson.put("device_name", param[2]);
				deviceJson.put("device_des", param[3]);
				deviceJson.put("device_status", param[4]);
				devicesJson.put("ÐòºÅ:" + i, deviceJson);
			}
			String json = devicesJson.toJSONString();
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.getWriter().print("failed");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
