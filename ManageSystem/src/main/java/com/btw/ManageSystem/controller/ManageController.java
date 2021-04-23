package com.btw.ManageSystem.controller;
import com.btw.ManageSystem.Service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManageController {
    @Autowired
    CommonService commonService;

    /**
     * @description: login
     * @param model
     * @return
     */
    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("data","1");
        return "login";
    }
    /**
     * @description: main
     * @param model
     * @return
     */
    @GetMapping("main")
    public String main(Model model){
        model.addAttribute("data","1");
        return "main";
    }
    /**
     * @description: 服务控制页面
     */
    @GetMapping("service-manage")
    public String serviceManage(Model model){
        model.addAttribute("data","1");
        return "service-manage";
    }

    /**
     * @param service service name
     * @param status service actuator name
     * @return json data
     * @description: get service run info
     */
    @GetMapping("actuator/{service}/{status}")
    @ResponseBody
    public String getServiceActuatorInfo(@PathVariable String service, @PathVariable String status){
        return commonService.getServiceStatus(service,status);
    }

    /**
     * @return result
     * @description: alter service run config
     */
    @GetMapping("config/alter")
    @ResponseBody
    public String alterServiceConfig(@RequestParam("service_name")String service_name,@RequestParam("config_name")String config_name,@RequestParam("config_value")String config_value){
        return commonService.alterServiceConfig(service_name,config_name,config_value);
    }
}
