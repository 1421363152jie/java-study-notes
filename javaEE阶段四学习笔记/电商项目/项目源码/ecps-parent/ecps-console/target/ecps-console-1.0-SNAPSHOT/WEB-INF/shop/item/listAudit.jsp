<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/ecps/console/common/taglibs.jsp"%>
<head>
	<title>商品审核</title>
	<meta name="heading" content="商品审核"/>
	<meta name="menu" content="ItemMgmtMenu"/>
	<script type="text/javascript" src="<c:url value='/${system}/res/js/jquery.form.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/${system}/res/js/jquery.tablesorter.js'/>"></script>
	<script type="text/javascript" src="${path}/ecps/console/res/item/listAudit.js"></script>
</head>
<body id="main">

<div class="frameL"><div class="menu icon">
	<jsp:include page="/${system}/common/itemmenu.jsp"/>
</div></div>

<div class="frameR"><div class="content">

	<div class="loc icon"><samp class="t12"></samp>当前位置：商品管理&nbsp;&raquo;&nbsp;<span class="gray" title="商品录入及上下架">商品审核</span></div>

	<h2 class="h2_ch"><span id="tabs" class="l">
        <a id="label4" href="${path}/item/listAudit.do?auditStatus=0&showStatus=1"   class="nor">待审核</a>
        <a id="label5" href="${path}/item/listAudit.do?auditStatus=1&showStatus=1"   class="nor">审核通过</a>
		 <a id="label7" href="${path}/item/listAudit.do?auditStatus=2&showStatus=1"  class="nor">审核未通过</a>
    </span></h2>
