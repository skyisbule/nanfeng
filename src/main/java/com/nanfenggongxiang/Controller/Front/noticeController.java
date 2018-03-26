package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.NoticeMapper;
import com.nanfenggongxiang.Domain.Notice;
import com.nanfenggongxiang.Domain.NoticeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by skyisbule on 2018/3/26.
 * 首页公告
 * 注意这里的接口均未鉴权，请勿外泄。
 */
@RestController
public class noticeController {

    @Autowired
    NoticeMapper dao;

    /**
     * 添加一条公告
     * @param notice 公告的实体
     * @return
     */
    @RequestMapping("/public/notice/add")
    public String add(Notice notice){
        return dao.insert(notice)==1?"success":"error";
    }

    /**
     * 拿到公告
     * @param isShow 是否在首页显示的
     * @return
     */
    @RequestMapping("/public/notice/get-show")
    public List<Notice> getShow(int isShow){
        NoticeExample e = new NoticeExample();
        e.createCriteria()
                .andIsShowEqualTo(isShow);
        return dao.selectByExample(e);
    }

    /**
     * 删除公告
     * @param id 公告的id
     * @return
     */
    @RequestMapping("/public/notice/delete-by-id")
    public String delete(int id){
        NoticeExample e = new NoticeExample();
        e.createCriteria()
                .andNidEqualTo(id);
        return dao.deleteByExample(e)==1?"success":"error";
    }

}
