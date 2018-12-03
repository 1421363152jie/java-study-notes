<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		var orderState=$("#orderState").val();
		if(orderState=="1"){
			$("#li1").css("background","green");
		}
		if(orderState=="2"){
			$("#li2").css("background","green");
		}
		
		
	});
function viewOrderDetail(orderId) {
	var diag = new Dialog();
	diag.Width = 850;
	diag.Height = 400;
	diag.ShowButtonRow=true;
	diag.Title = "订单的采购";
	diag.URL = "${path}/tranOrder_taskDetailBuying?order.orderId="+orderId;
	diag.OKEvent = function(){
   var win= diag.innerFrame.contentWindow;
	 //提交表单的方法获得返回值
	var result=win.grenOrder();
	 //如果成功页面要刷新
	if(result=="success"){
		//关闭弹出层
		diag.close();
		window.location.href="${path}/tranOrder_tasks?query.orderType=2&query.orderState=1";
	}
	};
	diag.show();
	diag.okButton.value="确认采购";
}	
	
function viewOrderDetail1(orderId) {
	var diag = new Dialog();
	diag.Width = 850;
	diag.Height = 400;
	diag.ShowButtonRow=true;
	diag.Title = "订单的采购";
	diag.URL = "${path}/tranOrder_taskDetailBuying?order.orderId="+orderId;
	diag.OKEvent = function(){
   var win= diag.innerFrame.contentWindow;
	 //提交表单的方法获得返回值
	var result=win.finishgrenOrder();
	 //如果成功页面要刷新
	if(result=="success"){
		//关闭弹出层
		diag.close();
		window.location.href="${path}/tranOrder_tasks?query.orderType=2&query.orderState=2";
	}
	};
	diag.show();
	diag.okButton.value="完成采购";
}	
		
	
</script>
<style>
li{
list-style-type: none;
 float: left;
 padding: 3px;
 border: 1px solid #5E9919; 
 width: 80px;
 text-align: center;
 margin-right: 1px;
  font-size: 15px;
}
</style>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">任务查询</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/tranOrder_tasks" method="post">
			<input type="hidden" name="query.orderType" id="orderType" value="<s:property value="query.orderType"/>">
			<input type="hidden" name="query.orderState" id="orderState" value="<s:property value="query.orderState"/>"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="8%">供&nbsp;应&nbsp;商:</td>
						<td width="29%">
      <s:select cssStyle="width:115px" list="#slist" name="query.supplierId" listKey="supplierId" listValue="name" headerKey="" headerValue="---请选择---"></s:select>								
						</td>
						<td width="8%">发货方式:</td>
						<td width="45%">
		 	<s:select cssStyle="width:137px" cssClass="kuan" list="#{'1':'自提货','2':'送货'}" headerKey="" headerValue="---请选择---" name="query.needs"></s:select>									
						</td>
						<td width=""><a id="query"> 
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			     <ul>
					<li id="li1"><a style="text-decoration: none; color: black;" href="${path}/tranOrder_tasks?query.orderType=2&query.orderState=1">待采购</a></li>
					<li id="li2"><a style="text-decoration: none; color: black;" href="${path}/tranOrder_tasks?query.orderType=2&query.orderState=2">采购中</a></li>
				</ul>
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="8%" height="30">订单类别</td>
						<td width="11%">供应商</td>
						<td width="7%">发货方式</td>
						<td width="6%">联系人</td>
						<td width="12%">联系方式</td>
						<td width="25%">地址</td>
						<td width="16%">详情</td>
					</tr>
					<s:iterator value="#page.list" var="order">
					<tr align="center" bgcolor="#FFFFFF">
							<td height="30">
					 <c:set var="orderType" value="${order.orderType}"></c:set>	
					 <e:OrderTypeText orderType="${orderType}"></e:OrderTypeText>		
							</td>
							<td>
					  <s:property value="#order.supplier.name"/>	
							</td>
							<td>
				     <s:property value="#order.supplier.needs==1?'自提货':'送货'"/>		
							</td>
							<td>
				     <s:property value="#order.supplier.contact"/>   			
							</td>
							<td><s:property value="#order.supplier.tel"/></td>
							<td align="left">&nbsp;
					    <s:property value="#order.supplier.address"/>   			
							</td>
							<td>
							   <s:if test="#order.orderState==1">
							    <a onclick="viewOrderDetail(<s:property value="#order.orderId"/>)" href="javascript:void(0)">
									<img src="${path}/images/icon_3.gif" />详情
								</a>
							   </s:if>
							    <s:else>
							    	<a onclick="viewOrderDetail1(<s:property value="#order.orderId"/>)" href="javascript:void(0)">
									<img src="${path}/images/icon_3.gif" />详情
								</a>
							    </s:else>
							
							</td>
						</tr>
					</s:iterator>
						
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
