package com.btw.userservice.controller;
import com.btw.userservice.service.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-21 15:07
 */
@RestController
@RequestMapping("serviceManage")
public class ServiceManageController {
    @Autowired
    ServiceManager manageService;

    @GetMapping("/pauseService")
    public String pauseService(HttpServletRequest request){
        return manageService.pauseService();
    }

    @GetMapping("/startService")
    public String startService(HttpServletRequest request){
        return manageService.startService();
    }

    @GetMapping("/alterConfig")
    public String alterConfig(HttpServletRequest request,@RequestParam("config_name")String config_name,@RequestParam("config_value")String config_value){
        return manageService.alterConfig(config_name,config_value);
    }
}