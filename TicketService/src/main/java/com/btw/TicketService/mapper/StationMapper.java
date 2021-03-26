package com.btw.TicketService.mapper;

import com.btw.TicketService.entity.Station;
import com.btw.TicketService.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Mapper
@Component("StationMapper")
public interface StationMapper {

    @Select("select * from city_stations where city = #{city}")
    ArrayList<Station> getAllStationS(@Param("city") String city);

}
