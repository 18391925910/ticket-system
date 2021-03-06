package com.btw.OrderService.controller;

import com.btw.OrderService.service.impl.OrderCommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrderController {
    @Autowired
    OrderCommonServiceImpl orderCommandService;

    @GetMapping("getOrder")
    String getOrder(HttpServletRequest request, @RequestParam("order_id") String order_id){
        return orderCommandService.getOrder(order_id);
    }
    @GetMapping("addOrder")
    String addOrder(HttpServletRequest request,@RequestParam("user_id")String user_id,@RequestParam("ticket_id") String ticket_id){
        return orderCommandService.addOrder(user_id,ticket_id);
    }
    @GetMapping("cancelOrder")
    String cancelOrder(HttpServletRequest request,@RequestParam("order_id")String order_id){
        return orderCommandService.cancelOrder(order_id);
    }
    @GetMapping("getUserAllOrder")
    String getUserAllOrder(HttpServletRequest request,@RequestParam("user_id") String user_id){
        return orderCommandService.getUserAllOrder(user_id);
    }
    @GetMapping("getAllOrder")
    String getAllOrder(HttpServletRequest request){
        return orderCommandService.getAllOrder();
    }

    /**
     * 生成纸质机票
     * @return
     */
    @GetMapping("getPaperTicket")
    String getPaperTicket(HttpServletRequest request,@RequestParam("order_id")String order_id){
        return orderCommandService.getPaperTicket(order_id);
    }
}
