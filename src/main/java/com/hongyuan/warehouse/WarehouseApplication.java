package com.hongyuan.warehouse;

import com.hongyuan.warehouse.services.WarehouseService;
import com.hongyuan.warehouse.services.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class WarehouseApplication {

    @Autowired
    WechatService wechatService;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @PostConstruct
    public void test() throws IOException {
        wechatService.getAccess_token();
        wechatService.setView();
    }

}
