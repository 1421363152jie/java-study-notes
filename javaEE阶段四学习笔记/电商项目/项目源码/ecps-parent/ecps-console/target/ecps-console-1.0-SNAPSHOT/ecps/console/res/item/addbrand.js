$(function () {

    $("#form111").submit(function () {
        var isSubmit=true;
        $(this).find("[reg2]").each(function () {
            //获得文本框输入的值
            var val=$(this).val();
            val=$.trim(val);
            //获得正则表达式的字符串
            var regStr=$(this).attr("reg2");
            //获得提示信息
            var tip=$(this).attr("tip");
            //获得name属性
            var name=$(this).attr("name");
            //创建正则表达式对象
            var reg=new RegExp(regStr);
            //检验正则
            if(!reg.test(val)){
                $(this).next("span").html("<font color='red'>"+tip+"</font>");
                isSubmit=false;
                return false;
            }else{
                if(name=="brandName"){
                    var result=validBrandName(val);
                    if(result=="no"){
                        $(this).next("span").html("<font color='red'>品牌已存在！</font>");
                        isSubmit=false;
                        return false;
                    }else{
                        $(this).next("span").html("");
                    }
                }else{
                    $(this).next("span").html("");
                }
            }
        })
      return isSubmit;
    });



    $("#form111").find("[reg2]").blur(function () {
        //获得文本框输入的值
        var val=$(this).val();
        val=$.trim(val);
        //获得正则表达式的字符串
        var regStr=$(this).attr("reg2");
        //获得提示信息
        var tip=$(this).attr("tip");
        //获得name属性
        var name=$(this).attr("name");
        //创建正则表达式对象
        var reg=new RegExp(regStr);
        //检验正则
        if(!reg.test(val)){
            $(this).next("span").html("<font color='red'>"+tip+"</font>");
        }else{
            if(name=="brandName"){
                var result=validBrandName(val);
                if(result=="no"){
                    $(this).next("span").html("<font color='red'>品牌已存在！</font>");
                }else{
                    $(this).next("span").html("");
                }
            }else{
                $(this).next("span").html("");
            }
        }
    });



    $("#form111").submit(function () {
        var isSubmit=true;
        $(this).find("[reg1]").each(function () {
            //获得文本框输入的值
            var val=$(this).val();
            val=$.trim(val);
            //获得正则表达式的字符串
            var regStr=$(this).attr("reg1");
            //获得提示信息
            var tip=$(this).attr("tip");
            //创建正则表达式对象
            var reg=new RegExp(regStr);
            //检验正则
            if(val!=null&&val!=""&&!reg.test(val)){
                $(this).next("span").html("<font color='red'>"+tip+"</font>");
                isSubmit=false;
                return false;
            }
            $(this).next("span").html("");
        })
        return isSubmit;
    });

    $("#form111").find("[reg1]").blur(function () {
        //获得文本框输入的值
        var val=$(this).val();
        val=$.trim(val);
        //获得正则表达式的字符串
        var regStr=$(this).attr("reg1");
        //获得提示信息
        var tip=$(this).attr("tip");
        //创建正则表达式对象
        var reg=new RegExp(regStr);
        //检验正则
        if(val!=null&&val!=""&&!reg.test(val)){
            $(this).next("span").html("<font color='red'>"+tip+"</font>");
        }else {
            $(this).next("span").html("");
        }

    })

})




function  validBrandName(brandName) {
    var result="yes";
    $.ajax({
      url:path+"/item/validBrandName.do",
        type:"post",
        async:false,
        data:{
            brandName:brandName
        },
        dataType:"text",
        success:function (responseText) {
            result=responseText;
        },
        error:function () {
            alert("系统错误!");
        }
    })
    return result;
}




function submitUpload() {
    var oprion={
        url:path+"/upload/uploadPic.do",
        type:"post",
        dataType:"text",
        success:function(responseText){
            var jsonObj=$.parseJSON(responseText);
        $('#imgsImgSrc').attr("src",jsonObj.realPath);
         alert(jsonObj.realPath)
          $('#imgs').val(jsonObj.relativePath);
          $("#lastPath").val(jsonObj.realPath);
          },
        error:function() {
            alert("系统出错!");
        }
        };
    $('#form111').ajaxSubmit(oprion);


}