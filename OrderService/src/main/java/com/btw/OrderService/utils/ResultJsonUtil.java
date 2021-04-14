package com.btw.OrderService.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:elvis.yue
 * @create: 2021/4/14
*  @description:  调用接口返回Json数据的工具类
 */
@Slf4j
@Component
public class ResultJsonUtil {

    public static final String RESULT_SUCCESS="success";
    public static final String RESULT_FAIL="fail";
    public static final String RESULT_STR="result";
    public static final String INFO_STR="info";
    public static ResultJson getInstance(){
        return new ResultJson();
    }
    public static class ResultJson{
        String result="{}";
        public ResultJson addParam(String key,String value){
            JSONObject jsonObject= JSON.parseObject(result);
            jsonObject.put(key,value);
            this.result=jsonObject.toJSONString();
            return this;
        }
        public ResultJson addObject(String key,String objectStr){
            JSONObject jsonObject=JSON.parseObject(objectStr);
            JSONObject curJsonObject=JSONObject.parseObject(result);
            curJsonObject.put(key,jsonObject);
            this.result=curJsonObject.toJSONString();
            return this;
        }
        public ResultJson addArray(String key, List<Object> list){
            JSONObject curJsonObject=JSONObject.parseObject(result);
            curJsonObject.put(key,list);
            this.result=curJsonObject.toJSONString();
            return this;
        }
        public String getResult(){
            log.info("Result:{}",result);
            return result;
        }
    }

}
