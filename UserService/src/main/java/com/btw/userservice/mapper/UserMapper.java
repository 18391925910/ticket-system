package com.btw.userservice.mapper;

import com.btw.userservice.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("UserMapper")
public interface UserMapper {

    //读取用户信息
    @Select("SELECT * FROM USER where id = #{id}")
    User getUser(@Param("id")String id);

    //注册
    @Insert("Insert into user(id,name,ps)values(#{id},#{name},#{ps})")
    void addUser(@Param("id")String id,@Param("name")String name,@Param("ps")String ps);

    //充值
    @Update("update user set count = #{count} where id = #{id}")
    void recharge(@Param("id") String id,@Param("count") int count);
    //消费
    @Update("update user set count = #{count} where id = #{id}")
    void cost(@Param("id") String id,@Param("count") int count);
    @Select("select * from user")
    List<User> getAllUser();
}
