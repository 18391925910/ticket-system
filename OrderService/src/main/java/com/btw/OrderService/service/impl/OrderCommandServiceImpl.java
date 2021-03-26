package com.btw.OrderService.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.btw.OrderService.entity.Order;
import com.btw.OrderService.entity.Ticket;
import com.btw.OrderService.mapper.OrderMapper;
import com.btw.OrderService.service.OrderCommandService;
import com.btw.OrderService.service.userServiceFeign.UserServiceFeignClient;
import com.btw.OrderService.service.ticketServiceFeign.TicketServiceFeignClient;
import com.btw.OrderService.utils.ResultJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;


@Service
public class OrderCommandServiceImpl extends OrderCommandService {
    @Resource
    TicketServiceFeignClient ticketService;
    @Resource
    UserServiceFeignClient userService;

    @Autowired
    OrderMapper orderMapper;
    @Override
    public String addOrder(String user_id, String ticket_id) {
        String result;
        JSONObject ticket = JSON.parseObject(ticketService.getTicket(ticket_id));
        String order_id=Long.toString(new Date().getTime());
        //日期
        DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
        if (ticket.getInteger("quantity")>0){
            orderMapper.addOrder(order_id,ticket.getString("ticket_id"),ticket.getString("start"),ticket.getString("end"),ticket.getString("start_time"),ticket.getString("end_time"),
                    ticket.getString("date"),ticket.getInteger("price"),user_id,df.format(new Date()),ticket.getString("flight_id"),"正常");
            result=ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addParam(ResultJsonUtil.INFO_STR,"购买成功！").getResult();
            ticketService.reduceTicketQuantity(ticket_id);
            userService.cost(user_id,ticket.getInteger("price"));
        }else {
            result=ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"票已售空！").getResult();
        }
        return result;
    }

    @Override
    public String cancelOrder(String order_id) {
        String result;
        Order order=orderMapper.getOrder(order_id);
        if (order!=null){
            orderMapper.cancelOrder(order_id);
            ticketService.addTicketQuantity(order.getTicket_id());
            userService.recharge(order.getUser_id(),order.getPrice());
            result=ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addParam(ResultJsonUtil.INFO_STR,"取消成功！").getResult();
        }else {
            result=ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"没有此订单！").getResult();
        }
        return result;
    }

    @Override
    public String getOrder(String order_id) {
        Order order=orderMapper.getOrder(order_id);
        return JSON.toJSONString(order);
    }

    @Override
    public String getUserAllOrder(String order_id) {
        return JSON.toJSONString(orderMapper.getUserAllOrder(order_id));
    }

    @Override
    public String getAllOrder() {
        return JSON.toJSONString(orderMapper.getAllOrder());
    }
}
