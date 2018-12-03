<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#empFrom").find("[regr]").keyup(function() {
			//获得每个文本框输入的值
			var val=$(this).val();
			//获得正则表达式
			var regstr=$(this).attr("regr");
			//获得提示信息
			var tip=$(this).attr("tip");
			//创建正则表达式对象
			var reg=new RegExp(regstr);
			//获得name
			var name=$(this).attr("name");	
			//校验正则表达式
			if(!reg.test(val)){
				//设置提示样式
				$(this).css("background","#FF9300");
				//设置提示文本
				$("#tip").html(tip);
				//展开提示信息
				$("#tip").show();
				$(this).next("span").hide();
			}else{
				//检验用户名是否存在
				if(name=="emp.username"){
					var result=validUsername(val);
					if(result=="no"){
						//设置提示样式
						$(this).css("background","#FF9300");
						//设置提示文本
						$("#tip").html("用户名已经存在");
						//展开提示信息
						$("#tip").show();
						$(this).next("span").hide();
					}
			
				}else{
			     	$(this).css("background","white");
			 	   //将隐藏
			     	$("#tip").hide();
			 	   //展示后面图片
			    	$(this).next("span").show();
				}
				 if(name=="rpassword"){
					  var password=$("#password").val();
					  if(password!=val){
						//设置提示样式
							$(this).css("background","#FF9300");
							//设置提示文本
							$("#tip").html("两次密码不一致!");
							//展开提示信息
							$("#tip").show();
							$(this).next("span").hide();
					  }
				  }else{
					 	$(this).css("background","white");
						 	//将隐藏
	                 	$("#tip").hide();
	                 	//展示后面图片
				    	$(this).next("span").show();
				  }		
			}
		});
		
		//非必须填表达
		$("#empFrom").find("[reg]").keyup(function() {
			//获得每个文本框输入的值
			var val=$(this).val();
			//获得正则表达式
			var regstr=$(this).attr("reg");
			//获得提示信息
			var tip=$(this).attr("tip");
			//创建正则表达式对象
			var reg=new RegExp(regstr);
			//校验正则表达式
			if(val!=null&&$.trim(val)&&!reg.test($.trim(val))){
				//设置提示样式
				$(this).css("background","#FF9300");
				//设置提示文本
				$("#tip").html(tip);
				//展开提示信息
				$("#tip").show();
				$(this).next("span").hide();
			}else{
				$(this).css("background","white");
				$("#tip").hide();
				//展示后面图片
		    	$(this).next("span").show();
			}			
		});	
	});
	
	
	
	/**
	ajax提交表单
	*/
	function submitFrom() {
		var result="";
		var isOk=validFrom();
		if(isOk){
			$("#empFrom").ajaxSubmit({
				  async:false,
				  dataType:"text",
				  success:function(responseText){
					  result =responseText;
				  }	
				
			});	
		}		
		return result;
	}
	
	function validFrom() {
		//设置提交标准
		var isSubmit=true;
		$("#empFrom").find("[regr]").each(function() {
			//获得每个文本框输入的值
			var val=$(this).val();
			//获得正则表达式
			var regstr=$(this).attr("regr");
			//获得提示信息
			var tip=$(this).attr("tip");
			//创建正则表达式对象
			var reg=new RegExp(regstr);
			//获得name
			var name=$(this).attr("name");	
			//校验正则表达式
			if(!reg.test(val)){
				//设置提示样式
				$(this).css("background","#FF9300");
				//设置提示文本
				$("#tip").html(tip);
				//展开提示信息
				$("#tip").show();
				//设置表单不可提交
				isSubmit=false;
				//跳出循环
				return false;
			}else{
				//检验用户名是否存在
				if(name=="emp.username"){
					var result=validUsername(val);
					if(result=="no"){
						//设置提示样式
						$(this).css("background","#FF9300");
						//设置提示文本
						$("#tip").html("用户名已经存在");
						//展开提示信息
						$("#tip").show();
						//设置表单不可提交
						isSubmit=false;
						//跳出循环
						return false;	
					}
					
				}
			  if(name=="rpassword"){
				  var password=$("#password").val();
				  if(password!=val){
					//设置提示样式
						$(this).css("background","#FF9300");
						//设置提示文本
						$("#tip").html("两次密码不一致!");
						//展开提示信息
						$("#tip").show();
						//设置表单不可提交
						isSubmit=false;
						//跳出循环
						return false;	 
				  }
			  }			
			}			
			$(this).css("background","white");
		});	
		//非必须填表达
		$("#empFrom").find("[reg]").each(function() {
			//获得每个文本框输入的值
			var val=$(this).val();
			//获得正则表达式
			var regstr=$(this).attr("reg");
			//获得提示信息
			var tip=$(this).attr("tip");
			//创建正则表达式对象
			var reg=new RegExp(regstr);
			//校验正则表达式
			if(val!=null&&$.trim(val)&&!reg.test($.trim(val))){
				//设置提示样式
				$(this).css("background","#FF9300");
				//设置提示文本
				$("#tip").html(tip);
				//展开提示信息
				$("#tip").show();
				//设置表单不可提交
				isSubmit=false;
				//跳出循环
				return false;
			}else{
				$(this).css("background","white");
			}			
		});
		return isSubmit;
		
	}
	
	
	//检验用户名是否存在
	function validUsername(username) {
		var result="";
		$.ajax({
			  url:"${path}/ajax_emp_validUsername",
			  async:false,
			  type:"post",
			  data:{
				"emp.username":username  
			  },	
			  dataType:"text",
			  success:function(responseText){
				  result=responseText;
			  }	
			});
		return result;
		
	}
	
	
	
	/* 
	function name() {
		$.ajax({
		  url:"",
		  async:false,
		  type:"post",
		  data:{
			  
		  },	
		  dataType:"text",
		  success:function(responseText){
			  
		  }	
		});
		
		
	} */
	
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="empFrom" action="${path}/ajax_emp_input" method="post"> 
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				  <td align="center">
				     <div id="tip" style="text-align: center; border: 1px solid #FACCD4; margin:5px; padding: 5px; width: 400px;background-color: #FFF2B9; color: red;display:none; "></div>
				    </td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center"><font color="red">*</font>用&nbsp;户&nbsp;名</td>
				      <td width="32%">
				      <s:textfield name="emp.username" type="text" size="25" regr="^\w{6,18}$" tip="请输入6到18位用户名"> </s:textfield><span style="display: none;"><img width="20px"  src="${path}/images/ok.png"></span>	
				      </td>
				      <td width="18%"align="center"><font color="red">*</font>真实姓名</td>
				      <td width="32%">
				    <s:textfield name="emp.name" type="text" size="25" regr="^[\u4e00-\u9fa5]{2,10}$" tip="请输入2到10位的真实姓名"></s:textfield><span style="display: none;"><img width="20px"  src="${path}/images/ok.png"></span>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center"><font color="red">*</font>密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
				      <td>
				     <s:textfield id="password" name="emp.password" type="password" size="25" regr="^[\w!@#$%^&*()_]{6,15}$" tip="请输入6到15位密码"></s:textfield> <span style="display: none;"><img width="20px"  src="${path}/images/ok.png"></span>	
				      </td>
				      <td  align="center"><font color="red">*</font>确认密码</td>
				      <td >
				      <s:textfield name="rpassword" type="password" size="25" regr="^[\w!@#$%^&*()_]{6,15}$" tip="请输入6到15位密码"></s:textfield><span style="display: none;"><img width="20px"  src="${path}/images/ok.png"></span> 	
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">电子邮箱</td>
				      <td>
				 <s:textfield name="emp.email" type="text" size="25" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="请输入正确的邮箱格式!"></s:textfield><span style="display: none;"><img width="20px"  src="${path}/images/ok.png"></span> 	
				      <td align="center">电话号码</td>
				      <td>
			    <s:textfield name="emp.tel" type="text" size="25" reg="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9]|17[0|1|2|3|5|6|7|8|9])\d{8}$" tip="请输入正确的手机号码格式!"></s:textfield><span style="display: none;"><img width="20px"  src="${path}/images/ok.png"></span> 	
					  </td>
				     </tr>
				      <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center"><font color='red'>*</font>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
				      <td>
			    	<s:select cssClass="kuan" list="#{'1':'男','2':'女'}"  name="emp.gender"></s:select>	    
					  </td>
				      <td align="center">地&nbsp;&nbsp;&nbsp;&nbsp;址</td>
				      <td>
		      <s:textfield name="emp.address" type="text" size="25" reg="^[\u4e00-\u9fa5\w]{1,50}$" tip="请输入1-50位的中文或英文地址!"></s:textfield><span style="display: none;"><img width="20px"  src="${path}/images/ok.png"></span> 			    
					  </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">出生日期</td>
				      <td>
				    <s:textfield name="emp.birthday" type="text" size="25" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>
					  </td>
				      <td align="center"><font color='red'>*</font>所属部门</td>
				      <td>
					<s:select cssStyle="width:190px" name="emp.dep.depId" list="#dlist" listKey="depId" listValue="name" ></s:select>		
					  </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
