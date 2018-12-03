<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=roles]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#supplierId").change(function() {
			var supplierId=$(this).val();
			$.ajax({
				  url:"${path}/ajax_supplier_getSupplier",
				  async:false,
				  type:"post",
				  data:{
					"query.supplierId":supplierId
				  },	
				  dataType:"text",
				  success:function(responseText){
                     $("#productType").empty();
                     $("#productType").append("<option value=''>---请选择---</option>");
                    var jsonArr=$.parseJSON(responseText);
				  for (var i = 0; i < jsonArr.length; i++) {
					  $("#productType").append("<option value='"+jsonArr[i].productTypeId+"'>"+jsonArr[i].name+"</option>");
				  }
				  }	
				});
	
			
		});
		
		
	});
	function submitFrom() {
		var result="";
			$("#productFrom").ajaxSubmit({
				  async:false,
				  dataType:"text",
				  success:function(responseText){
					  result =responseText;
				  }	
				
			});			
		return result;
	}	
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="productFrom" action="${path}/ajax_product_update" method="post">
			<input type="hidden" name="product.productId" value="<s:property value="product.productId"/>">
			<input type="hidden" name="product.productType.supplier.supplierId" value="<s:property value="product.productType.supplier.supplierId"/>">
			<input type="hidden" name="product.productType.productTypeId" value="<s:property value="product.productType.productTypeId"/>">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center">商品名称</td>
				      <td>
				  <s:textfield name="product.name" type="text" size="25"></s:textfield>    
				      </td>
				      <td  align="center">产&nbsp;&nbsp;&nbsp;&nbsp;地</td>
				      <td >
				   <s:textfield name="product.origin" type="text" size="25"></s:textfield>    
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">生产厂家</td>
				      <td>
			    <s:textfield name="product.producer" type="text" size="25"></s:textfield>    
				      <td align="center">单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
				      <td>
				    <s:textfield name="product.unit" type="text" size="25"></s:textfield>    
					  </td>
				     </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">进货单价</td>
				      <td>
				   <s:textfield name="product.inPrice" type="text" size="25"></s:textfield>    
					  </td>
				      <td align="center">销售单价</td>
				      <td>
			     <s:textfield name="product.outPrice" type="text" size="25"></s:textfield>  
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				</table>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
