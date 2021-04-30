package com.btw.helpservice.controller;
import com.btw.helpservice.service.impl.HelpCommandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelpController {
    @Autowired
    HelpCommandServiceImpl service;
    @GetMapping("userFeedBack")
    public String userFeedBack(HttpServletRequest request, @RequestParam("feedback_content")String feedback_content, @RequestParam("user_id")String user_id){
        return service.userFeedBack(feedback_content,user_id);
    }
    @GetMapping("getCustomerChatContent")
    public String getCustomerChatContent(HttpServletRequest request,@RequestParam("chat_content")String chat_content){
        return service.customerServiceChat(chat_content);
    }
    @GetMapping("allFeedBack")
    public String getAllFeedBack(HttpServletRequest request){
        return service.getAllUserFeedBack();
    }
}
