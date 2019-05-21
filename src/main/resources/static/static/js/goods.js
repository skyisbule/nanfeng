"use strict";
var doc = document,
gid = pengge.url.data("gid"),
page_num = 0,
hmessage = doc.getElementById("message"),
message_finish = false,
is_want_by=true,
uid = pengge.cookie.get("uid");
if (uid !== null) {
    uid = parseInt(uid)
}
pengge.post("/public/commodity/get-with-info-by-gid", "gid=" + gid,function(data) {
			if(data.length<6){
			alert("商品不存在！");
			location.href="/?"+Math.random();
			return;
		}

    var data_arr = JSON.parse(data),hpic= doc.getElementById("picture");
	is_want_by=(data_arr['is_want_by']!==0)?true:false;
    doc.title = pengge.nanfenggx.font_len(data_arr["goods_name"], 30) + " - 南风共享";
	    if (uid !== null) {
        doc.getElementById("layer").style.display = "block";
        doc.getElementById("message_login").style.display = "none";
        doc.getElementById("my_portrait").style.cssText = 'background-image: url(http://pic.zdnfbbs.cn/' + pengge.cookie.get("headPic") + ');display:block';
    } 
	if(data_arr["picture"]!==undefined){
		data_arr["picture"] = JSON.parse(data_arr["picture"]);
    for (var i = 0; i < data_arr["picture"].length; i++) {
       hpic.innerHTML += '<img onClick="window.open(this.src)" src="http://pic.zdnfbbs.cn/' + data_arr["picture"][i] + '">';
    }	
	}else{
		hpic.style.height="0";
	}
    doc.getElementById("title").innerHTML = data_arr["goods_name"];
    doc.getElementById("price").innerHTML = data_arr["price"] / 100;
    doc.getElementById("page_views").innerHTML += data_arr["page_views"];
    doc.getElementById("content").innerHTML = data_arr["content"];
    doc.getElementById("buy_now_text").innerHTML = ((uid === null) ? "<span><a href='/sign_in'>登录</a>后才能"+(is_want_by?"购买":"发布")+"商品哦！</span>": ("<span><p>"+(is_want_by?"卖":"买")+"家联系方式:</p>" + data_arr["contact"] + "</span>"));
    doc.getElementById("promulgator_info").innerHTML = '<span class="portrait" style="background-image: url(http://pic.zdnfbbs.cn/' + data_arr["head_pic"] + ')"></span><b>' + pengge.nanfenggx.font_len(data_arr["nick_name"],20)+ '</b><p>' + (data_arr["school"] || "中山大学南方学院") + '</p>';
    doc.getElementById("write_message").dataset.uid = data_arr["uid"];
    doc.getElementById("transaction").innerHTML = '<th>' + data_arr["selling"] + '</th><th>' + data_arr["release_num"] + '</th>';
    get_message_num();
	if(!is_want_by){
		doc.getElementById("buy").value = "我有此物";
	}
    if (data_arr["is_sell_out"] !== 0) {
        var dom = doc.getElementById("buy");
        dom.disabled = true;
        dom.style.backgroundColor = "#888";
        dom.value = "已下架";

    }
	
});
doc.getElementById("buy").onclick = function() {
    doc.getElementById("buy_now").style.display = "block";
		    if (uid !== null) {
				pengge.post("/private/order/add", "gid=" + gid+"&uid="+uid,function(){});

			}
}
function get_message_num() {
    pengge.post("/public/message/count-by-gid?gid=" + gid, "",
    function(dat) {
        doc.getElementById("messages").innerHTML = dat;
    },
    true);
}
function get_message() {

    pengge.post("/public/message/get-by-gid?gid=" + gid + "&page=" + page_num * 10, "",
    function(data) {
        var out = "",
        data_arr = JSON.parse(data);
        for (var j = 0; j < data_arr.length; j++) {
            out += '<div><span class="portrait" style="background-image: url(http://pic.zdnfbbs.cn/' + data_arr[j]["head_pic"] + ')"></span><b>' + pengge.nanfenggx.font_len(data_arr[j]["nick_name"],16) + '</b><h6>' + pengge.get_time(data_arr[j]["release_time"] / 1000) + '</h6><br><h5 onClick="reply(' + data_arr[j]["uid"] + ',\'' + pengge.nanfenggx.font_len(data_arr[j]["nick_name"],16) + '\')">' + data_arr[j]["content"] + '<br>&nbsp;</h5></div>';
        }
        if (!message_finish) {
            hmessage.innerHTML += out;
        }
        if (j < 10) {
            doc.getElementById("load_message").innerHTML = "没有更多留言了";
            message_finish = true;
        } else {
            page_num++;

        }
		if(pengge.url.data("mid")!==null){
		reply(pengge.url.data("releaser"), decodeURI(pengge.url.data("nick_name")));
		//pengge.post("/private/message/tag-readed","uid="+uid+"&gid="+gid,function(){});
        window.history.pushState({} , 0 , location.pathname + "?gid=" + gid + "&");
	}

    },
    true);

}
window.onload = function() {
    get_message();
    if(uid!==null){pengge.post("/private/favorite/is-favorited", "gid=" + gid + "&uid=" + uid,
    function(d) {
        var dom = doc.getElementById("favorite");
        if (d === "yes") {
            dom.onclick = null;
            dom.getElementsByTagName("span")[0].innerHTML = "已收藏"
        }
    })}
};
doc.getElementById("favorite").onclick = function() {
	this.getElementsByTagName("span")[0].innerHTML = "已收藏";
    this.onclick = null;
    pengge.post("/private/favorite/add", "gid=" + gid + "&uid=" + uid,function(){});
};	
window.addEventListener('scroll',
function() {
    var check = function() {
        if (((doc.body.scrollHeight) - (doc.documentElement.scrollTop || doc.body.scrollTop) - doc.body.clientHeight) <= doc.body.clientWidth * 0.7) {
            get_message();
        }
    }
    var timer = null;
    return function() {
        if (message_finish) {
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
} ());
function reply(id, nick_name) {
    if (id === uid) {
        return;
    }
    var dom = doc.getElementById("write_message");
    dom.value = "";
    dom.placeholder = "回复 " + nick_name;
    dom.focus();
    dom.dataset.uid = id;
    dom.dataset.name = nick_name;
    var toTop = function(target, parent) {
        var top = 0;

        while (target && target != parent) {
            top += target.offsetTop;
            target = target.offsetParent
        }
        return top
    }

    window.scrollTo(0, toTop(dom, doc) - doc.body.clientWidth * 0.25)

}
doc.getElementById("send_message").onclick = function() {
    var dom = doc.getElementById("write_message"),data="content=" + ((dom.dataset.name === "") ? "": ("回复@" + pengge.html_encode(dom.dataset.name) + "<br>")) + pengge.html_encode(dom.value) + "&receiver=" + dom.dataset.uid + "&gid=" + gid + "&releaser=" + uid;
    if (dom.value === "") {
        return;
    }
    dom.value = "";
    dom.dataset.name = "";
    dom.dataset.uid = uid;
    dom.placeholder = "想说点什么";
    pengge.post("/private/message/add",data,
    function(d) {
        if (d === "success") {

            hmessage.innerHTML = '<div id="load_message">正在加载……</div>';
            message_finish = false;
            page_num = 0;
            get_message();
            get_message_num();
        } else {
            alert("发送失败")
        }
    })

}