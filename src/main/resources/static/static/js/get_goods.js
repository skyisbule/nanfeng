"use strict";
var ty=(location.pathname.indexOf('search')>=0)?false:true,timer = null,
goods_finish = true,
ad=[],
ad_frequency=0,
ad_num=0,doc = document,
page_num = 0,
index_get_goods=[],
goods_type = 2,
vw = doc.body.clientWidth,
choose_top=false,wd="";
isWantBy=parseInt(pengge.url.data("type")||isWantBy);
doc.write('<style type="text/css">#choose {position:'+(ty?'absolute':'fixed')+';width:100vw;top:'+(ty?'70':'8')+'vw;left:0;height:17vw;z-index:100;text-align:center;line-height:8vw;font-size:4vw;border-bottom:0.2vw solid #ccc;background-color: #fff}#choose span {position:absolute;width:20vw;height:8vw;border-bottom:1vw solid rgb(211,23,18);bottom:0;z-index:101;color: rgb(211,23,18)}#choose .un {color: inherit!important;border: none!important;bottom: 1vw!important}#goods{position:absolute;top:'+(ty?'145':'25')+'vw;}.card{display:block;position:relative;width:94vw;margin:3vw;background-color:#fff;overflow:hidden;text-decoration:none;color:#222;border-bottom:0.2vw dotted #aaa;}.card div{position:relative;height:30vw;margin:2vw;text-align:center;top:15vw;white-space:nowrap;}.card p{position:relative;width:88vw;left:4vw;color:#888;overflow:hidden;font-size:4vw;margin-top:15vw;}.card img{position:absolute;top:50%;left:50%;display:block;opacity:0;-webkit-transition:opacity 0.5s;transition:opacity 0.5s;}.card h1{position:absolute;height:28vw;width:28vw;box-shadow:0 0 1vw 0 #555;overflow:hidden}.card h2{position:absolute;right:4vw;top:4vw;font-size:5vw;font-weight:500;color:rgb(252,82,69);font-family:Impact,Charcoal,sans-serif;}.card h3{position:absolute;left:17vw;top:1vw;font-size:4.5vw;font-weight:500;}.card h4{position:absolute;left:17vw;bottom:1vw;font-size:3.5vw;font-weight:300;}.card h5{position:relative;right:4vw;float:left;padding:0 0 4vw 8vw;font-size:4.5vw;font-weight:500;color:rgb(252,82,69);}.card h6{position:relative;right:4vw;float:right;font-size:4.1vw;font-weight:300;height:6vw;line-height:6vw;vertical-align:middle;color:#bbb;}.card h{position:absolute;display:block;left:3vw;top:1vw;height:11vw;width:11vw;border-radius:6vw;background-size:100% 100%;background-repeat:no-repeat;box-shadow:0 0 1vw 0 #555}.card span{position:absolute;top:1.5vw;display:block;width:94vw;height:13vw;}#loading {position:absolute;background-color:transparent;border-bottom: none!important; font-size:5vw;line-height:20vw;text-align:center;bottom:-50vw;height: 50vw}.ad{height:40vw;background-size:94vw 30vw;background-repeat:no-repeat;margin-bottom: 2vw;}.ad p{position: absolute!important; bottom: 0;-webkit-margin-before: 0; -webkit-margin-after: 0;height: 10vw;line-height: 10vw}.ad b{position: absolute; bottom: 0;right: 0;height: 10vw;line-height: 10vw;font-size:4vw;}</style><div id="choose"><span style="left: 25vw" onClick="choose(1)" class="'+(isWantBy?'':'un')+'">闲置</span><span style="right:25vw" onClick="choose(0)" class="'+(isWantBy?'un':'')+'">需求</span></div><div id="goods"></div><div style="position: absolute;top: 83vw;height: 100vh;width: 1vw;"></div>');

var choose_dom = doc.getElementById("choose").getElementsByTagName("span"),
hchoose = doc.getElementById("choose").style,
hgoods = doc.getElementById("goods");

