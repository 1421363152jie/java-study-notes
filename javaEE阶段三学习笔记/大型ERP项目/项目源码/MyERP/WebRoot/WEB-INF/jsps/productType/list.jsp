<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
	 $("#addProductType").click(function() {
		 var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "添加角色";
			diag.URL = "${path}/productType_input";
			diag.OKEvent = function(){
		   var win= diag.innerFrame.contentWindow;
			 //提交表单的方法获得返回值
			var result=win.submitFrom();
			 //如果成功页面要刷新
			if(result=="success"){
				//关闭弹出层
				diag.close();
				window.location.href="${path}/productType_list";
			}
			};
			diag.show();
		 });
	});
function updateProductType(productTypeId) {
	  var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "修改商品类型";
		diag.URL = "${path}/productType_update?productType.productTypeId="+productTypeId;
		diag.OKEvent = function(){
	   var win= diag.innerFrame.contentWindow;
		 //提交表单的方法获得返回值
		var result=win.submitFrom();
		 //如果成功页面要刷新
		if(result=="success"){
			//关闭弹出层
			diag.close();
			window.location.href="${path}/productType_list";
		}
		};
		diag.show();
}	
 function deleteProductType(productTypeId) {
	 Dialog.confirm('警告：您确认要删除该商品类别吗？',function(){	 	
		   window.location.href="${path}/productType_delete?productType.productTypeId="+productTypeId;	
		});	 
}	
	
	
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品类别管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/productType_list" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">供应商:</td>
						<td width="142">
			<s:select cssClass="kuan" list="#slist" name="query.supplierId" listKey="supplierId" listValue="name" headerKey="" headerValue="---请选择---"></s:select>				
						</td>
						<td width="60">类别名称:</td>
						<td width="149">
			 <s:textfield name="query.name" type="text" size="18"></s:textfield>	
						</td>
						<td width="70"><a id="query"> <img
								src="${path}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a id="addProductType" href="javascript:void(0)"><img
								src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="30%" height="30">供应商</td>
						<td width="30%">类别名称</td>
						<td width="40%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="productType"> 
						<tr align="center" bgcolor="#FFFFFF">
						<td width="30%" height="30">
						<s:property value="#productType.supplier.name"/>
						</td>
						<td>
						<s:property value="#productType.name"/>
						</td>
						<td>
							<img src="${path}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a onclick="updateProductType(<s:property value="#productType.productTypeId"/>)" href="javascript:void(0)" class="xiu">修改</a> 
							</span> 
							<img src="${path}/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="deleteProductType(<s:property value="#productType.productTypeId"/>)">删除</a>
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
