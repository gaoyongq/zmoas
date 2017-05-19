package com.zm.mall.web.business.shop;

import com.alibaba.fastjson.JSONObject;
import com.zm.mall.client.result.business.accounts.AccountUserResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.accountsUsers.UserListService;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.service.business.product.ProductInfoService;
import com.zm.mall.client.service.business.product.SupplierInfoService;
import com.zm.mall.client.service.business.shop.ShopInfoService;
import com.zm.mall.client.service.business.shopUser.ShopUserService;
import com.zm.mall.client.vo.business.accounts.AccountUserVo;
import com.zm.mall.client.vo.business.product.RegionInfoVo;
import com.zm.mall.constant.ConfigConstants;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.RegionInfo;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.domain.business.shop.PageableSupplierInfo;
import com.zm.mall.domain.business.shop.WeChatIdentity;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import com.zm.mall.domain.system.systemCode.EasyUIResult;
import com.zm.mall.service.SpaceBeanCopy;
import com.zm.mall.util.UploadUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 开店铺啦
 * Created by Bochao on 2017/4/13.
 */
@Controller
@RequestMapping("/business/shop")
public class ShopInfoController {

    @Resource
    private SupplierInfoService supplierInfoService;
    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private ShopInfoService shopInfoService;
    @Resource
    private UserListService userListService;
    @Resource
    private ShopUserService shopUserService;
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private UploadUtils uploadUtils;
    /**
     * 申请店铺填写信息页面
     * 回显地区等信息
     * @param model
     * @return
     */
    @RequestMapping("/shopInfo")
    public String shopInfo(Model model) {
        RegionInfoVo regionInfoVo = new RegionInfoVo();
        List<RegionInfo> regionInfos=productInfoService.selRegionInfoAll(regionInfoVo);
        model.addAttribute("regionInfos", regionInfos);
        model.addAttribute("brands",supplierInfoService.getAllBrands());
        return "business/shop/addShopInfo";
    }

