package com.btw.userservice.service;

public abstract class UserCommandService {
    public abstract String recharge(String id,int count);
    public abstract String cost(String id,int count);
    public abstract String getUserInfo(String id);
}
