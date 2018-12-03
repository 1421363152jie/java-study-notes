<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#commit").click(function() {
			$("form:first").submit();
		});
		

		//非必须填表达
		$("#depFrom").find("[reg]").keyup(function() {
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
	function submitFrom() {
		var result="";
		var isOk=validFrom();
		if(isOk){
			$("#depFrom").ajaxSubmit({
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
		//非必须填表达
		$("#depFrom").find("[reg]").each(function() {
			//获得每个文本框输入的值
			var val=$(this).val();
			//获得正则表达式
			var regstr=$(this).attr("reg");
			//获得提示信息
			var tip=$(this).attr("tip");
			//创建正则表达式对象
			var reg=new RegExp(regstr);
			//校验正则表达式
			if(!reg.test(val)){
				//设置提示样式
				$(this).css("background","#FF9300");
				//设置提示文本
				$("#tip").html(tip);
				//展开提示信息
				$("#tip").show();
				$(this).next("span").hide();
				//设置表单不可提交
				isSubmit=false;
				//跳出循环
				return false;
			}else{
				$(this).css("background","white");
				$(this).next("span").show();
			}			
		});
		return isSubmit;
		
	}
	
	
	
</script>
	<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">部门管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="depFrom" action="${path}/ajax_dep_input" method="post">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">部门名称</td>
				      <td width="32%">
  <s:textfield name="dep.name" type="text" size="25" reg="^[\u4e00-\u9fa5\w]{1,20}$" tip="请输入1-20位的中文或英文部门名称!"></s:textfield><span style="display: none;"><img width="20px"  src="${path}/images/ok.png"></span> 			    
				      </td>
				      <td width="18%" align="center">电话</td>
				      <td width="32%">
  <s:textfield name="dep.tel" type="text" size="25" reg="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9]|17[0|1|2|3|5|6|7|8|9])\d{8}$" tip="请输入正确的手机号码格式!"></s:textfield><span style="display: none;"><img width="20px"  src="${path}/images/ok.png"></span> 
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>			
			</div>
			</form>
			   <div id="tip" style="text-align: center; border: 1px solid #FACCD4; margin:5px; padding: 5px; width: 400px;background-color: #FFF2B9; color: red;display:none; "></div>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
