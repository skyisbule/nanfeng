package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.NoticeMapper;
import com.nanfenggongxiang.Domain.Notice;
import com.nanfenggongxiang.Domain.NoticeExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by skyisbule on 2018/3/26.
 * 首页公告
 * 注意这里的接口均未鉴权，请勿外泄。
 */
@Api(description = "公告相关的api,如无特殊说明均返回success和error")
@RestController
@RequestMapping(value = "",method = {RequestMethod.POST,RequestMethod.GET})
public class noticeController {

    @Autowired
    NoticeMapper dao;

    @ApiOperation("添加一条公告")
    @RequestMapping("/public/notice/add")
    public String add(Notice notice){
        return dao.insert(notice)==1?"success":"error";
    }

    @ApiOperation("拿到所有的公告")
    @RequestMapping("/public/notice/get-show")
    public List<Notice> getShow(@ApiParam("是否显示") Integer isShow){
        NoticeExample e = new NoticeExample();
        e.createCriteria()
                .andIsShowEqualTo(isShow);
        return dao.selectByExample(e);
    }

    @ApiOperation("删除公告")
    @RequestMapping("/public/notice/delete-by-id")
    public String delete(@ApiParam("公告的id") int id){
        NoticeExample e = new NoticeExample();
        e.setOrderByClause("nid desc");
        e.createCriteria()
                .andNidEqualTo(id);
        return dao.deleteByExample(e)==1?"success":"error";
    }

}
