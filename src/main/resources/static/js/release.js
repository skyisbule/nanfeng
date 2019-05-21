"use strict";
var doc = document,
pic_len = 0,
dom_arr = [
	doc.getElementById("title").getElementsByTagName("input")[0], 
	doc.getElementById("content").getElementsByTagName("textarea")[0], 
	doc.getElementById("add_pic"), 
	doc.getElementById("goods_type").getElementsByTagName("select")[0], 
	doc.getElementById("condition").getElementsByTagName("select")[0], 
	doc.getElementById("price").getElementsByTagName("input")[0]
];
if (pengge.cookie.get("uid") !== null) {
    doc.getElementById("layer").style.display = "none"
}
doc.getElementById("upload").onchange = function() {
    if (!this.files || !this.files[0] || pic_len > 9) {
        return
    };
    pic_len++;
    var b = doc.createElement("img"),
    z = doc.createElement("a");
    z.dataset.id = pic_len;
    b.style.left = (pic_len * 25 + 4) + "vw";
    z.style.left = (pic_len * 25 + 4) + "vw";
    b.src = "http:/static/images/loading.gif";
    dom_arr[2].appendChild(b);
    dom_arr[2].appendChild(z);
    var a = new FileReader();
    a.onload = function(c) {
        var e = c.target.result,
        d = new Image();
        d.src = e;
        d.onload = function() {
            var n = this,
            g = 1280,
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

dom_arr[2].onclick = function(b) {
    var d = b.target.dataset.id;
    if (d !== undefined) {
        d = parseInt(d) - 1;
        var a = dom_arr[2].getElementsByTagName("img"),
        c = dom_arr[2].getElementsByTagName("a");
        this.removeChild(b.target);
        this.removeChild(a[d]);
        pic_len--;
        for (; d < pic_len; d++) {
            a[d].style.left = ((d + 1) * 25 + 4) + "vw";
            c[d].style.left = ((d + 1) * 25 + 4) + "vw";
            c[d].dataset.id = (d + 1)
        }
    }
};

doc.getElementById("post").onclick = function() {

    var e = "",
    c = "/private/commodity/add";
    if (dom_arr[0].value === "") {
        pengge.nanfenggx.active(dom_arr[0]);
        return
    } else {
        e = "goodsName=" + pengge.html_encode(dom_arr[0].value)
    }
    if (dom_arr[1].value === "") {
        pengge.nanfenggx.active(dom_arr[1]);
        return
    } else {
        e += "&content=" + pengge.html_encode(dom_arr[1].value)
    }
    if (dom_arr[3].selectedIndex === 0) {
        pengge.nanfenggx.active(dom_arr[3]);
        return
    } else {
        e += "&goodsType=" + dom_arr[3].selectedIndex
    }
    if (dom_arr[5].value === "") {
        pengge.nanfenggx.active(dom_arr[5]);
        return
    } else {
        e += "&price=" + dom_arr[5].value * 100
    }
    e += "&uid=" + pengge.cookie.get("uid");
    if (dom_arr[4].style.display !== "none") {
        if (dom_arr[4].selectedIndex === 0) {
            pengge.nanfenggx.active(dom_arr[4]);
            return
        } else {
            e += "&conditions=" + dom_arr[4].selectedIndex
        }
    doc.getElementById("layer1").style.display = "block";

        e += "&isWantBy=1";
        var b = dom_arr[2].getElementsByTagName("img"),
        a;
        if (b.length > 0) {
            pengge.post("/public/pic/token?fileName=" + Math.random(), "",
            function(g) {
                img_upload(b, g,
                function(h) {
                    e += "&picture=" + JSON.stringify(h);
                    post(c, e);
                })
            })
        } else {
            e += "&picture=" + JSON.stringify([]);
            post(c, e);
        }
    } else {
    doc.getElementById("layer1").style.display = "block";

        e += "&isWantBy=0";
        post(c, e);
    }

}
function post(c, e) {
    pengge.post(c, e,
    function(da) {
        if (da === "success") {
           location.href="/";

        }

    })
}

function choose(a, dom) {
    if (dom.className === "") {
        return;
    }
    dom.className = "";
    dom.parentNode.getElementsByTagName("span")[(a) ? 1 : 0].className = "un";
    dom_arr[1].parentNode.style.height = (a) ? "": "50vw";
    dom_arr[1].placeholder = (a) ? "请输入物品描述": "请输入需求描述";
    dom_arr[2].style.display = (a) ? "": "none";
    dom_arr[4].style.display = (a) ? "": "none";
    dom_arr[5].placeholder = (a) ? "请输入期待卖出的价格": "请输入期待买入的价格";

}

function num(obj) {
    obj.value = obj.value.replace(/[^\d.]/g, "");
    obj.value = obj.value.replace(/^\./g, "");
    obj.value = obj.value.replace(/\.{2,}/g, ".");
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
}