    /**
     * 申请店铺填写信息页面-新版本
     * 回显地区等信息
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String register(Model model) {
        RegionInfoVo regionInfoVo = new RegionInfoVo();
        List<RegionInfo> regionInfos=productInfoService.selRegionInfoAll(regionInfoVo);
        model.addAttribute("regionInfos", regionInfos);
        model.addAttribute("brands",supplierInfoService.getAllBrands());
        return "business/shop/register";
    }

    /**
     * 添加用户申请店铺信息
     * @param session
     * @param supplierInfo
     * @return
     */
    @RequestMapping("/addShopInfo")
    public ModelAndView addShopInfo(HttpSession session, MultipartFile file, SupplierInfo supplierInfo) {
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        WeChatIdentity weChatIdentity = shopInfoService.getWeChatIdentityByUserId(userResult.getId());
        Users user = shopInfoService.getUserByWeChatIdentity(weChatIdentity);
        if (user == null) {
            return null;
        }
        try {
            String relUrl = uploadUtils.upload(file, "Picture", "shop/logo", ConfigConstants.REMOTE_IMAGE_UPLOAD_URL);
            supplierInfo.setLogo(relUrl);
        } catch (IOException e) {
            e.printStackTrace();
            supplierInfo.setLogo("");
        }
        supplierInfo.setShopName(supplierInfo.getName());
        supplierInfo.setUserId(user.getUserId().intValue());
        supplierInfo.setUserName(user.getUserName());
        supplierInfo.setPluteformid(weChatIdentity.getPlatformId());
        try {
            shopInfoService.addShopInfo(supplierInfo);
            return new ModelAndView("business/shop/success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * OA管理店铺页面，商城下所有店铺列表页
     * @param session
     * @param supplierInfo
     * @return
     */
    @RequestMapping("/shopInfoList")
    public String shopInfoList(HttpSession session, SupplierInfo supplierInfo) {
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        return "business/shop/shopInfoList";
    }

    /**
     * 审核店铺信息对话框页面
     * 回显店铺详细信息
     * @param session
     * @param model
     * @param supplierInfo
     * @return
     */
    @RequestMapping("/checkShopDlg")
    public String checkShopDlg(HttpSession session, Model model, SupplierInfo supplierInfo) {
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        if (userResult == null || supplierInfo == null || supplierInfo.getSupplierId() == null) {
            return null;
        }
        supplierInfo.setPluteformid(userResult.getPluteformid());
        SupplierInfo shopInfo = shopInfoService.getShopInfo(supplierInfo);
        model.addAttribute("shopInfo", shopInfo);
        return "business/shop/checkShopDlg";
    }

    /**
     * 审核店铺。
     * @param session
     * @param supplierInfo
     * @return
     */
    @RequestMapping("/checkShop")
    @ResponseBody
    public EasyUIResult checkShop(HttpSession session, SupplierInfo supplierInfo) {
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        if (userResult == null || supplierInfo == null || supplierInfo.getSupplierId() == null) {
            return null;
        }
        supplierInfo.setPluteformid(userResult.getPluteformid());
        SupplierInfo supplier = shopInfoService.getShopInfo(supplierInfo);
        supplierInfo.setUserId(supplier.getUserId());
        try {
            shopInfoService.checkShop(supplierInfo);
            return new EasyUIResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }

    }

    /**
     * 推荐店铺
     * @param session
     * @param supplierInfo
     * @return
     */
    @RequestMapping("/recommendShop")
    @ResponseBody
    public EasyUIResult recommendShop(HttpSession session, SupplierInfo supplierInfo) {
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        if (userResult == null || supplierInfo == null || supplierInfo.getSupplierId() == null) {
            return null;
        }
        try {
            shopInfoService.recommendShop(supplierInfo);
            return new EasyUIResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }

    }

    /**
     * OA异步加载所有店铺列表
     * @param session
     * @param supplierInfo
     * @return
     */
    @RequestMapping("/getShopInfoList")
    @ResponseBody
    public EasyUIListResult<SupplierInfo> getShopInfoList(HttpSession session, PageableSupplierInfo supplierInfo) {
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        supplierInfo.setPluteformid(userResult.getPluteformid());
        try {
            return shopInfoService.getShopInfoList(supplierInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIListResult<SupplierInfo>();
        }
    }

    /**
     * 微信端店铺搜索。
     * 返回店铺列表
     * @param param
     * @param supplierInfo
     * @return
     */
    @RequestMapping("/getShopInfoListForWeChat")
    @ResponseBody
    public EasyUIListResult<SupplierInfo> getShopInfoListForWeChat(@RequestBody String param, PageableSupplierInfo supplierInfo) {
        if (param.startsWith("{")) {
             supplierInfo = JSONObject.parseObject(param, PageableSupplierInfo.class);
        }
        try {
            return shopInfoService.getShopInfoList(supplierInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIListResult<SupplierInfo>();
        }
    }


    /**
     * 此方法用于日期的转换，如果未加，当页面日期格式转换错误，将报400错误，实际是因为此方法
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     *根据店铺主人查询
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getShopInfoByUserId")
    @ResponseBody
    public Object getShopInfoByUserId(HttpServletRequest request) throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s);
            }
            br.close();
            is.close();
            String users =sb.toString();
            isr.close();
            AccountUserVo accountUserVo= JSONObject.parseObject(users, AccountUserVo.class);
            AccountUserResult accountUserResult =userListService.selUsersByAppIdAndOpenId(accountUserVo);
            SupplierInfo supplInfo=new SupplierInfo();
            supplInfo.setUserId(accountUserResult.getUserId());
            SupplierInfo supplierInfo=shopInfoService.getShopInfoByUserId(supplInfo);
            JSONObject object=new JSONObject();
            String supplierInfoList=object.toJSONString(supplierInfo);
            return supplierInfoList;
        }catch (Exception e){
            throw  e;
        }
    }

    /**
     * 微信端按钮的操作 根据平台ID和openId查询客户表，
     * 根据followWay判断1返回该平台下的所有店铺2返回该用户关注的所有店铺
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectShopInfoAndFollow.action")
    @ResponseBody
    public Object selectShopInfoAndFollow(HttpServletRequest request)throws Exception{
        try{
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s);
            }
            br.close();
            is.close();
            String user=sb.toString();
            isr.close();
            AccountUserVo accountUser=JSONObject.parseObject(user, AccountUserVo.class);
            AccountUserResult accountUserResult=userListService.selUsersByAppIdAndOpenId(accountUser);
            Integer followWay=accountUserResult.getFollowWay();
            Users users = SpaceBeanCopy.accounUserVoToAccountUser(accountUser);
            users.setUserId(accountUserResult.getUserId());
            PageableSupplierInfo supplierInfo=JSONObject.parseObject(user, PageableSupplierInfo.class);
            if (followWay==1){
                supplierInfo.setPluteformid(accountUser.getPluteformid());
                EasyUIListResult<SupplierInfo> shopInfoList= shopInfoService.getShopInfoList(supplierInfo);
                return shopInfoList;
            }else {
                supplierInfo.setPluteformid(accountUser.getPluteformid());
                EasyUIListResult<SupplierInfo> shopInfoList=shopInfoService.getShopInfoListByAccountUser(supplierInfo,users);
                return  shopInfoList;
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 商城下的店铺销量
     * @param session
     * @param shopId
     * @param month
     * @param currentPage
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/selShopChampionHip.action")
    public ModelAndView selShopChampionHip(HttpSession session,String shopId,String month,Integer currentPage,String startTime,String endTime)throws Exception{
        ModelAndView mav = new ModelAndView("/business/shop/shopChampionHipShow");
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        try {
            SupplierInfo supplierInfo = new SupplierInfo();
            supplierInfo.setPluteformid(userResult.getPluteformid());
            //查询出商城下所有店铺
            List<SupplierInfo> supplierInfos = shopInfoService.selAllShop(supplierInfo);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            String year = sdf.format(date);
            Integer years = Integer.parseInt(year);
            if(month == null || "0".equals(month)){
                month = "1";
            }
            //计算月份
            if(startTime == null || startTime == "" || endTime == null || endTime ==""){
                startTime = year+"-"+month+"-1";
                String day = null;

                if("2".equals(month)){
                    //判断闰年
                    if(years%4==0 && years%100 !=0 ||years%400 ==0){
                        day = "29";
                    }else {
                        day = "28";
                    }
                }else if("4".equals(month) || "6".equals(month) || "9".equals(month) || "11".equals(month)){
                    day = "30";
                }else {
                    day = "31";
                }
                endTime = year+"-"+month+"-"+day ;
            }
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setPluteformid(userResult.getPluteformid());
            Integer totalPage = 1;
            Integer pageSize = 10;
            if (currentPage==null){
                currentPage=1;
            }
            List productIds = new ArrayList();
            List<OrderInfo> orderInfos = null;
            SupplierInfo si = null;
            if(supplierInfos != null){
                if(shopId == null || "".equals(shopId)){
                    shopId = supplierInfos.get(0).getSupplierId()+"";
                }
            }
            if(shopId != null && !"".equals(shopId)){
                ProductInfo productInfo = new ProductInfo();
                productInfo.setShopId(Long.parseLong(shopId));
                productInfo.setPluteformid(userResult.getPluteformid());
                //获取店铺下的产品
                List<ProductInfo> productInfos = productInfoService.selShopProduct(productInfo);
                //店铺下没有产品直接跳过订单操作
                if(productInfos == null || productInfos.size() == 0){

                }else{
                    for (int i = 0;i<productInfos.size();i++){
                        long productId = productInfos.get(i).getProductId();
                        productIds.add(productId);
                    }

                    Integer count = orderInfoService.selEmporiumShopChampionHipCount(startTime,endTime,orderInfo,productIds);
                    if (count==0){
                        totalPage =1;
                    }else {
                        totalPage = count % pageSize == 0 ? count/pageSize : count/pageSize + 1;//一共多少页数
                    }
                    Integer begionNumber = (currentPage-1)*pageSize;
                    orderInfo.setBegionNumber(begionNumber);
                    orderInfo.setCurrentPage(currentPage);
                    orderInfo.setPageSize(pageSize);
                    //获取有该店铺产品的订单
                    orderInfos = orderInfoService.selEmporiumShopChampionHip(startTime,endTime,orderInfo,productIds);
                }
                supplierInfo.setSupplierId(Long.parseLong(shopId));
                //获取店铺的名称
                si = shopInfoService.selOneShop(supplierInfo);
            }
            mav.addObject("shops",supplierInfos);
            mav.addObject("orderInfos",orderInfos);
            mav.addObject("month",month);
            mav.addObject("currentPage",currentPage);
            mav.addObject("totalPage",totalPage);
            mav.addObject("shopId",shopId);
            if(si == null){
                mav.addObject("shopName",null);
            }else {
                mav.addObject("shopName",si.getName());
            }
            return mav;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 店铺自己的销量
     * @param session
     * @param month
     * @param startTime
     * @param endTime
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shopChampionHip.action")
    public ModelAndView shopChampionHip(HttpSession session,String month,String startTime,String endTime,Integer currentPage)throws Exception {
        ModelAndView mav = new ModelAndView("/business/shop/ChampionHipShow");
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        Long shopId = null;
        if(userResult.getShopId() != null){
            shopId = userResult.getShopId();
        }

        if (userResult == null) {
            return null;
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(date);
        Integer years = Integer.parseInt(year);
        if(month == null || "0".equals(month)){
            month = "1";
        }
        //计算月份
        if(startTime == null || startTime == "" || endTime == null || endTime ==""){
            startTime = year+"-"+month+"-1";
            String day = null;

            if("2".equals(month)){
                //判断闰年
                if(years%4==0 && years%100 !=0 ||years%400 ==0){
                    day = "29";
                }else {
                    day = "28";
                }
            }else if("4".equals(month) || "6".equals(month) || "9".equals(month) || "11".equals(month)){
                day = "30";
            }else {
                day = "31";
            }
            endTime = year+"-"+month+"-"+day ;
        }
        SupplierInfo supplierInfo = new SupplierInfo();
        supplierInfo.setPluteformid(userResult.getParentPluteformId());
        SupplierInfo si = null;

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPluteformid(userResult.getParentPluteformId());
        Integer totalPage = 1;
        Integer pageSize = 10;
        if (currentPage==null){
            currentPage=1;
        }

        List productIds = new ArrayList();
        List<OrderInfo> orderInfos = null;

        ProductInfo productInfo = new ProductInfo();
        productInfo.setShopId(shopId);
        productInfo.setPluteformid(userResult.getParentPluteformId());
        //获取店铺下的产品
        List<ProductInfo> productInfos = productInfoService.selShopProduct(productInfo);
        //店铺下没有产品直接跳过订单操作
        if(productInfos == null || productInfos.size() == 0){

        }else{
            for (int i = 0;i<productInfos.size();i++){
                long productId = productInfos.get(i).getProductId();
                productIds.add(productId);
            }

            Integer count = orderInfoService.selEmporiumShopChampionHipCount(startTime,endTime,orderInfo,productIds);
            if (count==0){
                totalPage =1;
            }else {
                totalPage = count % pageSize == 0 ? count/pageSize : count/pageSize + 1;//一共多少页数
            }
            Integer begionNumber = (currentPage-1)*pageSize;
            orderInfo.setBegionNumber(begionNumber);
            orderInfo.setCurrentPage(currentPage);
            orderInfo.setPageSize(pageSize);
            //获取有该店铺产品的订单
            orderInfos = orderInfoService.selEmporiumShopChampionHip(startTime,endTime,orderInfo,productIds);
            supplierInfo.setSupplierId(shopId);
            //获取店铺的名称
            si = shopInfoService.selOneShop(supplierInfo);
        }
        mav.addObject("orderInfos",orderInfos);
        mav.addObject("month",month);
        mav.addObject("currentPage",currentPage);
        mav.addObject("totalPage",totalPage);
        mav.addObject("shopId",shopId);
        mav.addObject("type",1);
        if(si == null){
            mav.addObject("shopName",null);
        }else {
            mav.addObject("shopName",si.getName());
        }
        return mav;
    }

    /**
     * 店铺自己的月订单量
     * @param request
     * @param type
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/shopOrderQuantity.action")
    public ModelAndView shopOrderQuantity(HttpServletRequest request,String type,String startTime,String endTime)throws  Exception{
        if("1".equals(type)){
            return new ModelAndView("redirect:/business/shop/shopChampionHip.action");
        }else if("3".equals(type)){
            return new ModelAndView("redirect:/business/shop/shopOrderTotal.action");
        }
        UserResult userResult=(UserResult)request.getSession().getAttribute("userResult");
        Long shopId = null;
        if(userResult.getShopId() != null){
            shopId = userResult.getShopId();
        }
        List productIds = new ArrayList();
        List list = null;

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPluteformid(userResult.getParentPluteformId());

        ProductInfo productInfo = new ProductInfo();
        productInfo.setShopId(shopId);
        productInfo.setPluteformid(userResult.getParentPluteformId());
        //获取店铺下的产品
        List<ProductInfo> productInfos = productInfoService.selShopProduct(productInfo);

        //店铺下没有产品直接跳过订单操作
        if(productInfos == null || productInfos.size() == 0){

        }else{
            for (int i = 0;i<productInfos.size();i++){
                long productId = productInfos.get(i).getProductId();
                productIds.add(productId);
            }
            list = orderInfoService.shopOrderQuantity(startTime,endTime,orderInfo,productIds);
        }

        ModelAndView mav = new ModelAndView("/business/shop/ChampionHipShow");
        mav.addObject("type", 2);
        mav.addObject("list",list);
        return mav;
    }

    /**
     * 店铺自己的月订单金额
     * @param request
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shopOrderTotal.action")
    public ModelAndView shopOrderTotal(HttpServletRequest request,String startTime,String endTime) throws Exception{

        UserResult userResult=(UserResult)request.getSession().getAttribute("userResult");
        Long shopId = null;
        if(userResult.getShopId() != null){
            shopId = userResult.getShopId();
        }
        List productIds = new ArrayList();
        List money = null;

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPluteformid(userResult.getParentPluteformId());

        ProductInfo productInfo = new ProductInfo();
        productInfo.setShopId(shopId);
        productInfo.setPluteformid(userResult.getParentPluteformId());
        //获取店铺下的产品
        List<ProductInfo> productInfos = productInfoService.selShopProduct(productInfo);

        //店铺下没有产品直接跳过订单操作
        if(productInfos == null || productInfos.size() == 0){

        }else{
            for (int i = 0;i<productInfos.size();i++){
                long productId = productInfos.get(i).getProductId();
                productIds.add(productId);
            }
            money = orderInfoService.shopOrderTotal(startTime,endTime,orderInfo,productIds);
        }

        ModelAndView mav = new ModelAndView("/business/shop/ChampionHipShow");
        mav.addObject("type",3);
        mav.addObject("money",money);
        return mav;
    }
}
