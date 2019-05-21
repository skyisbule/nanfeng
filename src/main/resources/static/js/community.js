            "use strict";
var img_arr=[],boolisshow=false;
function img_upload(h,n,j,k){k=k||0;var m=h[k].src.split(",")[1]; var i=function(a){var c;if(a.indexOf("=")>0){var b=a.indexOf("=");a=a.substring(0,b)}c=parseInt(a.length-(a.length/8)*2);return c};var g="http://upload-z2.qiniu.com/putb64/"+i(m);var l=new XMLHttpRequest();l.onreadystatechange=function(){if(l.readyState==4){img_arr[k]=JSON.parse(l.responseText).key;k++;if(k>=h.length){j(img_arr)}else{img_upload(h,n,j,k)}}};l.open("POST",g,false);l.setRequestHeader("Content-Type","application/octet-stream");l.setRequestHeader("Authorization","UpToken "+n);l.send(m)};

     let nowS = '',bool = true,nowtype =1,page,btw =true,mypostId = 1,replyPage = 0,checkFull=true,saveP = {};    
      var json = {time:1};
       window.addEventListener("popstate", function (e) {
      if(boolisshow){
$('.liuyan').css('top','');$('.myreply').fadeIn(300) ;$('.rcontent').hide();$('.liuyan').css('background-color','');
window.history.pushState('','','#aff');  
boolisshow = false;
}else{
      a.ve.ifshow = false;setTimeout( ()=> { window.scrollTo(0,nowS);bool = true;replyPage = 0;


 });

}



    }, false);
$(document).ready(function(){
    page = 0;
    
    a.getTop('http://118.89.52.224:10080/public/post/get-by-page',{page,isTop:1,plate:nowtype});
    $('.ass').on('click',() =>{
       
    });
    
    
    
            $(window).scroll(_.debounce(function(){
           /*     $('.liuyan').css('bottom','3%');$('.myreply').fadeIn(300) ;$('.rcontent').hide();$('.liuyan').css('background-color',''); */
let scrollTop = parseInt($(this).scrollTop());

let scrollHeight = $(document).height();
let windowHeight = $(this).height();
if(bool){
    nowS = parseInt($(this).scrollTop());
console.log(nowS);

if(scrollTop + windowHeight == scrollHeight || ( (scrollTop + windowHeight - scrollHeight) <=100 &&(scrollTop + windowHeight - scrollHeight)>= -100 )){
console.log("已经到最底部了！");
//这里写实例的新增楼层的方法 和留言

page++;

a.addLIS('http://118.89.52.224:10080/public/post/get-by-page',{page,isTop:0,plate:nowtype});



}




}else{
    

//这里写实例的新增楼层的方法 和留言

replyPage++;
    a.addCONTS('http://118.89.52.224:10080/public/postReply/get-by-page',{page:replyPage,postId:mypostId});




    
    
    
}




},300)
        );


setTimeout(  ()=> {
     $('.myLoading').hide();
},300 );
});


