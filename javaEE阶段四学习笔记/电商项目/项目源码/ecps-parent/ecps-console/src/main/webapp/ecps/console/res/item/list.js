$(function () {

        var showStatus = parseInt($("#showStatus").val());
        if (showStatus == 0) {
            $("#label5").attr("class", "here");
        } else if (showStatus == 1) {
            $("#label4").attr("class", "here");
        } else {
            $("#label6").attr("class", "here");
        }




/*<span class="r page">
  <input type="hidden"  id="pageNum" name="pageNum" />
         <input type="hidden" id="totalCount" name="totalCount" />
        <input type="hidden" value="${page.pageNum}" id="currentPageNo" name="currentPageNo" />
        <input type="hidden" value="${page.totalPage}" id="totalPage" name="totalPage" />
        共<var id="pagePiece" class="orange">${page.totalCount}</var>条<var id="pageTotal">${page.pageNum}/${page.totalPage}</var>
        <a href="javascript:void(0);" id="firstPage" class="hidden" >首页</a>
        <a href="javascript:void(0);" id="previous" class="hidden" title="上一页">上一页</a>
        <a href="javascript:void(0);" id="next" class="hidden" title="下一页">下一页</a>
        <a href="javascript:void(0);" id="lastPage" class="hidden" >尾页</a>
        </span>*/

    //获得单前页码和总页数
    var  totalPage=parseInt($("#totalPage").val());
    var pageNum=parseInt($("#currentPageNo").val());
    
    
    //首页
    $("#firstPage").click(function () {
        $("#pageNum").val(1);
        $("#form1").submit();
    })

    //下一页
    $("#next").click(function () {
        $("#pageNum").val(pageNum+1);
        $("#form1").submit();
    })

    //上一页
    $("#previous").click(function () {
        $("#pageNum").val(pageNum-1);
        $("#form1").submit();
    })
     //尾页
    $("#lastPage").click(function () {
        $("#pageNum").val(totalPage);
        $("#form1").submit();
    })

    //页面分页按钮的展示
    if(pageNum==1&&totalPage==1){
        $("#firstPage").hide();
        $("#previous").hide();
        $("#next").hide();
        $("#lastPage").hide();
    }else if(pageNum==1&&totalPage>pageNum){
        $("#firstPage").hide();
        $("#previous").hide();
        $("#next").show();
        $("#lastPage").show();
    }else if(pageNum >1&&totalPage>pageNum){
        $("#firstPage").show();
        $("#previous").show();
        $("#next").show();
        $("#lastPage").show();
    }else if(pageNum >1&&totalPage==pageNum){
        $("#firstPage").show();
        $("#previous").show();
        $("#next").hide();
        $("#lastPage").hide();
    }
    $("#selectPage").change(function () {
        var pageNum=$(this).val();
        $("#pageNum").val(pageNum);
        $("#form1").submit();
    })

    $("#addItemNoteConfirm").click(function () {
        var note=$("#itemNote").val();
        $("#note").val(note);
        $("#showFrom").submit();
        tipHide("#addItemNote");
    })

})
function showItem(itemId,showStatus) {
    $("#itemId").val(itemId);
    $("#showStatus1").val(showStatus);
    tipShow("#addItemNote");
}
function singleDel(itemId) {
    if(confirm("确定要删除该商品数据吗?")){
        window.location.href=path+"/item/deleteItem.do?itemId="+itemId;
    }
}
function publishItem(itemId) {
    tipShow("#refundLoadDiv");
    $.ajax({
        url:path+"/item/publishItem.do"
        ,type:"post"
        ,data:{
            itemId:itemId
        },dataType:"text"
        ,success:function (responseText) {
            if(responseText=="success"){
                alert("发布成功!")
            }else{
                alert("发布失败!")
            }
            tipHide("#refundLoadDiv");
        }
    })

}