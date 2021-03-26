package com.btw.userservice.service;

import org.springframework.stereotype.Service;

@Service
public abstract class UserSignInService {
    public abstract String singInByPassword(String id, String ps);
    public abstract String singInByFace(String Face);
}
