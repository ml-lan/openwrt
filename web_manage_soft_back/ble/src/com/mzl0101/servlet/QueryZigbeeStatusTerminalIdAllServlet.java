package com.mzl0101.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.mzl0101.entity.Zigbee;
import com.mzl0101.service.ZigbeeService;

/**
 * Servlet implementation class QueryZigbeeStatusTerminalIdAllServlet
 */
@WebServlet("/QueryZigbeeStatusTerminalIdAllServlet")
public class QueryZigbeeStatusTerminalIdAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryZigbeeStatusTerminalIdAllServlet() {
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
		
		 try {
			ZigbeeService zs = new ZigbeeService();
			ArrayList<Zigbee> zigbee_list_result = new ArrayList<Zigbee>();
			zigbee_list_result = zs.queryZigbeeByLatelyALL();
			JSONObject zigbee_list_results_json = new JSONObject();
			/*for (Zigbee zb : zigbee_list_result) {
				System.out.println(zb.getAll_light_status());
				System.out.println(zb.getTerminal_id());
				System.out.println(zb.getData_time());
				System.out.println(zb.getHumidity());
				System.out.println(zb.getTemperature());
				zigbee_list_result_json.put("id", zb.getId());
				zigbee_list_result_json.put("terminal_id", zb.getTerminal_id());
				zigbee_list_result_json.put("temperature", zb.getTemperature());
				zigbee_list_result_json.put("humidity", zb.getHumidity());
				zigbee_list_result_json.put("all_light_status", zb.getAll_light_status());
				zigbee_list_result_json.put("data_time", zb.getData_time().substring(0, 19));
				zigbee_list_results_json.put("ÐòºÅ:", zigbee_list_result_json);
			}*/
			for(int i=0;i<zigbee_list_result.size();i++)
			{
				Zigbee zb = zigbee_list_result.get(i);
				JSONObject zigbee_list_result_json = new JSONObject();
				zigbee_list_result_json.put("id", zb.getId());
				zigbee_list_result_json.put("terminal_id", zb.getTerminal_id());
				zigbee_list_result_json.put("temperature", zb.getTemperature());
				zigbee_list_result_json.put("humidity", zb.getHumidity());
				zigbee_list_result_json.put("all_light_status", zb.getAll_light_status());
				zigbee_list_result_json.put("data_time", zb.getData_time().substring(0, 19));
				zigbee_list_results_json.put("ÐòºÅ:"+i, zigbee_list_result_json);
			}
			String json = zigbee_list_results_json.toJSONString();
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
