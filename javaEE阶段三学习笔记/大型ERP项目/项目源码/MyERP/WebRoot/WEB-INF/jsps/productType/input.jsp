<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
function submitFrom() {
	var supplierId=$("#supplierId").val();
	var pname=$("#pname").val();
	var result="";
	var isOk=validSupplierIdAndName(supplierId,pname);
	if(isOk=="yes"){
		 $("#productTypeFrom").ajaxSubmit({
			  async:false,
			  dataType:"text",
			  success:function(responseText){
				  result =responseText;
			  }		
		});
	}else{
		Dialog.alert("提示：商品类别已存在!",function(){
			
		});
	}
	
	return result;
}	
function validSupplierIdAndName(supplierId,name) {
	var result="yes";
	$.ajax({
		  url:"${path}/ajax_productType_validSupplierIdAndName",
		  async:false,
		  type:"post",
		  data:{
			"productType.supplierId":supplierId,
			"productType.name":name
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
			<span class="page_title">商品类别管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="productTypeFrom" action="${path}/ajax_productType_input" method="post">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商</td>
				      <td width="82%" colspan="3">
	 <s:select id="supplierId" cssClass="kuan" cssStyle="width:190px" list="#slist" name="productType.supplier.supplierId" listKey="supplierId" listValue="name" headerKey="" headerValue="---请选择---"></s:select>				
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">商品类别名称</td>
				      <td width="82%" colspan="3">
				  <s:textfield id="pname" name="productType.name" type="text" size="25"></s:textfield>    	
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>		
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
