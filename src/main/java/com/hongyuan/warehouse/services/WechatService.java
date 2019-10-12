package com.hongyuan.warehouse.services;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import com.hongyuan.warehouse.pojo.wechat.WechatBase;
import com.hongyuan.warehouse.pojo.wechat.WechatEvent;
import com.hongyuan.warehouse.pojo.wechat.WechatTalkMessage;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXB;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

@Service
public class WechatService {
    private String Event_subscribe = "subscribe";
    private String Event_unsubscribe = "unsubscribe";
    private String appid="wx6787a9dcb670047b";
    private String appsecret="030fb01ab88fe1c7404bbb8e2826ebca";
    private String acusstoken="";
    public String talk(WechatTalkMessage wct, String msg) throws IOException {
        change(wct, wct.getToUserName());
        wct.setContent(msg);
        Writer writer = new StringWriter();
        JAXB.marshal(wct, writer);
        return writer.toString();
    }

    public String event(HttpServletRequest request) throws IOException, URISyntaxException {
        InputStream inputStream = inpustStreamtoStringtoinputstream(request.getInputStream());
        WechatBase wcb = JAXB.unmarshal(inputStream, WechatBase.class);
        inputStream.reset();
        System.out.println(wcb.getMsgType());
        if (wcb.getMsgType().equals("event")) {
            WechatEvent wct = JAXB.unmarshal(inputStream, WechatEvent.class);
            if (wct.equals(Event_subscribe)) {
                WechatTalkMessage wechatTalkMessage = new WechatTalkMessage();
                wechatTalkMessage.setFromUserName(wct.getToUserName());
                wechatTalkMessage.setToUserName(wcb.getFromUserName());
                return talk(wechatTalkMessage, "感谢您关注宏源电子铭板公众号！\n 如有业务需求请回复" + '"' + "业务" + '"');
            }
        } else if (wcb.getMsgType().equals("text")) {
            WechatTalkMessage wechatTalkMessage = JAXB.unmarshal(inputStream, WechatTalkMessage.class);
            if (wechatTalkMessage.getContent().contains("业务")) {
                return talk(wechatTalkMessage, "QQ:1152650793\n 微信:kai1152650793 \n 田生");
            }
        }
        return "";
    }

    public void change(WechatTalkMessage wct, String one) {
        wct.setToUserName(wct.getFromUserName());
        wct.setFromUserName(one);
    }

    public InputStream inpustStreamtoStringtoinputstream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        is.close();
        baos.close();
        return new ByteArrayInputStream(baos.toString().getBytes());
    }

    @Scheduled(fixedRate = 1000*60*60*2)
    public void getAccess_token() throws IOException {
        System.out.println("来拿accesstoken啦");
        String getaccesstoken = sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret);
        JSONObject jsonObject = JSONObject.parseObject(getaccesstoken);
        this.acusstoken=jsonObject.getString("access_token");
    }

    public void setView() throws IOException {
        String data= "{\n" +
                "\t\"button\": [{\n" +
                "\t\t\"type\": \"view\",\n" +
                "\t\t\"name\": \"货物\",\n" +
                "\t\t\"url\": \"http://122.51.16.101/hongyuan/Goods.html/\"\n" +
                "\t}]\n" +
                "}";
        System.out.println(new String(data.getBytes(),"UTF-8"));
        System.out.println(data);
        String sendpost = sendpost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+this.acusstoken,data);
        System.out.println("accesstoken="+acusstoken);
        System.out.println(sendpost);

        System.out.println(JSONObject.parseObject(sendpost).getInteger("errcode"));
        System.out.println(sendpost);
        if (JSONObject.parseObject(sendpost).getInteger("errcode")==0){
            System.out.println("设置成功!");
        }else {
            throw new Error("菜单出现错误!");
        }
    }

    private String sendpost(String url,String data) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type","applicaiton/json;charset=UTF-8");
        httpPost.setEntity(new StringEntity(data,Charset.forName("UTF-8")));
        return EntityUtils.toString(httpClient.execute(httpPost).getEntity());
    }
    private String sendGet(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpGet get = new HttpGet(url);
        return  EntityUtils.toString(httpClient.execute(get).getEntity());
    }
}
