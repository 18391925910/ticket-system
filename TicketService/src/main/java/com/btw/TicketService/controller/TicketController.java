package com.btw.TicketService.controller;
import com.btw.TicketService.service.impl.TicketCommandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TicketController {
    @Autowired
    TicketCommandServiceImpl service;

    @GetMapping("getAllTickets")
    public String getAllTickets(HttpServletRequest request){
        return service.getAllTickets();
    }

    @GetMapping("getTicketsBySED")
    public String getTicketsBySED(HttpServletRequest request,String start,String end,String date){
        return service.getTicketsBySED(start,end,date);
    }

    @GetMapping("getTicket")
    public String getTicket(HttpServletRequest request,@RequestParam("ticket_id") String ticket_id){
        return service.getTicket(ticket_id);
    }

    @GetMapping("addTicketQuantity")
    public String addTicketQuantity(HttpServletRequest request,@RequestParam("ticket_id")String ticket_id){
        return service.addTicketQuantity(ticket_id);
    }
    @GetMapping("reduceTicketQuantity")
    public String reduceTicketQuantity(HttpServletRequest request,String ticket_id){
        return service.reduceTicketQuantity(ticket_id);
    }

    @GetMapping("getStations")
    public String getCityStations(HttpServletRequest request,@RequestParam("city")String city){
        return service.getCityStations(city);
    }
}
