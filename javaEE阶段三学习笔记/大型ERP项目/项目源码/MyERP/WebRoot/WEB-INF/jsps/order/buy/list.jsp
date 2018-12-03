<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
        var orderState=$("#orderState").val();
		if(orderState=="1"){
			$("#li1").css("background","green");
		}
		if(orderState=="2"){
			$("#li2").css("background","green");
		}
		if(orderState=="3"){
			$("#li3").css("background","green");
		}
		//添加
		$("#addOrder").click(function() {
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "订单的添加";
			diag.URL = "${path}/orderModel_input";
			diag.OKEvent = function(){
		   var win= diag.innerFrame.contentWindow;
			 //提交表单的方法获得返回值
			var result=win.submitFrom();
			 alert(result)
			 //如果成功页面要刷新
			if(result=="success"){
				//关闭弹出层
				diag.close();
				window.location.href="${path}/orderModel_list?query.orderType=1&query.orderState=1";
			}
			};
			diag.show();
		});
		
		
		
		
	});
	//订单详情
	function viewDetail(orderId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "添加部门";
		diag.URL = "${path}/orderModel_orderDetail?order.orderId="+orderId;
		diag.OKEvent = function(){
	   var win= diag.innerFrame.contentWindow;
		};
		diag.show();
		diag.addButton("next","操作日志",function(){
			 var win= diag.innerFrame.contentWindow;
				var diag1 = new Dialog();
				diag1.Width = 700;
				diag1.Height = 300;
				diag1.ShowButtonRow=true;
				diag1.Title = "操作日志";
				diag1.URL = "${path}/consoleLog_consoleLog?query.entityId="+orderId+"&query.tableName=order_model&query.optType=订单审核";
				diag1.OKEvent = function(){
				 var win= diag1.innerFrame.contentWindow;
				};
			 
		diag1.show();
		});
		
	}
	//修改
	function updateDetail(orderId) {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "订单的添加";
		diag.URL = "${path}/orderModel_update?order.orderId="+orderId;
		diag.OKEvent = function(){
	   var win= diag.innerFrame.contentWindow;
		 //提交表单的方法获得返回值
		var result=win.submitFrom();
		 //如果成功页面要刷新
		if(result=="success"){
			//关闭弹出层
			diag.close();
			window.location.href="${path}/orderModel_list?query.orderType=1&query.orderState=1";
		}
		};
		diag.show();
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
			<span class="page_title">进货订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/orderModel_list" method="post"> 
			<div class="square-o-top">
			<input type="hidden" name="query.orderType" id="orderType" value="<s:property value="query.orderType"/>">
			<input type="hidden" name="query.orderState" id="orderState" value="<s:property value="query.orderState"/>">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30"></td>
						<td> 
							<!-- <select class="kuan" style="width:113px">
								<option value="-1">----请-选-择----</option>
								<option value="1">未审核</option>
								<option value="0">已审核</option>
								<option value="0">正在采购</option>
								<option value="0">入库完成</option>
							</select>  -->
						</td>
						<td>下单人:</td>
						<td>
						<s:textfield name="query.createrName" type="text" size="14"></s:textfield>
						</td>
						<td>总量:</td>
						<td>
						<s:textfield name="query.minTotalNum" type="text" size="14"></s:textfield>
						</td>
						<td>到 </td>
						<td>&nbsp;&nbsp;
						<s:textfield name="query.maxTotalNum" type="text" size="14"></s:textfield>
						</td>
						<td><a id="query"> 
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td>下单时间:</td>
						<td>
			     	<s:textfield name="query.minCreateTime" type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>				
						</td>
						<td>到</td>
						<td>
	                <s:textfield name="query.maxCreateTime" type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>				
						</td>
						<td>总额:</td>
						<td>
						<s:textfield name="query.minTotalPrice" type="text" size="14"></s:textfield>
						</td>
						<td>到</td>
						<td>&nbsp;&nbsp;
						<s:textfield name="query.maxTotalPrice" type="text" size="14"></s:textfield>
						</td>
						<td>
							<a id="addOrder" href="javascript:void(0)">
								<img src="${path}/images/can_b_02.gif" border="0" /> 
							</a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
		        <ul>
					<li id="li1"><a style="text-decoration: none; color: black;" href="${path}/orderModel_list?query.orderType=1&query.orderState=1">未审核</a></li>
					<li id="li2"><a style="text-decoration: none; color: black;" href="${path}/orderModel_list?query.orderType=1&query.orderState=2">审核失败</a></li>
					<li id="li3"><a style="text-decoration: none; color: black;" href="${path}/orderModel_list?query.orderType=1&query.orderState=3">审核通过</a></li>
				</ul>	
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">订单号</td>
						<td width="9%">供应商</td>
						<td width="10%">制单人</td>
						<td width="20%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="8%">订单总金额</td>
						<td width="10%">详情</td>
					</tr>
					<s:iterator value="#page.list" var="order">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">
						<s:property value="#order.orderNum"/>
						</td>
						<td>
						<s:property value="#order.supplier.name"/>
						</td>
						<td>
						<s:property value="#order.orderCreater.name"/>
						</td>
						<td>
						<s:property value="#order.createTime"/>
						</td>
						<td>
						<s:property value="#order.totalNum"/>
						</td>
						<td align="right">
						<s:property value="#order.totalPrice"/>
						 元</td>
						<td>
						  <s:if test="#order.orderState==2">
						   <a onclick="updateDetail(<s:property value="#order.orderId"/>)" href="javascript:void(0)" class="xiu">修改</a>
						  </s:if>
							<a onclick="viewDetail(<s:property value="#order.orderId"/>)" href="javascript:void(0)" class="xiu">详情</a>
						</td>
					</tr>
					</s:iterator>
				</table>
				<%@include file="../../tools/paging.jsp" %>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
