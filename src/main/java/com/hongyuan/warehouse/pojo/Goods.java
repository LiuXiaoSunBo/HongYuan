package com.hongyuan.warehouse.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class Goods {
    @TableId
    Integer goodsid;
    String companyname;
    String  goodsname;
    String goodsmaterial;
    String goodsgel;
    String goodsproductions;
    String goodsshipment;
    String goodsshipmentdata;
    String mould;
    String imgurl;
    String truenumber;
    Integer isover;
    @TableField(exist = false)
    Warehouse warehouse;
}
