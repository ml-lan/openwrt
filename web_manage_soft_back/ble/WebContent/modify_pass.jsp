<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>修改密码</title>
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
			<h3>修改密码</h3>
			<hr>
			<div class="am-btn-group">
				<a href="#" class="am-btn am-btn-secondary am-btn-sm"><i
					class="am-icon-qq am-icon-sm"></i> QQ</a> <a href="#"
					class="am-btn am-btn-success am-btn-sm"><i
					class="am-icon-github am-icon-sm"></i> Github</a>
			</div>
			<br> <br>

			<form method="post" class="am-form foget_pass_form"
				action="${CTX_PATH}/servlet/ModifyPassServlet">
				<p>请输入重置后的新密码。</p>
				 <label for="password">密码:</label>
			      <input type="password" name="password" id="password" value="">
			       <br>
			      <label for="check_password">重复密码:</label>
			      <input type="password" name="" id="check_password" value="">
			       <br>
			       <input type="text" name="uuid" id="uuid" value="" style="display:none" >
				<div class="am-cf">
					<input type="submit" name="" id="btn_modify_pass" value="立即修改"
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
$("#btn_modify_pass").click(function(){
	var pass=$("#password").val();
	var check_pass=$("#check_password").val();
	if(pass==''){
		alert('密码为必填项');
		return ;
	}
	if(check_pass==''){
		alert('重复密码为必填项');
		return ;
	}
	if(pass!=check_pass){
		alert('两次密码不一致');
		return ;
	}
	
});

function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

var uuid = getQueryString("uuid");
$("#uuid").val(uuid);

<%
String msg = (String)request.getAttribute("msg");
%>
var msg='<%=msg%>';
if(msg=="failed"){
	$.message({
        message:'修改密码失败',
        type:'error'
 	});
	
}
else if(msg=="success"){
	 $.message('修改密码成功');
}
</script>
</body>
</html>