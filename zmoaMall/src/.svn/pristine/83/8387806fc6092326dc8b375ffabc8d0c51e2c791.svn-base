package com.zm.mall;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author
 * @create 2016-12-30 9:50
 */
public class WechatUtil {

    private final static int CONNECT_TIMEOUT = 500000; // in milliseconds
    private final static String DEFAULT_ENCODING = "UTF-8";

    public static String postData(String urlStr, String data){
        return postData(urlStr, data, null);
    }

    public static String postData(String urlStr, String data, String contentType){
        BufferedReader reader = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if(contentType != null)
                conn.setRequestProperty("content-type", contentType);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if(data == null)
                data = "";
            writer.write(data);
            writer.flush();
            writer.close();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (ConnectException e) {
            System.out.println("连接超时！");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (reader != null)
                    reader.close();
                conn.disconnect();
            } catch (IOException e) {
            }
        }
        return null;
    }

}
