var photos = [];
var photoPath = localStorage.getItem("userPath");
var fileType = localStorage.getItem("certificateFileType");
$(function () {
    if (photoPath != null && photoPath != "") {
    	getHidePhotoList(photoPath);
    } else {
        getHidePhotoList(null);
    }
});

// 加载接地图片
function getHidePhotoList(data) {
    $("#dowebok").empty();
    var results = data;
    if (results != null && results.length > 0) {
        var html = "";
        var s = results.replaceAll("@","/");
        var l =bonuspath + '/backstage/user/downFile?headerUrl='+s;
        var path = l;
        html += "<li class='disPhotoLi'>";
        html += '<img data-original="' + path + '" src="' + path + '"  class="img" />';
        html += " </li>";
        $("#dowebok").append(html);
        /*var viewer = new Viewer(document.getElementById('dowebok'), {
            url: 'data-original',
            show: function () {
                viewer.update();
            }
        });*/
    } else {
        var html = "<li style='color:black;float: left;border: none; font-size: 20px;height: 20%;position: absolute;\n" + "left: 315px;\n" + "top: 170px;'>没有相关数据</li>";
        $("#dowebok").append(html);
    }
}