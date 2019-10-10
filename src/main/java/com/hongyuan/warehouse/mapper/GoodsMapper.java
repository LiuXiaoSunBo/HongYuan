package com.hongyuan.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongyuan.warehouse.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface GoodsMapper extends BaseMapper<Goods> {
}
