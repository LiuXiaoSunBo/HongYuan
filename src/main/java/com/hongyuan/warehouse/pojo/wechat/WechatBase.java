package com.hongyuan.warehouse.pojo.wechat;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WechatBase {
    String ToUserName;
    String FromUserName;
    String CreateTime;
    String MsgType;
}
