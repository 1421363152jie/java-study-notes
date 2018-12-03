<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="taglibs.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="author" content="http://www.asiainfo-linkage.com/" />
<meta name="copyright" content="asiainfo-linkage.com 版权所有，未经授权禁止链接、复制或建立镜像。" />
<meta name="description" content="中国移动通信 name.com"/>
<meta name="keywords" content="中国移动通信 name.com"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes, minimum-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes" />


<title></title>
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="search" type="application/opensearchdescription+xml" href="../opensearch.xml" title="移动购物" />
<link rel="stylesheet" href="../res/css/style.css" />
<script src="../res/js/jquery.js"></script>
<script src="../res/js/com.js"></script>
</head>
<body>
<div class="box bg_white">
	<ul class="uls x_50x50">
		<c:forEach items="${browsesList}" var="browses">
			<li>
				<a href="${path}/html/${browses.itemId}.html"  target="_blank" class="pic"><img src="${request_file_path}${browses.imgs}" alt="摩托罗拉XT319" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#"  target="_blank">${browses.itemName}</a></dt>
					<dd class="orange">￥${browses.skuPrice}</dd>
				</dl>
			</li>
		</c:forEach>
	</ul>
</div></body>
</html>

