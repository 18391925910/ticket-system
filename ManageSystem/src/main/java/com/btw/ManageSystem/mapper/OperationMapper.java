package com.btw.ManageSystem.mapper;

import com.btw.ManageSystem.entity.Operation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-20 11:38
 */
@Component
@Mapper
public interface OperationMapper {
    @Select("select * from operation where id = {#operation_id}")
    Operation selectOperation(String operation_id);
}