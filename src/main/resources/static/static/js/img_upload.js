"use strict";
var img_arr=[];
function img_upload(h,n,j,k){k=k||0;var m=h[k].src.split(",")[1]; var i=function(a){var c;if(a.indexOf("=")>0){var b=a.indexOf("=");a=a.substring(0,b)}c=parseInt(a.length-(a.length/8)*2);return c};var g="http://upload-z2.qiniu.com/putb64/"+i(m);var l=new XMLHttpRequest();l.onreadystatechange=function(){if(l.readyState==4){img_arr[k]=JSON.parse(l.responseText).key;k++;if(k>=h.length){j(img_arr)}else{img_upload(h,n,j,k)}}};l.open("POST",g,false);l.setRequestHeader("Content-Type","application/octet-stream");l.setRequestHeader("Authorization","UpToken "+n);l.send(m)};