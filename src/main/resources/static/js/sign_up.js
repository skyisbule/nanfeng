"use strict";
head(["注册"]);
var doc = document,
hpost=doc.getElementById("post"),
dom1= doc.getElementById("user"),
dom2= doc.getElementById("pwd"),
dom3= doc.getElementById("NickName"),
htip= doc.getElementById("tip");
dom1.onblur=function(){
    if (pengge.input.test("phone", dom1.value)){
    pengge.post("/public/hasTelNum","tel="+dom1.value,function(d){
		if(d==="hasExist"){
			htip.innerHTML="电话号码已存在";
			htip.style.display = "";
            hpost.disabled = true;
			  pengge.nanfenggx.active(dom1);
		}else{
			htip.style.display = "none";
            hpost.disabled = false;
		}
	});   
    }
	
}
dom3.onblur=function(){
	if (dom3.value!==""){
    pengge.post("/public/hasNickName","nickName="+pengge.html_encode(dom3.value),function(d){
		if(d==="hasExist"){
			htip.innerHTML="昵称已被存在";
			htip.style.display = "";
            hpost.disabled = true;
			  pengge.nanfenggx.active(dom3);
		}else{
			htip.style.display = "none";
            hpost.disabled = false;
		}
	});   
    
	}
}
hpost.onclick = function() {
    var post_data = "";
    
    if (! (pengge.input.test("phone", dom1.value))) {
        pengge.nanfenggx.active(dom1);
        return;
    } else {
        post_data += "telNum=" + dom1.value + "&contact=手机号：" + dom1.value ;
    }
    if (dom2.value === "") {
        pengge.nanfenggx.active(dom2);
        return;
    } else {
        post_data += "&passwd=" + pengge.html_encode(dom2.value);
    }
	if (dom3.value === "") {
        pengge.nanfenggx.active(dom3);
        return;
    } else {
        post_data += "&nickName=" + pengge.html_encode(dom3.value);
    }
	this.disabled = true;
    pengge.post("/regist", post_data + "&signature=我爱南风、我爱分享&headPic=Fo68xdhE-LcKvE_l9c2Gs1hhpbos",
    function(data) {
        if (data === "success") {
 pengge.post("/login", "tel=" + pengge.html_encode(dom1.value)+"&passwd=" + pengge.html_encode(dom2.value),
    function(data) {if(data[0]==="{"){
	 		var arr=JSON.parse(data);
			pengge.cookie.set("nickName",arr["nickName"],31104000,"/");
			pengge.cookie.set("headPic",arr["headPic"],31104000,"/");
			pengge.cookie.set("uid",arr["uid"],31104000,"/");
            location.href = "/?" + Math.random();
 }else{alert("登录失败")}
    });
        } else {
           htip.style.display = "";
            hpost.disabled = false;
        }

    });

}
window.onload=function(){choose(0);}