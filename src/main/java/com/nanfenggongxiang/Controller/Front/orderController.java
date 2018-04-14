package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.OrderMapper;
import com.nanfenggongxiang.Dao.orderDao;
import com.nanfenggongxiang.Domain.Order;
import com.nanfenggongxiang.Domain.OrderExample;
import com.nanfenggongxiang.Service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/4/12.
 * 用于提供订单相关的api
 */
@Api("提供订单相关的api")
@RestController
public class orderController {

    @Autowired
    orderDao complexDao;
    @Autowired
    OrderMapper dao;
    @Autowired
    OrderService service

    @ApiOperation("传用户id，返回该id所拍下的商品")
    @RequestMapping("/private/order/get-by-uid")
    public List<Map<String,Object>> get(int uid){
        return complexDao.getByUid(uid);
    }

    @ApiOperation("添加一条记录")
    @RequestMapping("/private/order/add")
    public String add(Order order){
        if (service.isAdded(order.getUid(),order.getGid()))
            return "exist";
        Date date = new Date();
        order.setBuyTime(date);
        dao.insert(order);
        return "success";
    }

    @ApiOperation("删除一条记录")
    @RequestMapping("/private/order/delete")
    public String delete(int uid,int gid){
        OrderExample e = new OrderExample();
        e.createCriteria()
                .andUidEqualTo(uid)
                .andGidEqualTo(gid);
        return dao.deleteByExample(e)==1?"success":"error";
    }

}
