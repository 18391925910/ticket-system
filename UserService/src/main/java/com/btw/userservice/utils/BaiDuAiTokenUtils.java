package com.btw.userservice.utils;

import com.alibaba.fastjson.JSON;
import com.btw.userservice.config.BaiDuAiConfig;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class BaiDuAiTokenUtils {

    public static String getToken(){
        String access_token;
        String getAccessTokenUrl = BaiDuAiConfig.authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + BaiDuAiConfig.APP_KEY
                // 3. 官网获取的 Secret Key
                + "&client_secret=" +BaiDuAiConfig.SECRET_KEY;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            access_token = JSON.parseObject(result).getString("access_token");
            return access_token;
        } catch (Exception e) {
            return "error";
        }
    }
}
