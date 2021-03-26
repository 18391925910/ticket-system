package com.btw.TicketService.service.impl;

import com.alibaba.fastjson.JSON;
import com.btw.TicketService.entity.Station;
import com.btw.TicketService.entity.Ticket;
import com.btw.TicketService.mapper.StationMapper;
import com.btw.TicketService.mapper.TicketMapper;
import com.btw.TicketService.service.TicketCommandService;
import com.btw.TicketService.utils.ResultJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TicketCommandServiceImpl extends TicketCommandService {
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    StationMapper stationMapper;

    public String getAllTickets(){
        return JSON.toJSONString(ticketMapper.getAllTickets());
    }
    public String getTicket(String ticket_id){
        return JSON.toJSONString(ticketMapper.getTicket(ticket_id));
    }
    //根据出发地，目的地，以及出发日期查询
    public String getTicketsBySED(String start,String end,String date){
        ArrayList<Ticket> list=new ArrayList<>();
        System.out.println(start+end+date);
        ArrayList<Station> start_stations=stationMapper.getAllStationS(start);
        ArrayList<Station> end_stations=stationMapper.getAllStationS(end);
        for (int i=0;i<start_stations.size();i++){
            for (int j=0;j<end_stations.size();j++){
                list.addAll(ticketMapper.getTicketsBySED(start_stations.get(i).getStation(),end_stations.get(j).getStation(),date));
            }
        }
        return JSON.toJSONString(list);
    }
    public String addTicketQuantity(String ticket_id){
        Ticket ticket= ticketMapper.getTicket(ticket_id);
        String result;
        if (ticket!=null){
            int quantity=ticket.getQuantity();
            ticketMapper.updateTicketQuantity(ticket_id,quantity+1);
            result= ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addParam(ResultJsonUtil.INFO_STR,"更新成功").getResult();
        }else {
            result= ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"更新失败").getResult();
        }
        return result;
    }
    public String reduceTicketQuantity(String ticket_id){
        Ticket ticket= ticketMapper.getTicket(ticket_id);
        String result;
        if (ticket!=null){
            int quantity=ticket.getQuantity();
            ticketMapper.updateTicketQuantity(ticket_id,quantity-1);
            result= ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addParam(ResultJsonUtil.INFO_STR,"更新成功").getResult();
        }else {
            result= ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"更新失败").getResult();
        }
        return result;
    }
}
