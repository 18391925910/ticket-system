package com.btw.OrderService.service.ticketServiceFeign;

import feign.Headers;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "TICKET",fallback = TicketServiceFallback.class)
@Component
public interface TicketServiceFeignClient {
    @GetMapping(value = "getTicket",headers = {"user_id=10240000","token=4302"})
    String getTicket(@RequestParam("ticket_id") String ticket_id);
    //购票成功，减少票的数量
    @GetMapping(value = "reduceTicketQuantity",headers = {"user_id=10240000","token=4302"})
    String reduceTicketQuantity(@RequestParam("ticket_id") String ticket_id);
    //取消订单，增加票的数量
    @GetMapping(value = "addTicketQuantity",headers = {"user_id=10240000","token=4302"})
    String addTicketQuantity(@RequestParam("ticket_id") String ticket_id);
}
