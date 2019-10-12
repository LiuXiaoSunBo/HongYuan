package com.hongyuan.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongyuan.warehouse.pojo.Goods;
import com.hongyuan.warehouse.pojo.Warehouse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface GoodsMapper extends BaseMapper<Goods> {
    @Results({
            @Result(property = "goodsid",column = "goodsid"),
            @Result(property = "warehouse",column = "goodsid",one = @One(select = "com.hongyuan.warehouse.mapper.WareHouseMapper.selectById"))
    })
    @Select("select * from goods where goodsid=#{id}")
    Goods getall(Integer id);

}
