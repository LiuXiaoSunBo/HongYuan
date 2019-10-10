package com.hongyuan.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongyuan.warehouse.pojo.Warehouse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WareHouseMapper extends BaseMapper<Warehouse> {
}
