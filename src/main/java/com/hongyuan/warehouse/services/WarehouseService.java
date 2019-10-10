package com.hongyuan.warehouse.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongyuan.warehouse.mapper.WareHouseMapper;
import com.hongyuan.warehouse.pojo.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    WareHouseMapper wareHouseMapper;
    public List<Warehouse> getall(){
        return  wareHouseMapper.selectList(null);
    }

    public List<Warehouse> getallbyname(String name){
        return  wareHouseMapper.selectList(new QueryWrapper<Warehouse>().like("goodsname",name));
    }

    public Boolean insertGoodsinWareHouse(Warehouse warehouse){
        if (wareHouseMapper.insert(warehouse)>0){
            return true;
        }
        return false;
    }
    public Warehouse getw(Integer id){
        return wareHouseMapper.selectById(id);
    }
    public int upd(Warehouse warehouse){
        return  wareHouseMapper.update(warehouse,null);
    }


}
