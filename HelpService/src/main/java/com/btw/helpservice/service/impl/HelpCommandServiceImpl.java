package com.btw.helpservice.service.impl;

import com.btw.helpservice.mapper.HelpMapper;
import com.btw.helpservice.service.HelpCommandService;
import com.btw.helpservice.utils.ResultJsonUtil;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;


@Service
public class HelpCommandServiceImpl extends HelpCommandService {
    @Autowired
    HelpMapper mapper;
    @Override
    public String userFeedBack(String text,String user_id) {
        mapper.addUserFeedback(Long.toString(new Date().getTime()),text,user_id,new Date().toString());
        return ResultJsonUtil.getInstance().addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_SUCCESS)
                .addParam(ResultJsonUtil.INFO_STR,"提交成功，感谢您的反馈！").getResult();
    }

    /**AI客服服务
     * @description:调用turing123智能客服接口
     * @param text
     * @return
     */
    @Override
    public String customerServiceChat(String text) {
        String api_key="00333b1afc1c496390cbb4a6483cc444";
        String user_id="400482";
        String host_path="http://openapi.tuling123.com/openapi/api/v2";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post=new HttpPost(host_path);
        CloseableHttpResponse response = null;
        String result="";
        String json="{\n" +
                "\t\"reqType\":0,\n" +
                "    \"perception\": {\n" +
                "        \"inputText\": {\n" +
                "            \"text\": \""+text+"\"\n" +
                "        },\n" +
                "        \"inputImage\": {\n" +
                "            \"url\": \"imageUrl\"\n" +
                "        },\n" +
                "        \"selfInfo\": {\n" +
                "            \"location\": {\n" +
                "                \"city\": \"陕西\",\n" +
                "                \"province\": \"汉中\",\n" +
                "                \"street\": \"东关街道\"\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"userInfo\": {\n" +
                "        \"apiKey\": \""+api_key+"\",\n" +
                "        \"userId\": \""+user_id+"\"\n" +
                "    }\n" +
                "}";
        try {
            StringEntity stringEntity=new StringEntity(json,"UTF-8");
            //stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json;charset=UTF-8");//发送json数据需要设置contentType
            post.setEntity(stringEntity);
            response=httpClient.execute(post);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                result = EntityUtils.toString(response.getEntity());// 返回json格式：
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }
}
