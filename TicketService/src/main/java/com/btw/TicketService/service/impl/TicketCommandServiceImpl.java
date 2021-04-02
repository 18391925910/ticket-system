package com.btw.TicketService.service.impl;

import com.alibaba.fastjson.JSON;
import com.btw.TicketService.entity.Station;
import com.btw.TicketService.entity.Ticket;
import com.btw.TicketService.mapper.StationMapper;
import com.btw.TicketService.mapper.TicketMapper;
import com.btw.TicketService.service.TicketCommandService;
import com.btw.TicketService.utils.ResultJsonUtil;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class TicketCommandServiceImpl implements TicketCommandService {
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    StationMapper stationMapper;
    @Override
    public String getAllTickets(){
        return JSON.toJSONString(ticketMapper.getAllTickets());
    }
    @Override
    public String getTicket(String ticket_id){
        return JSON.toJSONString(ticketMapper.getTicket(ticket_id));
    }
    @Override
    //根据出发地，目的地，以及出发日期查询
    public String getTicketsBySED(String start,String end,String date){
        ArrayList<Ticket> list=new ArrayList<>();
        System.out.println(start+end+date);
        ArrayList<Station> start_stations=stationMapper.getCityStations(start);
        ArrayList<Station> end_stations=stationMapper.getCityStations(end);
        for (int i=0;i<start_stations.size();i++){
            for (int j=0;j<end_stations.size();j++){
                list.addAll(ticketMapper.getTicketsBySED(start_stations.get(i).getStation(),end_stations.get(j).getStation(),date));
            }
        }
        log.info("size:{size}"+list.size());
        String result;
        if (list.size()>0){
            result=ResultJsonUtil.getInstance()
                    .addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addParam(ResultJsonUtil.INFO_STR,"success")
                    .addArray("data", JSON.toJSONString(list)).getResult();
        }else{
            result=ResultJsonUtil.getInstance()
                    .addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"no data")
                    .getResult();
        }
        log.info(result);
        return result;
    }
    @Override
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
    @Override
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
    @Override
    public String getCityStations(String city){
        List<Station> stations=stationMapper.getCityStations(city);
        log.info("size:{size}"+stations.size());
        String result;
        if (stations!=null){
            result=ResultJsonUtil.getInstance()
                    .addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addParam(ResultJsonUtil.INFO_STR,"success")
                    .addArray("data", JSON.toJSONString(stations)).getResult();
        }else{
            result=ResultJsonUtil.getInstance()
                    .addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"failed")
                    .getResult();
        }
        return result;
    }
}
