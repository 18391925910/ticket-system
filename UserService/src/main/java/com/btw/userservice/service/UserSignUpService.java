package com.btw.userservice.service;

import org.springframework.stereotype.Service;

@Service
public abstract  class UserSignUpService {
    public abstract String singUp(String id, String name, String ps, String face_base64);

}
