<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝源进销存(教学版)-系统登录页</title>
<script>
	$(function() {
		$("#login_ok").click(function() {
			$("form:first").submit();
		});	
		//判断不登陆退出登陆页
		if(top.location.href!=location.href){
			top.location.href=location.href;
		}
		
		$("#capthaImg").click(function() {
			var srcPath="${path}/ajax_emp_getImage?date="+new Date();
			$(this).attr("src",srcPath);
		});
		
		 var tip=$("#tip").val();
		  if(tip=="capthaError"){
		   $("#tipdiv").show(300);
		   $("#tipdiv").html("验证码错误!");
		  }
		  if(tip=="unameandpwrodError"){
			$("#tipdiv").show(300);
			$("#tipdiv").html("用户名或密码错误!");
		  }
		
	});
	
</script>
</head>
<body>
	<div class="container-login">
		<input type="hidden" id="tip" value="<s:property  value="#tip"/>"/>
		<div class="login-pic">
			<div class="login-text">
	      <div id="tipdiv" style="text-align: center; border: 1px solid #FACCD4;padding:3px;width:180px; height:10px; background-color: #FFF2B9; color: red; display:none; "></div>
				<form action="${path}/emp_login" method="post">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td  height="22">用户名:</td>
							<td colspan="2">
								<input name="emp.username"  type="text" size="15" />
							</td>
						</tr>
						<tr>
							<td height="26">密&nbsp;&nbsp;码:</td>
							<td colspan="2">
								<input name="emp.password"  type="password" size="15" />
							</td>
						</tr>
						<tr>
							<td height="25">验证码:</td>
							<td >
								<input name="captha" type="text" size="6" />
							</td>
							<td ><img id="capthaImg" src="${path}/ajax_emp_getImage" />
							</td>
						</tr>
						<tr>
							<td height="30">&nbsp;</td>
							<td colspan="2">
								<a href="javascript:void(0)" id="login_ok">
									<img src="${path}/images/denglu_bg_03.gif" 
										 name="Image1" width="40"	
										 height="22" border="0"  
										 onmouseover="MM_swapImage(this,'${path}/images/denglu_h_03.gif')" 
										 onmouseout="MM_swapImage(this,'${path}/images/denglu_bg_03.gif')" /></a>
								<a href="#">
									<img src="${path}/images/giveup_bg_03.gif" 
										 name="Image2" width="42" 
										 height="22" border="0"  
										 onmouseover="MM_swapImage(this,'${path}/images/giveup_h_03.gif')" 
										 onmouseout="MM_swapImage(this,'${path}/images/giveup_bg_03.gif')" /></a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
