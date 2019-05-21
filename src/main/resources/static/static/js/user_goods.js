"use strict";
z = true;
var fz_data = [["favourite", ["我的收藏"], "/private/favorite/get", true, "/private/favorite/delete", "删除", "我收藏的"], ["release", ["在架宝贝", "下架宝贝"], "/private/commodity/my_release", true, "/private/commodity/close", "下架", "我发布的"], ["buy", ["我拍下的"], "/private/order/get-by-uid", false, "", "", "我拍下的"]],
fz_num = 0,
ty = pengge.url.data("type");

if (ty === null) {
    location.href = "/";
}
for (; fz_num < 3;) {
    if (ty.indexOf(fz_data[fz_num][0]) >= 0) {
        break;
    }
    fz_num++;
}
if (fz_num >= 3) {
    location.href = "/";
} else {
    doc.title = fz_data[fz_num][6] + " - 南风共享"
}

head(fz_data[fz_num][1]);
function get_goods() {
    pengge.post(fz_data[fz_num][2], "page=" + page_num * 10 + "&isWantBuy=1&isSellOut=" + isWantBy + "&uid=" + uid,
    function(data) {

        var out = "",
        data_arr = JSON.parse(data);

        for (var j = 0; j < data_arr.length; j++) {
            out += '<a name="card" class="card" target="view_window" href="goods?gid=' + data_arr[j]["gid"] + '&"><h1>' + pengge.nanfenggx.font_len(data_arr[j]["goods_name"], 18) + '</h1><h2>' + pengge.get_time(data_arr[j]["release_time"] / 1000) + '</h2>';
            if (data_arr[j]["picture"].toString().length > 5) {
                data_arr[j]["picture"] = JSON.parse(data_arr[j]["picture"]);
                out += '<div><img onload="img_onload(this)" src="http://pic.zdnfbbs.cn/' + data_arr[j]["picture"][0] + '" alt=""/></div><span style="float: left;">';
            } else {
                out += "<span style='width:100%'>";
            }
            out += '<h3>' + pengge.nanfenggx.font_len(data_arr[j]["content"].replace(/<br>/g, " "), 100) + '</h3><h6>￥' + data_arr[j]["price"] / 100 + '</h6>' + ((isWantBy === 0 && fz_data[fz_num][3]) ? '<input type="button" onClick="sold_out(' + data_arr[j]["gid"] + ',this)" value="' + fz_data[fz_num][5] + '">': "") + '</span></a>';
        }
        hgoods.innerHTML += out;
        if (j < 10) {
            goods_finish = true;
            doc.getElementById("loading").innerHTML = "没有更多商品了";

        } else {
            page_num++;

        }

    });
}
function img_onload(a) {
    if (a.offsetTop < vw * 0.2) {
        if (a.height > a.width) {
            a.style.width = "30vw";
        } else {
            a.style.height = "30vw";
        }
        a.style.margin = a.height / ( - 2) + "px " + a.width / ( - 2) + "px";
        a.style.opacity = "1";
    } else {
        a.style.display = "none"
    }

}
function sold_out(a, b) {
    b.onclick = null;
    b = b.parentNode.parentNode;
    b.href = "javascript:void(0)";
    pengge.post(fz_data[fz_num][4], 'gid=' + a + "&uid=" + uid,
    function(d) {
        if (d === "success") {
            hgoods.innerHTML = '<div class="card goods" id="loading">正在加载……</div>';
            get_goods();
        }
    })
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
window.onload = function() {
    choose(0, true);
}