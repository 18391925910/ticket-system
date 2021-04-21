package com.btw.OrderService.service.impl;

import com.alibaba.fastjson.JSON;
import com.btw.OrderService.entity.CreateKafkaMsgBO;
import com.btw.OrderService.entity.Order;
import com.btw.OrderService.entity.Ticket;
import com.btw.OrderService.mapper.OrderMapper;
import com.btw.OrderService.service.OrderCommonService;
import com.btw.OrderService.service.userServiceFeign.UserServiceFeignClient;
import com.btw.OrderService.service.ticketServiceFeign.TicketServiceFeignClient;
import com.btw.OrderService.utils.ResultJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


@Service
@Slf4j
public class OrderCommonServiceImpl extends OrderCommonService {
    @Resource
    TicketServiceFeignClient ticketService;
    @Resource
    UserServiceFeignClient userService;

    @Resource
    OrderMapper orderMapper;
    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public String createOrder(String user_id, String ticket_id){
        String result;
        Ticket ticket = JSON.parseObject(ticketService.getTicket(ticket_id),Ticket.class);
        String order_id=Long.toString(new Date().getTime());
        //日期
        DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
        if (ticket.getQuantity()>0){
            orderMapper.addOrder(order_id,ticket.getTicket_id(),ticket.getStart(),ticket.getEnd(),ticket.getStart_time(),ticket.getEnd_time(),
                    ticket.getDate(),ticket.getPrice(),user_id,df.format(new Date()),ticket.getFlight_id(),"正常");
            result=ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addParam(ResultJsonUtil.INFO_STR,"buy ticket success！").getResult();
            ticketService.reduceTicketQuantity(ticket_id);
            userService.cost(user_id,ticket.getPrice());
        }else {
            result=ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"ticket has been sold out").getResult();
        }
        return result;
    }

    @Override
    public String addOrder(String user_id, String ticket_id) {
        String data=JSON.toJSONString(new CreateKafkaMsgBO(user_id,ticket_id));
        kafkaTemplate.send("order",data);
        LoggerFactory.getLogger("createOrderLog").info("start create order");
        return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                .addParam(ResultJsonUtil.INFO_STR,"order is creating").getResult();
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
                    .addParam(ResultJsonUtil.INFO_STR,"cancel order success").getResult();
        }else {
            result=ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"This order is not available").getResult();
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
    public String getPaperTicket(String order_id) {
        return null;
    }



    @Override
    public String getAllOrder() {
        ArrayList list =orderMapper.getAllOrder();
        String result=ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                .addParam(ResultJsonUtil.INFO_STR,"success").addArray("orders",list).getResult();
        return result;
    }
}
