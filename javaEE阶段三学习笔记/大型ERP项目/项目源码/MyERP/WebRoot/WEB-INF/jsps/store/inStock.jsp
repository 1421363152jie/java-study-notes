<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
function inStockTrue(productId,orderDetailId) {
	var result="";
	 var storeId=$("#storeId").val();
	 var productNum=$("#productNum").val();
	 $.ajax({
		  url:"${path}/ajax_store_inStock",
		  async:false,
		  type:"post",
		  data:{
			"query.storeId":storeId,
			"productNum":productNum,
			"productId":productId,
			"orderDetailId":orderDetailId
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
			<span class="page_title">进货订单明细</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>仓库:</td>
						<td class="order_show_msg" colspan="2">
			  <s:select list="#slist" listKey="storeId" listValue="name" id="storeId"></s:select>
						</td>
						<td>入库数量:</td>
						<td class="order_show_msg">
					    <input type="text" id="productNum">
						</td>
					</tr>
				</table>
			</div>	
	</div>
	<div class="content-bbg"></div>
</div>
