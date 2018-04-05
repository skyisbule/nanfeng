/*pengge tools*/
"use strict";
(function(){window.pengge={post:function(b,d,c,f,a){a=a||true;f=(f)?"GET":"POST";var e=new XMLHttpRequest();e.open(f,b,a);e.setRequestHeader("Content-type","application/x-www-form-urlencoded");e.onreadystatechange=function(){if(e.readyState===4&&(e.status===200||e.status===304)){c.call(this,e.responseText)}};e.send(d)},cookie:{
set:function(b,d,a,e,c)/*name,value,max_age,path,domain*/
{document.cookie=b+"="+escape(d)+";path="+((e)?(e):"/")+((a)?(";max-age="+a):"")+((c)?(";domain="+c):"")},
get:function(b)
{var a=document.cookie.match(new RegExp("(^| )"+b+"=([^;]*)(;|$)"));if(a!=null){return a[2]}return null}
},url:{data:function(a){var b=new RegExp("(^|&)"+a+"=([^&]*)(&|$)");var c=location.search.substr(1).match(b);if(c!==null){return unescape(c[2])}return null}},html_encode:function(e){var d="",c="",a={"?":"%3F","!":"%21","=":"%3D","(":"%28",")":"%29","#":"%23","%":"%25","&":"%26","<":"%3C",">":"%3E","+":"%2B"};for(var b=0;b<e.length;b++){c=a[e[b]];d+=((c!==undefined)?c:e[b])}return d.replace(/\r?\n/g,"<br>")},input:{check:function(b,a){
	if(a.onkeydown===null&&a.onkeyup===null){
	var e_list={num:/\D/g,"ch":/[^\u4E00-\u9FA5]/g},d=e_list[b];
	 a.onkeydown=do_this;
	a.onkeyup=do_this;
	function do_this(){a.value=a.value.replace(d,'');}	
	}},test:function(b,a){
	
	var e_list={phone:/^1[345678]\d{9}$/};
	return (e_list[b].test(a))?true:false;
	}
	
	}}}());