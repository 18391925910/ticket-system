package com.btw.ManageSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-23 15:01
 */
@Controller
@RequestMapping("data")
public class DataStatisticController {
    /**
     * @description: 数据统计
     * @param modelAndView
     * @return
     */
    @GetMapping("statistic")
    public String DataStatistic(ModelAndView modelAndView){
        return "data-statictic";
    }
}