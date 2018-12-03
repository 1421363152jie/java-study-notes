<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
function assignOrderDetail(orderId) {
	var diag = new Dialog();
	diag.Width = 850;
	diag.Height = 400;
	diag.ShowButtonRow=true;
	diag.Title = "添加供应商";
	diag.URL = "${path}/tranOrder_taskDetail?order.orderId="+orderId;
	diag.OKEvent = function(){
   var win= diag.innerFrame.contentWindow;
	 //提交表单的方法获得返回值
	var result=win.assignTask();
	 //如果成功页面要刷新
	if(result=="success"){
		//关闭弹出层
		diag.close();
		window.location.href="${path}/tranOrder_taskList?query.orderType=1&query.orderState=3";
	}
	};
	diag.show();
}	
	
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品运输管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/tranOrder_taskList" method="post">
		  <input type="hidden" name="query.orderType" id="orderType" value="<s:property value="query.orderType"/>">
			<input type="hidden" name="query.orderState" id="orderState" value="<s:property value="query.orderState"/>"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>下单时间:</td>
						<td>
				<s:textfield name="query.minCreateTime" type="text" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>				
						</td>
						<td>到&nbsp;</td>
						<td>
			      <s:textfield name="query.maxCreateTime" type="text" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>				
						</td>
						<td>供&nbsp;应&nbsp;商:</td>
						<td>
		      <s:select cssStyle="width:115px" list="#slist" name="query.supplierId" listKey="supplierId" listValue="name" headerKey="" headerValue="---请选择---"></s:select>								
						</td>
						<td>下单人:</td>
						<td><s:textfield name="query.createrName" type="text" size="10"></s:textfield></td>
						<td>&nbsp;</td>
						<td><a id="query"> 
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td>审核时间:</td>
						<td>
					<s:textfield name="query.minCheckTime" type="text" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>				
						</td>
						<td>到&nbsp;</td>
						<td>
		     	<s:textfield name="query.maxCheckTime" type="text" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>	
						</td>
						<td>发货方式:</td>
						<td>
			 	<s:select cssClass="kuan" list="#{'1':'自提货','2':'送货'}" headerKey="" headerValue="---请选择---" name="query.needs"></s:select>				
						</td>
						<td>审核人:</td>
						<td>
			        <s:textfield name="query.checkerName" type="text" size="10"></s:textfield>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="10%" height="30">订单类别</td>
						<td width="13%">下单时间</td>
						<td width="13%">制单人</td>
						<td width="13%">审核时间</td>
						<td width="13%">审核人</td>
						<td width="15%">供应商</td>
						<td width="13%">发货方式</td>
						<td width="10%">跟单人</td>
					</tr>
					<s:iterator value="#page.list" var="order">

						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">
					<c:set var="orderType" value="${order.orderType}"></c:set>	
					<e:OrderTypeText orderType="${orderType}"></e:OrderTypeText>	
							</td>
							<td>
						<s:property value="#order.createTime"/>	
							</td>
							<td>
						<s:property value="#order.orderCreater.name"/>			
							</td>
							<td>
					   	<s:property value="#order.checkTime"/>			
							</td>
							<td>
						<s:property value="#order.checkerOrder.name"/>			
							</td>
							<td>
					  	 <s:property value="#order.supplier.name"/>				
							</td>
							<td>
							 <s:property value="#order.supplier.needs==1?'自提货':'送货'"/>					
							</td>
							<td><img src="${path}/images/icon_3.gif" /> <span
								style="line-height:12px; text-align:center;"> <a onclick="assignOrderDetail(<s:property value="#order.orderId"/>)" 
									href="javascript:void" class="xiu">任务指派 </a>
							</span></td>
						</tr>
					</s:iterator>

				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
