package com.btw.TicketService;

import com.btw.TicketService.entity.Ticket;
import com.btw.TicketService.mapper.TicketMapper;
import com.btw.TicketService.service.impl.TicketCommandServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;

@SpringBootTest(classes = TicketServiceApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class TestService {
    @Autowired
    TicketCommandServiceImpl service;

    @Test
    public void testA(){
        String s=service.getTicketsBySED("咸阳","浦东","2020-05-14");
        System.out.println(s);
    }
}
