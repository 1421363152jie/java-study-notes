<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %> 
<script type="text/javascript">
	$(function() {
	
	});
function inStockProduct(productId,orderDetailId) {
	var orderId=$("#orderId").val();
	var diag = new Dialog();
	diag.Width = 850;
	diag.Height = 200;
	diag.ShowButtonRow=true;
	diag.Title = "商品的入库";
	diag.URL = "${path}/store_inStock";
	diag.OKEvent = function(){
   var win= diag.innerFrame.contentWindow;
	 //提交表单的方法获得返回值
	var result=win.inStockTrue(productId,orderDetailId);
	 //如果成功页面要刷新
	if(result=="success"){
		//关闭弹出层
		diag.close();
		window.location.href="${path}/tranOrder_inDetail?order.orderId="+orderId;
	}
	};
	diag.show();	
}	
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<input type="hidden" id="orderId" value="<s:property value="order.orderId"/>">
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>订 单 号:</td>
						<td class="order_show_msg">
						<s:property value="order.orderNum"/>
						</td>
						<td>商品总量:</td>
						<td class="order_show_msg">
						<s:property value="order.totalNum"/>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="inOrder" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品类别</td>
						<td width="20%">商品名称</td>
						<td width="20%">已入库数量</td>
						<td width="20%">剩余数量</td>
						<td width="20%">操作</td>
					</tr>
					 <s:iterator value="order.details" var="detail">
					         <tr aa="bb" align="center" bgcolor="#FFFFFF">
								<td height="30">
								<s:property value="#detail.product.productType.name"/>
								</td>
								<td><s:property value="#detail.product.name"/></td>
								<td>
								 <s:property value="#detail.detailNum - #detail.surplus"/>
								</td>
								<td>
								 <s:property value="#detail.surplus"/>
								 <s:property value="#detail.product.unit"/>
								</td>
								<td>
								<s:if test="#detail.surplus!=0">
								<input type="button" onclick="inStockProduct(<s:property value="#detail.product.productId"/>,<s:property value="#detail.orderDetailId"/>)" value="入库"/>
								</s:if>
								
							</tr>
					 </s:iterator>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
