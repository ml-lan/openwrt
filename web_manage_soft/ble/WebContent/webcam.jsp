<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>智能监控</title>
<style type="text/css">
.webcam_img_list{
	display:none;
}
.webcam_img_list li label{
	margin-bottom: 7px;
    margin-left: 8px;
    margin-top: -10px;
}
</style>
</head>
<body>

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
		

		
		<div data-am-widget="list_news"
			class="am-list-news am-list-news-default">
			<!--列表标题-->
			<div class="am-list-news-hd am-cf">
				<!--带更多链接-->
				
				<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
				<h2>监控</h2>
				
			</div>

			<ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-thumbnails webcam_img_list">
		
			</ul>

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
window.onload=selectWebcam;
function selectWebcam(){
	$.ajax({        
	    type : "POST", //提交方式
	    url : "${CTX_PATH}/servlet/SelectWebcam_picServlet",//路径
	    dataType:"json",
	    success : function(result) {//返回数据根据结果进行相应的处理
	    	if(result)
	    	{
	    		 $.each(result,function(index,item){
	    			 console.log(item);
	    			 var li_data = "<li><img class='am-thumbnail' src='"
	    			 +"${CTX_PATH}/img/webcam/"
	    			 +index
	    			 +".jpg"
	    			 +"'>"
	    			 +"<label>"
	    			 +item
	    			 +"</label>"
	    			 +"</li>";
	    			 $(".webcam_img_list").append(li_data);
	    			 
	    		 })
	    		
	    		 $(".webcam_img_list").css("display","block");
	    		 $.message('查询成功');
	    		 
	    		
	    	} 
	    	else{
	    		 $.message({
	    		        message:'查询失败',
	    		        type:'error'
	    		 });
	    	}
	    }
	}); 

}
</script>
</body>
</html>