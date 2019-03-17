<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>设备管理</title>
<style type="text/css">
#device_list li{
	height:150px;
	position:relative;
}
#device_list li img {
	width: 60%;
	padding:1rem 0rem 0rem 1rem;
}
.modify-btn{
position:absolute;
width:5rem;
bottom:1rem;
left:10rem;
}
.click-btn{
position:absolute;
width:5rem;
bottom:1rem;
right:3rem;
}
.status-lab p{
font-weight:800;
text-align: center;
}
.time_set{
display:none;
}
#devices_id  + div{
float:left;
}
#set_time + div{
float:right;
}
#set_time + div +p{
margin-top:2rem;
}
</style>
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
				
				<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
				
				
				<h2>蓝牙设备列表</h2>
				
			</div>

			<div class="am-list-news-bd">
				<ul class="am-list" id="device_list">

					<!--缩略图在标题下方居左-->

					
				</ul>
			</div>

		</div>

		<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
		  <div class="am-modal-dialog">
		    <div class="am-modal-hd">编辑</div>
		    <div class="am-modal-bd">
		      <label for="device_name">设备名称:</label>
		      <input type="text" class="am-modal-prompt-input">
		      <label for="device_des">设备描述:</label>
		      <input type="text" class="am-modal-prompt-input">
		    </div>
		    <div class="am-modal-footer">
		      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
		      <span class="am-modal-btn" data-am-modal-confirm>提交</span>
		    </div>
		  </div>
		</div>
		
		
		<div data-am-widget="list_news"
			class="am-list-news am-list-news-default time_set">
			<!--列表标题-->
			<div class="am-list-news-hd am-cf">
				<!--带更多链接-->
			
				<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
				<h2>定时设置</h2>
				
			</div>

			<div class="am-list-news-bd" id="time_selects">
				
				
				<select data-am-selected="{dropUp: 1, btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}" id="devices_id">
				  <option value="0">序号0</option>
				  <option value="1">序号1</option>
				  <option value="2">序号2</option>
				  <option value="3">序号3</option>
				  <option value="4">序号4</option>
				</select>
				
				<select data-am-selected="{dropUp: 1, btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}" id="set_time">
				  <option value="5">5</option>
				  <option value="10">10</option>
				  <option value="15">15</option>
				  <option value="20">20</option>
				  <option value="25">25</option>
				  <option value="30">30</option>
				</select>
				<div class="clear"></div>
				 <p>
				 <button class="am-btn am-btn-secondary  am-btn-lg custom_time_btn am-btn-block" >自定义时间</button>
				 </br>
				 <button class="am-btn am-btn-primary  am-btn-lg custom_time_sub am-btn-block" >设置提交</button>
				 
				 </p>
			</div>
			
			

		</div>	
		<div class="am-modal am-modal-prompt" tabindex="-1" id="time-prompt">
		  <div class="am-modal-dialog">
		    <div class="am-modal-hd">自定义时间</div>
		    <div class="am-modal-bd">
		      <input type="text" class="am-modal-prompt-input">
		    </div>
		    <div class="am-modal-footer">
		      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
		      <span class="am-modal-btn" data-am-modal-confirm>提交</span>
		    </div>
		  </div>
		</div>
		
		
		<div data-am-widget="list_news"
			class="am-list-news am-list-news-default time_set">
			<!--列表标题-->
			<div class="am-list-news-hd am-cf">
				<!--带更多链接-->
			
				<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
				<h2><a href="./zigbee.jsp">zigbee管理</a></h2>
				
			</div>
			
			<div class="am-list-news-bd">
				<figure data-am-widget="figure"
				class="am am-figure am-figure-default "
				data-am-figure="{  pureview: 'true' }">



				<img src="${CTX_PATH}/img/zigbee_core.jpg"
					data-rel="${CTX_PATH}/img/zigbee_core.jpg" alt="zigbee_core" />
				<figcaption class="am-figure-capition-btm">zigbee协调器示意图</figcaption>


				</figure>
				
				
				<figure data-am-widget="figure"
				class="am am-figure am-figure-default "
				data-am-figure="{  pureview: 'true' }">



				<img src="${CTX_PATH}/img/zigbee_devices.jpg"
					data-rel="${CTX_PATH}/img/zigbee_devices.jpg" alt="zigbee_devices" />
				<figcaption class="am-figure-capition-btm">zigbee开发板示意图</figcaption>


				</figure>
				<a class="am-btn am-btn-success am-btn-primary am-btn-block" href="./zigbee.jsp" onclick="if(confirm('确认吗？')==false)return false;">进入zigbee管理</a>
			
			</div>
		
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
window.onload=queryDevice;

/*
 * 查询数据库获取设备状态
 */
function queryDevice(){
$.AMUI.progress.start();
	
$.ajax({        
    type : "GET", //提交方式
    dataType:"json",
    url : "${CTX_PATH}/servlet/QueryDevicesServlet",//路径
     success : function(result) {//返回数据根据结果进行相应的处理
    	
       	  $.each(result,function(index,items){
				var device_status;
				if(items.device_status==1){
					device_status = "关";
				}
				else if(items.device_status==0){
					device_status = "开";
				}
		  		var lidata = "<li class='am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-bottom-left'>"
		          +"<h3 class='am-list-item-hd'>"
		          +index
		          +items.device_name
		          +"</h3>"
		          +"<div class='am-u-sm-4 am-list-thumb'>"
		          +"<img src='${CTX_PATH}/img/wind.jpg' alt=''/>"
		          +"</div>"
		          +"<div class=' am-u-sm-4  am-list-main'>"
		          +"<div class='am-list-item-text'>"
		          +items.device_des
		          +"</div>"
		          +"</div>"
		          +" <div class=' am-u-sm-4  am-list-main right'>"
		          +"<div class='am-list-item-text status-lab'><p>"
		          +device_status
		          +"</p></div>"
		          +"</div>"
		          +"<button type='button' class='am-btn am-btn-secondary am-btn-xs modify-btn'>"
		          + "编辑"
		          +"</button>"
		          +"<button type='button' class='am-btn am-btn-danger am-btn-xs click-btn'>"
		          + "开关"
		          +"</button>"
		          +"</li>";
		          $("#device_list").append($(lidata))
		         
		          $.AMUI.progress.done();
		          $(".time_set").css("display","block");
		        	  		
       	  })
	
     }
}); 
}
/*
 * 绑定事件
 */
