<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
	<link rel="stylesheet" href="${path}/js/zTree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="${path}/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript">
var tree =null;
$(function() {
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	var zNodes =${zNodes};
   tree=$.fn.zTree.init($("#treeDemo"), setting, zNodes);

});

function getNodes(roleId) {
	var permId="";
	var nodes=tree.getCheckedNodes(true);
	for (var i = 0; i <nodes.length; i++) {
		permId=permId+nodes[i].id+",";
	}
	var result=getRolePerm(roleId,permId);
	return result;
}

function getRolePerm(roleId,permId) {
	var result="";
	$.ajax({
		  url:"${path}/ajax_role_grantPerm",
		  async:false,
		  type:"post",
		  data:{
			"role.roleId":roleId,
			"permId":permId
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
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>
	</div>
	<div class="content-bbg"></div>
</div>
