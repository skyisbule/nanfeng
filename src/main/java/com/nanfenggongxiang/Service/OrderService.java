package com.nanfenggongxiang.Service;

import com.nanfenggongxiang.Dao.OrderMapper;
import com.nanfenggongxiang.Domain.Order;
import com.nanfenggongxiang.Domain.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by skyisbule on 2018/4/14.
 */
@Service
public class OrderService {

    @Autowired
    OrderMapper dao;

    //判断一个用户有没有拍下过这件商品
    //true表示已经添加过了
    public boolean isAdded(int uid,int gid){
        OrderExample e = new OrderExample();
        e.createCriteria()
                .andGidEqualTo(gid)
                .andUidEqualTo(uid);
        List<Order> list = dao.selectByExample(e);
        return !list.isEmpty();
    }

}
