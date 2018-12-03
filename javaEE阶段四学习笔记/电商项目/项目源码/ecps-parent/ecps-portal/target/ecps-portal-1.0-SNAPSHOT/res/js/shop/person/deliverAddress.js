$(function () {

    $("#province").change(function () {
        var areaId=$(this).val();
        loadAddr(areaId,"mycity");
    })
    $("#mycity").change(function () {
        var areaId=$(this).val();
        loadAddr(areaId,"district");
    })

    $("#jvForm").submit(function () {
        var isSubmit=true;
        var addrLength=parseInt($("#addrLength").val());
        if(addrLength>=5){
            alert("亲，收货地址最多添加5个哦！");
            isSubmit=false;
        }
        return isSubmit;
    })
})
function loadAddr(areaId,selectId) {
    $.ajax({
        url:path+"/user/login/getAddr.do",
        type:"post",
        async:false,
        data:{
            areaId:areaId
        },
        dataType:"text",
        success:function (responseText) {
            var jsonArr=$.parseJSON(responseText);
            var areaList=jsonArr.areaList;
            if(selectId=="mycity"){
                $("#mycity").empty();
                $("#district").empty();
                $("#mycity").append("<option value='-1' selected>城市</option>");
                $("#district").append("<option value='' selected>县/区</option>")
            }else if(selectId=="district"){
                $("#district").empty();
                $("#district").append("<option value='' selected>县/区</option>")
            }
            if(areaList!=null&&areaList.length>0){
                var option="";
                for (var i=0;i<areaList.length;i++){
                    option=option+"<option value='"+areaList[i].ereaId+"'>"+areaList[i].ereaName+"</option>";
                }
                $("#"+selectId).append(option);
            }
        },
        error:function () {
            alert("系统出错啦");
        }
    })
}



function modify(shipAddrId) {
    $.ajax({
        url:path+"/user/login/modify.do",
        type:"post",
        data:{
            shipAddrId:shipAddrId
        },
        dataType:"text",
        success:function (responseText) {
            var jsonObj=$.parseJSON(responseText);
            $("#shipAddrId").val(jsonObj.addr.shipAddrId)
            $("#shipName").val(jsonObj.addr.shipName);
            $("#province").val(jsonObj.addr.province);
            loadAddr(jsonObj.addr.province,"mycity");
            $("#mycity").val(jsonObj.addr.city);
            loadAddr(jsonObj.addr.city,"district");
            $("#district").val(jsonObj.addr.district);
            $("#addr").val(jsonObj.addr.addr);
            $("#zipCode").val(jsonObj.addr.zipCode);
            $("#phone").val(jsonObj.addr.phone);
            if(jsonObj.addr.defaultAddr==1){
                $("#defaultAddr").attr("checked","checked");
            }else {
                $("#defaultAddr").removeAttr("checked");
            }
        },
        error:function () {
            alert("系统出错啦");
        }
    })

}
function del(shipAddrId) {
    if(confirm("确定要删除该条地值信息吗?")){
        window.location.href=path+"/user/login/deleteAddr.do?shipAddrId="+shipAddrId;
    }
}

function setDefaultAddr(shipAddrId) {
    if(confirm("设置该条地址为默认收货地址?")){
        window.location.href=path+"/user/login/setDefaultAddr.do?shipAddrId="+shipAddrId;
    }
}