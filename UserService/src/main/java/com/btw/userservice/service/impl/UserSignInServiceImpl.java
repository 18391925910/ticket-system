package com.btw.userservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.btw.userservice.config.BaiDuAiConfig;
import com.btw.userservice.entity.User;
import com.btw.userservice.mapper.UserMapper;
import com.btw.userservice.service.UserSignInService;
import com.btw.userservice.utils.BaiDuAiTokenUtils;
import com.btw.userservice.utils.ResultJsonUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserSignInServiceImpl extends UserSignInService {
    @Autowired
    UserMapper userMapper;
    @Override
    public String singInByPassword(String id, String ps) {
        User user=userMapper.getUser(id);
        if (user!=null) {
            if (user.getPs().equals(ps)){
                return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS).getResult();
            }else return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"password is wrong").getResult();
        }
        else return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                .addParam(ResultJsonUtil.INFO_STR,"has no user").getResult();
    }

    @Override
    public String singInByFace(String face_base64) {
        String face=face_base64.substring(22);
        String resul =searchUserByFace(face);
        if (!JSON.parseObject(resul).getString("error_code").equals("0")){
            return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                    .addParam(ResultJsonUtil.INFO_STR,"search face failed").getResult();
        }else  {
            JSONObject object=(JSONObject) JSON.parseObject(resul).get("result");
            JSONArray array= (JSONArray) object.get("user_list");
            String user_id=(String) ((JSONObject)array.get(0)).get("user_id");
            return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                    .addObject("user_info","{\"user_id\":\""+user_id+"\"}").getResult();
        }
    }
    private static String searchUserByFace(String face){
        System.out.println(face);
        String url= BaiDuAiConfig.API_SEARCH_FACE+ BaiDuAiTokenUtils.getToken();
        PostMethod postMethod = new PostMethod(url) ;
        postMethod.setRequestHeader("Content-Type", "application/json") ;
        String result="";
        try {
            NameValuePair[] data = {
                    new NameValuePair("image",face),
                    new NameValuePair("image_type","BASE64"),
                    new NameValuePair("group_id_list","user")
            };
            postMethod.setRequestBody(data);
            HttpClient httpClient=new HttpClient();
            int response = httpClient.executeMethod(postMethod); // 执行POST方法
            result = postMethod.getResponseBodyAsString() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
