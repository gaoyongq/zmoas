package com.zm.mall.web.system;

import com.alibaba.fastjson.JSONObject;
import com.zm.mall.util.DownloadUtils;
import com.zm.mall.util.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片
 * Created by Bochao on 2017/4/22.
 */
@Controller
public class ImageController {
    @Resource
    private UploadUtils uploadUtils;
    @Resource
    private DownloadUtils downloadUtils;

    /**
     * 按比例展示图片
     * @param response
     * @param proportion 比例。例：16:9
     */
    @RequestMapping("/image/{module}/{year}/{month}/{day}/{fileName:\\d+\\.\\w+}")
    public void image(HttpServletResponse response,
                      @PathVariable("module") String module,
                      @PathVariable("year") String year,
                      @PathVariable("month") String month,
                      @PathVariable("day") String day,
                      @PathVariable("fileName") String fileName,
                      String proportion) {
        String relUrl = module + "/" + year + "/" + month + "/" + day + "/" + fileName;
        try {
            downloadUtils.showImageByProportion(response.getOutputStream(), relUrl, proportion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按比例展示图片
     * @param response
     * @param relUrl 图片的相对路径
     * @param proportion 比例。例：16:9
     */
    @RequestMapping("/showImage")
    public void showImageByProportion(HttpServletResponse response, String relUrl, String proportion) {
        try {
            downloadUtils.showImageByProportion(response.getOutputStream(), relUrl, proportion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片缩略图展示
     * @param response
     * @param relUrl 图片的相对路径
     * @param height 图片高度
     * @param width 图片宽度
     * @param force 忽略比例，强制宽高。1代表不按比例，0代表按比例
     */
    @RequestMapping("/imageThumbnail")
    public void imageThumbnail(HttpServletResponse response, String relUrl, Integer height, Integer width, Integer force) {
        try {
            downloadUtils.imageThumbnail(response.getOutputStream(), relUrl, height, width, force);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 为其他应用使用HttpClient远程上传功能提供接口
     * @param file 文件表单名
     * @param mediaType 媒体类型。用于分类存储
     * @param module 文件所属业务模块。用于分类存储
     * @return 文件所在位置的相对路径
     */
    @RequestMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(MultipartFile file, String mediaType, String module) {
        String relUrl = null;
        try {
            relUrl = uploadUtils.upload(file, mediaType, module);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relUrl;
    }

    /**
     * UEditor自定义图片上传Action
     * @param file 提交的图片表单名称
     * @return UEditor用于回显图片的数据
     */
    @RequestMapping("/ueditorUpload")
    @ResponseBody
    public String upload(MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        try {
            String relUrl = uploadUtils.upload(file, "picture", "ueditor");
            //UEditor规定所需字段
            jsonObject.put("state", "SUCCESS");
            jsonObject.put("url", relUrl);
            String[] splits = relUrl.split("/");
            String fileName = splits[splits.length-1];
            jsonObject.put("title", fileName);
            jsonObject.put("original", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    /**
     * 图片应用的图片上传插件WebUploader所需的Controller
     * @throws IOException
     */
    @RequestMapping("webUploader/imageUpload")
    @ResponseBody
    public String imageUpload(MultipartFile file) throws IOException {
        String imageUrl = uploadUtils.upload(file, "picture", "product");
        return imageUrl;
    }


}
