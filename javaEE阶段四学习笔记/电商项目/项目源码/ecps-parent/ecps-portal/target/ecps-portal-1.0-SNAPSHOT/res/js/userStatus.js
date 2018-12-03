$(function () {
    $.ajax({
        url:path+"/user/getUser.do",
        type:"post"
        ,dataType:"text",
        success:function (responseText) {
            var jsonObj=$.parseJSON(responseText);
            $("#loginAlertIs").html(jsonObj.user.username);
        },
        error:function () {
            alert("系统出错啦!");
        }

    })
})