package com.btw.OrderService.service.userServiceFeign;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserServiceFallback implements UserServiceFeignClient {
    @Override
    public String cost(String id, int cost_count) {
        return null;
    }

    @Override
    public String recharge(String id, int recharge_count) {
        return null;
    }
}
