package com.btw.ManageSystem.config;

import lombok.Data;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-09 10:19
 */
public class BaseConfig {
    private static String user_id="10240000";
    private static String token="4302";

    public static String getToken() {
        return token;
    }

    public static String getUser_id() {
        return user_id;
    }
}