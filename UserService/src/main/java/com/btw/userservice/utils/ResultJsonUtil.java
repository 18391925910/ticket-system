package com.btw.userservice.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/*
*   调用接口返回Json数据的工具类
 */
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
        public String getResult(){
            return result;
        }
    }

}