function get_goods(data) {
        var out = "",
        data_arr = JSON.parse(data),
        pic_len = 0,
		content_len;
        for (var j = 0; j < data_arr.length; j++) {
			if(Math.random()<ad_frequency){
				if(ad_num>=ad.length){ad_frequency=0;add_ad();}else{
							 out += '<a name="card" onclick="leave()" class="card ad" style="background-image: url(' + ad[ad_num]["pic"] + ');" href="' + ad[ad_num]["links"] + '"><p>' + pengge.nanfenggx.font_len(ad[ad_num]["content"],32) + '</p><b>广告</b></a>';
				ad_num++;
				}


			}
			index_get_goods.push(data_arr[j]);

			content_len=300;
            out += '<a name="card" class="card" onclick="leave()"  href="goods?gid=' + data_arr[j]["gid"] + '"><h2>￥' + data_arr[j]["price"] / 100 + '</h2>';
			if(data_arr[j]["picture"]!==undefined&&data_arr[j]["picture"].toString().length>5){
			content_len=40;
			out+="<div>";
			if(typeof(data_arr[j]["picture"])==="string"){data_arr[j]["picture"] = JSON.parse(data_arr[j]["picture"]);}
            pic_len = (data_arr[j]["picture"].length >= 3) ? 3 : data_arr[j]["picture"].length;
            for (var i = 0; i < pic_len; i++) {
                out += '<h1 style="left:'+(100/3*i)+'%"><img onload="img_onload(this)" src="http://pic.zdnfbbs.cn/' + data_arr[j]["picture"][i] + '"alt=""/></h1>';
            }
            out += '</div>';
			}
            out +=  '<p>' + pengge.nanfenggx.font_len(data_arr[j]["content"].replace(/<br>/g, " "),content_len) + '</p><span><h style="background-image:url(http://pic.zdnfbbs.cn/' + data_arr[j]["head_pic"] + ')"></h><h3>' + pengge.nanfenggx.font_len(data_arr[j]["nick_name"],16) + '</h3><h4>' + (data_arr[j]["school"] || "中山大学南方学院") + '</h4></span><h5>' + ((data_arr[j]["is_sell_out"] === 0) ? ((isWantBy===1)?"正在销售":"正渴望着"): "已下架") + '</h5><h6>' + pengge.get_time(data_arr[j]["release_time"] / 1000)  + '</h6></a>';
        }
        hgoods.innerHTML += out;
        if (j < 10) {
            goods_finish = true;
            doc.getElementById("loading").innerHTML = "没有更多商品了";

        } else {
            page_num++;

        }

    }


function img_onload(a) {
if (a.offsetTop < vw * 0.2) {
		if(a.height>a.width){a.style.width="28vw";}else{a.style.height="28vw";}
		a.style.margin=a.height/(-2)+"px "+a.width/(-2)+"px";
        a.style.opacity = "1";
    } else {
        a.style.display = "none"
    }

}

doc.onscroll = function() {

    //变量scrollTop是滚动条滚动时，距离顶部的距离
    var scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
    //变量windowHeight是可视区的高度
    var windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
    //变量scrollHeight是滚动条的总高度
    var scrollHeight = document.documentElement.scrollHeight||document.body.scrollHeight;
    //滚动条到底部的条件
    if(scrollTop+windowHeight==scrollHeight){
        console.log("底部了")
        page_num+=1;
        //写后台加载数据的函数
        pengge.post("/public/commodity/get-with-info-by-page", "isWantBy=" + isWantBy + "&goodsType=" + goods_type + "&page=" + page_num + "&isSellOut=0",
            function(data) {
                get_goods(data);
            });
    }
    return;


    var top = doc.documentElement.scrollTop || doc.body.scrollTop;
    console.log("sccroll");
    if (top >= vw * 0.75) {
        if (ty&&!choose_top) {
			choose_top=true;
            hchoose.cssText = "position: fixed;top: -5vw;"
        }

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

    else {
        if (ty&&choose_top) {
			choose_top=false;
            hchoose.cssText = ""
        }
    }

}

function add_ad(){
	pengge.post("/public/ad/get-all","num="+ad_num,function(d){
	    var data = JSON.parse(d);	
		ad_frequency=data[0]["frequency"];

		for(var i=1;i<data.length;i++){
			ad.push(data[i]);
		}
	})	
}



function choose(a,ss) {
    if (ss!==true&&choose_dom[a].className !== "") {
        return;
    }
	  
    if(choose_top){window.scrollTo(0,vw*0.75);}else if(!ty){window.scrollTo(0,0);}
   
    choose_dom[isWantBy].className = "";
    isWantBy = a;choose_dom[isWantBy].className = "un";
    if(ty){ chare_type(goods_type, true)}else{
			hgoods.innerHTML = '<div class="card goods" id="loading">'+((wd==="")?"请输入关键词搜索":"正在加载……")+'</div>';
if(wd===""){return;}
	   window.history.pushState({} , 0 , location.pathname + "?type=" + isWantBy + "&wd=" + encodeURI(wd)+ "&");
	doc.title="搜索 "+pengge.nanfenggx.font_len(wd,20)+" - 南风共享";
	get_goods();
	}
	
	
}

function leave(){
	sessionStorage.setItem("index_get_goods",JSON.stringify(index_get_goods));
	sessionStorage.setItem("index_page",(window.pageYOffset ||doc.documentElement.scrollTop || doc.body.scrollTop || 0)+'');
	
}