<
	<form id="auditFrom" action="${path}/item/auditItem.do" method="post">
		<input type="hidden" id="itemId" name="itemId">
		<input type="hidden" id="auditStatus1" name="auditStatus">
		<input type="hidden" id="note" name="note">
	</form>

	<form id="form1" name="form1" action="${path}/item/listAudit.do" method="post">
		<input type="hidden" id="auditStatus" name="auditStatus" value="${qc.auditStatus}">
		<div class="sch">
			<input type="hidden" id="userSearch" name="userSearch" />
			<p>搜索：
				<ui:select name="catID" list="catList" rootId="0" defaulttext="所有分类" defaultvalue="" currentValue="${catID}"/>
				<select id="brandId" name="brandId" value="">
					<option value="">全部品牌</option>
					<c:forEach items="${bList}" var="brand">
						<option value="${brand.brandId }" <c:if test="${qc.brandId == brand.brandId}">selected</c:if>>${brand.brandName }</option>
					</c:forEach>
				</select>
				<select id="auditStatus" name="auditStatus" >
					<option value="" selected>全部审核状态</option>
					<option value="0" <c:if test="${qc.auditStatus == 0}">selected</c:if>>待审核</option>
					<option value="1" <c:if test="${qc.auditStatus == 1}">selected</c:if>>通过</option>
					<option value="2" <c:if test="${qc.auditStatus == 2}">selected</c:if>>不通过</option>
				</select>
				<input type="text" id="searchText" value="${qc.itemName }" name="itemName" title="请输入商品名称" class="text20 medium gray" /><input type="submit" id="goSearch" class="hand btn60x20" value="查询" />
			</p></div>

		<div class="page_c">
        <span class="l">
        </span>
			<span class="r inb_a">
            <a href="${path}/item/toAddItem.do" class="btn80x20" title="添加商品">添加商品</a>
        </span>
		</div>

		<table cellspacing="0" summary="" class="tab" id="myTable">
			<thead>
			<th>商品编号</th>
			<th class="wp">商品名称</th>
			<th>图片</th>
			<th>新品</th>
			<th>推荐</th>
			<th>特价</th>
			<th>上下架</th>
			<th>审核状态</th>
			<th>操作</th>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr>
					<td>${item.itemNo}</td>
					<td>${item.itemName}</td>
					<td><img alt=""
							 src="${request_file_path}${item.imgs}"
							 width="50" height="50"></td>

					<td>
						<c:if test="${item.isNew==1}">
							<span class="is"></span>
						</c:if>
						<c:if test="${item.isNew==0}">
							<span class="not"></span>
						</c:if>
					</td>

					<td>
						<c:if test="${item.isGood==1}">
							<span class="is"></span>
						</c:if>
						<c:if test="${item.isGood==0}">
							<span class="not"></span>
						</c:if>
					</td>
					<td>
						<c:if test="${item.isHot==1}">
							<span class="is"></span>
						</c:if>
						<c:if test="${item.isHot==0}">
							<span class="not"></span>
						</c:if>
					</td>
					<td>
						<c:if test="${item.showStatus==0}">
							<span class="is"></span>
						</c:if>
						<c:if test="${item.showStatus==1}">
							<span class="not"></span>
						</c:if>
					</td>
					<td>
						<c:if test="${item.auditStatus==0}">
							待审核
						</c:if>
						<c:if test="${item.auditStatus==1}">
							审核通过
						</c:if>
						<c:if test="${item.auditStatus==2}">
							审核失败
						</c:if>
					</td>

					<td>
						<c:if test="${item.auditStatus==0}">
							<a href="/ecps-console/shop/item/viewItem.jsp" title="查看">查看</a>
							<a href="/ecps-console/ecps/console/item/editItem.do?type=1&itemId=2384">编辑</a>
							<a href="javascript:void(0);" onclick="singleDel('2384')">删除</a>
							<a href="javascript:void(0);" onclick="auditItem(${item.itemId},1)">通过</a>
							<a href="javascript:void(0);" onclick="auditItem(${item.itemId},2)">不通过</a>
						</c:if>
                         <c:if test="${item.auditStatus==1}">
						<a href="/ecps-console/shop/item/viewItem.jsp" title="查看">查看</a>
						<a href="/ecps-console/ecps/console/item/editItem.do?type=1&itemId=2384">编辑</a>
						<a href="javascript:void(0);" onclick="singleDel('2384')">删除</a>
					     </c:if>
						<c:if test="${item.auditStatus==2}">
							<a href="/ecps-console/shop/item/viewItem.jsp" title="查看">查看</a>
							<a href="/ecps-console/ecps/console/item/editItem.do?type=1&itemId=2384">编辑</a>
							<a href="javascript:void(0);" onclick="singleDel('2384')">删除</a>
							<a href="javascript:void(0);" >提交重审</a>
						</c:if>

					</td>
				</tr>
			</c:forEach>



			</tbody>
			<tr>
				<td colspan="13" align="right">
					选择：<a href="javascript:void(0);" title="全选" onclick="checkAllIds();">全选</a>
					<samp>-</samp> <a href="javascript:void(0);" title="取消" onclick="uncheckAllIds();">取消</a>
				</td>
			</tr>
		</table>

		<div class="page_c">
        <span class="l inb_a">
        </span>
			<span class="r page">
	          <input type="hidden"  id="pageNum" name="pageNum" />
             <input type="hidden" id="totalCount" name="totalCount" />
            <input type="hidden" value="${page.pageNum}" id="currentPageNo" name="currentPageNo" />
            <input type="hidden" value="${page.totalPage}" id="totalPage" name="totalPage" />
                    共<var id="pagePiece" class="orange">${page.totalCount}</var>条<var id="pageTotal">${page.pageNum}/${page.totalPage}</var>
	         <a href="javascript:void(0);" id="firstPage" class="hidden" >首页</a>
            <a href="javascript:void(0);" id="previous" class="hidden" title="上一页">上一页</a>
            <a href="javascript:void(0);" id="next" class="hidden" title="下一页">下一页</a>
				<select id="selectPage">
					<c:forEach begin="1" end="${page.pageNum}"  var="mypage">
						<option value="${mypage}"  <c:if test="${page.pageNum==mypage}">selected</c:if>>第${mypage}页</option>
					</c:forEach>
				</select>
	        <a href="javascript:void(0);" id="lastPage" class="hidden" >尾页</a>
        </span>
		</div>
	</form>
</div></div>
				</body>