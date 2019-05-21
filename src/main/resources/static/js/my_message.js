"use strict";
z=true;
head(["未读消息", "已读消息"]);
window.onload = function() {

    choose(0,true);

}
function get_goods() {
    pengge.post("/private/message/get-if-readed", "page=" + page_num * 10 + "&uid=" + uid + "&isReaded=" + isWantBy,
    function(data) {

        var out = "",
        data_arr = JSON.parse(data);

        for (var j = 0; j < data_arr.length; j++) {
            out += '<li data-gid="'+ data_arr[j]["gid"]+'" data-mid="'+ data_arr[j]["mid"]+'" data-releaser="'+ data_arr[j]["releaser"]+ '" data-nick_name="' + encodeURI(data_arr[j]["nick_name"]) + '" onclick="jump(this)"><span style="background-image: url(http://pic.zdnfbbs.cn/' + data_arr[j]["head_pic"] + ')"></span><h1>' + pengge.nanfenggx.font_len(data_arr[j]["nick_name"], 18) + '</h1><h2>' + pengge.nanfenggx.font_len(data_arr[j]["content"].replace(/<br>/g, " "), 20) + '</h2><h3>' + pengge.get_time(data_arr[j]["release_time"] / 1000) + '</h3>'+/*'<div><img onload="img_onload(this)" src="http://pic.zdnfbbs.cn/' + data_arr[j]["picture"] + '" alt=""/></div>'+*/'</li>';

        }
        hgoods.innerHTML += out;
        if (j < 10) {
            goods_finish = true;
            doc.getElementById("loading").innerHTML = "没有更多消息了";

        } else {
            page_num++;

        }

    });
}
/*function img_onload(a) {
    if (a.offsetTop < vw * 0.2) {
        if (a.height > a.width) {
            a.style.width = "20vw";
        } else {
            a.style.height = "20vw";
        }
        a.style.margin = a.height / ( - 2) + "px " + a.width / ( - 2) + "px";
        a.style.opacity = "1";
    } else {
        a.style.display = "none"
    }

}*/

//
function jump(a){
	var a_data=a.dataset,next=function(){
		choose(isWantBy,true);
		location.href="goods?gid="+a_data.gid+"&mid="+a_data.mid+"&releaser="+a_data.releaser+"&nick_name="+a_data.nick_name+"&";
	}
	if(isWantBy===0){
pengge.post("/private/message/tag-readed","uid="+uid+"&gid="+a_data.gid,next);
	}else{next();}
	
	
}
doc.onscroll = function() {
    var top = doc.documentElement.scrollTop || doc.body.scrollTop;

    var check = function() {
        if (((doc.body.scrollHeight) - top - doc.body.clientHeight) <= vw) {
            get_goods();
        }
    }

    if (goods_finish) {
        return;
    }
    if (timer === null) {
        check();
        timer = setTimeout(function() {
            check();
            timer = null;
        },
        1000);
    }

}