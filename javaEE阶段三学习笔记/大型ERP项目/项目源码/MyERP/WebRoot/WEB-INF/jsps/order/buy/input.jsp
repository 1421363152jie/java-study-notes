<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglibs.jsp" %>
<script type="text/javascript">
	//修改供应商
	$(function() {
		$("#supplierId").change(function() {
			var supplierId=$(this).val();
			getProductType(supplierId);
			//选中一个供应商，禁用掉不能再选
			$(this).attr("disabled","disabled");
			$("#supplierIdHid").val(supplierId);
		});	
		$(".goodsType").change(function() {
			var productTypeId=$(this).val();
			var trobj=$(this).parent().parent();
			getProduct(productTypeId,trobj);
			
		});
		$(".goods").change(function() {
			var productId=$(this).val();
			var ptrobj=$(this);
			var count =0;
			$(this).parent().parent().parent().find(".goods").each(function() {
				 var productId1=$(this).val();
				if(productId1==productId){
					count++;
				}	
			});
			if(count==2){
				Dialog.alert("提示：商品已存在!");
				$(this).find("[value='"+productId+"']").remove();
				
			}else{
				getProductDetail(productId,ptrobj.parent().parent());
			}
			
		});
     	
		//新建一条数据
		$("#add").click(function() {
			//获得一行,克隆
		  var tr=$("#defaultTr").clone(true);
			//新建的id属性删除
			tr.removeAttr("id");
			//把新拷贝的行中下拉清空
			tr.find(".goods").empty();
			//获得当前拷贝的商品的类别Id
			var productTypeId=tr.find(".goodsType").val();
				//追加到前面
		  $("#finalTr").before(tr);
			//加载当前商品类别下的商品
		getProduct(productTypeId,tr);
		});
		
	  
		
		
	      //删除
	      $(".deleteBtn").click(function() {
			
	    	var trobj=$(this).parent().parent();  
	    	 var trId=trobj.attr("id");
	    	if(trId=="defaultTr"){
	    		Dialog.alert("提示:该列不可删除!");
	    	}else{
	    		trobj.remove();
	    		var totalPrice=0;
	    		//重新计算总价
				$("#order").find(".total").each(function() {
					var trtotal=parseInt($(this).attr("Total"));
					totalPrice=totalPrice+trtotal;
				});
				//获得总结的html设置上值
				$(".all").html(totalPrice+"元");
	    	}
	    	 
		});
	 
		
			
			
		   $(".prices").keyup(function() {
			var inPrice=parseInt($(this).val());
			 //获得商品数量
	            var count=parseInt($(this).parent().parent().find(".prices").val());
	           //计算小计
	          $(this).parent().parent().find(".total").html(inPrice*count+"元");
	            //给自定义属性赋值方便计算总计
	            $(this).parent().parent().find(".total").attr("Total",inPrice*count);
	            //计算总价
	            var totalCount=0;
	            $(this).parent().parent().parent().find(".total").each(function() {
				    var toatlnum=parseInt($(this).attr("Total"));
				    totalCount=totalCount+toatlnum;
			    });
	           //给总计一行设置参数
	           $(".all").html(totalCount+"元");
	       
	    });	

		   $(".num").keyup(function() {
				var count=parseInt($(this).val());
				 //获得商品数量
		            var inPrice=parseInt($(this).parent().parent().find(".num").val());
		           //计算小计
		          $(this).parent().parent().find(".total").html(inPrice*count+"元");
		            //给自定义属性赋值方便计算总计
		            $(this).parent().parent().find(".total").attr("Total",inPrice*count);
		            //计算总价
		            var totalCount=0;
		            $(this).parent().parent().parent().find(".total").each(function() {
					    var toatlnum=parseInt($(this).attr("Total"));
					    totalCount=totalCount+toatlnum;
				    });
		           //给总计一行设置参数
		           $(".all").html(totalCount+"元");
		          
				
		});	
  
		   
		   
	  
	});
	//获得商品类型
    function getProductType(supplierId) {
    	var trobj=$("#defaultTr");
	  $.ajax({
		  url:"${path}/ajax_supplier_getSupplier",
		  async:false,
		  type:"post",
		  data:{
			"query.supplierId":supplierId
		  },	
		  dataType:"text",
		  success:function(responseText){
           trobj.find(".goodsType").empty();
          var jsonArr=$.parseJSON(responseText);
		  for (var i = 0; i < jsonArr.length; i++) {
			  $("#defaultTr").find(".goodsType").append("<option value='"+jsonArr[i].productTypeId+"'>"+jsonArr[i].name+"</option>");
		  }
		  }	
	});
	  
	  var prtype=$("#defaultTr").find(".goodsType").val();
	  getProduct(prtype,trobj);
}	
	//获得商品
    function getProduct(productTypeId,trobj) {
	  $.ajax({
		  url:"${path}/ajax_productType_getproduct",
		  async:false,
		  type:"post",
		  data:{
			"query.productTypeId":productTypeId
		  },	
		  dataType:"text",
		  success:function(responseText){
           trobj.find(".goods").empty();
          var jsonArr=$.parseJSON(responseText);
		  for (var i = 0; i < jsonArr.length; i++) {
			  var isEext=false;
			  $("#order").find(".goods").each(function() {
				 var productId=$(this).val();
				 if(productId==jsonArr[i].productId+""){
					 isEext=true;
				 }				  
			});
			  if(!isEext){
				  trobj.find(".goods").append("<option value='"+jsonArr[i].productId+"'>"+jsonArr[i].name+"</option>");
			  }	  
		  }
		  }	
	});
	  var prId=$(trobj).find(".goods").val();
	  getProductDetail(prId,trobj);
	  
}	 
    //获得商品的明细
    function getProductDetail(productId,trobj) {
	  $.ajax({
		  url:"${path}/ajax_product_getproductDetail",
		  async:false,
		  type:"post",
		  data:{
			"query.productId":productId
		  },	
		   dataType:"text",
		  success:function(responseText){
          var jsonobj=$.parseJSON(responseText);
            trobj.find(".prices").val(jsonobj.inPrice);
           //获得商品数量
           var count=parseInt(trobj.find(".num").val());
           //计算小计
           trobj.find(".total").html(jsonobj.inPrice*count+"元");
            //给自定义属性赋值方便计算总计
           trobj.find(".total").attr("Total",jsonobj.inPrice*count);
            //计算总价
            var totalCount=0;
           trobj.parent().find(".total").each(function() {
			    var toatlnum=parseInt($(this).attr("Total"));
			    totalCount=totalCount+toatlnum;
		    });
           //给总计一行设置参数
           $(".all").html(totalCount+"元");
          
		  }	
	});
}	 
	
      function submitFrom() {
		var result="";
			$("#orderFrom").ajaxSubmit({
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
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form  id="orderFrom" action="${path}/ajax_orderModel_submitOrder" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px">
		   <s:select id="supplierId" cssClass="kuan"  cssStyle="width:190px" list="#slist" name="order.supplier.supplierId" listKey="supplierId" listValue="name" headerKey="" headerValue="---请选择---"></s:select>													
						<input id="supplierIdHid" type="hidden" name="order.supplierId">
						</td>
						<td height="30">
							<a id="add"><img src="${path}/images/can_b_02.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<tr id="defaultTr" align="center" bgcolor="#FFFFFF">
						<td height="30">
							<select name="productTypeIds"  class="goodsType" style="width:200px">
							
							</select>
						</td>
						<td>
							<select name="productIds" class="goods" style="width:200px">
								
							</select>
						</td>
						<td><input name="detailNum" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>
						<td><input name="detailPries" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="100"/> 元</td>
						<td class="total" Total="" align="right">100.00&nbsp;元</td>
						<td>
							<a class="deleteBtn delete xiu" value="4"><img src="${path}/images/icon_04.gif" /> 删除</a>
						</td>
					</tr>
					<tr id="finalTr" align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right">100.00&nbsp;元</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	
	<div class="content-bbg"></div>
</div>
