package com.btw.OrderService.service;

public abstract class OrderCommonService {
    public abstract String createOrder(String user_id, String ticket_id);
    public abstract String cancelOrder(String order_id);
    public abstract String getOrder(String order_id);
    public abstract String getAllOrder();
    public abstract String getUserAllOrder(String order_id);
    public abstract String getPaperTicket(String order_id);
    public abstract String addOrder(String user_id, String ticket_id);
}
