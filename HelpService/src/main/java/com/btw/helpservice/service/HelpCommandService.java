package com.btw.helpservice.service;

import org.springframework.stereotype.Service;

public abstract class HelpCommandService {
    public abstract String userFeedBack(String text,String user_id);
    public abstract String customerServiceChat(String text);
}
