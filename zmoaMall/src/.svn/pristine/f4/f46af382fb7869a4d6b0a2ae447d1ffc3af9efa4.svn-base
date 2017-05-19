package com.zm.mall.web.system;

import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.AdService;
import com.zm.mall.constant.ConfigConstants;
import com.zm.mall.domain.system.Ad;
import com.zm.mall.domain.system.EasyUIPaginationAd;
import com.zm.mall.domain.system.User;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import com.zm.mall.domain.system.systemCode.EasyUIResult;
import com.zm.mall.util.DownloadUtils;
import com.zm.mall.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商家广告
 * Created by Bochao on 2017/3/17.
 */
@Controller
@RequestMapping("/business/ad")
public class AdController {
    @Resource
    private AdService adService;
    @Resource
    private UploadUtils uploadUtils;
    @Resource
    private DownloadUtils downloadUtils;

    /**
     * 分页查询广告列表
     * @param request
     * @param ad
     * @return
     */
    @RequestMapping("/getAdList")
    @ResponseBody
    public EasyUIListResult getAdList(HttpServletRequest request, EasyUIPaginationAd ad) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        ad.setPlatformId(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            ad.setShopId(userResult.getShopId());
            ad.setPlatformId(userResult.getParentPluteformId());
        }
        return adService.getAdList(ad);
    }

    /**
     * 查询所有广告列表
     * @param request
     * @param ad
     * @return
     */
    @RequestMapping("/getAds")
    @ResponseBody
    public List<Ad> getAds(HttpServletRequest request, Ad ad) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        ad.setPlatformId(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            ad.setShopId(userResult.getShopId());
            ad.setPlatformId(userResult.getParentPluteformId());
        }
        return adService.getAds(ad);
    }

    /**
     * 添加广告
     * @param request
     * @param file
     * @param ad
     * @return
     */
    @RequestMapping("/addAd")
    @ResponseBody
    public EasyUIResult addAd(HttpServletRequest request, MultipartFile file, Ad ad) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        ad.setPlatformId(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            ad.setShopId(userResult.getShopId());
            ad.setPlatformId(userResult.getParentPluteformId());
        }
        ad.setType(1);
        User user = new User();
        user.setId(userResult.getId());
        ad.setCreateUser(user);
        ad.setUpdateUser(user);

        try {
            //将图片保存到远程图片服务器
            String relUrl = uploadUtils.upload(file, "picture", "ad");
            ad.setImgUrl(relUrl);
        } catch (IOException e) {
            e.printStackTrace();
            ad.setImgUrl("");
        }

        try {
            adService.addAd(ad);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }

        return new EasyUIResult();
    }

    @RequestMapping("/addAdImage")
    @ResponseBody
    public String addAdImage(MultipartFile file, String mediaType, String module) {
        String relUrl = null;
        try {
            relUrl = uploadUtils.upload(file, mediaType, module);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relUrl;
    }

    /**
     * 修改广告
     * @param request
     * @param file
     * @param ad
     * @return
     */
    @RequestMapping("/updateAd")
    @ResponseBody
    public EasyUIResult updateAd(HttpServletRequest request, MultipartFile file, Ad ad) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        ad.setPlatformId(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            ad.setShopId(userResult.getShopId());
            ad.setPlatformId(userResult.getParentPluteformId());
        }
        ad.setType(1);
        User user = new User();
        user.setId(userResult.getId());
        ad.setUpdateUser(user);
        try {
            String relUrl = uploadUtils.upload(file, "picture", "ad");
            if (relUrl != null) ad.setImgUrl(relUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            adService.updateAd(ad);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }

        return new EasyUIResult();
    }

    /**
     * 修改广告状态
     * @param request
     * @param ad
     * @return
     */
    @RequestMapping("/updateAdStatus")
    @ResponseBody
    public EasyUIResult updateAdStatus(HttpServletRequest request, Ad ad) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        ad.setPlatformId(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            ad.setShopId(userResult.getShopId());
            ad.setPlatformId(userResult.getParentPluteformId());
        }
        User updateUser = new User();
        updateUser.setId(userResult.getId());
        ad.setUpdateUser(updateUser);
        try {
            adService.updateAdStatus(ad);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }

        return new EasyUIResult();
    }

    @RequestMapping("/showImageByProportion")
    public void showImage(HttpServletResponse response, String relUrl) {
        try {
            byte[] bytes = downloadUtils.getFileBytes(relUrl);
            if (bytes != null) response.getOutputStream().write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按比例展示图片
     * @param response
     * @param relUrl
     * @param proportion 比例。例：16:9
     */
    @RequestMapping("/showImage")//showImageByProportion
    public void showImageByProportion(HttpServletResponse response, String relUrl, String proportion) {
        try {
            downloadUtils.showImageByProportion(response.getOutputStream(), relUrl, proportion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按比例展示图片
     * @param response
     * @param relUrl
     * @param proportion 比例。例：16:9
     */
    @RequestMapping("/image/{mediaType}/{module}/{year}/{month}/{day}/{fileName:\\d+\\.\\w+}")//Picture/Ad/2017/04/21/1492738864910.jpg
    public void image(HttpServletResponse response,
                      @PathVariable("mediaType") String mediaType,
                      @PathVariable("module") String module,
                      @PathVariable("year") String year,
                      @PathVariable("month") String month,
                      @PathVariable("day") String day,
                      @PathVariable("fileName") String fileName) {
        String relUrl = mediaType + "/" + module + "/" + year + "/" + month + "/" + day + "/" + fileName;
        try {
            downloadUtils.showImageByProportion(response.getOutputStream(), relUrl, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*-----------------------页面跳转------------------------*/

    @RequestMapping("/list")
    public String list() {
        return "business/advertisement/adList";
    }

    @RequestMapping("/addAdDlg")
    public String addAdDlg() {
        return "business/advertisement/addAdDlg";
    }

    @RequestMapping("/updateAdDlg")
    public String updateAdDlg(Model model, Long id, String platformId) {
        Ad ad = adService.getAdById(id, platformId);
        model.addAttribute("ad", ad);
        return "business/advertisement/updateAdDlg";
    }
}
