<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
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
	
   <nav data-am-widget="menu" class="am-menu  am-menu-dropdown1"  
     data-am-menu-collapse
    
    
    
    > 
    <a href="javascript: void(0)" class="am-menu-toggle">
        <img src="data:image/svg+xml;charset&#x3D;utf-8,&lt;svg xmlns&#x3D;&quot;http://www.w3.org/2000/svg&quot; viewBox&#x3D;&quot;0 0 42 26&quot; fill&#x3D;&quot;%23fff&quot;&gt;&lt;rect width&#x3D;&quot;4&quot; height&#x3D;&quot;4&quot;/&gt;&lt;rect x&#x3D;&quot;8&quot; y&#x3D;&quot;1&quot; width&#x3D;&quot;34&quot; height&#x3D;&quot;2&quot;/&gt;&lt;rect y&#x3D;&quot;11&quot; width&#x3D;&quot;4&quot; height&#x3D;&quot;4&quot;/&gt;&lt;rect x&#x3D;&quot;8&quot; y&#x3D;&quot;12&quot; width&#x3D;&quot;34&quot; height&#x3D;&quot;2&quot;/&gt;&lt;rect y&#x3D;&quot;22&quot; width&#x3D;&quot;4&quot; height&#x3D;&quot;4&quot;/&gt;&lt;rect x&#x3D;&quot;8&quot; y&#x3D;&quot;23&quot; width&#x3D;&quot;34&quot; height&#x3D;&quot;2&quot;/&gt;&lt;/svg&gt;" alt="Menu Toggle"/>
    </a>


      <ul class="am-menu-nav am-avg-sm-1 am-collapse">
          <li class="">
            <a href="./register.jsp" class="" >注册</a>
          </li>
          <li class="">
            <a href="./login.jsp" class="" >登录</a>
          </li>
      </ul>

  </nav>

</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <h3>登录</h3>
    <hr>
    <div class="am-btn-group">
      <a href="#" class="am-btn am-btn-secondary am-btn-sm"><i class="am-icon-qq am-icon-sm"></i> QQ</a>
      <a href="#" class="am-btn am-btn-success am-btn-sm"><i class="am-icon-github am-icon-sm"></i> Github</a>
    </div>
    <br>
    <br>

    <form method="post" class="am-form" action="${CTX_PATH}/servlet/UserLoginServlet">
      <label for="username">用户名:</label>
      <div class="am-input-group">
  		<span class="am-input-group-label"><i class="am-icon-user am-icon-fw"></i></span>
  		<input type="text" class="am-form-field" placeholder="" name="username" id="username" value="">
	  </div>
      <br>
      <label for="password">密码:</label>
      <div class="am-input-group">
  		<span class="am-input-group-label"><i class="am-icon-lock am-icon-fw"></i></span>
 		<input type="password" name="password" id="password" value="" class="am-form-field" placeholder="">
	 </div>
      <br>
      <!-- <label for="remember-me">
        <input id="remember-me" type="checkbox">
           	记住密码
      </label>
       -->
      <br />
      <div class="am-cf">
        <input type="submit" name="" id="btn_login" value="登 录" class="am-btn am-btn-primary am-btn-block">
        <br />
        <input type="button" name="" id="btn_reg" value="^_^?点击注册" class="am-btn am-btn-default am-btn-sm am-fl">
    	<input type="button" name="" id="btn_forgot_pass" value="忘记密码 ^_^? " class="am-btn am-btn-default am-btn-sm am-fr">
   
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
$("#btn_login").click(function(){
	var username = $("#username").val();
	var password = $("#password").val();
	
	if(username=""){
		alert("用户名不能为空");
		return ;
	}
	if(password=""){
		alert("密码不能为空");
		return ;
	}

	
});
$("#btn_reg").click(function(){
	window.location.href='./register.jsp';
});
$("#btn_forgot_pass").click(function(){
	window.location.href='./forget_pass.jsp';
});

</script>
</body>
</html>