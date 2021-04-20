package com.btw.TicketService.mapper;

import com.btw.TicketService.entity.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-20 11:34
 */
@Mapper
@Component
public interface CityMapper {
    @Select("Select * from city where city = #{city}")
    City selectCity(@Param("city") String city);

    @Insert("insert into City (city,station_quantity,time_area,location,hot_star) values (#{city},#{station_quantity},#{time_area},#{location},#{hot_star})")
    void addCity(@Param("city") String city,@Param("time_area") String time_area,@Param("location") String location,@Param("hot_star") String hot_star);
}