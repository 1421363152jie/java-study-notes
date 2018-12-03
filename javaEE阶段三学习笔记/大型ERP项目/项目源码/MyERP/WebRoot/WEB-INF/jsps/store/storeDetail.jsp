<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
function assignTask() {
	
}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">仓库明细</span>
		</div> 
	</div>
	<div class="content-text">
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr height="30px" align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="20%">商品名称</td>
						<td width="20%">数量</td>
					</tr>
					<s:iterator value="#details" var="detail">
						<tr height="30px" align="center" bgcolor="#FFFFFF">
						<td> 
						<s:property value="#detail.product.name"/>
						</td> 
						<td>
					  <s:property value="#detail.num"/>
						</td>
					</s:iterator>
				</table>
				<br/>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
