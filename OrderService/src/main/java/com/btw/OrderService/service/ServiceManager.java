package com.btw.OrderService.service;

import com.btw.OrderService.utils.ResultJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-21 15:13
 * @description: 服务控制 动态参数
 */
@Slf4j
@Service
public class ServiceManager {
    public String pauseService(){
        ServiceManager.ManageServiceConfig.pauseService();
        return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                .addParam(ResultJsonUtil.INFO_STR,"pause success").getResult();
    }

    public String startService() {
        ServiceManager.ManageServiceConfig.startService();
        return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                .addParam(ResultJsonUtil.INFO_STR,"start Service").getResult();
    }

    public String alterConfig(String config_name,String config_value){
        log.info("config_name:{}config_value:{}",config_name,config_value);
        return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                .addParam(ResultJsonUtil.INFO_STR,"alert Service config Success current config-"+config_name+":"+config_value).getResult();
    }

    public static class ManageServiceConfig{

        private static boolean isServicePause=false;

        public static boolean isServicePause() {
            return isServicePause;
        }

        public static void pauseService() {
            ManageServiceConfig.isServicePause = true;
        }
        public static void startService() {
            ManageServiceConfig.isServicePause = false;
        }

    }
}
