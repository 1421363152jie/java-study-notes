<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
function assignTask() {
	var result="";
	var orderId=$("#orderId").val();
	var empId=$("#assginEmp").val();
	$.ajax({
		  url:"${path}/ajax_tranOrder_assignOrder",
		  async:false,
		  type:"post",
		  data:{
			"order.orderCompleter.empId":empId,
			"order.orderId":orderId
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
			<span class="page_title">运输订单明细</span>
		</div> 
		<input type="hidden" id="orderId" value="<s:property value="order.orderId"/>">
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">企业名称:</td>
						<td class="order_show_msg">
					<s:property value="order.supplier.name"/>	
						</td>
						<td height="30">订单类别:</td>
						<td class="order_show_msg">
						<e:OrderTypeText orderType="${order.orderType }"></e:OrderTypeText>
						</td>
				     	<td>提货方式:</td>
						<td class="order_show_msg"><s:property value="order.supplier.needs==1?'自提货':'送货'"/></td>
						<td>订 单 号:</td>
						<td class="order_show_msg" colspan="2">
					    <s:property value="order.orderNum"/></td>		
						
					</tr>
					<tr> 	
						<td>联&nbsp;系&nbsp;人:</td>
						<td class="order_show_msg">
						<s:property value="order.supplier.contact"/>
						</td>
						<td>联系方式:</td>
						<td class="order_show_msg">
						<s:property value="order.supplier.tel"/>
						</td>
						<td>商品总量:</td>
						<td class="order_show_msg">
					 <s:property value="order.totalNum"/>		
						</td>
					   <td>地&nbsp;&nbsp;&nbsp;&nbsp;址:</td>
						<td class="order_show_msg">
						<s:property value="order.supplier.address"/>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;订&nbsp;&nbsp;单&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品类别</td>
						<td width="20%">商品名称</td>
						<td width="20%">数量</td>
					</tr>
					<s:iterator value="#details" var="detail">
						<tr align="center" bgcolor="#FFFFFF">
						<td height="30">
						<s:property value="#detail.product.productType.name"/>
						</td>
						<td> 
						<s:property value="#detail.product.name"/>
						</td> 
						<td>
					  <s:property value="#detail.detailNum"/>
						</td>
					</s:iterator>
					<tr align="right">
						<td height="30" width="50%" colspan="2">指派人</td>
						<td width="20%">
						<s:select list="#dep.emps" listKey="empId" listValue="name" id="assginEmp"></s:select>
						</td>
					</tr>
				</table>
				<br/>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
