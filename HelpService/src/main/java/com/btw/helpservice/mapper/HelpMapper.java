package com.btw.helpservice.mapper;

import com.btw.helpservice.entity.FeedBack;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("HelpMapper")
public interface HelpMapper {
    @Insert("insert into user_feedback(id,content,user_id,date)values(#{id},#{content},#{user_id},#{date})")
    void addUserFeedback(@Param("id")String id,@Param("content")String content,@Param("user_id")String user_id,@Param("date")String date);

    @Select("select * from user_feedback")
    List<FeedBack> getAllFeedBack();
}
