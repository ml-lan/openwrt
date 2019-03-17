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
import com.mzl0101.entity.Zigbee;
import com.mzl0101.service.ZigbeeService;

/**
 * Servlet implementation class QueryZigbeeByLately
 */
@WebServlet("/QueryZigbeeByLately")
public class QueryZigbeeByLately extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryZigbeeByLately() {
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
		/*PrintWriter writer = response.getWriter();  
        writer.println("GET " + request.getRequestURL() + " "  
                + request.getQueryString());  
		 */
		String queryString = "";  
        Map<String, String[]> params = request.getParameterMap(); 
        if(params.size()>0){
        	
            for (String key : params.keySet()) {  
                String[] values = params.get(key);  
                for (int i = 0; i < values.length; i++) {  
                    String value = values[i];  
                    queryString += key + "=" + value + "&";  
                }  
            }  
            // 去掉最后一个空格  
            queryString = queryString.substring(0, queryString.length() - 1);  
        }
        
        //writer.println("GET " + request.getRequestURL() + " " + queryString);
        
        //System.out.print(queryString);
        
        String terminal_id;
        ZigbeeService zs = new ZigbeeService();
        if("".equals(queryString) ||queryString == null)
        {
        	terminal_id = null;
        }
        else{
        	terminal_id  = queryString.substring(12);
        }
		
		try {
			//System.out.print(terminal_id);
			Zigbee zb = zs.queryZigbeeByLately(terminal_id);
			JSONObject zigbeeJsonLately = new JSONObject();
			zigbeeJsonLately.put("id", zb.getId());
			zigbeeJsonLately.put("terminal_id", zb.getTerminal_id());
			zigbeeJsonLately.put("temperature", zb.getTemperature());
			zigbeeJsonLately.put("humidity", zb.getHumidity());
			zigbeeJsonLately.put("all_light_status", zb.getAll_light_status());
			zigbeeJsonLately.put("data_time", zb.getData_time().substring(0, 19));
			String json = zigbeeJsonLately.toJSONString();
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			PrintWriter writer = response.getWriter();  
	        writer.println("没有数据"); 
	       
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.doGet(request, response);
		
	}

}
