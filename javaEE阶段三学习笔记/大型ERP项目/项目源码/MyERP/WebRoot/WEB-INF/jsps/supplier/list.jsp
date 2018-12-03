<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
		$("#addSupplier").click(function() {
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "添加供应商";
			diag.URL = "${path}/supplier_input";
			diag.OKEvent = function(){
		   var win= diag.innerFrame.contentWindow;
			 //提交表单的方法获得返回值
			var result=win.submitFrom();
			 //如果成功页面要刷新
			if(result=="success"){
				//关闭弹出层
				diag.close();
				window.location.href="${path}/supplier_list";
			}
			};
			diag.show();	
			
		});	
	});
	//修改
   function updateSupplier(supplierId) {
	var diag = new Dialog();
	diag.Width = 850;
	diag.Height = 400;
	diag.ShowButtonRow=true;
	diag.Title = "修改供应商信息";
	diag.URL = "${path}/supplier_update?supplier.supplierId="+supplierId;
	diag.OKEvent = function(){
   var win= diag.innerFrame.contentWindow;
	 //提交表单的方法获得返回值
	var result=win.submitFrom();
	 //如果成功页面要刷新
	if(result=="success"){
		//关闭弹出层
		diag.close();
		window.location.href="${path}/supplier_list";
	}
	};
	diag.show();	
}	
	function deleteSupplier(supplierId) {
		Dialog.confirm('警告：您确认要删除该供应商吗？',function(){	 	
			   window.location.href="${path}/supplier_delete?supplier.supplierId="+supplierId;	
			});
	}
	
	
	
	
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">供应商管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/supplier_list" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="28%" height="30">&nbsp;</td>
						<td width="8%">供应商:</td>
						<td width="17%">
					  <s:textfield name="query.name" type="text" size="18" ></s:textfield>
						</td>
						<td width="8%">联系人:</td>
						<td width="17%">
					  <s:textfield name="query.contact" type="text" size="18" ></s:textfield>	
						</td>
						<td width="12%">
							<a id="query"><img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td height="30">&nbsp;</td>
						<td>电话:</td>
						<td>
						  <s:textfield name="query.tel" type="text" size="18" ></s:textfield>	
						</td>
						<td>提货方式：</td>
						<td>
						<s:select cssClass="kuan" list="#{'1':'自提货','2':'送货'}" headerKey="" headerValue="---请选择---" name="query.needs"></s:select>	
						</td>
						<td>
							<a id="addSupplier" href="javascript:void(0)"><img	src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">供应商</td>
						<td width="20%">地址</td>
						<td width="20%">联系人</td>
						<td width="12%">电话</td>
						<td width="12%">送货方式</td>
						<td width="16%">操作</td>
					</tr>
			       <s:iterator value="#page.list" var="supplier">
			       <tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">
						<s:property value="#supplier.name"/>
						</td>
						<td>
						<s:property value="#supplier.address"/>
						</td>
						<td>
						<s:property value="#supplier.contact"/>
						</td>
						<td>
						<s:property value="#supplier.tel"/>
						</td>
						<td>
						<s:property value="#supplier.needs==1?'自提货':'送货'"/>
						 </td>
						<td>
							<img src="${path}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a onclick="updateSupplier(<s:property value="#supplier.supplierId"/>)" href="javascript:void(0)" class="xiu">修改</a>
							</span> 
							<img src="${path}/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="deleteSupplier(<s:property value="#supplier.supplierId"/>)">删除</a>
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
