<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
</script>
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">操作日志信息</span>
		</div>
	</div>
	<div class="">
			<!--"square-o-top"end-->
			<div class="">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center" style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="5%" height="30">操作人员</td>
						<td width="5%">时间</td>
						<td width="5%">备注</td>
					</tr>
					<s:iterator value="#clist" var="log">
					 <tr align="center" bgcolor="#FFFFFF" >
						<td width="5%" height="30">
						<s:property value="#log.emp.name"/>
						</td>
						<td>
						<s:property value="#log.optTime"/>
						</td>
						<td>
						<s:property value="#log.note"/>
						</td>
					</tr>
					</s:iterator>
				
				    
				</table>
			</div>
	</div>
