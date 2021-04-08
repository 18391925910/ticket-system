package com.btw.helpservice.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-02 14:44
 * @description: 接口权限验证
 */
@Slf4j
public class TokenVerifier {
    /**
     *
     * @param user_id
     * @param token
     * @return
     * @description: token算法 id/x-y=token
     * x=1024
     * y=5698
     */
    public static boolean verifyToken(String user_id,String token){
        if (user_id==null||token==null) {
            log.info("user_id==null||token==null");
            return false;
        }
        int x=1024;
        int y=5698;
        int relToken=Integer.parseInt(user_id)/x-y;
        log.info("token:{},realToken:{}",token,relToken);
        return token.equals(String.valueOf(relToken));
    }
}