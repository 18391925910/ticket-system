package com.btw.OrderService.entity;

import lombok.Data;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-13 09:45
 */
@Data
public class CreateKafkaMsgBO {
    private String user_id;
    private String ticket_id;

    public CreateKafkaMsgBO(String user_id, String ticket_id) {
        this.ticket_id=ticket_id;
        this.user_id=user_id;
    }
}
