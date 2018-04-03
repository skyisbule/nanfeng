package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.MessageDao;
import com.nanfenggongxiang.Dao.MessageMapper;
import com.nanfenggongxiang.Domain.Message;
import com.nanfenggongxiang.Domain.MessageExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by skyisbule on 2018/3/26.
 * 留言控制器
 */
@Api(description = "商品下边留言相关的api，如无特殊说明则api返回success或error")
@RestController
public class messageController {

    @Autowired
    MessageMapper dao;
    @Autowired
    MessageDao messageDao;

    /**
     *
     * @param message  留言的实体
     * @return         成功与否
     */
    @ApiOperation("添加一条商品的留言")
    @RequestMapping(value = "/private/message/add",method = RequestMethod.POST)
    public String add(//@CookieValue("id")int uid,
                      Message message){
        //message.setReleaser(uid);
        message.setIsReaded(0);
        return dao.insert(message)==1?"success":"error";
    }

    /**
     *
     * @param gid    商品id
     * @param page   第几页 从0开始
     * @return       message的集合
     */
    @ApiOperation("拿到某商品的留言信息，每次最多返回10条")
    @RequestMapping(value = "/public/message/get-by-gid",method = RequestMethod.GET)
    public List<Message> getByGoodsId(@ApiParam("你想查询的商品的id") int gid,
                                      @ApiParam("第几页，从0开始。") int page){
        MessageExample e = new MessageExample();
        e.setOffset(page*10);
        e.setLimit(10);
        e.createCriteria()
                .andGidEqualTo(gid);
        return dao.selectByExample(e);
    }

    @ApiOperation("拿到某商品有多少条留言")
    @RequestMapping(value = "/public/message/count-by-gid",method = RequestMethod.GET)
    public Integer getCount(@ApiParam("商品id") int gid){
        return messageDao.getCount(gid);
    }

}
