package com.btw.ManageSystem.Service;

import com.btw.ManageSystem.config.BaseConfig;
import com.btw.ManageSystem.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-20 15:07
 */
@Service
public class CommonService {
    @Value("${manage.service-base-url}")
    String baseUrl;

    /**
     *
     * @param service_name
     * @param status_name
     * @return
     * @description:  get service actuator info
     */
    public String getServiceStatus(String service_name,String status_name){
        String url=baseUrl+"/"+service_name+"/"+"actuator/"+status_name;
        HashMap<String,String> headers=new HashMap<>();
        headers.put("user_id", BaseConfig.getToken());
        headers.put("token",BaseConfig.getUser_id());
        return HttpUtils.get(url,headers);
    }

    /**
     * @description: alter service config
     */
    public String alterServiceConfig(String service_name,String config_name,String config_value){
        String url=baseUrl+"/"+service_name+"/"+"serviceManage/alterConfig?config_name="+config_name+"&config_value="+config_value;
        HashMap<String,String> headers=new HashMap<>();
        headers.put("user_id","10240000");
        headers.put("token","4302");
        return HttpUtils.get(url,headers);
    }
}