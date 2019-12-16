package com.hongyuan.warehouse.services;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
public class TencentCloudService {

   private String SecretId="AKIDa8Ypl56HEySlvvnEqLq6HMFyTidPDYou";
    private String SecretKey="2pMUNGF3bu0qY4UyIXo4qmagkljcOjx5";
    private COSCredentials cred = new BasicCOSCredentials(SecretId, SecretKey);
    private  Region region = new Region("ap-shanghai");
    private  String bucketName = "hongyuan-1300363160";
    private  ClientConfig clientConfig = new ClientConfig(region);
    // 3 生成 cos 客户端。
    private  COSClient cosClient = new COSClient(cred, clientConfig);
    private String url = "https://hongyuan-1300363160.cos.ap-shanghai.myqcloud.com/";
    public String upload(File file, String name){
        cosClient.putObject(new PutObjectRequest(bucketName,name,file));
        return url+name;
    }
}
