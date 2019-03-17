<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>忘记密码</title>
<style type="text/css">
.foget_pass_form p {
	text-indent: 3rem;
}
</style>
</head>
<body>
	<div class="header">
		<div class="am-g">
			<h1>
				<a href="./index.jsp">Ble Manage</a>
			</h1>
			<p>
				Openwrt bluetooth smart home<br /> 路由器丨智能家居控制中心
			</p>
		</div>
		<hr />

		<nav data-am-widget="menu" class="am-menu  am-menu-dropdown1"
			data-am-menu-collapse>
			<a href="javascript: void(0)" class="am-menu-toggle"> <img
				src="data:image/svg+xml;charset&#x3D;utf-8,&lt;svg xmlns&#x3D;&quot;http://www.w3.org/2000/svg&quot; viewBox&#x3D;&quot;0 0 42 26&quot; fill&#x3D;&quot;%23fff&quot;&gt;&lt;rect width&#x3D;&quot;4&quot; height&#x3D;&quot;4&quot;/&gt;&lt;rect x&#x3D;&quot;8&quot; y&#x3D;&quot;1&quot; width&#x3D;&quot;34&quot; height&#x3D;&quot;2&quot;/&gt;&lt;rect y&#x3D;&quot;11&quot; width&#x3D;&quot;4&quot; height&#x3D;&quot;4&quot;/&gt;&lt;rect x&#x3D;&quot;8&quot; y&#x3D;&quot;12&quot; width&#x3D;&quot;34&quot; height&#x3D;&quot;2&quot;/&gt;&lt;rect y&#x3D;&quot;22&quot; width&#x3D;&quot;4&quot; height&#x3D;&quot;4&quot;/&gt;&lt;rect x&#x3D;&quot;8&quot; y&#x3D;&quot;23&quot; width&#x3D;&quot;34&quot; height&#x3D;&quot;2&quot;/&gt;&lt;/svg&gt;"
				alt="Menu Toggle" />
			</a>


			<ul class="am-menu-nav am-avg-sm-1 am-collapse">
				<li class=""><a
					href="./register.jsp" class="">注册</a>
				</li>
				<li class=""><a href="./login.jsp"
					class="">登录</a></li>
			</ul>

		</nav>

	</div>
	<div class="am-g">
		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
			<h3>忘记密码</h3>
			<hr>
			<div class="am-btn-group">
				<a href="#" class="am-btn am-btn-secondary am-btn-sm"><i
					class="am-icon-qq am-icon-sm"></i> QQ</a> <a href="#"
					class="am-btn am-btn-success am-btn-sm"><i
					class="am-icon-github am-icon-sm"></i> Github</a>
			</div>
			<br> <br>

			<form method="post" class="am-form foget_pass_form"
				action="${CTX_PATH}/servlet/FindPassServlet">
				<p>请输入注册时的邮箱，我们会在一会给您发送一封邮件，请您登录邮箱查看邮件，点击链接进入修改密码。</p>
				<label for="user_email">邮箱:</label> <input type="email"
					name="user_email" id="user_email" value=""> <br>
				<div class="am-cf">
					<input type="submit" name="" id="btn_find_pass" value="立即申请找回"
						class="am-btn am-btn-primary am-btn-block">

				</div>
			</form>

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
$("#btn_find_pass").click(function(){
	var user_email=$("#email").val();
	if(user_email==''){
		alert('邮箱为必填项');
		return ;
	}
	
});
<%
String msg = (String)request.getAttribute("msg");
%>
var msg='<%=msg%>';
if(msg=="failed"){
	$.message({
        message:'邮件发送失败',
        type:'error'
 	});
	
}
else if(msg=="success"){
	 $.message('邮件已发送');
}
</script>
</body>
</html>