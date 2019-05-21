     let nowS = '',bool = true,nowtype =0,page,replyPage = 0,mypnewsId,ifnull=false,checkFull=true,saveP=null,boolisshow=false;    
     var json = {time:1};
       window.addEventListener("popstate", function (e) {
if(boolisshow){


$('.liuyan').css('top','');$('.myreply').fadeIn(300) ;$('.rcontent').hide();$('.liuyan').css('background-color','');

boolisshow=false;
window.history.pushState('','','#aff');
}else{

 $('#news').show();a.ve.ifshow = false;setTimeout( ()=> { window.scrollTo(0,nowS);bool = true; replyPage = 0; });

 

}
     
    }, false);
$(document).ready(function(){
    page = 0;
    
    a.getTop('http://118.89.52.224:10080/public/news/get-by-page',{page,type:nowtype,isTop:1});
    $('.ass').on('click',() =>{
         a.getTop('http://118.89.52.224:10080/public/news/get-by-page',{page,type:nowtype,isTop:1});
         ifnull = false;
window.scrollTo(0,0);
    });
    
    
            $(window).scroll(_.debounce(function(){
                a.ve.dragshow =false;
                
                
                
let scrollTop = parseInt($(this).scrollTop());

let scrollHeight = $(document).height();
let windowHeight = $(this).height();
if(bool){
    nowS = parseInt($(this).scrollTop());
console.log(nowS);
console.log(windowHeight);
 console.log(windowHeight+' '+scrollTop+' '+scrollHeight);
if((scrollTop + windowHeight == scrollHeight || ( (scrollTop + windowHeight - scrollHeight) <=100 &&(scrollTop + windowHeight - scrollHeight)>= -100 )) &&!ifnull){
console.log("已经到最底部了！");

page++;
a.addLIS('http://118.89.52.224:10080/public/news/get-by-page',{page,type:nowtype,isTop:0});



}



}else{
    console.log(windowHeight+' '+scrollTop+' '+scrollHeight+'????????');



   replyPage++;
    a.addCONTS('http://118.89.52.224:10080/public/newsReply/get-by-page',{page:replyPage,newsId:mypnewsId,isTop:0});




 
    
}




},300)
        );
setTimeout(()=>{
   $('.myLoading').hide(); 
},1000);
});


