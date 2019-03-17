package com.mzl0101.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzl0101.service.Webcam_picService;
import com.mzl0101.utils.FileOperator;

/**
 * Servlet implementation class SelectWebcam_picServlet
 */
@WebServlet("/SelectWebcam_picServlet")
public class SelectWebcam_picServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectWebcam_picServlet() {
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
		String id = request.getParameter("id");
		Webcam_picService wps = new Webcam_picService();
		try {
			List Webcam_pic_data_time_list = wps.selectWebcam_pic();
			JSONArray json = JSONArray.parseArray(JSON.toJSONString(Webcam_pic_data_time_list));
			response.getWriter().print(json);
			/*Thread.currentThread();
			Thread.sleep(1*60*1000);
			FileOperator fo = new FileOperator();
			fo.deletefile("F:\\eclipse_workspace\\ble\\WebConetent\\img\\webcam");*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
