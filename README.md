# 关于南风
这是一个二手交易的网站，是我个人的一个创业项目，意在为学校提供一个二手交易以及信息分享的平台。<br>
其运营载体为微信公众号，如下：<br>
![avatar](http://pic.zdnfbbs.cn/7.jpg)<br>
# 关于交接
我写这篇文档的时间是2018年6月27日。<br>在这次更新里，我完善了这个项目的文档，整理了整个项目的代码以及注释，删除了一些不必要的东西。<br>
然后因为实习的原因，这个项目我会在今年8月份交给计协的下任开发部部员进行维护和迭代。<br>
# 关于技术
简单介绍一下技术方面。
### 产品的模块及功能
该项目是一个二手交易平台，那么它目前的主要模块有：<br>
1. 个人信息相关（注册、登录、发布等）
2. 商品模块，可以发布商品信息以及求购信息，可以收藏商品，可以产生订单。
3. 资讯模块，后台发布资讯，用户可以看到以及留言。
4. 社区模块，相当于mini贴吧。
5. 后台管理。
### 数据库的E-R图如下
![avatar](http://pic.zdnfbbs.cn/db-e-r.png)<br>
### 开发使用的技术
服务端：JDK8+springboot1.5.1+mybatis+mysql5.7<br>
前端：html，css，js。资讯和社区模块由vue.js开发，后台使用了layui，其余为纯js。<br>
注意：静态资源全部接入了七牛，而git上的代码里并没有上传staticController，如需使用请找我要，要么自行实现，但请注意做好资源迁移。
### 整体架构
目前的架构为彻底的前后端分离。<br>
html、css、js放在<strong>阿里cdn</strong>上。<br>
图片和静态资源存储在<strong>七牛</strong>上。<br>
然后单独一台服务器专门提供api服务。
### 后端代码
后端代码的包的安排主要为：Controller,Dao,Domain,Filter,Service,Util。<br>
没有什么特殊的地方，稍有开发经验的人基本一眼就知道啥意思，注释我也写的比较全，我在此就不啰嗦了。<br>
值得注意的是，持久层我是用了mybatis的代码生成器，简化了增删改查的操作，如果不会用也请联系我。
### 关于前后端协作
既然是前后端分离，那么肯定离不开协作的部分，此我使用了swagger，如要开启直接在启动类上边把注解的注释去掉，即可开启api查看的页面了。
# 最后，关于产品
[预览网址在这里：http://nanfeng.heartqiu.cn](http://nanfeng.heartqiu.cn/)<br>
请务必使用手机打开，要么浏览器按f12，打开手机模式。
# 几张预览图
![avatar](http://pic.zdnfbbs.cn/1.png)<br>
![avatar](http://pic.zdnfbbs.cn/2.png)<br>
![avatar](http://pic.zdnfbbs.cn/3.png)<br>
![avatar](http://pic.zdnfbbs.cn/4.png)<br>
![avatar](http://pic.zdnfbbs.cn/5.png)<br>
![avatar](http://pic.zdnfbbs.cn/6.png)<br>
