function findMsg(phone,msg){
    var url = "http://api.ktsms.cn/sms_token?ddtkey=bonus&secretkey=DpUMTr75";
    var content = "【产业工人管理平台】";
    content=content+msg;
    var userPhone = phone;

    url = url + "&mobile=" + userPhone + "&content=" + content;

    $.ajax({
        type: 'POST',
        url: url,
        data: {},
        dataType: "json",
        success: function(data) {
            console.log(data+"success");
        },
        error: function (XMLHttpRequest, textStatus, e) {
            console.log("数据请求发生异常,请稍后重试");
        }
    });

}