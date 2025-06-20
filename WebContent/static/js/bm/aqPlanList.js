var  uname = localStorage.getItem("uname");
var  pwd = localStorage.getItem("pwd");
$(function() {
	checkLogin();
});

function checkLogin(){
      
	$.ajax({
		type: "post",
		url: "http://10.1.0.142:1999/gz_aqgqj/login/userLogin",
		data: {
			username: uname,
			password: pwd
		},
		success: function(data) {
		    // alert(JSON.stringify(data));
			if(data.code == "200") {
				var  token =data.token;
				localStorage.setItem("token", data.token);
				localStorage.setItem("name", name);
				localStorage.setItem("isPlanAdd", "1");
				//window.open("http://192.168.0.14:21625/gz-gqj/index.html")
				window.open("http://10.1.0.142:1999/gz-aqgqj/index.html?token="+token+'&isPlanAdd=1')
			} else {
				alert("没有权限！");
			}

		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！"+XMLHttpRequest.status);
		}
	});
}
