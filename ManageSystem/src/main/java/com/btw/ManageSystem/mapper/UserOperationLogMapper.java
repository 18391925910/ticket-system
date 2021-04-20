package com.btw.ManageSystem.mapper;
import com.btw.ManageSystem.entity.UserOperationLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-20 11:33
 */
@Mapper
@Component
public interface UserOperationLogMapper {
    @Select("select * from user_operation_log where id = {#id}")
    UserOperationLog selectOperationLog(@Param("id") String id);

    @Insert("insert into user_operation_log (user_id,operation_id,date,description) values (#{id},#{user_id},#{operation_id},#{date},#{description})")
    void addOperationLog( @Param("user_id") String user_id, @Param("operation_id") String operation_id, @Param("date") String date,@Param("description") String description);
}