class F {
    
    
    constructor(redata = [],ansy,myid =null,myhead=null){
        this.ve = new Vue({
            el:'#data',
            data:{
                lis:[],
                conts:[],
                nowc:{},
                postdata:'',
                postdatasave:'??',
                ifshow:false,
                qo:'',
                qt:'',
                replyId:'',
                tempId:'',//点击了的帖子的id
                replycont:'',
                showDataW:redata,  //一共5个位置
                ansytype:ansy,
                myid:myid,
                myhead:myhead,
                warning:false,
                rawHtml:'',
                rawHtmlS:'',
               reg : /<img.*?>/g,
               reg2:/.*圖/,
               myLoading:false
                
            },
            methods:{
                textLength(title){
                    
                   return title.split('').slice(0,20).join(''); 
                },
                imgdele(content){
                    let c = content.replace(/<\s*img.+?>/g,' ');
                 
                    return c;
                },
                move(abc){
                    try{
                      
                       return abc.replace(/abc/g,' '); 
                    }catch(e){
                        return abc;
                    }
                    
                   
                },
                myDate(ydate){
                    
                    var date = new Date(ydate);
                     
   
                    
                    if(ydate !== '刚刚'){
                    var y = date.getFullYear();  
                var m = date.getMonth() + 1;  
                m = m < 10 ? ('0' + m) : m;  
                var d = date.getDate();  
                d = d < 10 ? ('0' + d) : d;  
                var h = date.getHours();  
                h=h < 10 ? ('0' + h) : h;  
                var minute = date.getMinutes();  
                minute = minute < 10 ? ('0' + minute) : minute;  
                var second=date.getSeconds();  
                second=second < 10 ? ('0' + second) : second;  
                return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;  
                    }else{
                        return '刚刚';
                    }
                    
                },
                post(url,p = {},func= undefined ){
if(!document.cookie){
self.location.href="/sign_in" ;
}
                     document.getElementById('模板').innerHTML = ' ';
                   var that = this;
                    let cc = 0;
                    $('.myLoading').show();
                    
                    
                    if(document.getElementsByClassName('abc').length !== 0){
                        var promiseGroup = [],lg = document.getElementsByClassName('abc').length;
                        for(let i =0;i<lg;i++){
                            let a = new Promise(function(re,rj){
                                
                                Vue.http.post(allurl+'http://118.89.52.224:10080/public/pic/token',{fileName:Math.random()}, {emulateJSON: true,credentials:true}).then(  (response) => {
                        img_upload(document.getElementsByClassName('abc'),response.bodyText,function(data){ 
                            document.getElementsByClassName('abc')[i].src = 'http://pic.zdnfbbs.cn/'+data[i];
               re('success');                    
    },i); });
                            });
                            promiseGroup.push(a);
                            
                        }
                        
                        Promise.all(promiseGroup).then(function(){  //贼丑 你确定不把下面的写成函数？
                             p.uid = that.myid;
             p.title = that.qo;
             p.content = document.getElementById('上传图片list').innerHTML.replace(/script/g,' ');
             
             p.isTop = 0;
             p[that.ansytype] =nowtype;
            delete p.uid;
         Vue.http.post(allurl+url,p, {emulateJSON: true,credentials:true}).then(  (response) => {
          if(response.bodyText === 'success'){ a.ve.rawHtml='';a.ve.rawHtmlS=''; {$('.myLoading').hide(); $('.tli').css('top','');$('.mypost').show() ;$('.postcontent').hide();$('.postpng').show(); $('.postspan').show();$('.colorchange').css('background-color','');}
              that.lis = [];
              page = 0;
              a.getTop('http://118.89.52.224:10080/public/post/get-by-page',{page,isTop:1,plate:nowtype});
          
 
          that.warning =true;
          setTimeout(()=>{  $('.warning').text('发布成功'); },200);
         
          setTimeout(()=>{ that.warning = false; },1000);
          
                        }
          if(func){  func(); };
         }); 
                        });
                        
                        
                        
                     /*    Vue.http.post(allurl+'http://118.89.52.224:10080/public/pic/token',{fileName:Math.random()}, {emulateJSON: true,credentials:true}).then(  (response) => {
                        img_upload(document.getElementsByClassName('abc'),response.bodyText,function(data){
                            
                            
                        let group =   JSON.stringify(data);
                        group.forEach(function(item,index){
                            document.getElementsByClassName('abc')[index].src = item;
                        }); 
     
                        
                    });
                    
                    
                    
                    
                    });  */
                    //尾巴
                    }else{
                        
                        p.uid = this.myid;
             p.title = this.qo;
             p.content = document.getElementById('上传图片list').innerHTML.replace(/script/g,' ');
             p.isTop = 0;
             p[this.ansytype] =nowtype;
          delete p.uid;
         Vue.http.post(allurl+url,p, {emulateJSON: true}).then(  (response) => {
          if(response.bodyText === 'success')  {$('.myLoading').hide(); console.log('发布成功');$('.tli').css('top','');$('.mypost').show() ;$('.postcontent').hide();$('.postpng').show(); $('.postspan').show();$('.colorchange').css('background-color','')
              
              this.lis = [];
              page = 0;
              a.getTop('http://118.89.52.224:10080/public/post/get-by-page',{page,isTop:1,plate:nowtype});
             /* p.reply_num = '0';
              p.release_time = '刚刚';
              p.head_pic = this.myhead;
              p.nick_name = '我';
          this.lis.unshift(p);//['uid','releaseTime','title','content','replyNum'] */

          this.warning =true;
          setTimeout(()=>{  $('.warning').text('发布成功'); },200);
         
          setTimeout(()=>{ this.warning = false; },1000);
          
                        }
          if(func){  func(); };
         }); 
                 
                        
                        
                        
                    }
                    
                   
                    
                    
                    
         
    },
    Reply(url,p = {},func = undefined ){
if(!document.cookie){
self.location.href="/sign_in" ;
}
                    //需要  postId uid content
                                p.uid = this.myid;        
            p.postId = this.tempId;
             p.content = this.replycont.replace(/script/g,' ');
                    if(1){
             
            
             console.log(p);//这是发布的也包括留言  一切发送的东西
         Vue.http.post(allurl+url,p, {emulateJSON: true,credentials:true}).then(  (response) => {
          if(response.bodyText === 'success')  { console.log('发布成功');$(".tli").css("top","");$(".mypost").show() ;$(".postcontent").hide();$(".postpng").show() ; 
              
              $('.liuyan').css('top','');$('.myreply').fadeIn(300) ;$('.rcontent').hide();$('.liuyan').css('background-color',''); 
            /*  p.head_pic = myhead;
              p.release_time = '刚刚';
              p.nick_name = '我';
              this.conts.push(p); */
        this.another(saveP.A,saveP.B,saveP.C);
              /*this.conts = [];
              a.addCONTS('http://118.89.52.224:10080/private/postReply/add',p.postId); */
              
                        }
          if(func){  func(); };
         },(error)=>{
             self.location.href="/sign_in" ;
         }); 
                    }else{
                        this.warning =true;
          setTimeout(()=>{  $('.warning').text('先晃晃再来回复自己的帖子'); },200);
         
          setTimeout(()=>{ this.warning = false; },1000);
                    }
                    
           
    },
    another(li,p={}/*回复的实体*/,url = undefined){
       
        saveP = {A:li,B:p,C:url};
        checkFull=true;
        this.conts = [];
       this.nowc = li;
        this.tempId = li.pid;
        a.changeNOWC(url,p);
      mypostId =  p.postId;
    }, a(b){
                   
                    if(this.rawHtmlS){
                        console.log(b.replace(this.reg2,'') );
                        
                        return this.rawHtmlS +b.replace(this.reg2,'');
                        
                    }else{
                        return b;
                    }
                    
                }
    
    
            }
        });
    }
    
    
   
    
    getTop(url,p={}){
        
          if(nowtype ==0){
            url = 'http://118.89.52.224:10080/public/post/get-all-by-page';
            delete p.plate;
            delete p.isTop;
        }
        
        /* if(p.type == null){
            delete p.type;
        }  
        
         if(p.plate == null){
            delete p.plate;
        } */
        for(let c in p){
            if(p.c == null){
                delete p.c;
            }
        }
         var that = this;                                            
             Vue.http.post(allurl+url,p, {emulateJSON: true}).then(  (response) => {
            let f =  eval("("+response.bodyText+")");
            
            
            f.forEach( (data) => {  a.ve.lis.push(data);  }  );
            if(that.ve.lis[0] !== undefined){
               btw = false;
            }
            
            
            p.isTop = 0;
      if(nowtype !==0){ console.log(p); this.addLIS(url,p);}
        });
            
    }
    
