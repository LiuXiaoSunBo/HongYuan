package com.hongyuan.warehouse.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Login {
    @TableId
    private String openid;
}
