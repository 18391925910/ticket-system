package com.btw.OrderService.service.userServiceFeign;

import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "USER",fallback = UserServiceFallback.class)
@Component
public interface UserServiceFeignClient {
    @GetMapping("cost")
    String cost(@RequestParam("id") String id, @RequestParam("count") int cost_count);
    @GetMapping("recharge")
    String recharge(@RequestParam("id") String id,@RequestParam("count") int recharge_count);
}
