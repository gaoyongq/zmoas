package com.zm.mall.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 文件下载工具类
 * Created by Bochao on 2017/2/17.
 */
@Component
@PropertySource("classpath:urlPrefix.properties")
public class DownloadUtils {
    @Autowired
    private Environment env;

    @Value("#{systemProperties['webapp.absUrl']}")
    private String webAppAbsUrl;

    /**
     * 根据文件的相对路径返回文件的字节数组
     * @param relUrl 文件的相对路径。相对于url.upload
     * @return 目标文件的字节数组
     * @throws java.io.IOException
     */
    public byte[] getFileBytes(String relUrl) throws IOException {

        String prefix = env.getProperty("url.upload");

        if (prefix == null || prefix.equals("")) {
            prefix = webAppAbsUrl + "Upload/";
        }

        if (relUrl == null || relUrl.equals("")) {
            return null;
        }

        String filename = prefix + relUrl;

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;

        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }


    public void showImageByProportion(ServletOutputStream outputStream, String relUrl, String proportion) throws Exception {
        String prefix = env.getProperty("url.upload");
        if (prefix == null || prefix.equals("")) {
            prefix = webAppAbsUrl + "Upload/";
        }
        if (relUrl == null || relUrl.equals("")) {
            return;
        }
        String filename = prefix + "/" + relUrl;

        //读入图片文件
        File srcImg = new File(filename);
        if (!srcImg.exists()) {
            //throw new FileNotFoundException(filename);
            srcImg = new File(webAppAbsUrl + "/images/babImg.png");
        }

        BufferedImage image = ImageIO.read(srcImg);

        //图片比例
        double w = image.getWidth();
        double h = image.getHeight();

        if (proportion != null) {
            String[] proportions = proportion.split(":");
            try {
                w = Double.valueOf(proportions[0]);
                h = Double.valueOf(proportions[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //原图片宽高
        double width = image.getWidth();
        double height = image.getHeight();

        //裁剪起始坐标
        double x = 0;
        double y = 0;

        //计算裁剪所需参数
        if (width / height > w / h) {
            width = w / h * image.getHeight();
            x = Math.abs(image.getWidth() - width) / 2;
        }
        if (width / height < w / h) {
            height = h / w * image.getWidth();
            y = Math.abs(image.getHeight() - height) / 2;
        }

        //调用工具类裁剪
        ImageUtil imageUtil = new ImageUtil();
        imageUtil.cutImage(srcImg, outputStream,
                (int)Math.round(x) , (int)Math.round(y),
                (int)Math.round(width), (int)Math.round(height));
    }

    /**
     * 图片缩略图
     * @param outputStream
     * @param relUrl 图片位置的相对路径。相对于urlPrefix.properties配置文件的url.upload值
     * @param height 高度（像素）
     * @param width 宽度（像素）
     * @param force 忽略比例，强制宽高。1代表强制，0代表不强制
     * @throws Exception
     */
    public void imageThumbnail(
            ServletOutputStream outputStream,
            String relUrl,
            Integer height,
            Integer width,
            Integer force) throws Exception {

        String prefix = env.getProperty("url.upload");
        if (prefix == null || prefix.equals("")) {
            prefix = webAppAbsUrl + "Upload/";
        }
        if (relUrl == null || relUrl.equals("")) {
            return;
        }
        String filename = prefix + "/" + relUrl;

        //读入图片文件
        File srcImg = new File(filename);
        if (!srcImg.exists()) {
            throw new FileNotFoundException(filename);
        }

        BufferedImage image = ImageIO.read(srcImg);

        //原图片宽高
        if (width == null) {
            width = image.getWidth();
        }
        if (height == null) {
            height = image.getHeight();
        }

        if (force == null || force.equals(0)) {
            //保持原始比例
            Thumbnails.of(srcImg).size(height, width).toOutputStream(outputStream);
        } else if (force.equals(1)) {
            //强制宽高，不保持原始比例
            Thumbnails.of(srcImg).forceSize(height, width).toOutputStream(outputStream);
        }
    }
}
