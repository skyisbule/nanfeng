"use strict";
head(["登录"]);
var doc = document;
doc.getElementById("post").onclick = function() {
    var dom1, post_data = "";
    dom1 = doc.getElementById("user");
    if (dom1.value === "") {
        pengge.nanfenggx.active(dom1);
        return;
    } else {
        post_data += "tel=" + pengge.html_encode(dom1.value);
    }
    dom1 = doc.getElementById("pwd");
    if (dom1.value === "") {
        pengge.nanfenggx.active(dom1);
        return;
    } else {
        post_data += "&passwd=" + pengge.html_encode(dom1.value);
    }
    this.disabled = true;
    pengge.post("/login", post_data,
    function(data) {
		if(data[0]==="{"){
		var arr=JSON.parse(data),referrer = document.referrer,url="";
			if(referrer == "" || referrer.indexOf("/sign_in") >= 0){url="/?";}
			else if(referrer.indexOf("/user") >= 0){url="/?page=user&";}
			else{url=referrer + ((referrer.indexOf("?") >= 0) ? "&": "?");}
			pengge.cookie.set("nickName",arr["nickName"],31104000,"/");
			pengge.cookie.set("headPic",arr["headPic"],31104000,"/");
			pengge.cookie.set("uid",arr["uid"],31104000,"/");
        location.href =  url + Math.random();	
		}else{
		 document.getElementById("tip").style.display = "";
         doc.getElementById("post").disabled = false;	
		}
    });

}
window.onload=function(){choose(0);}

