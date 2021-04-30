package com.btw.OrderService.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Properties;
@Slf4j
class KafkaTest {
    KafkaProducer<String,String> kafkaProducer;
    Properties properties;
    @BeforeEach
    public void before(){
        properties=new Properties();
        properties.put("bootstrap.servers","175.24.30.169:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer= new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
    }

    @Test
    void kafkaListenerTest(){
        ProducerRecord<String,String> record=new ProducerRecord<>("test","1");
        kafkaProducer.send(record,(r,e)->{
            log.info("record:{},exception:{}",r,e);
        });
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}