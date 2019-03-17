<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--这个jsp页面主要引入css、js等库文件 --%>
<%-- 当前项目上下文路径 --%>
<c:set var="CTX_PATH" value="${pageContext.request.contextPath}"
	scope="application" />

<script type="text/javascript">
	var CTX_PATH = "${CTX_PATH}";
</script>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<!-- Set render engine for 360 browser -->
<meta name="renderer" content="webkit">

<!-- No Baidu Siteapp-->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="mobile-web-app-capable" content="yes">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />

<!-- Tile icon for Win8 (144x144 + tile color) -->
<meta name="msapplication-TileImage"
	content="${CTX_PATH}/i/app-icon72x72@2x.png">
<meta name="msapplication-TileColor" content="#0e90d2">
<!-- css -->

<link rel="icon" href="${CTX_PATH}/i/favicon.png" type="image/png" />
<link rel="icon" sizes="192x192"
	href="${CTX_PATH}/i/app-icon72x72@2x.png">
<link rel="apple-touch-icon-precomposed"
	href="${CTX_PATH}/i/app-icon72x72@2x.png">

<link rel="stylesheet" href="${CTX_PATH}/css/amazeui.min.css">
<link rel="stylesheet" href="${CTX_PATH}/css/app.css">

<link rel="stylesheet" href="${CTX_PATH}/css/public/style.css">
<link rel="stylesheet" href="${CTX_PATH}/css/message.css">

<!-- js-lib -->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${CTX_PATH}/js/jquery-3.1.1.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="${CTX_PATH}/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="${CTX_PATH}/js/amazeui.min.js"></script>
<script src="${CTX_PATH}/js/message.min.js"></script>
<script src="${CTX_PATH}/js/public.js"></script>
