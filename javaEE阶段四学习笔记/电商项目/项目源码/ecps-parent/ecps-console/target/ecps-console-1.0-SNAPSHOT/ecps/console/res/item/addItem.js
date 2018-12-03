$(function(){

    var fck = new FCKeditor("itemDesc");
    fck.BasePath = path+"/ecps/console/res/plugins/fckeditor/";
    fck.Config["ImageUploadURL"] = path+"/upload/uploadForFck.do";
    fck.Height = 400;
    fck.ReplaceTextarea();


    function valid(){
        if(!skuSepValueValid()){
            $("#button1").removeAttr("disabled");
            return false;
        }
        if(!preData()){
            $("#button1").removeAttr("disabled");
            return false;
        }
        return true;
    }
    $("#button1").click(function(){
        $("#myForm").ajaxSubmit({
            beforeSubmit:valid,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            type:'post',
            dataType: "text",
            success:function(responseText){
                alert("success");
            }
        });
        return false;
    });

    function skuSepValueValid(){
        var list = new Array();
        var result=true;
        $(".sp_0").each(function(){
            var buffer="";
            var checkedNum = 0;
            $(this).find(".specValue4").each(function(){
                var obj=$(this).next();
                if(obj.attr("type")=="radio"){
                    var tempBuffer = $(this).nextAll("input:checked").val();
                    if($.trim(tempBuffer) != "" && tempBuffer != null){
                        checkedNum++;
                    }
                    buffer+= tempBuffer;
                }
            });


            if(result){
                list.push(buffer);
            }else{
                return false;
            }
        });
        return result;
    }

    function showResponse(responseText, statusText){
        $("#button1").removeAttr("disabled");
        var obj=eval("("+responseText+")");
        alert(obj.message);
        if(obj.result=="success"){
            document.location.href="<c:url value='/${system }/item/listEntity.do'/>";
        }

    }

});
//==================================================================================================================
$(function(){
    var divNum=1;
    var tObj;
    $("#tabs a").each(function(){
        if($(this).attr("class").indexOf("here") == 0){tObj = $(this)}
        $(this).click(function(){
            var c = $(this).attr("class");
            if(c.indexOf("here") == 0){return;}
            var ref = $(this).attr("ref");
            var ref_t = tObj.attr("ref");
            tObj.attr("class","nor");
            $(this).attr("class","here");
            $(ref_t).hide();
            $(ref).show();
            tObj = $(this);
            if(ref == '#tab_2'){
                FCKeditorAPI.GetInstance('itemDesc').Focus();
                //FCKeditorAPI.GetInstance('itemDesc').EditorDocument.body.innerHTML = '12123123';
            }
        });
    });
    $("input[reg1]").blur(function(){
        var a=$(this);
        var reg = new RegExp(a.attr("reg1"));
        var objValue = a.val();
        if(!reg.test(objValue)){
            if(a.next("span").length ==0){
                a.after("<span>"+a.attr("desc")+"</span>");
            }
        }else{
            a.next("span").remove();
        }
    });
    //实现页面规格的自动增加和删除
    $("#button2").click(function(){
         divNum++;
         var divHtml=$("#sp_0").html();
         divHtml="<div id='sp_"+divNum+"' class='sp_0'>"+divHtml+"</div>";
        divHtml=divHtml.replace(/specradio1/g,"specradio"+divNum);
        /*sort1      skuPrice1     marketPrice1    stockInventory1    skuUpperLimit1  sku1  location1   showStatus1    skuType1*/
        divHtml=divHtml.replace(/sort1/g,"sort"+divNum);
        divHtml=divHtml.replace(/skuPrice1/g,"skuPrice"+divNum);
        divHtml=divHtml.replace(/marketPrice1/g,"marketPrice"+divNum);
        divHtml=divHtml.replace(/stockInventory1/g,"stockInventory"+divNum);
        divHtml=divHtml.replace(/skuUpperLimit1/g,"skuUpperLimit"+divNum);
        divHtml=divHtml.replace(/sku1/g,"sku"+divNum);
        divHtml=divHtml.replace(/location1/g,"location"+divNum);
        divHtml=divHtml.replace(/showStatus1/g,"showStatus"+divNum);
        divHtml=divHtml.replace(/skuType1/g,"skuType"+divNum);
        divHtml=divHtml.replace(/#sp_0/g,"#sp_"+divNum);
        //写回divNum的值方便后台获取
        $("#divNum").val(divNum);
        $(".page_c").before(divHtml);
    });

    $("#showStatus3").click(function(){
        var a=$("#auditStatus1").attr("checked");
        if("checked" != a){
            alert("必须得审核通过后，才能上架");
            $("#showStatus4").attr("checked",true);
        }
    });
    $("#auditStatus0").click(function(){
        $("#showStatus4").attr("checked",true);
        $("#showStatus1").attr("value","1");
    });
    $("#auditStatus2").click(function(){
        $("#showStatus4").attr("checked",true);
        $("#showStatus1").attr("value","1");
    });
    $("#showStatus4").click(function(){
        $("#showStatus1").attr("value","1");
    });
});

//商品规格的redio选中与取消
$(".sp_0").find("input[type=radio]").live("dblclick",function(){
    if($(this).attr('checked') == 'checked'){
        $(this).removeAttr('checked');
    }else{
        $(this).attr('checked','checked');
    }
});

function changePri(obj){
    var reg0=/^[0-9]{1,7}\.{0,1}[0-9]*$/;
    var test=obj.value;
    if(!reg0.test(test)){
        return;
    }
    var test1=test.indexOf(".");
    var firstSub=test.substring(0,test1);
    var lastSub=test.substring(test1+1,test.length);
    if(lastSub.length >= 3) {
        lastSub=lastSub.substring(0, 2);
    }
    if(lastSub.length==1){
        lastSub=lastSub+'0';
    }
    if(lastSub.length==0){
        lastSub='00';
    }
    if(test1==-1){
        obj.value=test+".00";
    }
    else{
        obj.value=firstSub+'.'+lastSub;
    }
}
function clickRemove(id){
  if(id=="#sp_0"){
      alert("默认最小的销售单元不可删除!");
  }else{
      if(confirm("确定要删除该最小销售单元的数据吗?")){
          $(id).remove();
      }
  }

}




function submitImgSize1Upload() {
        var oprion={
            url:path+"/upload/uploadPic.do",
            type:"post",
            dataType:"text",
            success:function(responseText){
                var jsonObj=$.parseJSON(responseText);
                $('#imgsImgSrc').attr("src",jsonObj.realPath);
                $('#imgs').val(jsonObj.relativePath);
                $("#lastPath").val(jsonObj.realPath);
            },
        error:function() {
            alert("系统出错!");
        }
    };
    $('#myForm').ajaxSubmit(oprion);
}