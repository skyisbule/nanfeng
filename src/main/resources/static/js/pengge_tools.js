"use strict";
var Agents=["Android","iPhone","SymbianOS","Windows Phone","iPad","iPod"];var flag=true;for(var v=0;v<Agents.length;v++){if(navigator.userAgent.indexOf(Agents[v])>0){flag=false;break}}if(flag){setTimeout(function(){document.write("抱歉，该网页不支持电脑访问。")},10)};
/*pengge tools*/
(function(){window.pengge={
post:function(g,k,l,i,h)
	{h=h||true;i=(i)?"GET":"POST";var j=new XMLHttpRequest();j.open(i,g,h);j.setRequestHeader("Content-type","application/x-www-form-urlencoded");j.onreadystatechange=function(){if(j.readyState===4&&(j.status===200||j.status===304)){l.call(this,j.responseText)}};j.send(k)},
cookie:{
set:function(f,i,g,h,j)/*name,value,max_age,path,domain*/
	{document.cookie=f+"="+escape(i)+";path="+((h)?(h):"/")+((g)?(";max-age="+g):"")+((j)?(";domain="+j):"")},
get:function(c)
	{var d=document.cookie.match(new RegExp("(^| )"+c+"=([^;]*)(;|$)"));if(d!=null){return unescape(d[2])}return null}
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
get_time: function(a)
	{a=(Date.parse(new Date())/1000)-a;if(a<=0){return"刚刚"}if(a<60){return a+"秒前"}if(a<3600){return Math.floor(a/60)+"分钟前"}if(a<86400){return Math.floor(a/3600)+"小时前"}return Math.floor(a/86400)+"天前"},
char_num:function(d)
	{var e=0,a=d.length,b=-1;for(var c=0;c<a;c++){b=d.charCodeAt(c);if(b>=0&&b<=128){e+=1}else{e+=2}}return e},
nanfenggx:{
active:function(a)
	{a.focus();a.parentNode.className="card active";a.onkeydown=pengge.nanfenggx.noactive;a.onafterpaste=pengge.nanfenggx.noactive;a.onchange=pengge.nanfenggx.noactive},
noactive:function()
	{this.parentNode.className="card";this.onkeydown=null;this.onafterpaste=null;this.onchange=null},
font_len:function(e,d)
	{var c=e.length,f=Math.floor(d/2);if(c*2<=d){return e}var g=e.substr(0,f);d--;for(;f<c;f++){g+=e[f];if(pengge.char_num(g)>d){return g+"…"}}return g}
}
}}());