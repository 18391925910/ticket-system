package com.btw.TicketService.service;

import com.btw.TicketService.entity.Station;

import java.util.List;

public  interface TicketCommandService {
    String getAllTickets();
    String getTicket(String ticket_id);
    String getTicketsBySED(String start,String end,String date);
    String addTicketQuantity(String ticket_id);
    String reduceTicketQuantity(String ticket_id);
    String getCityStations(String city);
}