    addLIS(url,p={}){

        if(nowtype ==0){
            url = 'http://118.89.52.224:10080/public/post/get-all-by-page';
            delete p.plate;
            delete p.isTop;
        }
var that =this;

         Vue.http.post(allurl+url,p, {emulateJSON: true}).then(  (response) => {
            let fs =  eval("("+response.bodyText+")");
            fs.forEach( (data) => {  a.ve.lis.push(data);  }  );
            if(that.ve.lis[0] === undefined && btw){
               $('#mutext').html('偷偷告诉你这里什么都没有...'); 
            }
            
            
         },(err) =>{
             self.location.href="/sign_in" ;
         });
    }
    
    addCONTS(url,p){  
        if(checkFull){
            Vue.http.get(allurl+url,{params:p}).then(  (response) => {
            var f =  eval("("+response.bodyText+")");
            if(!f[0]){
                checkFull = false;
            }
            f.forEach( (data) => {  this.ve.conts.push(data);  }  );
         
         },(err) =>{
             self.location.href="/sign_in" ;
         });
        }
        
        
    }
    
    changeNOWC(url,p={}){

        
        setTimeout(()=>{
            bool = false;
window.scrollTo(0,0);

        this.ve.ifshow = true;
          //  p = {page:0,postId:this.ve.nowc.pid};
        this.addCONTS(url,p);
        },200);
        
    }
    
}

function previewFiles() {

  var preview = document.querySelector('#upload');
  var files   = document.querySelector('input[type=file]').files;

  function readAndPreview(file) {

    
    if ( true ) {
      var reader = new FileReader();

      reader.addEventListener("load", function () {
        
        /*image.height = 100;
        image.title = file.name;
        image.src = this.result;*/
       
      //  a.imgUrl = this.result;
     
      let o =document.createElement("IMG");
o.src=this.result;
o.setAttribute("class", "abc"); 
o.style.maxWidth = '100%';
 
 o.style.marginRight = '5px';
 document.getElementById('模板').innerHTML = ' ';
        document.getElementById('模板').appendChild(o);
        a.ve.rawHtml +=  document.getElementById('模板').innerHTML;
        
        a.ve.rawHtmlS = a.ve.rawHtml;
        /*preview.appendChild( image );*/
      }, false);

      reader.readAsDataURL(file);
    }

  }

  if (files) {
    [].forEach.call(files, readAndPreview);
  }

}
       
  function  change(){
      a.ve.rawHtml = document.getElementById('上传图片list');
  }




let a = new F(['nick_name','release_time','title','content','reply_num'],'plateId',1,'http://wx4.sinaimg.cn/mw690/006Zdy2vgy1fn5cqqdux0j31kw0vnjzi.jpg');  //后面两个 一个是id   一个是图片帮我设置下吧？

a.ve.postdatasave = {uid:null,title:null,content:null,postId:null};




            $('.myfont').click( function(){
              $('.myfont').removeClass('blue');
               $(this).addClass('blue');
            });
            
var allurl = '';