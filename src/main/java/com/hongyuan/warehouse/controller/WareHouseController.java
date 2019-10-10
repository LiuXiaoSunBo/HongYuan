package com.hongyuan.warehouse.controller;

import com.hongyuan.warehouse.pojo.Warehouse;
import com.hongyuan.warehouse.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class WareHouseController {
    @Autowired
    WarehouseService warehouseService;
    @RequestMapping("/getware")
    public List<Warehouse> warehouses(){
        return warehouseService.getall();
    }
    @RequestMapping("/getwarebyname")
    public List<Warehouse> warehouses(String name){
        return warehouseService.getallbyname(name);
    }
    @RequestMapping("/insertwarehouse")
    public String warehouse(Warehouse warehouse){
       if (warehouseService.insertGoodsinWareHouse(warehouse)){
           return "1";
       }
       return "0";
    }
}
