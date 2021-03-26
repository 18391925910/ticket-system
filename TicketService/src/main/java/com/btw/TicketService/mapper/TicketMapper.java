package com.btw.TicketService.mapper;

import com.btw.TicketService.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Mapper
@Component("TicketMapper")
public interface TicketMapper {

    @Select("select * from ticket")
    ArrayList<Ticket> getAllTickets();

    @Select("select * from ticket where ticket_id = #{ticket_id}")
    Ticket getTicket(@Param("ticket_id") String ticket_id);

    @Select("select * from ticket where start like '%${start}%' and end like '%${end}%' and date like '%${date}%'")
    ArrayList<Ticket> getTicketsBySED(@Param("start") String start,@Param("end") String end,@Param("date") String date);

    @Update("update ticket set quantity = #{quantity} where ticket_id=#{ticket_id}")
    void updateTicketQuantity(@Param("ticket_id") String ticket_id,@Param("quantity") int quantity);

}
