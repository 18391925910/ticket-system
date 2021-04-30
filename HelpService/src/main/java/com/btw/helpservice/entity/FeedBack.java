package com.btw.helpservice.entity;

import lombok.Data;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-30 09:10
 */
@Data
public class FeedBack {
    private String id;
    private String content;
    private String user_id;
    private String date;
}