<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>主页</title>
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

			<figure data-am-widget="figure"
				class="am am-figure am-figure-default "
				data-am-figure="{  pureview: 'true' }">



				<img src="${CTX_PATH}/img/mt7620a.jpg"
					data-rel="${CTX_PATH}/img/mt7620a.jpg" alt="mt7620a" />
				<figcaption class="am-figure-capition-btm">路由器开发板示意图</figcaption>


			</figure>

			<article class="am-article">
				<div class="am-article-hd">
					<h1 class="am-article-title">系统简介</h1>
				</div>

				<div class="am-article-bd">
					<p>
						
						智能家居（Smart Home），是通过物联网技术将家中各种设备连接到一起，提供家庭照明控制、家电控制和安防控制等多种功能，旨在为用户提供高效、舒适、安全和环保的居住环境。智能家居控制系统是智能家居系统的核心，管理和控制各种家居设备。国内外的智能家居产业界在智能家居设备、连接协议、操作系统和交互方式方面进行了研究，推出了自己的解决方案。但目前仍处于同一厂商家居设备互联互通阶段。学术界的研究主要集中在智能家居网关设计、多协议融合和交互方式设计等方面。本课题拟以家庭无线路由器为载体，以开源路由器固件OpenWrt为基础，设计和实现一个简易的智能家居控制中心系统，该系统具有设备接入、家居设备控制与管理和用户交互接口等功能。在异构家居设备的透明接入和自适应控制方面进行一些初步的探索。
					</p>
				</div>
			</article>
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
</script>
</body>
</html>