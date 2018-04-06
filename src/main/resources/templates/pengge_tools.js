/*pengge tools*/
"use strict";

    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (navigator.userAgent.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
   if(flag){setTimeout(function(){document.write("抱歉，该网页不支持电脑访问。")},10);}

(function(){window.pengge={
post:function(g,k,l,i,h)
	{h=h||true;i=(i)?"GET":"POST";var j=new XMLHttpRequest();j.open(i,g,h);j.setRequestHeader("Content-type","application/x-www-form-urlencoded");j.onreadystatechange=function(){if(j.readyState===4&&(j.status===200||j.status===304)){l.call(this,j.responseText)}};j.send(k)},
cookie:{
set:function(f,i,g,h,j)/*name,value,max_age,path,domain*/
	{document.cookie=f+"="+escape(i)+";path="+((h)?(h):"/")+((g)?(";max-age="+g):"")+((j)?(";domain="+j):"")},
get:function(c)
	{var d=document.cookie.match(new RegExp("(^| )"+c+"=([^;]*)(;|$)"));if(d!=null){return d[2]}return null}
		},
url:{
data:function(e)
	{var d=new RegExp("(^|&)"+e+"=([^&]*)(&|$)");var f=location.search.substr(1).match(d);if(f!==null){return unescape(f[2])}return null}
		},
html_encode:function(h)
	{var i="",j="",g={"?":"%3F","!":"%21","=":"%3D","(":"%28",")":"%29","#":"%23","%":"%25","&":"%26","<":"%3C",">":"%3E","+":"%2B"};for(var f=0;f<h.length;f++){j=g[h[f]];i+=((j!==undefined)?j:h[f])}return i.replace(/\r?\n/g,"<br>")},
input:{
check:function(c,e)
	{if(e.onkeydown===null&&e.onkeyup===null){var f={num:/\D/g,"ch":/[^\u4E00-\u9FA5]/g},h=f[c];e.onkeydown=g;e.onkeyup=g;var g=function(){e.value=e.value.replace(h,"")}}},
test:function(c,d)
	{var e={phone:/^1[345678]\d{9}$/,"email":/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/};return(e[c].test(d))?true:false}
		},
back_close:function()
	{history.go(-1);window.opener=null;window.open("","_self");window.close()},
nanfenggx:{
active:function(a)
	{a.focus();a.parentNode.className="card active";a.onkeydown=pengge.nanfenggx.noactive;a.onafterpaste=pengge.nanfenggx.noactive;a.onchange=pengge.nanfenggx.noactive},
noactive:function()
	{this.parentNode.className="card";this.onkeydown=null;this.onafterpaste=null;this.onchange=null}
		}
}}());