$(function () {
	$("#device_list").on("click",".click-btn",function(e){
		var status = $(this).parent().children(".right").children().children("p").html().trim();
		var label_all = $(this).parent().children("h3").html().trim();
		var id_num = parseInt(label_all.substr(3,1))+1;
		var id = id_num.toString();
		if(status=="关")
		{
			console.log(id);
			console.log("点击实现开");
			updateDeviceStatus(id,"0");
			$(this).parent().children(".right").children().children("p").html("开");
		}
		else if(status=="开")
		{
			console.log(id);
			console.log("点击实现关");
			updateDeviceStatus(id,"1");
			$(this).parent().children(".right").children().children("p").html("关");
			
		}
		//console.log(e.target.tagName+"is clicked");	
	})
});

/*
 * 更新数据库设备状态
 */
function updateDeviceStatus(id,change_sta){

	$.ajax({        
	    type : "POST", //提交方式
	    url : "${CTX_PATH}/servlet/UpdateDevicesServlet",//路径
	    data:{
	    	"id":id,
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

$(function() {
	  $.AMUI.progress.start();
	  $("#device_list").on("click",".modify-btn",function(e){
		  var label_all = $(this).parent().children("h3").html().trim();
		  var id_num = parseInt(label_all.substr(3,1))+1;
		  var id = id_num.toString();
		  $('#my-prompt').modal({
		      relatedTarget: this,
		      onConfirm: function(e) {
		        //alert('你输入的是：' + e.data || '');
		       
				if(e.data[0]!==""&&e.data[1]!=="")
				{
					//console.log(e.data);
					modifyDeviceStatus(id,e.data[0],e.data[1]);
					$.AMUI.progress.done();
				}
				else{
					$.message({
	    		        message:'编辑失败',
	    		        type:'error'
	    		 	});
				}
		      },
		      onCancel: function(e) {
		    	  	$.message({
		    	        message:'编辑已取消',
		    	        type:'info'
		    	    });
		      }
		    });
	  })

});

/**
 * 编辑设备名称及描述
 */
function modifyDeviceStatus(id,device_name,device_des){

	$.ajax({        
	    type : "POST", //提交方式
	    url : "${CTX_PATH}/servlet/ModifyDevicesServlet",//路径
	    data:{
	    	"id":id,
	    	"device_name":device_name,
	    	"device_des":device_des,
	    },
	    success : function(result) {//返回数据根据结果进行相应的处理
	    	if(result=="success")
	    	{
	    		 $.message('编辑成功');
	    	} 
	    	else{
	    		 $.message({
	    		        message:'编辑失败',
	    		        type:'error'
	    		 });
	    	}
	    }
	}); 
}



$(function() {
	 
	  $(".custom_time_btn").on("click",function(e){
		 
		  $('#time-prompt').modal({
		      relatedTarget: this,
		      onConfirm: function(e) {
		        //alert('你输入的是：' + e.data || '');
		      
				if(e.data[0]!=="")
				{
					//console.log(e.data);
					$("#set_time").append("<option value='"+e.data+"'>"+e.data+"</option>");
					$("#set_time").find("option[value="+e.data+"]").attr("selected",true);  
				}
				else{
					$.message({
	    		        message:'自定义失败',
	    		        type:'error'
	    		 	});
				}
		      },
		      onCancel: function(e) {
		    	  	$.message({
		    	        message:'自定义已取消',
		    	        type:'info'
		    	    });
		      }
		    });
	  })
	  
	 
	  
	  $(".custom_time_sub").on("click",function(e){
		 	 var id = $("#devices_id option:selected").val();
			 var set_time = $("#set_time option:selected").val();
			 var num_id = parseInt(id);
			 //console.log(id+" "+set_time);
			 var id_device_sta = $($("#device_list").children().children("h3")[num_id]).next().next().next().children().children("p").html().trim();
			 if(id_device_sta=="开")
				 {
				 timingDeviceTask(id,set_time,"1");
				 }
			 else if(id_device_sta=="关"){
				 timingDeviceTask(id,set_time,"0")
			 }
			 else{
				 return;
			 }
			 
	  })

});

/**
 * 定时任务
 */
 
 function timingDeviceTask(id,set_time,change_sta){

		$.ajax({        
		    type : "POST", //提交方式
		    url : "${CTX_PATH}/servlet/TimingDevicesTaskServlet",//路径
		    data:{
		    	"id":id,
		    	"set_time":set_time,
		    	"change_sta":change_sta,
		    },
		    success : function(result) {//返回数据根据结果进行相应的处理
		    	if(result=="success")
		    	{
		    		 $.message('设置成功');
		    	} 
		    	else{
		    		 $.message({
		    		        message:'设置失败',
		    		        type:'error'
		    		 });
		    	}
		    }
		}); 
	}
 <%
 String msg = (String)request.getAttribute("msg");
 %>
 var msg='<%=msg%>';
 if(msg=="failed"){
 	$.message({
         message:'修改失败',
         type:'error'
  	});
 	
 }
 else if(msg=="success"){
 	 $.message('修改成功');
 }
 

</script>
</body>
</html>