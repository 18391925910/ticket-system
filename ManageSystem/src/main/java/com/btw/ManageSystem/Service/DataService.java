package com.btw.ManageSystem.Service;

import com.alibaba.fastjson.JSONObject;
import com.btw.ManageSystem.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-29 16:20
 */
@Service
public class DataService {
    @Value("${manage.service-base-url}")
    String baseUrl;
    public String getAllData(String serviceName){
        String data;
        try {
            switch (serviceName){
                case "order": data=getOrderServiceData();break;
                case "ticket": data=getTicketServiceData();break;
                case "help": data=getHelpServiceData();break;
                case "user": data=getUserServiceData();break;
                default:data="{}";
            }
        }catch (Exception e){
            data="{'result':'failed',msg:{"+e.getMessage()+"}}";
            e.printStackTrace();
        }
        return data;
    }
    private String getOrderServiceData() throws Exception{
        String allOrderUrl=baseUrl+"/order"+"/"+"getAllOrder";
        String orderResult=HttpUtils.getWithToken(allOrderUrl);
        Map<String,String> data=new HashMap<>();
        data.put("order",orderResult);
        return buildData(data);
    }
    private String getUserServiceData() throws Exception{
        String allUserUrl=baseUrl+"/user"+"/"+"getAllUser";
        String userResult=HttpUtils.getWithToken(allUserUrl);
        Map<String,String> data=new HashMap<>();
        data.put("user",userResult);
        return buildData(data);
    }
    private String getTicketServiceData()throws Exception{
        String allTicketUrl=baseUrl+"/ticket"+"/"+"getAllTickets";
        String ticketResult=HttpUtils.getWithToken(allTicketUrl);
        Map<String,String> data=new HashMap<>();
        data.put("ticket",ticketResult);
        return buildData(data);
    }
    private String getHelpServiceData()throws Exception{
        String allOrderUrl=baseUrl+"/help"+"/"+"allFeedBack";
        String feedbackResult=HttpUtils.getWithToken(allOrderUrl);
        Map<String,String> data=new HashMap<>();
        data.put("feedback",feedbackResult);
        return buildData(data);
    }
    private String buildData(Map<String,String> data){
        JSONObject jsonObject=new JSONObject();
        data.forEach(jsonObject::put);
        return JSONObject.toJSONString(jsonObject);
    }
}