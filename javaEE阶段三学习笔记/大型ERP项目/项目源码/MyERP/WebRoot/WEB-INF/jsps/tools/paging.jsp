<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
$(function() {
	$("#query").click(function() {
		$("form:first").submit();
	});

var tatalPage=parseInt($("#totalPage").val());
var pageNo=parseInt($("#cpageNo").val());
if(pageNo==1&&tatalPage==1){
	$("#fir").hide();
	$("#pre").hide();
	$("#next").hide();
	$("#last").hide();
}else if(pageNo==1&&tatalPage>pageNo){
	$("#fir").hide();
	$("#pre").hide();
	$("#next").show();
	$("#last").show();
}else if(pageNo>1&&tatalPage>pageNo){
	$("#fir").show();
	$("#pre").show();
	$("#next").show();
	$("#last").show();
}else if(pageNo>1&&tatalPage==pageNo){
	$("#fir").show();
	$("#pre").show();
	$("#next").hide();
	$("#last").hide();
}
//下一页
$("#next").click(function(){
	$("#pageNo").val(pageNo+1);
	$("form:first").submit();
	
});
//上一页	
$("#pre").click(function(){
	$("#pageNo").val(pageNo-1);
	$("form:first").submit();
	
});
//首页
$("#fir").click(function(){
	$("#pageNo").val(1);
	$("form:first").submit();
	
});
//末页

$("#last").click(function(){
	$("#pageNo").val(tatalPage);
	$("form:first").submit();
	
});

//输入页面跳转
 $("#jump").click(function(){
	var val=$("#selectPage").val();
	var reg=/^\d{0,}$/;
	//判断是否为数字
	if(!reg.test(val)){
		alert("请输入数字1到"+tatalPage+"的数字");
	}else{
		var selectPageNum=parseInt(val);
		//判断输入小于1或者大于总页数
		if(selectPageNum<1||selectPageNum>tatalPage){
			alert("请输入数字1到"+tatalPage+"的数字");
		}else{
			$("#pageNo").val(val);
			$("form:first").submit(); 
		}
		
	}

});
	
	
	
});

</script>
<br/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
				   
					<tr>
						<td width="51%">&nbsp;
						<!-- 用两个隐藏域计算是否展示哪些按钮 -->
						<input type="hidden" id="totalPage" value="<s:property value="#page.totalPage"/>">
						<input type="hidden" id="cpageNo" value="<s:property value="#page.pageNo"/>">
						<input type="hidden" id="pageNo" name="query.pageNo" value="<s:property value="#page.pageNo"/>">
						</td>
						<td width="13%">共<s:property value="#page.totalCount"/> 条记录
						<td width="6%">
						 <input type="button" value="首页" id="fir" style="background-color: #99CD44;margin: 1px 1px;">
						</td>
						<td width="6%">
						 <input type="button" value="上一页" id="pre" style="background-color: #99CD44;margin: 1px 1px;">
					     </td>
						<td width="6%">
						<input type="button" value="下一页" id="next" style="background-color: #99CD44;margin: 1px 1px;">
						</td>
						<td width="6%">
						<input type="button" value="末页" id="last" style="background-color: #99CD44;margin: 1px 1px;">
						</td>
						<td width="6%">
						<input type="text" size="1" id="selectPage" value="<s:property value="#page.pageNo"/>">
						</td>
						<td width="6%">
						<input type="button" value="跳转" id="jump">
						</td>
						<td width="12%">当前第<span style="color:red;"></span><s:property value="#page.pageNo"/>/<s:property value="#page.totalPage"/>页
						</td>
					</tr>
				</table>
