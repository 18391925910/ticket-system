package com.btw.OrderService.controller;

import com.btw.OrderService.service.impl.OrderCommandServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderCommandServiceImpl orderCommandService;

    @GetMapping("getOrder")
    String getOrder(@RequestParam("order_id") String order_id){
        return orderCommandService.getOrder(order_id);
    }
    @GetMapping("addOrder")
    String addOrder(@RequestParam("user_id")String user_id,@RequestParam("ticket_id") String ticket_id){
        return orderCommandService.addOrder(user_id,ticket_id);
    }
    @GetMapping("cancelOrder")
    String cancelOrder(@RequestParam("order_id")String order_id){
        return orderCommandService.cancelOrder(order_id);
    }
    @GetMapping("getUserAllOrder")
    String getUserAllOrder(@RequestParam("user_id") String user_id){
        return orderCommandService.getUserAllOrder(user_id);
    }
    @GetMapping("getAllOrder")
    String getAllOrder(){
        return orderCommandService.getAllOrder();
    }

    /**
     * 生成取票码
     * @return
     */
    @GetMapping("createTicketQRCode")
    String createTicketQRCode(@RequestParam("order_id")String order_id){
        return orderCommandService.createTicketQRCode(order_id);
    }
}
