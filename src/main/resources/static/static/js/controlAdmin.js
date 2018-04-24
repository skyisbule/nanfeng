/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class admin {
    constructor(data={},methods={}){
        data.lis = [];data.contents = [];data.page = 0;
        
        this.ve = new Vue({
            el:'#data',
            data,
            methods
        });
    }
    
    addlis(url,p){
        
       
        
       Vue.http.get(allurl+url,{params:p}).then(  (response) => {
            let f =  eval("("+response.bodyText+")");
            f.forEach( (data) => {  this.ve.lis.push(data);  }  );
         
   
           
           
           
           if(p.isTop == 1){
            a.ve.isTop = 0;
            p.isTop = 0;
            this.addlis(url,p);
          
        }
           
           
        });
        
        
    }
    
    addcontents(url,p){
        Vue.http.get(allurl+url,{params:p}).then(  (response) => {
            let f =  eval("("+response.bodyText+")");
            f.forEach( (data) => {  this.ve.contents.push(data);  }  );
            if(!f[0]){alert('没有了');}else{ a.addcontents(url,p); }
        });
    }
    
    updateORadd(url,p){
        Vue.http.get(allurl+url,p, {emulateJSON: true}).then(  (response) => {
           alert(response.bodyText);
        });
    };
    
}

var allurl = 'http://nanfeng.heartqiu.cn/';

