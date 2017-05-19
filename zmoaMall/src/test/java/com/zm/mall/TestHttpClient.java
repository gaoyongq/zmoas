package com.zm.mall;

import com.zm.mall.domain.system.Ad;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;

/**
 * Created by Bochao on 2017/4/21.
 */
public class TestHttpClient {

    public void remoteSaveAd(String url, byte[] bytes, Ad ad) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", bytes)
                .addTextBody("name", ad.getName())
                .addTextBody("url", ad.getUrl())
                .addTextBody("status", String.valueOf(ad.getStatus()))
                .build();
        httpPost.setEntity(httpEntity);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            HttpEntity responseEntity = httpResponse.getEntity();
            //HttpClient自带的工具类读取返回数据
            System.out.println(EntityUtils.toString(responseEntity));
            System.out.println(responseEntity.getContent());
            // 打印响应长度
            System.out.println("Response content length: " + responseEntity.getContentLength());
            // 打印响应内容
            System.out.println(EntityUtils.toString(responseEntity, Charset.forName("UTF-8")));

        } else {
            System.out.println("请求失败！错误码：" + statusCode);
        }

        EntityUtils.consume(httpEntity);
        httpResponse.close();
        httpClient.close();
    }

    public static void main(String[] args) {
        //String relUrl = "/Upload/Shop/Images/ProductThumbs/20150506/T207x270_201505061540191168262.jpg";
        /*String relUrl = "Upload/Shop/Images/ProductThumbs/20150506/T207x270_201505061540191168262.jpg";
        if (relUrl.startsWith("/")) {
            relUrl = relUrl.replaceFirst("/", "");
        }
        System.out.println(relUrl);*/

        String relUrl = "Upload/Shop/Images/ProductThumbs/20150506/T207x270_201505061540191168262.jpg";
        relUrl = relUrl.replaceFirst("/?", "");
        System.out.println(relUrl);
        File srcImg = new File("D:/Pictures/1111.jpg");
        if (srcImg.exists()) System.out.println("文件存在");
    }
}
