"use strict";
var doc=document,
headPic=pengge.cookie.get("headPic"),
nickName=pengge.cookie.get("nickName"),
uid=pengge.cookie.get("uid");
if (uid === null) {
    location.href = "/user_guest";
} else {
    doc.getElementById("main").innerHTML = '<span style="background-image:url(http://pic.zdnfbbs.cn/' + headPic + ')"></span><h1>' + pengge.nanfenggx.font_len((nickName || "南风共享用户"),20) + '</h1><h2>' + (pengge.cookie.get("school") || "中山大学南方学院") + '</h2>';

				
	var j1=new XMLHttpRequest();j1.open("GET","http:/static/images/user_ui");j1.setRequestHeader("Content-type","application/x-www-form-urlencoded");j1.send("");j1.onreadystatechange=function(){if(j1.readyState===4&&(j1.status===200||j1.status===304)){

        var ui_data = j1.responseText.split("|"),
        user_index = [{
            "goods_list?type=release_list&": "我发布的",
            "goods_list?type=buy_list&": "我拍下的"
        },
        {
            "my_settings": "个人设置",
            "goods_list?type=my_favourite&": "我收藏的",
            "my_message": "我的消息"
        },
        {
            "help": "帮助",
            "about": "关于"
        }],
        out = '<div class="card" style="height: 70vw ;opacity: 0"></div>',
        o = 1,
        i,
        j;

        for (j = 0; j < user_index.length; j++) {
            for (i in user_index[j]) {
                out += '<a class="card left" target="view_window" href="' + i + '"><svg style="left:3vw;" viewBox="0 0 1024 1024"><path style="fill:rgb(252,82,69)" d="' + ui_data[o] + '"></path></svg>' + user_index[j][i] + '<svg style="right:3vw;" viewBox="0 0 1024 1024"><path d="' + ui_data[0] + '"></path></svg></a>';
                o++;
            }
            out += '<div class="card" style="height: 5vw ;opacity: 0"></div>';
        }
        doc.getElementById("card").innerHTML = out;
    }
    }
}
document.getElementById("post").onclick=function(){
pengge.cookie.set("headPic","",-1,"/");
pengge.cookie.set("nickName","",-1,"/");
pengge.cookie.set("uid","",-1,"/");
pengge.cookie.set("session","",-1,"/");
setTimeout(function(){ location.href = "/user_guest";},10);
}
