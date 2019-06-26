# Host: 118.89.52.224  (Version 5.7.26)
# Date: 2019-06-26 09:59:06
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "advertisement"
#

DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `pic` varchar(155) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `times` datetime DEFAULT NULL,
  `power` int(11) DEFAULT NULL,
  `links` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Structure for table "commodity"
#

DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `gid` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `uid` int(11) DEFAULT NULL COMMENT '发布者id',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名字',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `conditions` int(1) DEFAULT NULL COMMENT '成色',
  `content` varchar(2500) DEFAULT NULL COMMENT '详细描述',
  `picture` varchar(1000) DEFAULT NULL COMMENT '存图片的url',
  `goods_type` int(1) DEFAULT NULL COMMENT '商品的类型',
  `page_views` int(11) DEFAULT NULL COMMENT '浏览量',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `is_sell_out` int(1) DEFAULT NULL COMMENT '是否卖出',
  `is_want_by` int(1) DEFAULT NULL COMMENT '是否是想买',
  PRIMARY KEY (`gid`)
) ENGINE=MyISAM AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='商品';

#
# Structure for table "favorites"
#

DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites` (
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `gid` int(11) DEFAULT NULL COMMENT '商品id'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='收藏';

#
# Structure for table "info"
#

DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `tel_num` char(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(80) DEFAULT NULL COMMENT '邮箱',
  `passwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `release_num` int(11) DEFAULT NULL COMMENT '累计发布数量',
  `signature` varchar(100) DEFAULT NULL COMMENT '个性签名',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `head_pic` varchar(255) DEFAULT NULL COMMENT '头像',
  `qq` varchar(255) DEFAULT NULL COMMENT 'qq号',
  `wechat` varchar(255) DEFAULT NULL COMMENT '微信号',
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='个人信息表';

#
# Structure for table "message"
#

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `mid` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `releaser` int(11) DEFAULT NULL COMMENT '写这条留言的用户id',
  `receiver` int(11) DEFAULT NULL COMMENT '接收留言者的id',
  `content` varchar(1254) DEFAULT NULL COMMENT '留言内容',
  `is_readed` int(1) DEFAULT NULL COMMENT '是否已读',
  `gid` int(11) DEFAULT NULL COMMENT '商品id',
  `release_time` datetime DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='留言表';

#
# Structure for table "news"
#

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `nid` int(11) NOT NULL AUTO_INCREMENT COMMENT '资讯id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(5555) DEFAULT NULL COMMENT '资讯内容',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `classification` int(1) DEFAULT NULL COMMENT '所属类别',
  `is_top` int(11) DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=MyISAM AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COMMENT='资讯';

#
# Structure for table "news_comment"
#

DROP TABLE IF EXISTS `news_comment`;
CREATE TABLE `news_comment` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '评论者id',
  `content` varchar(1255) DEFAULT NULL,
  `release_time` datetime DEFAULT NULL,
  `nid` int(11) DEFAULT NULL,
  PRIMARY KEY (`reply_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='资讯评论表';

#
# Structure for table "notice"
#

DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `nid` int(11) NOT NULL DEFAULT '0' COMMENT '公告id',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '图片的url',
  `is_show` int(1) DEFAULT NULL COMMENT '是否展示',
  `links` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='公告表';

#
# Structure for table "orders"
#

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `uid` int(11) DEFAULT NULL COMMENT '买家的id',
  `gid` int(11) DEFAULT NULL COMMENT '商品id',
  `buy_time` datetime DEFAULT NULL COMMENT '下单时间',
  `status` int(11) DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`oid`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='订单表';

#
# Structure for table "plate"
#

DROP TABLE IF EXISTS `plate`;
CREATE TABLE `plate` (
  `plate_id` int(11) NOT NULL AUTO_INCREMENT,
  `plate_name` varchar(255) DEFAULT NULL COMMENT '版面名字',
  PRIMARY KEY (`plate_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='版面';

#
# Structure for table "post"
#

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '作者的id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(2555) DEFAULT NULL COMMENT '一楼的内容',
  `reply_num` int(11) DEFAULT NULL COMMENT '回复数量',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `is_top` int(1) DEFAULT NULL COMMENT '是否是置顶',
  `plate_id` int(1) DEFAULT NULL COMMENT '板块id',
  PRIMARY KEY (`pid`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='帖子表';

#
# Structure for table "post_reply"
#

DROP TABLE IF EXISTS `post_reply`;
CREATE TABLE `post_reply` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL COMMENT '帖子id',
  `uid` int(11) DEFAULT NULL COMMENT '作者id',
  `content` varchar(2255) DEFAULT NULL COMMENT '回复内容',
  `release_time` datetime DEFAULT NULL COMMENT '回复时间',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否被删除',
  PRIMARY KEY (`rid`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='帖子回复表';
