<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript" src="${path}/js/FusionCharts/JSClass/FusionCharts.js">
</script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
		var chart=new FusionCharts('${path}/js/FusionCharts/Charts/FCF_Column3D.swf',"ChartId","800","250");
		//bug不能再以下地方注释
		var data=$("#xmlDate").val();
		chart.setDataXML(data);
		chart.render('Pie');
		$("#viewBill").change(function() {
			var swf=$(this).val();
			var chart=new FusionCharts('${path}/js/FusionCharts/Charts/'+swf,"ChartId","800","250");
			//bug不能再以下地方注释
			var data=$("#xmlDate").val();
			chart.setDataXML(data);
			chart.render('Pie');
			
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货报表</span>
		</div>
	</div>
	<input type="hidden" id="xmlDate" value="<s:property value="#fcxml"/>">
	<div class="content-text">
		<form  action="${path}/bill_bill" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="70" height="30">报表类别:</td>
						<td width="140">
							<input type="radio" name="all" checked="checked">商品名称
						</td>
						<td width="70">订单类别:</td>
						<td width="190">
						<s:select list="#{'1':'采购单','2':'运输单','3':'入库单'}" headerKey="" headerValue="-----请选择----" name="query.orderType"></s:select>	
							
						</td>
						<td width="70">开始日期:</td>
						<td width="190">
						<s:textfield name="query.startTime"  type="text" size="18" onfocus="c.showMoreDay=false;c.show(this);"></s:textfield>	
						<td ><a id="query"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					 </tr>
					<tr>
						<td height="30">&nbsp;</td>
						<td>
							<input type="radio" name="all">销售人员
						</td>
						<td>厂商名称:</td>
						<td>
		<s:select cssClass="kuan" list="#suppliers" listKey="supplierId" listValue="name" headerKey="" headerValue="----请-选-择----" name="query.supplierId"></s:select>					
						</td>
						<td>结束日期:</td>
						<td width="190">
			      <select id="viewBill">
						<option value="FCF_Column3D.swf">柱状图3D</option>
						<option value="FCF_Column2D.swf">柱状图2D</option>
						<option value="FCF_Line.swf">线型图</option>
						<option value="FCF_Pie2D.swf">饼图2D</option>
						<option value="FCF_Pie3D.swf">饼图3D</option>
						<option value="FCF_Area2D.swf">区域图</option>
						<option value="FCF_Bar2D.swf">条形图</option>
			      </select>
			           </td>
						<td>
							<a href="demo.xls">
								<img src="${path}/images/can_b_03.gif" border="0" />
							</a>
							</td>
					</tr>
				</table>
			</div>
				<div id="Pie" style="height: 250px">
			   
			  </div>
			<!--"square-o-top"end-->
			<div class="square-order" style="overflow: auto; height: 150px">
				<table width="100%" border="1" cellpadding="0" cellspacing="0"
					style="float:left;" >
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td colspan="2" width="49%" height="30">商品名称</td>
						<td colspan="2" width="28%">总数量</td>
						<td width="23%">详情</td>
					</tr>
					<s:iterator value="#blist" var="bill">
						<tr align="center" bgcolor="#FFFFFF">
							<td colspan="2" width="30%" height="30">
							<s:property value="#bill.pname"/>
							</td>
							<td colspan="2">
							<s:property value="#bill.count"/>
							</td>
							<td><a href="javascript:void(0)" class="xiu info" value="1">
									详情 </a></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
