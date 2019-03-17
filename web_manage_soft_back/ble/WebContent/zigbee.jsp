<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>zigbee设备管理</title>
</head>
<body>
<%
 
			
			Cookie cc[]= request.getCookies();//获取客户端本地的cookie数据
		
			boolean flag=false;//表示没有登录
			String username="";//用户姓名
			for(Cookie c:cc){
				if(c.getName().equals("islogin")){
					if(c.getValue().equals("true")){
						flag=true;
					}
				}
				if(c.getName().equals("username")){
					username=c.getValue();
				}
			}
			
		 %>
	<div class="header">
		<div class="am-g">
			<h1><a href="./index.jsp">Ble Manage</a></h1>
			<p>
				Openwrt bluetooth smart home<br /> 路由器丨智能家居控制中心
			</p>
		</div>
		<hr />

</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
   <h1>欢迎:<%= username%></h1>
    
   	<div data-am-widget="list_news"
			class="am-list-news am-list-news-default">
			<!--列表标题-->
			<div class="am-list-news-hd am-cf">
				<!--带更多链接-->
				
				
				<h2>zigbee设备列表</h2>
				
			</div>
	</div>
	
	<div class="am-scrollable-horizontal">
				<table  class="am-table am-table-bordered am-table-striped am-text-nowrap am-table-centered" id="zigbee_list">
				    <thead>
				        <tr>
				            <th>终端</th>
				            <th>温度</th>
				            <th>湿度</th>
				            <th>状态</th>
				            <th>开关</th>
				        </tr>
				        
				    </thead>
				    <tbody id="zigbee_tablebody">
   					</tbody>
				</table>
			</div>
  </div>
</div>

<footer data-am-widget="footer" class="am-footer am-footer-default"
		data-am-footer="{  }">

		<div class="am-footer-miscs ">

			<p>
				由 <a href="http://mzl0101.com/" title="开发者博客" target="_blank"
					class="">mzl</a> 提供技术支持
			</p>
			<span id="time_show"></span>
			<p>CopyRight©2018 Inc. Licensed under MIT license.</p>

		</div>
	</footer>
	<div data-am-widget="gotop" class="am-gotop am-gotop-fixed">
		<a href="#top" title="返回顶部"> <img class="am-gotop-icon-custom"
			src="${CTX_PATH}/img/goTop.gif" />
		</a>
	</div>

<script type="text/javascript">
$(function(){
	//时间函数
	startTime();
})
<%
Cookie cks[]= request.getCookies();
String val=null;
for(Cookie c:cks){
if(c.getName().equals("islogin")){
	val=c.getValue();
}
}

%>

var islogin='<%=val%>';
if(islogin=='null'){
	window.location.href='./login.jsp';
}


window.onload=queryZigbeeLightStatusAll;


/*
 * 查询数据库获取zigbee设备状态
 */
function queryZigbeeLightStatusAll(){
$.AMUI.progress.start();
	
$.ajax({        
    type : "POST", //提交方式
    dataType:"json",
    url : "${CTX_PATH}/servlet/QueryZigbeeStatusTerminalIdAllServlet",//路径
    success : function(result) {
    	
       	  $.each(result,function(index,items){
		
       		var all_light_status;
			if(items.all_light_status==1){
				all_light_status = "关";
			}
			else if(items.all_light_status==0){
				all_light_status = "开";
			}
	  		var tr_data =  "<tr>"
	  			+"<td>"
	  			+items.id+":"+items.terminal_id
	  			+"</td>"
            	+"<td>"
            	+items.temperature
            	+"</td>"
            	+"<td>"
            	+items.humidity
           		+"</td>"
           		+"<td>"
            	+all_light_status
           		+"</td>"
           		+"<td>"
           	 	+"<button type='button' class='am-btn am-btn-danger am-btn-xs click-btn'>"
	         	+ "开关"
	          	+"</button>"
	          	+"</td>"
        		+"</tr>";

	          
	          $("#zigbee_list tbody").append($(tr_data))
	          $("#zigbee_list tbody td").attr("class","am-text-middle");
	          $.AMUI.progress.done();
		        
		        	  		
       	  })
	
     }
}); 
}

/**
 * 按钮开关点击事件
 */
 $("#zigbee_tablebody").on("click","button",function(e){
	var light_status_now = $(this).parent().prev().html().trim();
	var terminal_id = $(this).parent().prev().prev().prev().prev().html().trim();
	//console.log(light_status_now+id);
	if(light_status_now == "开")
	{
		updateZigbeeLightStatusByid(terminal_id,"1");
		$(this).parent().prev().html("关");
	}
	else if(light_status_now == "关")
	{
		updateZigbeeLightStatusByid(terminal_id,"0")
		$(this).parent().prev().html("开");
	}
 })
 
 /*
 * 更新数据库设备状态
 */
function updateZigbeeLightStatusByid(terminal_id,change_sta){

	$.ajax({        
	    type : "POST", //提交方式
	    url : "${CTX_PATH}/servlet/ModifyZigbeeLightServlet",//路径
	    data:{
	    	"terminal_id":terminal_id,
	    	"change_sta":change_sta,
	    },
	    success : function(result) {//返回数据根据结果进行相应的处理
	    	if(result=="success")
	    	{
	    		 $.message('更新成功');
	    	} 
	    	else{
	    		 $.message({
	    		        message:'更新失败',
	    		        type:'error'
	    		 });
	    	}
	    }
	}); 
}
</script>
</body>
</html>