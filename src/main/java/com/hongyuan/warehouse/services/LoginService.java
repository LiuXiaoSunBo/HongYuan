package com.hongyuan.warehouse.services;

import com.hongyuan.warehouse.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginMapper mapper;
    public Boolean login(String openid){
        if (mapper.selectById(openid)!=null){
            return true;
        }
        return false;
    }
}
