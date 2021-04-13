package com.btw.OrderService.service.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.btw.OrderService.entity.CreateKafkaMsgBO;
import com.btw.OrderService.service.OrderCommandService;
import com.btw.OrderService.service.impl.OrderCommandServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-13 08:58
 * @description: create order by kafka send from other service
 */
@Component
@Slf4j
public class KafkaListener {
    @Resource
    OrderCommandServiceImpl orderCommandService;
    Logger kafkaLog= LoggerFactory.getLogger("createOrderLog");
    @org.springframework.kafka.annotation.KafkaListener(topics = {"order"})
    public void listenCreateOrder(ConsumerRecord<?,?> record){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            String data=message.toString();
            CreateKafkaMsgBO object= JSON.parseObject(data, CreateKafkaMsgBO.class);
            log.info(object.toString());
            try {
                String result=orderCommandService.createOrder(object.getUser_id(),object.getTicket_id());
                kafkaLog.info("createOrderResult:{}",result);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}