$(function () {
    $("#register").click(function () {
        console.log("注册处理");
        var uname = $("#uname").val();
        var upwd = $("#upwd").val();
        var sendMsg = {
            uname: uname,
            upwd: upwd
        };
        console.log("登录处理请求信息：" + JSON.stringify(sendMsg));
        $.ajax({
            "url": "/ajaxRegister",
            "type": "POST",
            contentType: "application/json",
            "data": JSON.stringify(sendMsg),
            "dataType": "json",
            "success": function (json) {
                console.log("success 返回数据=" + json);
                var rescode = json.rescode;
                var resdesc = json.resdesc;
                if (rescode == 00) {
                    alert(resdesc);
                } else {
                    alert(rescode + " " + resdesc);
                }
            },
            "error": function (xmlhttp, errorText) {
                console.log(xmlhttp);
                console.log(typeof xmlhttp.status);
                switch (xmlhttp.status) {
                    case 404:
                        alert("未找到URL资源");
                        break;
                    case 405:
                        alert("无效请求方式");
                        break;
                    case 500:
                        alert("服务器内部错误");
                        break;
                    default:
                        alert("异常");
                        break;
                }
                ;
            }

        });
    }),

        $("#login").click(function () {
            alert("1111111");
            var uname = $("#uname").val();
            var upwd = $("#upwd").val();
            var sendMsg = {
                uname: uname,
                upwd: upwd
            };
            console.log("登录处理请求信息：" + JSON.stringify(sendMsg));
            $.ajax({
                url: "/loginAjax",
                type: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(sendMsg),
                success: function (json) {
                    var resCode = json.rescode;
                    var resDesc = json.resdesc;
                    var ext = json.ext;
                    if (resCode == 00) {
                        alert("登录成功");
                    } else {
                        alert(resCode + resDesc);
                    }
                },
                error: function (xmlhttp, errorText) {

                }

            });
        }),

        $("#select").click(function () {
            console.log("登录处理");
            // alert("login");
            console.log("查询");
            var uname = $("#uname").val();
            var upwd = $("#upwd").val();
            var sendMsg = {
                uname: uname,
                upwd: upwd
            };
            console.log("请求信息：" + JSON.stringify(sendMsg));
            $.ajax({
                "url": "/ajaxSelect",
                "type": "POST",
                "contentType": "application/json",
                "data": JSON.stringify(sendMsg),
                "dataType": "json",
                "success": function (json) {
                    console.log("success 返回数据=" + JSON.stringify(json));
                    var rescode = json.rescode;
                    var resdesc = json.resdesc;
                    var ext = JSON.parse(json.ext);
                    console.log("success 返回数据=" + ext);
                    if (rescode == 00) {
                        alert("查询成功");
                        var content = "";
                        for (var i = 0; i < ext.length; i++) {
                            console.log(ext[i]);
                            console.log(ext[i].uname);
                            content += ext[i].id + " ";
                            content += ext[i].uname + " ";
                            content += ext[i].upwd + " ";
                            content += "<br>";
                        }
                        $("#content").html(content);
                    } else {
                        alert(rescode + resdesc)
                    }
                },
                "error": function (xmlhttp, errorText) {
                    console.log(xmlhttp);
                    console.log(typeof xmlhttp.status);
                    switch (xmlhttp.status) {
                        case 404:
                            alert("未找到URL资源");
                            break;
                        case 405:
                            alert("无效请求方式");
                            break;
                        case 500:
                            alert("服务器内部错误");
                            break;
                        default:
                            alert("异常");
                            break;
                    }
                    ;
                }

            });
        })

})
