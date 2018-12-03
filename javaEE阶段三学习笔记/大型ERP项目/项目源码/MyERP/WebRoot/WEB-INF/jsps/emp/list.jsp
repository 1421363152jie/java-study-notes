<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		
	});
	function addEmp() {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "添加员工";
		diag.URL = "${path}/emp_input";
		diag.OKEvent = function(){
	   var win= diag.innerFrame.contentWindow;
		 //提交表单的方法获得返回值
		var result=win.submitFrom();
		 //如果成功页面要刷新
		if(result=="success"){
			//关闭弹出层
			diag.close();
			window.location.href="${path}/emp_list";
		}
		};
	
		diag.show();
		
	}
	
	/**
	修改
	*/
	function updateEmp(empId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "添加员工";
		diag.URL = "${path}/emp_update?emp.empId="+empId;
		diag.OKEvent = function(){
	   var win= diag.innerFrame.contentWindow;
		 //提交表单的方法获得返回值
		var result=win.submitFrom();
		 //如果成功页面要刷新
		if(result=="success"){
			//关闭弹出层
			diag.close();
			window.location.href="${path}/emp_list";
		}
		};
	
		diag.show();
		
	}
	//删除
	function deletEmp(empId) {

		Dialog.confirm('警告：您确认要删除该员工吗？',function(){
		 	
		   window.location.href="${path}/emp_delete?emp.empId="+empId;	
		});
	}
	
	/**
	  分配角色
	*/
	function assignRole(empId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 450;
		diag.ShowButtonRow=true;
		diag.Title = "添加员工";
		diag.URL = "${path}/emp_listpop?emp.empId="+empId;
		diag.OKEvent = function(){
	   var win= diag.innerFrame.contentWindow;
		 //提交表单的方法获得返回值
	    var roleIds=win.gRole();
	    var result=grentRole(empId,roleIds);	 
		 //如果成功页面要刷新
		if(result=="success"){
			//弹出提示成功
			Dialog.alert("提示：角色分配成功",function(){
				diag.close();
			});
		}
		};
	
		diag.show();
	}
	function grentRole(empId,roleIds) {
		var result="";
		$.ajax({
			  url:"${path}/ajax_emp_grantrole",
			  async:false,
			  type:"post",
			  data:{
				"emp.empId":empId,
				"roleIds":roleIds
			  },	
			  dataType:"text",
			  success:function(responseText){
				  result=responseText;
			  }	
			});
		return result;
	}
	
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/emp_list" method="post">
			<div class="square-o-top">

				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">用&nbsp;户&nbsp;名</td>
						<td>
						<s:textfield name="query.username" type="text" size="14"></s:textfield></td>
						<td>真实姓名</td>
						<td><s:textfield name="query.name" type="text" size="14"></s:textfield></td>
						<td>电&nbsp;&nbsp;&nbsp;&nbsp;话</td>
						<td>
						<s:textfield name="query.tel" type="text" size="14"></s:textfield>
						</td>
						<td>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
						<td>
							<s:select cssClass="kuan" list="#{'1':'男','2':'女'}" headerKey="" headerValue="----请选择---" name="query.gender"></s:select>
						</td>
							
						<td width="70"><a onclick="addEmp()" href="javascript:void(0)"> <img src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td  height="30">电子邮件</td>
						<td>
						<s:textfield name="query.email" type="text" size="14"></s:textfield>
						</td>
						<td>出生日期</td>
						<td>
							<s:textfield name="query.startBirthday"  type="text"  size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>
						</td>
						<td>出生日期</td>
						<td>
						<s:textfield name="query.endBirthday"  type="text"  size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>
						</td>
						<td>部门名称</td>
						<td>
							<s:select name="query.depId" list="#dlist" listKey="depId" listValue="name" headerKey="" headerValue="---请选择---"></s:select>
						</td>
						<td><a id="query"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="10%" height="30">用户名</td>
						<td width="10%">真实姓名</td>
						<td width="5%">性别</td>
						<td width="12%">出生日期</td>
						<td width="10%">电话</td>
						<td width="9%">电子邮件</td>
						<td width="8%">所属部门</td>
						<td width="20%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="emp">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">
						<s:property value="#emp.username"/>
						</td>
						<td>
						<s:property value="#emp.name"/>
						</td>
						<td>
						<s:property value="#emp.gender==1?'男':'女'"/>
						</td>
						<td>
						<s:property value="#emp.birthday"/>
						</td>
						<td>
						<s:property value="#emp.tel"/>
						</td>
						<td>
						<s:property value="#emp.email"/>
						</td>
						<td>
						<s:property value="#emp.dep.name"/>
						</td>
						<td>
						   <img src="${path}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a onclick="assignRole(<s:property value="#emp.empId"/>)" href="javascript:void(0)" class="xiu">分配角色</a>
							</span> 
							<img src="${path}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a onclick="updateEmp(<s:property value="#emp.empId"/>)" href="javascript:void(0)" class="xiu">修改</a>
							</span> 
							<img src="${path}/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="deletEmp(<s:property value="#emp.empId"/>)">删除</a>
							</span>
						</td>
					</tr>
					</s:iterator>
				</table>
				<%@include file="../tools/paging.jsp" %>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
