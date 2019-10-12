package com.hongyuan.warehouse.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@Repository
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    @TableId
    Integer goodsid;
    String time;
    Integer goodsnumber;
}
