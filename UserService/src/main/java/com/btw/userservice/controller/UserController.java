package com.btw.userservice.controller;

import com.btw.userservice.mapper.UserMapper;
import com.btw.userservice.service.impl.UserCommandServiceImpl;
import com.btw.userservice.service.impl.UserSignInServiceImpl;
import com.btw.userservice.service.impl.UserSignUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserSignUpServiceImpl userSingUpService;
    @Autowired
    UserSignInServiceImpl userSingInService;
    @Autowired
    UserCommandServiceImpl userCommandService;

    /**
     *    注册
     *   @Param:face_base64是使用base64编码和url编码后的image信息
    */
    @PostMapping("signUp")
    String signUp(@RequestParam("id") String id, @RequestParam("name")String name,
                 @RequestParam("ps") String ps,@RequestParam("face_base64") String face_base64 ){
        return userSingUpService.singUp(id,name,ps,face_base64);
    }
    //人脸登陆
    @PostMapping("signInByFace")
    String signInByFace(@RequestParam("face_base64") String face_base64){
        return userSingInService.singInByFace(face_base64);
    }
    //密码登陆
    @PostMapping("signInByPassword")
    String signInByPassword(@RequestParam("id") String id,@RequestParam("ps") String ps){
        return userSingInService.singInByPassword(id,ps);
    }
    //充值
    @GetMapping("recharge")
    String recharge(@RequestParam("id") String id,@RequestParam("count") int recharge_count){
        return userCommandService.recharge(id,recharge_count);
    }
    //消费：减少count
    @GetMapping("cost")
    String cost(@RequestParam("id") String id,@RequestParam("count") int cost_count){
        return userCommandService.cost(id,cost_count);
    }
    @GetMapping("getUserInfo")
    String getUserInfo(@RequestParam("id") String id){
        return userCommandService.getUserInfo(id);
    }
}