class F {
    
    
    constructor(redata = [],ansy,setId,head){
        this.ve = new Vue({
            el:'#data',
            data:{
                ALLSHOW:true,
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
                myid:setId,
                dragshow:false,//全部框
                warning:false
                
            },
            methods:{
               classification(type){
                   switch(type){
                       case 0:return '全部';
                       case 1:return '打听';
                       case 2:return '寻物';
                       case 3:return '福利';
                       case 4:return '求伴';
                       case 5:return '校园';
                       case 6:return '兼职';
                       case 7:return '微商';
                       default:return ' ';
                   }
               },
                ppic(li,p = 0){
                    try{
                         let end =  li.match(/src\s*=\s*(\"|\').+?(\"|\')/g)[p];
                   
                   return end.replace(/src/,' ').trim().replace(/^=/,' ').trim().replace(/^(\"|\')/g,'').replace(/(\"|\')$/,'');//这个叫我真的不会正则  
                    }catch(e){
                        return 'static/images/mapico4.png';
                    }
                
                },
                piccontent(li){
                    if(li.match(/src\s*=\s*(\"|\').+?(\"|\')/g) === null){return 0;}
                    if(li.match(/src\s*=\s*(\"|\').+?(\"|\')/g).length >2){ return 3; }else if(li.match(/src\s*=\s*(\"|\').+?(\"|\')/g).length  >=1){ return 1; }else{ return 0; };
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
                    //差板块id  和
                    
             p = this.postdatasave;
             p.uid = this.myid;
             p.title = this.qo;
             p.content = this.qt.replace(/script/g,' ');
             console.log(head);
             p[this.ansytype] =nowtype;
            
         Vue.http.get(allurl+url,{params:p}).then(  (response) => {
          if(response.bodyText === 'success')  { console.log('发布成功');$(".tli").css("top","");$(".mypost").show() ;$(".postcontent").hide();$(".postpng").show() ; 
              p.replyNum = '0';
              p.releaseTime = '刚刚';
          this.lis.push(p);//['uid','releaseTime','title','content','replyNum']
                        }
          if(func){  func(); };
         }); 
    },
    Reply(url,p = {},func = undefined ){
if(!document.cookie){
self.location.href="/sign_in" ;
}
                    //需要  postId uid content
            p.uid = this.myid;        
            p.nid = this.tempId;  
             p.content = this.replycont.replace(/script/g,' ');
             console.log(p);//这是发布的也包括留言  一切发送的东西
             delete p.uid;
         Vue.http.post(allurl+url,p,{emulateJSON: true,credentials: true}).then(  (response) => {
          if(response.bodyText === 'success')  { console.log('发布成功');$(".tli").css("top","");$(".mypost").show() ;$(".postcontent").hide();$(".postpng").show() ; 
              $('.liuyan').css('top','');$('.myreply').fadeIn(300) ;$('.rcontent').hide();$('.liuyan').css('background-color','');
          //this.conts.push(p);
        //  this.contents = [];
        this.another(saveP.A,saveP.B,saveP.C);
                        }
          if(func){  func(); };
         },(error)=>{
             self.location.href="/sign_in" ;
         }); 
    },
    another(li,p={}/*回复的实体*/,url = undefined){
        
        saveP = {A:li,B:p,C:url};
        checkFull=true;
        this.conts = [];
       this.nowc = li;
        this.tempId = li.nid;
        console.log('??');
        mypnewsId = p.nid;

        a.changeNOWC(url,p);
    }
            }
        });
    }
    
    
   
    
    getTop(url,p={}){
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
                    var that =this;                                 
             Vue.http.get(allurl+url,{params:p}).then(  (response) => {
            let f =  eval("("+response.bodyText+")");
            f.forEach( (data) => {  a.ve.lis.push(data);  } 
                    
                    
                    );
            /*if(that.ve.lis[0] === undefined){
                udf = true;
               $('#mutext').html('偷偷告诉你这里什么都没有...'); 
            }*/
           p.isTop = 0;
        this.addLIS(url,p);
        });
            
    }
    
    addLIS(url,p={}){    
        $('#bottomWarning').css('height','50px');
        $('#bottomWarning').html("<img  alt='加载' src = 'static/images/loading.gif'/>");
var that =this;
         Vue.http.get(allurl+url,{params:p}).then(  (response) => {


            let fs =  eval("("+response.bodyText+")");
          setTimeout(  ()=>{fs.forEach( (data) => {  a.ve.lis.push(data);  }  );},100  )  ;
            if(fs){
              // $('#mutext').html('偷偷告诉你这里什么都没有...'); 
             // alert('内容都没有啦');
             //ifnull =true;
              setTimeout( ()=> {$('#bottomWarning').html("<p  style='color:red;font-size:12px;'>已经到底啦~</p>");
              
                
                
                },500); 
              setTimeout( ()=>{$('#bottomWarning').animate({height:'0px'});},2000  );
            }else{
 try{ if(p.isTop ===0)ifnull =true; }catch(e){   } 
}
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
         
         });
        }
        
        
    }
    
    changeNOWC(url,p={}){

        
        setTimeout(()=>{
            bool = false;
        this.ve.ifshow = true;

          //  p = {page:0,postId:this.ve.nowc.pid};
        this.addCONTS(url,p);
        },200);
        
    }
    
}

let a = new F(['AO','releaseTime','title','content','nothing'],'nid');


a.ve.postdatasave = {nid:null,title:null,content:null,nid:null};


//  写一个实体吧  
//{ uid title content replyNum isTop plateId }  {  postId uid content  isDelete  }

 $('.myfont').click( function(){
    
              $('.myfont').removeClass('blue');
               $(this).addClass('blue');
            });

            
            
var allurl = '';