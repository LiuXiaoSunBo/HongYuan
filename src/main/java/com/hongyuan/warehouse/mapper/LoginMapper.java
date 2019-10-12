package com.hongyuan.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongyuan.warehouse.pojo.Login;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginMapper extends BaseMapper<Login> {
}
