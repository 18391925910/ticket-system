package com.btw.OrderService.mapper;

import com.btw.OrderService.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Mapper
@Component("UserMapper")
public interface OrderMapper {
    @Select("select * from ticket_system.order")
    ArrayList<Order> getAllOrder();

    @Select("select * from ticket_system.order where user_id=#{user_id}")
    ArrayList<Order> getUserAllOrder(@Param("user_id") String user_id);

    @Select("Select * from ticket_system.order where order_id= #{order_id}")
    Order getOrder(@Param("order_id")String order_id);

    @Select("Select * from ticket_system.order where user_id = #{user_id} and flight_id =#{flight}")
    ArrayList<Order> getOrderByFlight(@Param("user_id") String user_id,@Param("flight_id") String flight_id);
    @Select("Delete from ticket_system.order where order_id = #{order_id}")
    void cancelOrder(@Param("order_id") String order_id);
    //增加订单
    @Insert("insert into ticket_system.order (order_id,ticket_id,start,end,start_time,end_time,date,price,user_id," +
            "sell_time,flight_id,state) values (#{order_id},#{ticket_id},#{start},#{end},#{start_time},#{end_time}," +
            "#{date},#{price},#{user_id},#{sell_time},#{flight_id},#{state})")
    void addOrder(@Param("order_id") String order_id,@Param("ticket_id") String ticket_id,@Param("start") String start,@Param("end") String end,
                  @Param("start_time") String start_time,@Param("end_time") String end_time,@Param("date") String date,
                  @Param("price") int price,@Param("user_id") String user_id,@Param("sell_time") String sell_time,
                  @Param("flight_id") String flight_id,@Param("state") String state);
}
