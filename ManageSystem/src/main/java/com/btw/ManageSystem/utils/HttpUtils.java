package com.btw.ManageSystem.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @create: elvis.yue
 * @date 2021/4/23 14:02
 */
@Slf4j
public class HttpUtils {

    static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    public static String get(String url){
        return get(url,null);
    }
    /**
     * with token
     */
    public static String getWithToken(String url){
        Map<String,String> header=new HashMap<>();
        header.put("user_id","10240000");
        header.put("token","4302");
        return get(url,header);
    }
    public static String get(String url, Map<String,String> headers) {
        HttpGet httpGet = new HttpGet(url);
        if (!Objects.isNull(headers)){
            headers.forEach(httpGet::setHeader);
        }
        CloseableHttpResponse response;
        String body="";
        try {
            response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            body = EntityUtils.toString(responseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    public static String post(String url,String body, Header headers) {
        HttpPost post=new HttpPost(url);
        StringEntity entity=new StringEntity(body,"UTF-8");
        post.setEntity(entity);
        post.setHeader(headers);
        CloseableHttpResponse response;
        String data="";
        try {
            response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            data = EntityUtils.toString(responseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
