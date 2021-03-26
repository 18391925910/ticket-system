package com.btw.userservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.btw.userservice.config.BaiDuAiConfig;
import com.btw.userservice.mapper.UserMapper;
import com.btw.userservice.service.UserSignUpService;
import com.btw.userservice.utils.BaiDuAiTokenUtils;
import com.btw.userservice.utils.ResultJsonUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class UserSignUpServiceImpl extends UserSignUpService {
    @Autowired
    UserMapper userMapper;
    @Override
    public String singUp(String id, String name, String ps, String face_base64) {
        String result="";
        if (userMapper.getUser(id)==null){
            String resul= addFaceToFaceLibrary(id,face_base64);
            if (JSON.parseObject(resul).getString("error_code").equals("0")){
                userMapper.addUser(id,name,ps);
                result= ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addParam(ResultJsonUtil.INFO_STR,"注册成功").getResult();}
            else result= ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,resul).getResult();
        }else
            result=ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS).
                    addParam(ResultJsonUtil.INFO_STR,"账号已存在").getResult();

        return result;
    }

    private static String addFaceToFaceLibrary(String id, String face_base64){
        String face=face_base64.substring(22);
        String url=BaiDuAiConfig.API_ADD_FACE+ BaiDuAiTokenUtils.getToken();
        PostMethod postMethod = new PostMethod(url) ;
        postMethod.setRequestHeader("Content-Type", "application/json") ;
        String result="";
        try {
            NameValuePair[] data = {
                    new NameValuePair("user_id",id),
                    new NameValuePair("group_id","user"),
                    new NameValuePair("image",face),
                    new NameValuePair("image_type","BASE64")
            };
            postMethod.setRequestBody(data);
            HttpClient httpClient=new HttpClient();
            int response = httpClient.executeMethod(postMethod); // 执行POST方法
            result = postMethod.getResponseBodyAsString() ;
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
