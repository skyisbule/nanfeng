"use strict";
var goods_type_dom = doc.getElementById("goods_type").getElementsByTagName("td");
function chare_type(type, q) {
	if(choose_top){window.scrollTo(0,vw*0.75);}
    var chare = function(n, s) {
        n = (n + 7) % 8;
        goods_type_dom[n].style.color = (s ? "": "#f00");
    }
    if (goods_type === type && q !== true) {
        return;
    }
    chare(goods_type, true);
    goods_type = type;
    chare(goods_type, false);
    page_num = 0;
    hgoods.innerHTML = '<div class="card goods" id="loading">正在加载……</div>';
	
	
		if(sessionStorage.getItem("index_get_goods")===null)
	{
		pengge.post("/public/commodity/get-with-info-by-page", "isWantBy=" + isWantBy + "&goodsType=" + goods_type+ "&page=" + page_num * 10 + "&isSellOut=0",function(d){get_goods(d)});
		
	}else{
		
		get_goods(sessionStorage.getItem("index_get_goods"));
		setTimeout(function(){
		window.scrollTo(0,parseInt(sessionStorage.getItem("index_page")));
		sessionStorage.removeItem("index_get_goods");
		sessionStorage.removeItem("index_page");
		},100);
	}
  
}

window.onload = function() {
 add_ad();
	
	var j=new XMLHttpRequest();j.open("GET","http:/static/images/home_ui");j.setRequestHeader("Content-type","application/x-www-form-urlencoded");j.onreadystatechange=function(){if(j.readyState===4&&(j.status===200||j.status===304)){
	var svg_arr = j.responseText.split("|"),
        svg_dom = doc.getElementsByName("svg");
        for (var i = 0; i < svg_arr.length; i++) {

            svg_dom[i].innerHTML += '<svg viewBox="0 0 1024 1024"><path d="' + svg_arr[i] + '" /></svg>';
        }
        chare_type(0);
	
	}};j.send("")
	
	
	
	pengge.post("/front/notice/get", "isShow=1",function(g) {
		if(g.toString().length<=5){return ;}
    var f = doc.getElementById("notice").getElementsByTagName("a"),
    e = "";
    do {
        e += "<span>" + g + "</span>"
    } while ( e . length < 100 );
    f[0].innerHTML = e;
    f[1].innerHTML = e;
    var a = f[0].offsetWidth,
    c = 400 * e.length;
    f[0].style.left = a + "px";
    f[1].style.left = a + "px";
    doc.getElementsByTagName("style")[0].innerHTML += "@keyframes notice{from{transform:translate(0,0)}to{transform:translate(-" + a * 2 + "px,0)}}@-webkit-keyframes notice{from{-webkit-transform:translate(0,0)}to{-webkit-transform:translate(-" + a * 2 + "px,0)}}";
    var b = "notice " + c + "ms linear infinite";
    f[0].style.animation = b;
    f[0].style.WebkitAnimation = b;
    b = "notice " + c + "ms linear " + c / 2 + "ms infinite";
    f[1].style.animation = b;
    f[1].style.WebkitAnimation = b
});
}



