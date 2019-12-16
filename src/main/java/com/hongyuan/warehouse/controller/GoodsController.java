package com.hongyuan.warehouse.controller;

import com.hongyuan.warehouse.pojo.Goods;
import com.hongyuan.warehouse.pojo.Warehouse;
import com.hongyuan.warehouse.services.GoodsService;
import com.hongyuan.warehouse.services.TencentCloudService;
import com.hongyuan.warehouse.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    TencentCloudService tencentCloudService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    WarehouseService warehouseService;
    @RequestMapping("/getpic")
    public String getpic(MultipartFile file, String name, String tap) throws IOException {
        File f = File.createTempFile(name,tap);
        file.transferTo(f);
        System.out.println(f.getName());
        return  tencentCloudService.upload(f, name);
    }
    @RequestMapping("/getgoods")
    public List<Goods> test(){
        return goodsService.getall();
    }
    @RequestMapping("/getgoodsall")
    public Goods test(Integer id){
        return goodsService.getgoodsall(id);
    }
    @RequestMapping("/getGoodsbyname")
    public List<Goods> test(String name){
        return goodsService.getallbynumber(name);
    }
   @RequestMapping("/getbyid")
   public Goods t(Integer id){return  goodsService.getbyid(id);}
    @PostMapping("/insertgoods")
    public String test(@RequestBody  Goods goods,@RequestParam(required = false) Integer warehome){
        if (goods.getImgurl()==""||goods.getImgurl()==null){
            return "0";
        }
        if (goodsService.getbyid(goods.getGoodsid())==null){
            System.out.println(goods.toString());
            if (goodsService.insertGoods(goods)==true){
                return "1";
            }
        }else {
            if (goodsService.update(goods)==true){
                if (warehome!=0){
                    if (warehouseService.getw(goods.getGoodsid())==null){
                        Boolean aBoolean = warehouseService.insertGoodsinWareHouse(new Warehouse(goods.getGoodsid(), goods.getGoodsshipmentdata(),warehome));
                        if (aBoolean==true){
                            return "1";
                        }
                    }else {
                        int aBoolean = warehouseService.upd(new Warehouse(goods.getGoodsid(), goods.getGoodsshipmentdata(),warehome));
                        if (aBoolean==1){
                            return "1";
                        }
                    }
                }

            }

        }
        return "0";
    }
}
