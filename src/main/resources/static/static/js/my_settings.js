"use strict";
head(["个人设置"]);
var doc = document,
list = ["nickName", "realName", "telNum", "email", "contact", "signature"],
old_user_arr=[],
hpost=doc.getElementById("post"),
dom3=doc.getElementById("nickName");
if(pengge.cookie.get("uid")===null){
	location.href="/sign_in"
}
doc.getElementById("upload").onchange = function() {
    if (!this.files || !this.files[0]) {
        return
    };
    var b = doc.getElementById("showHeadPic");
    b.src = "http:/static/images/loading.gif";
    var a = new FileReader();
    a.onload = function(c) {
        var e = c.target.result,
        d = new Image();
        d.src = e;
        d.onload = function(gg) {
            var n = this,
            g = 600,
            m = g * n.height / n.width;
            var k = doc.createElement("canvas"),
            o = doc.createAttribute("width");
            o.nodeValue = g;
            var i = doc.createAttribute("height");
            i.nodeValue = m;
            k.setAttributeNode(o);
            k.setAttributeNode(i);
            k.getContext("2d").drawImage(n, 0, 0, g, m);
            var f = k.toDataURL("image/jpg", 0.3);
            b.src = ((f.length > e.length) ? e: f)
        }
    };
    a.readAsDataURL(this.files[0])
};
pengge.post("/public/user/get-info-by-id", "uid=" + pengge.cookie.get("uid"),function(data) {
	var d;
    old_user_arr = JSON.parse(data);
    doc.getElementById("showHeadPic").src = "http://pic.zdnfbbs.cn/" + old_user_arr["headPic"];
    for (var i = 0; i < list.length; i++) {
		d=old_user_arr[list[i]];
		if(d==="null"||d===null){old_user_arr[list[i]]="";continue;}
        doc.getElementById(list[i]).value = d;
    }

});
function check_input(type, dom) {
    if (!pengge.input.test(type, dom.value)) {
        dom.focus();
        pengge.nanfenggx.active(dom);
    }

}
dom3.onblur=function(){
	if (dom3.value!==""&&dom3.value!==old_user_arr["nickName"]){
    pengge.post("/public/hasNickName","nickName="+pengge.html_encode(dom3.value),function(d){
		var dom=dom3.parentNode.getElementsByTagName("span")[0];
		if(d==="hasExist"){
			dom.innerHTML="昵称 - 该昵称已存在";
			dom.style.color="#f00";
            hpost.disabled = true;
			pengge.nanfenggx.active(dom3);
		}else{
			dom.innerHTML="昵称";
			dom.style.color="";
            hpost.disabled = false;
		}
	});   
    
	}
}
hpost.onclick = function() {
    var dom;
    this.disabled = true;
    this.value = "正在提交"
    this.style.backgroundColor = "#888";
    for (var i = 0; i < list.length; i++) {
        dom = doc.getElementById(list[i]);

        if (dom.value === "") {
            if (old_user_arr[list[i]] === null||old_user_arr[list[i]] === "") {
                continue;
            } else {
                pengge.nanfenggx.active(dom);
                return;
            }
        }

        if (dom.value !== old_user_arr[list[i]]) {
            old_user_arr[list[i]] = pengge.html_encode(dom.value);
        }

    }

    if (doc.getElementById("showHeadPic").src[0] === "d") {
        var b = doc.getElementById("headPic").getElementsByTagName("img");
        pengge.post("/public/pic/token?fileName=" + Math.random(), "",
        function(g) {
            img_upload(b, g,
            function(h) {
               old_user_arr["headPic"] = h[0];
                post();
            })
        })

    } else {
        post()
    }
}
function post() {
    var data = "dl=0";
    for (var x in old_user_arr) {
        data += "&" + x + "=" + old_user_arr[x];
    }
    pengge.post("/private/user/update", data,
    function(d) {
        if (d === "success") {
			pengge.cookie.set("nickName",old_user_arr["nickName"],31104000,"/");
			pengge.cookie.set("headPic",old_user_arr["headPic"],31104000,"/");
			location.href="/user?"+Math.random();
        }
    })
}
window.onload=function(){choose(0);}
