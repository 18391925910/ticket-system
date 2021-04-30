package com.btw.userservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.btw.userservice.entity.User;
import com.btw.userservice.mapper.UserMapper;
import com.btw.userservice.service.UserCommandService;
import com.btw.userservice.utils.ResultJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl extends UserCommandService {
    @Autowired
    UserMapper mapper;
    @Override
    public String recharge(String id, int count) {
        User user=mapper.getUser(id);
        if (user==null){
            return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"has no user").getResult();
        }else {
            int curCount=user.getCount()+count;
            mapper.recharge(id,curCount);
            return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addParam(ResultJsonUtil.INFO_STR,"current count:"+curCount).getResult();
        }
    }

    @Override
    public String cost(String id, int count) {
        User user=mapper.getUser(id);
        if (user==null){
            return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"has no user").getResult();
        }else {
            int curCount=user.getCount()-count;
            mapper.recharge(id,curCount);
            return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addParam(ResultJsonUtil.INFO_STR,"current count:"+curCount).getResult();
        }
    }

    @Override
    public String getUserInfo(String id) {
        User user=mapper.getUser(id);
        return JSON.toJSONString(user);
    }
    public String getAllUser(){
        return JSONArray.toJSONString(mapper.getAllUser());
    }
}
