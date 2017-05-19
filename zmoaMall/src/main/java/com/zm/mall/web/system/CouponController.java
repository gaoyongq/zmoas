package com.zm.mall.web.system;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.system.CouponService;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.system.coupon.*;
import com.zm.mall.domain.system.systemCode.EasyUIResult;
import com.zm.mall.util.DownloadUtils;
import com.zm.mall.util.UploadUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 优惠券模块
 * Created by Bochao on 2017/2/27.
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;
    @Resource
    private UploadUtils uploadUtils;
    @Resource
    private DownloadUtils downloadUtils;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /*-------- 活动分类模块 Start --------*/

    /**
     * 添加活动分类
     * @param request
     * @param couponActivityCategory
     * @return
     */
    @RequestMapping("/addCouponActivityCategory")
    @ResponseBody
    public EasyUIResult addCouponActivityCategory(
            HttpServletRequest request, CouponActivityCategory couponActivityCategory) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return new EasyUIResult("权限不足");
        }

        if (couponActivityCategory.getName() == null) {
            return new EasyUIResult("缺少必须参数");
        }
        couponActivityCategory.setPlatformId(userResult.getPluteformid());
        int i = couponService.addCouponActivityCategory(couponActivityCategory);
        if (i == 0) {
            return new EasyUIResult("增加失败");
        }
        return new EasyUIResult();
    }

    /**
     * 获取活动分类List
     * @param request
     * @return
     */
    @RequestMapping("/getCouponActivityCategoryList")
    @ResponseBody
    public List<CouponActivityCategory> getCouponActivityCategoryList(HttpServletRequest request) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        return couponService.getCouponActivityCategoryList(userResult.getPluteformid());
    }

    /**
     * 获取活动分类EasyUIListResult
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getCouponActivityCategories")
    @ResponseBody
    public EasyUIListResult getCouponActivityCategories(
            HttpServletRequest request, Long page, Long rows) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        if (page == null || page < 1) {
            page = 1L;
        }
        if (rows == null) {
            rows = 10L;
        }
        return couponService.getCouponActivityCategories(userResult.getPluteformid(), page, rows);
    }
    /*-------- 活动分类模块 End --------*/

    /*-------- 活动模块 Start --------*/

    /**
     * 添加活动
     * @param request
     * @param couponActivity
     * @param file
     * @return
     */
    @RequestMapping("/addCouponActivity")
    @ResponseBody
    public EasyUIResult addCouponActivity(
            HttpServletRequest request, CouponActivity couponActivity, MultipartFile file) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return new EasyUIResult("权限不足");
        }
        if (couponActivity.getName() == null || couponActivity.getCategory() == null ||
                couponActivity.getStartTime() == null || couponActivity.getEndTime() == null) {
            return new EasyUIResult("缺少必须参数");
        }
        try {
            String relUrl = uploadUtils.upload(file, "picture", "couponActivity");
            couponActivity.setImgUrl(relUrl);
        } catch (IOException e) {
            e.printStackTrace();
            couponActivity.setImgUrl("");
        }
        couponActivity.setPlatformId(userResult.getPluteformid());
        int i = couponService.addCouponActivity(couponActivity);
        if (i == 0) {
            return new EasyUIResult("增加失败");
        }
        return new EasyUIResult();
    }

    /**
     * 获取活动List
     * @param request
     * @return
     */
    @RequestMapping("/getCouponActivityList")
    @ResponseBody
    public List<CouponActivity> getCouponActivityList(HttpServletRequest request) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        return couponService.getCouponActivityList(userResult.getPluteformid());
    }

    /**
     * 获取活动EasyUIListResult
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getCouponActivities")
    @ResponseBody
    public EasyUIListResult getCouponActivities(
            HttpServletRequest request, Long page, Long rows) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        if (page == null || page < 1) {
            page = 1L;
        }
        if (rows == null) {
            rows = 10L;
        }
        return couponService.getCouponActivities(userResult.getPluteformid(), page, rows);
    }

    /**
     * 更新活动状态
     * @param request
     * @param activityId
     * @param status
     * @return
     */
    @RequestMapping("/updateActivityStatus")
    @ResponseBody
    public EasyUIResult updateActivityStatus(
            HttpServletRequest request, Long activityId, int status) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        int i = couponService.updateActivityStatus(userResult.getPluteformid(), activityId, status);
        return new EasyUIResult();
    }

    /*-------- 活动模块 End --------*/


    /*-------- 优惠券模块 Start --------*/

    /**
     * 添加优惠券源
     * @param request
     * @param file
     * @param productIds
     * @param couponSource
     * @return
     */
    @RequestMapping("/addCouponSource")
    @ResponseBody
    public EasyUIResult addCouponSource(
            HttpServletRequest request, MultipartFile file,
            String[] productIds, CouponSource couponSource) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return new EasyUIResult("权限不足");
        }
        if (couponSource.getName() == null || couponSource.getActivity() == null
                || couponSource.getFaceValue() == 0 || couponSource.getTotalNumber() == 0
                || couponSource.getNeedPoint() == 0) {
            return new EasyUIResult("缺少必须参数");
        }

        List<Long> prodIds = new ArrayList<Long>();
        List<Long> prodCatIds = new ArrayList<Long>();

        if (productIds != null) {
            for (String productId : productIds) {
                if (productId.startsWith("prod")) {
                    prodIds.add(Long.valueOf(productId.replaceAll("prod", "")));
                } else {
                    prodCatIds.add(Long.valueOf(productId));
                }
            }
        }

        couponSource.setProdIds(prodIds);
        couponSource.setProdCatIds(prodCatIds);

        try {
            String relUrl = uploadUtils.upload(file, "picture", "couponSource");
            couponSource.setImgUrl(relUrl);
        } catch (IOException e) {
            e.printStackTrace();
            couponSource.setImgUrl("");
        }
        couponSource.setPlatformId(userResult.getPluteformid());
        couponService.addCouponSource(couponSource);

        EasyUIResult easyUIResult = new EasyUIResult();
        easyUIResult.setData(couponSource.getId()+"");
        return easyUIResult;
    }

    /**
     * 获取优惠券源EasyUIListResult
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getCouponSources")
    @ResponseBody
    public EasyUIListResult getCouponSources(
            HttpServletRequest request, Long page, Long rows) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        if (page == null || page < 1) {
            page = 1L;
        }
        if (rows == null) {
            rows = 10L;
        }
        return couponService.getCouponSources(userResult.getPluteformid(), page, rows);
    }

    /**
     * 更新优惠券源状态
     * @param request
     * @param couponId
     * @param status
     * @return
     */
    @RequestMapping("/updateCouponStatus")
    @ResponseBody
    public EasyUIResult updateCouponStatus(
            HttpServletRequest request, Long couponId, int status) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        int i = couponService.updateCouponSourceStatus(userResult.getPluteformid(), couponId, status);
        return new EasyUIResult();
    }

    /**
     * 获取已发放的优惠券EasyUIListResult
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getUserCoupons")
    @ResponseBody
    public EasyUIListResult getUserCoupons(
            HttpServletRequest request, Long page, Long rows) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }

        return couponService.getUserCoupons(userResult.getPluteformid(), page, rows);
    }
    /*-------- 优惠券模块 End --------*/


    /*-------- 优惠券规则模块 Start --------*/

    /**
     * 添加优惠券规则
     * @param request
     * @param couponRule
     * @return
     */
    @RequestMapping("/addCouponRule")
    @ResponseBody
    public EasyUIResult addCouponRule(HttpServletRequest request, CouponRule couponRule) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return new EasyUIResult("权限不足");
        }
        couponRule.setPlatformId(userResult.getPluteformid());
        int i = couponService.addCouponRule(couponRule);
        return new EasyUIResult();
    }

    /**
     * ！！！存在Bug未解决-优惠券码表id是写死的
     * 获取优惠券规则页面展现结果EasyUIListResult
     * @param request
     * @param couponSourceId
     * @return
     */
    @RequestMapping("/getCouponRuleResult")
    @ResponseBody
    public EasyUIListResult getCouponRuleResult(HttpServletRequest request, Long couponSourceId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        return couponService.getCouponRuleResult(userResult.getPluteformid(), 31L, couponSourceId);
    }
    /*-------- 优惠券规则模块 End --------*/


    /*-------- 关联商品模块 Start --------*/

    /**
     * 获取父分类下所有子分类，用于页面树形展现
     * @param request
     * @param parentId
     * @return
     */
    @RequestMapping("/getProductCategoriesByParentId")
    @ResponseBody
    public List<ProductCategory> getProductCategoriesByParentId(
            HttpServletRequest request, @RequestParam(value="id", required=false) Long parentId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        return couponService.getProductCategoriesByParentId(userResult.getPluteformid(), parentId);
    }
    /*-------- 关联商品模块 End --------*/

    /*-------- 优惠券发放用户 Start --------*/

    /**
     * 获取所有用户信息，用于向特定用户分发优惠券
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getUsers")
    @ResponseBody
    public EasyUIListResult getUsers(HttpServletRequest request, Long page, Long rows) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        return couponService.getUsers(userResult.getPluteformid(), page, rows);
    }

    /**
     * 向特定用户分发优惠券
     * @param request
     * @param couponSourceId
     * @param userIds
     * @return
     */
    @RequestMapping("/addCouponForUsers")
    @ResponseBody
    public EasyUIResult addCouponForUserId(
            HttpServletRequest request, Long couponSourceId, Long[] userIds) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return new EasyUIResult("权限不足");
        }
        if (couponSourceId == null || userIds == null) {
            return new EasyUIResult("缺少必须参数");
        }

        return couponService.addCouponForUsers(userResult, couponSourceId, userIds);
    }

    /**
     * 向预定义用户组分发优惠券
     * @param request
     * @param couponSourceId
     * @param userGroup
     * @param sendNumber
     * @return
     */
    @RequestMapping("/addCouponForUserGroup")
    @ResponseBody
    public EasyUIResult addCouponForUserGroup(
            HttpServletRequest request, Long couponSourceId, String userGroup, Long sendNumber) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return new EasyUIResult("权限不足");
        }
        if (couponSourceId == null || userGroup == null || sendNumber == null) {
            return new EasyUIResult("缺少必须参数");
        }
        EasyUIResult easyUIResult = couponService.addCouponForUserGroup(userResult, couponSourceId, userGroup, sendNumber);
        return easyUIResult;
    }
    /*-------- 优惠券发放用户 End --------*/


    /*-------- 优惠券兑换接口 Start --------*/
    @RequestMapping("/exchangeCoupon")
    @ResponseBody
    public ExchangeCouponResult exchangeCoupon(@RequestBody String param, ExchangeCouponDto exchangeCouponDto) {

        if (param.startsWith("{")) {
            exchangeCouponDto = JSONObject.parseObject(param, ExchangeCouponDto.class);
        }

        if (exchangeCouponDto.getUserId() == null || exchangeCouponDto.getPlatformId() == null || exchangeCouponDto.getCouponSourceId() == null) {
            return new ExchangeCouponResult("缺少必须参数");
        }
        if (exchangeCouponDto.getNumber() == null || exchangeCouponDto.getNumber() < 1) {
            exchangeCouponDto.setNumber(1);
        }

        try {
            return couponService.exchangeCoupon(exchangeCouponDto.getUserId(), exchangeCouponDto.getPlatformId(), exchangeCouponDto.getCouponSourceId(), exchangeCouponDto.getNumber());
        } catch (Exception e) {
            return new ExchangeCouponResult("发生异常：" + e.getMessage());
        }

    }

    /**
     * 查询用户拥有的优惠券接口
     */
    @RequestMapping("/getCouponsByUserId")
    @ResponseBody
    public String getCouponsByUserId(@RequestBody String param, UserCouponDto userCouponDto) {

        if (param.startsWith("{")) {
            userCouponDto = JSONObject.parseObject(param, UserCouponDto.class);
        }
        if (userCouponDto.getPlatformId() == null || userCouponDto.getStatus() == null
                || userCouponDto.getUserId() == null) {
            return "缺少必须参数";
        }
        if (userCouponDto.getPage() == null) {
            userCouponDto.setPage(1L);
        }
        if (userCouponDto.getRows() == null) {
            userCouponDto.setRows(10L);
        }
        if (userCouponDto.getPage() < 1) userCouponDto.setPage(1L);
        ExchangeCouponResult exchangeCouponResult = couponService.getCouponsByUserId(
                userCouponDto.getPlatformId(), userCouponDto.getUserId(),
                userCouponDto.getStatus(), userCouponDto.getPage(), userCouponDto.getRows());

        try {
            if (userCouponDto.getCallback() == null) {
                return MAPPER.writeValueAsString(exchangeCouponResult);
            } else {
                return userCouponDto.getCallback() + "(" + MAPPER.writeValueAsString(exchangeCouponResult) + ")";
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*@RequestMapping("/getCouponsByUserId")
    @ResponseBody
    public ExchangeCouponResult getCouponsByUserId(
            @RequestParam(value = "userId") String userIdStr,
            @RequestParam(value = "platformId") String platformId,
            @RequestParam(value = "page", required = false) String pageStr,
            @RequestParam(value = "rows", required = false) String rowsStr,
            @RequestParam(value = "status") Integer status) {
        Long userId = null;
        Long page = null;
        Long rows = null;
        if (userIdStr == null || platformId == null || status == null) {
            return new ExchangeCouponResult("缺少必须参数");
        }
        if (pageStr == null || pageStr.equals("null")) {
            pageStr = "1";
        }
        try {
            userId = Long.valueOf(userIdStr);
            page = Long .valueOf(pageStr);
            rows = Long .valueOf(rowsStr);
        } catch (NumberFormatException e) {
            return new ExchangeCouponResult("非法参数");
        }
        if (page < 1) page = 1L;
        ExchangeCouponResult exchangeCouponResult = couponService.getCouponsByUserId(platformId, userId, status, page, rows);

        return exchangeCouponResult;
    }*/

    /**
     * 通过活动id查询优惠券接口
     * @param param
     * @return
     */
    @RequestMapping(value="/getCouponSourcesByActivityId")
    @ResponseBody
    public ExchangeCouponResult getCouponSourcesByActivityId(
            @RequestBody String param,
            CouponActivityDto couponActivityDto) {
        if (param.startsWith("{")) {
            couponActivityDto = JSONObject.parseObject(param, CouponActivityDto.class);
        }
        if (couponActivityDto.getActivityId() == null || couponActivityDto.getPlatformId() == null) {
            return new ExchangeCouponResult("缺少必须参数");
        }
        try {
            ExchangeCouponResult exchangeCouponResult = new ExchangeCouponResult();
            List<CouponSource> couponSources = couponService.getCouponSourcesByActivityId(couponActivityDto.getPlatformId(), couponActivityDto.getActivityId());
            exchangeCouponResult.setData(couponSources);
            return exchangeCouponResult;
        } catch (Exception e) {
            return new ExchangeCouponResult("发生异常：" + e.getMessage());
        }
    }

    /**
     * 通过平台查询正在进行的活动接口
     * @param param
     * @param couponActivityDto
     * @return
     */
    @RequestMapping("/getCouponActivitiesInProgress")
    @ResponseBody
    public ExchangeCouponResult getCouponActivitiesInProgress(
            @RequestBody String param,
            CouponActivityDto couponActivityDto) {

        if (param.startsWith("{")) {
            couponActivityDto = JSONObject.parseObject(param, CouponActivityDto.class);
        }
        if (couponActivityDto.getPlatformId() == null) {
            return new ExchangeCouponResult("缺少必须参数");
        }

        try {
            List<CouponActivity> list = couponService.getCouponActivitiesInProgress(couponActivityDto.getPlatformId());
            ExchangeCouponResult result = new ExchangeCouponResult();
            result.setData(list);
            return result;
        } catch (Exception e) {
            return new ExchangeCouponResult("发生异常：" + e.getMessage());
        }
    }

    /**
     * 查询订单可用优惠券接口
     */
    @RequestMapping("/getUsableCouponsByProducts")
    @ResponseBody
    public String getUsableCouponsByProducts(@RequestBody String param, UsableCouponDto usableCoupon) {
        if (param.startsWith("{")) {
            usableCoupon = JSONObject.parseObject(param, UsableCouponDto.class);
        }
        if (usableCoupon.getPlatformId() == null || usableCoupon.getUserId() == null) {
            return "缺少必须参数";
        }
        //适配IOS
        if (usableCoupon.getProductList() != null && usableCoupon.getProductList().size() != 0) {
            StringBuffer str = new StringBuffer();
            for (CartItemDto cartItemDto : usableCoupon.getProductList()) {
                if (str.length() != 0) {
                    str.append(",");
                }
                str.append(cartItemDto.getProductId());
                str.append(":");
                str.append(cartItemDto.getProductNum());
                str.append(":");
                str.append(cartItemDto.getProductPrice());
            }
            usableCoupon.setProducts(str.toString());
        }
        ExchangeCouponResult exchangeCouponResult = couponService.getUsableCouponsByProducts(usableCoupon.getPlatformId(), usableCoupon.getUserId(), usableCoupon.getProducts(), usableCoupon.getPage(), usableCoupon.getRows());
        try {
            if (usableCoupon.getCallback() == null) {
                return MAPPER.writeValueAsString(exchangeCouponResult);
            } else {
                return usableCoupon.getCallback() + "(" + MAPPER.writeValueAsString(exchangeCouponResult) + ")";
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询优惠券可应用的产品接口
     */
    @RequestMapping("/getProductsByCouponId")
    @ResponseBody
    public EasyUIListResult<ProductInfo> getProductsByCouponId(@RequestBody String param, PageableCouponSource couponSource) {
        if (param.startsWith("{")) {
            couponSource = JSONObject.parseObject(param, PageableCouponSource.class);
        }
        EasyUIListResult<ProductInfo> result = null;
        try {
            result = couponService.getProductsByCouponId(couponSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 用户使用优惠券接口
     * @param param
     * @param userCouponDto
     * @return
     */
    @RequestMapping("/useCoupon")
    @ResponseBody
    public ExchangeCouponResult useCoupon(
            @RequestBody String param,
            UserCouponDto userCouponDto) {
        if (param.startsWith("{")) {
            userCouponDto = JSONObject.parseObject(param, UserCouponDto.class);
        }
        if (userCouponDto.getCouponId() == null || userCouponDto.getOrderId() == null || userCouponDto.getPlatformId() == null) {
            return new ExchangeCouponResult("缺少必须参数");
        }

        return couponService.useCoupon(userCouponDto.getPlatformId(), userCouponDto.getCouponId(), userCouponDto.getOrderId());
    }

    /*-------- 优惠券兑换接口 End --------*/



    /*-------- 页面跳转 Start --------*/
    @RequestMapping("/showImage")
    public void showImage(HttpServletResponse response, String relUrl) {
        try {
            byte[] bytes = downloadUtils.getFileBytes(relUrl);
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/couponActivityCategoryPage")
    public String couponCategoryPage(HttpServletRequest request, Model model) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        model.addAttribute("userId", userResult.getId());
        return "system/coupon/couponActivityCategory";
    }

    @RequestMapping("/couponSourceDetail")
    public String couponSourceDetail(HttpServletRequest request, Long id, Model model) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        List<Product> productList = couponService.getCouponProductsBySourceId(userResult.getPluteformid(), id);
        model.addAttribute("productList", productList);
        List<ProductCategory> productCatList = couponService.getCouponProductCatsBySourceId(userResult.getPluteformid(), id);
        model.addAttribute("productCatList", productCatList);
        String imgUrl = couponService.getCouponImgUrlById(userResult.getPluteformid(), id);
        model.addAttribute("imgUrl", imgUrl);
        return "system/coupon/couponSourceDetail";
    }

    @RequestMapping("/couponActivityDetail")
    public String couponActivityDetail(HttpServletRequest request, Long id, Model model) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String imgUrl = couponService.getActivityImgUrlById(userResult.getPluteformid(), id);
        model.addAttribute("imgUrl", imgUrl);
        String introduction = couponService.getActivityIntroductionById(userResult.getPluteformid(), id);
        model.addAttribute("introduction", introduction);
        return "system/coupon/couponActivityDetail";
    }

    @RequestMapping("/couponActivityPage")
    public String couponActivityPage(HttpServletRequest request, Model model) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        model.addAttribute("userId", userResult.getId());
        return "system/coupon/couponActivity";
    }

    @RequestMapping("/couponGrantPage")
    public String couponGrantPage() {
        return "system/coupon/couponGrant";
    }

    @RequestMapping("/couponStatusPage")
    public String couponStatusPage() {
        return "system/coupon/couponStatus";
    }

    @RequestMapping("/couponHistoryPage")
    public String couponHistoryPage() {
        return "system/coupon/couponHistory";
    }

    @RequestMapping("/couponImportPage")
    public String couponImportPage() {
        return "system/coupon/couponImport";
    }

    @RequestMapping("/couponManagePage")
    public String couponManagePage(HttpServletRequest request) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        return "system/coupon/couponManage";
    }

    @RequestMapping("/couponRule")
    public String couponRule(HttpServletRequest request, Model model, Long couponId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        CouponSource couponSource = couponService.getCouponSourceById(userResult.getPluteformid(), couponId);
        model.addAttribute("couponSource", couponSource);
        model.addAttribute("userId", userResult.getId());
        return "system/coupon/couponRule";
    }

    @RequestMapping("/couponActivityAddExchange")
    public String couponActivityAddExchange() {
        return "system/coupon/couponActivityAddExchange";
    }

    @RequestMapping("/couponActivityAddGive")
    public String couponActivityAddGive() {
        return "system/coupon/couponActivityAddGive";
    }
    @RequestMapping("/couponSendUserDlg")
    public String couponSendUserDlg(HttpServletRequest request, Model model, Long sourceId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        model.addAttribute("sourceId", sourceId);
        model.addAttribute("userId", userResult.getId());
        return "system/coupon/couponSendUserDlg";
    }
    @RequestMapping("/couponSendBatchDlg")
    public String couponSendBatchDlg(HttpServletRequest request, Model model, Long sourceId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        CouponSource couponSource = couponService.getCouponSourceById(userResult.getPluteformid(), sourceId);
        model.addAttribute("couponSource", couponSource);
        model.addAttribute("userId", userResult.getId());
        return "system/coupon/couponSendBatchDlg";
    }
    @RequestMapping("/couponActivityAddNormal")
    public String couponActivityAddNormal(HttpServletRequest request, Model model) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        model.addAttribute("userId", userResult.getId());
        return "system/coupon/couponActivityAddNormal";
    }
    /*-------- 页面跳转 End --------*/

    /**
     * 此方法用于日期的转换，如果未加，当页面日期格式转换错误，将报400错误，实际是因为此方法
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
