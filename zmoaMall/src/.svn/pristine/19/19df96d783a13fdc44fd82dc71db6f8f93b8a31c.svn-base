package com.zm.mall.util;

import com.zm.mall.domain.system.Ad;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件上传工具类
 * Created by Bochao on 2017/2/17.
 */
@Component
@PropertySource("classpath:urlPrefix.properties")
public class RemoteUploadUtils {
    @Autowired
    private Environment env;

    @Value("#{systemProperties['webapp.absUrl']}")
    private String webAppAbsUrl;

    /**
     * 文件上传
     * @param file Spring的MultipartFile类
     * @param mediaType 文件类型。e.g. picture, video..
     * @param module 模块。e.g. employee, user, department..
     * @return 返回文件上传的相对路径。相对于url.upload
     * @throws java.io.IOException
     */
    public String upload(MultipartFile file, String mediaType, String module) throws IOException {
        //设置文件存放位置
        String uploadUrl = env.getProperty("url.upload");
        if (uploadUrl == null || uploadUrl.equals("")) {
            uploadUrl = webAppAbsUrl + "Upload/";
        }

        if (file == null || file.isEmpty()) {
            return null;
        }
        byte[] bytes = file.getBytes();
        String[] nameArr = file.getOriginalFilename().split("\\.");
        String fileType = nameArr[nameArr.length-1];
        SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy/MM/dd/");
        String dir = dateFormat.format(new Date());
        String fileName = System.currentTimeMillis() + "." + fileType;
        // 文件的相对路径名
        String relUrl = mediaType + "/" + module + dir + fileName;
        // 文件的绝对路径名
        String absUrl = uploadUrl + "/" + relUrl;

        File path = new File(uploadUrl + "/" + mediaType + "/" + module + dir);
        if (!path.exists()) {
            path.mkdirs();
        }
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(absUrl));
        out.write(bytes);
        out.flush();
        out.close();

        return relUrl;
    }

    public void remoteSave(String url, byte[] bytes) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", bytes)
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
}
