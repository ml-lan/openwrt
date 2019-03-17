package com.mzl0101.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.mzl0101.dao.DevicesDao;
import com.mzl0101.dao.ZigbeeDao;
import com.mzl0101.service.ZigbeeService;

/**
 * Servlet implementation class QueryZigbeeServlet
 */
@WebServlet("/QueryZigbeeServlet")
public class QueryZigbeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryZigbeeServlet() {
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
		String queryString = "";  
        Map<String, String[]> params_url = request.getParameterMap(); 
        if(params_url.size()>0){
        	
            for (String key : params_url.keySet()) {  
                String[] values = params_url.get(key);  
                for (int i = 0; i < values.length; i++) {  
                    String value = values[i];  
                    queryString += key + "=" + value + "&";  
                }  
            }  
            // 去掉最后一个空格  
            queryString = queryString.substring(0, queryString.length() - 1);  
        } 
        String terminal_id;
      
        if("".equals(queryString) ||queryString == null)
        {
        	terminal_id = null;
        }
        else{
        	terminal_id  = queryString.substring(12);
        }
		try {
			ZigbeeService zs = new ZigbeeService();
			String zigbee_str = zs.queryZigbee(terminal_id);
			String[] params = zigbee_str.split("%");
			JSONObject zigbeesJson = new JSONObject();
			for (int i = 0; i < params.length; i++) {

				String[] param = params[i].split("_");
				JSONObject zigbeeJson = new JSONObject();
				zigbeeJson.put("id", param[0]);
				zigbeeJson.put("terminal_id", param[1]);
				zigbeeJson.put("temperature", param[2]);
				zigbeeJson.put("humidity", param[3]);
				zigbeeJson.put("all_light_status", param[4]);
				zigbeeJson.put("data_time", param[5].substring(0, 19));
				zigbeesJson.put("序号:" + i, zigbeeJson);
			}
			String json = zigbeesJson.toJSONString();
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
	        response.getWriter().print("failed");
		}
	}

}
