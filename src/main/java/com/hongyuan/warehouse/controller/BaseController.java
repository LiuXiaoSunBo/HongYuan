package com.hongyuan.warehouse.controller;

import com.hongyuan.warehouse.pojo.wechat.WechatTalkMessage;
import com.hongyuan.warehouse.services.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;

@RestController
public class BaseController {
    @Autowired
    WechatService wechatService;
    @RequestMapping("/")
    public String test(HttpServletRequest request, @RequestParam(value="id",required = false,defaultValue = "") String echostr) throws IOException, URISyntaxException {
        if (echostr!=""&&echostr!=null){
            return echostr;
        }
        return wechatService.event(request);
    }
}
