package com.btw.OrderService.service.ticketServiceFeign;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class TicketServiceFallback implements TicketServiceFeignClient {

    @Override
    public String getTicket(String ticket_id) {
        return null;
    }

    @Override
    public String reduceTicketQuantity(String ticket_id) {
        return null;
    }

    @Override
    public String addTicketQuantity(String ticket_id) {
        return null;
    }
}
