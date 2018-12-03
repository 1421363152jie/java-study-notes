$(function () {

    $(".bg_text input").blur(function () {
        var val=$(this).val();
        val=$.trim(val);
        var inputName=$(this).attr("name");
        if(val==null||val==""){
            if(inputName=="username"){
                $("#errorName").html("<font color='#dc143c'>请输入您的用户名!</font>");
                $("#errorName").show(500);
            }else if(inputName=="password"){
                $("#errorName").html("<font color='#dc143c'>请输入您的登陆密码!</font>");
                $("#errorName").show(500);
            }else if(inputName=="captcha"){
                $("#errorName").html("<font color='#dc143c'>请输入验证码!</font>");
                $("#errorName").show(500);
            }

        }else {
            $("#errorName").hide();
        }
    })

    var tip=$("#tip").val();
    if(tip=="code_error"){
        $("#errorName").html("<font color='#dc143c'>验证码错误!</font>");
        $("#errorName").show(500);
    }else if(tip=="UandP_error"){
        $("#errorName").html("<font color='#dc143c'>用户名或密码错误!</font>");
        $("#errorName").show(500);
    }





})
function changeImage() {
    var ImgPath=path+"/user/getImage.do?date="+new Date();
    $("#capImage").attr("src",ImgPath);
}
