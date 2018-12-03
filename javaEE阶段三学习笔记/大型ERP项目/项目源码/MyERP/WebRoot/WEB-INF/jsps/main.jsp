<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${path}/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<title>北京蓝源进销存系统-系统主页</title>
<script >
$(document).ready(function(){
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		var zNodes =${zNodes};
    	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});
</script>
</head>
<body>
	<div class="container">
		<div class="head">
			<div class="head-left">
				<span style="font-weight:bold; color:#1f4906">欢迎您-</span><br />
				<span style="color:#4a940d">
				<s:property  value="#session.user.name"/>
				</span>
			</div>
			<div class="head-right">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="32%">
							<a href="emp/changePwd.jsp" target="main">
								<img src="${path}/images/head-l.gif"	border="0" />
							</a>
						</td>
						<td width="26%">
							<a href="${path}/emp_logout">
								<img src="${path}/images/head-m.gif"	border="0" />
							</a>
						</td>
						<td width="7%">&nbsp;</td>
						<td width="35%"><a href="#"><img src="${path}/images/head-r.gif"
								border="0" />
						</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--"head"end-->

		<div class="content">
			<div class="left">
				<div style="margin-left:2px;">
					<img src="${path}/images/left-top.gif" width="162" height="25" />
				</div>
				<div class="left-bottom">
			          	<div class="zTreeDemoBackground left" style="height: 600px;">
							<ul id="treeDemo" class="ztree" style="height: 600px; width: 170px"></ul>
						</div>
						
				</div>
				<!--"left-bottom"end-->
			</div>
			<!--"left"end-->

			<iframe id="frame-contect" src="${path}/erp_context"
				style="width:848px;float:right;height:530px" scrolling="no"
				name="main" frameborder="0"></iframe>
			<!--"content-right"end-->
		</div>
		<!--"content"end-->
		<div class="footer">
			<div style="margin-top:5px;">
				<table width="98%" border="0" cellpadding="0" cellspacing="0"
					align="center">
					<tr>
						<td width="82%"><img src="${path}/images/icon_1.gif" />&nbsp; <a
							class="lanyo" href="https://user.qzone.qq.com/1421363152?ADUIN=1421363152&ADSESSION=1525751757&ADTAG=CLIENT.QQ.5563_MyTip.0&ADPUBNO=26785&source=namecardhoverstar">蓝源信息技术 2018</a></td>
						<td width="18%" valign="middle"><img src="${path}/images/icon_2.gif" />&nbsp;
							<a class="lanyo" href="https://mail.qq.com/cgi-bin/frame_html?sid=_yu0ZyCE5l6vWg5U&r=49dbc3846e6607225ad325e30fd09b20">如有疑问请与技术人员联系</a></td>
					</tr>
				</table>
			</div>

		</div>
		<!--"footer"end-->
	</div>
	<!--"container"end-->
</body>
</html>
