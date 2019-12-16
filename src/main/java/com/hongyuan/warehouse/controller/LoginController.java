package com.hongyuan.warehouse.controller;

import com.alibaba.fastjson.JSONObject;
import com.hongyuan.warehouse.services.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @RequestMapping("/login")
    public Boolean getlogin(@RequestBody String openid){
        String queryVariable = JSONObject.parseObject(openid).getString("queryVariable");
        if (queryVariable!=null&&!queryVariable.equals("")){
            return  loginService.login(queryVariable);
        }
        return false;
    }
}
