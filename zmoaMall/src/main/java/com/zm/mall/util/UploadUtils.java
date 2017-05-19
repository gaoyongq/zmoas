package com.zm.mall.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
public class UploadUtils {
    @Autowired
    private Environment env;

    @Value("#{systemProperties['webapp.absUrl']}")
    private String webAppAbsUrl;

    /**
     * 文件上传
     * @param file Spring的MultipartFile类
     * @param mediaType 文件类型。e.g. picture, video..
     * @param module 模块。e.g. employee, user, department..
     * @return 图片上传位置的相对路径。相对于urlPrefix.properties配置文件的url.upload值
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

    /**
     * 远程文件上传
     * @param file Spring的MultipartFile类
     * @param mediaType 文件类型。e.g. picture, video..
     * @param module 模块。e.g. employee, user, department..
     * @param remoteUrl 远程服务器的上传接口Url e.g. http://192.168.1.200:8081/imageUpload.action
     * @return 图片上传位置的相对路径。相对于urlPrefix.properties配置文件的url.upload值
     * @throws java.io.IOException
     */
    public String upload(MultipartFile file, String mediaType, String module, String remoteUrl) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        byte[] bytes = file.getBytes();
        String[] nameArr = file.getOriginalFilename().split("\\.");
        String fileType = nameArr[nameArr.length-1];
        return remoteSave(remoteUrl, bytes, fileType, mediaType, module);
    }

    /**
     * HttpClient跨域请求
     * @param url 远程服务器的上传接口Url e.g. http://192.168.1.200:8081/imageUpload.action
     * @param bytes 文件字节数组
     * @param fileType 文件后缀名
     * @param mediaType 媒体类型。用于分类存储
     * @param module 文件所属业务模块。用于分类存储
     * @return 图片上传位置的相对路径。相对于urlPrefix.properties配置文件的url.upload值
     * @throws IOException
     */
    private String remoteSave(String url, byte[] bytes, String fileType, String mediaType, String module) throws IOException {
        String relUrl = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", bytes, ContentType.DEFAULT_BINARY, fileType)
                .addTextBody("mediaType", mediaType)
                .addTextBody("module", module)
                .build();
        httpPost.setEntity(httpEntity);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            HttpEntity responseEntity = httpResponse.getEntity();
            relUrl = EntityUtils.toString(responseEntity);
        } else {
            System.out.println("请求失败！错误码：" + statusCode);
        }

        EntityUtils.consume(httpEntity);
        httpResponse.close();
        httpClient.close();

        return relUrl;
    }
}
