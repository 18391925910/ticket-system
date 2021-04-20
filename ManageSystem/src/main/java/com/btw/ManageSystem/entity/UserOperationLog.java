package com.btw.ManageSystem.entity;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-20 11:41
 */
public class UserOperationLog {

    private String id;
    private String user_id;
    private String operation_id;
    private String date;
    private String description;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setOperation_id(String operation_id) {
        this.operation_id = operation_id;
    }
    public String getOperation_id() {
        return operation_id;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

}