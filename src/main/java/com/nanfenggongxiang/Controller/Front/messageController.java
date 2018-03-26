package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.MessageDao;
import com.nanfenggongxiang.Domain.Message;
import com.nanfenggongxiang.Domain.MessageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nanfenggongxiang.Dao.MessageMapper;

import java.util.List;

/**
 * Created by skyisbule on 2018/3/26.
 * 留言控制器
 */
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
    @RequestMapping("/private/message/add")
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
    @RequestMapping("/public/message/get-by-gid")
    public List<Message> getByGoodsId(int gid,int page){
        MessageExample e = new MessageExample();
        e.setOffset(page*10);
        e.setLimit(10);
        e.createCriteria()
                .andGidEqualTo(gid);
        return dao.selectByExample(e);
    }

    @RequestMapping("/public/message/count-by-gid")
    public Integer getCount(int gid){
        return messageDao.getCount(gid);
    }

}
