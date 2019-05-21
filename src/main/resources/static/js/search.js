"use strict";
window.onload = function() {
wd=pengge.url.data("wd")||"";
	doc.getElementById("search_input").value=wd;
	 hgoods.innerHTML = '<div class="card goods" id="loading">正在加载……</div>';
			if(sessionStorage.getItem("index_get_goods")===null)
	{
		pengge.post("/public/commodity/search", "isWantBy=" + isWantBy + "&goodsType=0&goodsName="+ pengge.html_encode(wd) + "&page=" + page_num * 10 + "&isSellOut=0",function(d){get_goods(d)});
		
	}else{
		
		get_goods(sessionStorage.getItem("index_get_goods"));
		setTimeout(function(){
		window.scrollTo(0,parseInt(sessionStorage.getItem("index_page")));
		sessionStorage.removeItem("index_get_goods");
		sessionStorage.removeItem("index_page");
		},100);
	}
}