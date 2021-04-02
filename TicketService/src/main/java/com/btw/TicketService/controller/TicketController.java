package com.btw.TicketService.controller;

import com.alibaba.fastjson.JSON;
import com.btw.TicketService.mapper.TicketMapper;

import com.btw.TicketService.service.impl.TicketCommandServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @Autowired
    TicketCommandServiceImpl service;
    @GetMapping("getAllTickets")
    public String getAllTickets(){
        return service.getAllTickets();
    }
    @GetMapping("getTicketsBySED")
    public String getTicketsBySED(String start,String end,String date){
        return service.getTicketsBySED(start,end,date);
    }
    @GetMapping("getTicket")
    public String getTicket(@RequestParam("ticket_id") String ticket_id){
        return service.getTicket(ticket_id);
    }
    @GetMapping("addTicketQuantity")
    public String addTicketQuantity(@RequestParam("ticket_id")String ticket_id){
        return service.addTicketQuantity(ticket_id);
    }
    @GetMapping("reduceTicketQuantity")
    public String reduceTicketQuantity(String ticket_id){
        return service.reduceTicketQuantity(ticket_id);
    }
    @GetMapping("getStations")
    public String getCityStations(@RequestParam("city")String city){
        return service.getCityStations(city);
    }
}
