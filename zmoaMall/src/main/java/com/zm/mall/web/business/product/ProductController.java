package com.zm.mall.web.business.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.product.CategoryInfoResult;
import com.zm.mall.client.result.business.product.ProductInfoResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.product.ProductInfoService;
import com.zm.mall.client.service.business.shopUser.ShopUserService;
import com.zm.mall.client.service.system.AdService;
import com.zm.mall.client.service.system.CouponService;
import com.zm.mall.client.vo.business.product.*;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.constant.ConfigConstants;
import com.zm.mall.domain.business.product.*;
import com.zm.mall.domain.system.Ad;
import com.zm.mall.domain.system.EasyUIPaginationAd;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import com.zm.mall.domain.system.coupon.ProductCategory;
import com.zm.mall.domain.system.systemCode.EasyUIResult;
import com.zm.mall.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by lp on 2016/11/12.
 * 直接访问页面
 */
@Controller
@RequestMapping("/business")
@PropertySource("classpath:urlPrefix.properties")
public class ProductController extends BaseController {
    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private AdService adService;
    @Autowired
    private Environment env;
    @Resource
    private ShopUserService shopUserService;
    @Resource
    private CouponService couponService;

    @RequestMapping(value = "/product.action")
    public ModelAndView product() throws Exception {
        try {
            return new ModelAndView("/business/show");
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 查询商品
     * @param productN
     * @param productC
     * @param category
     * @param saleStatus
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectProduct.action")
    @ResponseBody
    public Page product(HttpServletRequest request,String  productN,String productC,String category,Integer saleStatus,Integer currentPage) throws Exception {
        try {
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            Page page = new Page();
            page.setPluteformid(user.getPluteformid());
            if (user.getShopId() != null) {
                page.setShopId(user.getShopId());
                page.setPluteformid(user.getParentPluteformId());
            }
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductName(productN);
            productInfoVo.setProductCode(productC);
            productInfoVo.setSearchProductCategories(category);
            productInfoVo.setSaleStatus(saleStatus);
            productInfoVo.setPluteformid(user.getPluteformid());
            productInfoVo.setShopId(user.getShopId());
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            Page resultPage = productInfoService.selectProductInfo(page,productInfoVo);
            return resultPage;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 获得产品属性
     * @param ProductId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/skuAttr.action")
    public ModelAndView selectAttr(HttpServletRequest request,Long ProductId) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductId(ProductId);
            productInfoVo.setPluteformid(user.getPluteformid());//平台Id
            Map map = new HashMap();
            List<AttributeInfo> sku =productInfoService.selSkuById(productInfoVo);
            List<AttributeInfo> spec =productInfoService.selSpecById(productInfoVo);
            map.put("Skus",sku);
            map.put("Specs",spec);
            return new ModelAndView("/business/skualert",map);
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 更新属性
     * @param ProductId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selUpdateSku.action")
    @ResponseBody
    public  List<AttributeInfo> selUpdateSku(HttpServletRequest request,Long ProductId) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductId(ProductId);
            productInfoVo.setPluteformid(user.getPluteformid());//平台Id
            List<AttributeInfo> sku =productInfoService.selSkuById(productInfoVo);
            return sku;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 更新规格
     * @param ProductId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selUpdateSpecs.action")
    @ResponseBody
    public  List<AttributeInfo> selUpdateSpecs(HttpServletRequest request,Long ProductId) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductId(ProductId);
            productInfoVo.setPluteformid(user.getPluteformid());//平台Id
            List<AttributeInfo> spec =productInfoService.selSpecById(productInfoVo);
            return spec;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 更新库存
     * @param ProductId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selUpdateSkuInfo.action")
    @ResponseBody
    public  List<SKUInfo> selSkuInfoById(HttpServletRequest request,Long ProductId) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            SKUInfoVo skuInfoVo = new SKUInfoVo();
            skuInfoVo.setProductId(ProductId);
            skuInfoVo.setPluteformid(user.getPluteformid());//平台Id
            List<SKUInfo> skuInfos =productInfoService.selSkuInfoById(skuInfoVo);
            return skuInfos;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 更新关联
     * @param ProductId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selProductRelated.action")
    @ResponseBody
    public  List<ProductInfo> selProductRelated(HttpServletRequest request,Long ProductId) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductId(ProductId);
            productInfoVo.setPluteformid(user.getPluteformid());//平台Id
            List<ProductInfo> productInfos =productInfoService.selProductRelated(productInfoVo);
            return productInfos;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 跟新路径
     * @param productId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selProductImageByProductId.action")
    @ResponseBody
    public List<ProductImage> selProductImageByProductId(HttpServletRequest request,Long productId)throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductId(productId);
            productInfoVo.setPluteformid(user.getPluteformid());//平台Id
            List<ProductImage> productImages = productInfoService.selProductImageByProductId(productInfoVo);
            return productImages;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 更新分类
     */
    @RequestMapping(value = "/selProductCategoryByProductId.action")
    @ResponseBody
    public List<SelProductUtil<CategoryInfo>> selProductCategoryByProductId(HttpServletRequest request,Long productId)throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductId(productId);
            productInfoVo.setPluteformid(user.getPluteformid());//平台Id
            List<SelProductUtil<CategoryInfo>> selProductUtils = productInfoService.preUpDateCateGory(productInfoVo);
            return selProductUtils;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 查询分类
     * @return
     */
    @RequestMapping(value = "/selCategory.action")
    @ResponseBody
    public List<CategoryInfo> categoryInfo(HttpServletRequest request,Integer fatherId,Integer depath) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            CategoryInfoVo categoryInfoVo = new CategoryInfoVo();
            categoryInfoVo.setParentCategoryId(fatherId);
            categoryInfoVo.setDepth(depath);
            categoryInfoVo.setPluteformid(user.getPluteformid());//平台Id
            if (user.getShopId() != null) {
                categoryInfoVo.setShopId(user.getShopId());
                categoryInfoVo.setPluteformid(user.getParentPluteformId());
            }
            return productInfoService.categoryInfo(categoryInfoVo);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 录入查询
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/preEntering.action")
    public ModelAndView preEntering(HttpSession session)throws Exception {
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        try{
            Map map = new HashMap();
            ProductInfo productInfo = productInfoService.preProductInfo(platformId, userResult.getShopId());
            map.put("productInfo", productInfo);
            return new ModelAndView("/business/entering", map);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 获得类型属性
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selAttributeInfo.action")
    @ResponseBody
    public List<AttributeInfo> selAttributeInfo(HttpServletRequest request,Integer id ,Integer status) throws Exception {
        try {
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            AttributeInfoVo attributeInfoVo = new AttributeInfoVo();
            attributeInfoVo.setStatus(status);
            attributeInfoVo.setTypeId(id);
            attributeInfoVo.setPluteformid(user.getPluteformid());//平台Id
            List<AttributeInfo> attributeInfoList = productInfoService.selAttributeInfo(attributeInfoVo);
            return attributeInfoList;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获得分类品牌
     * @param id
     * @return
     */
    @RequestMapping(value = "/selBrandInfo.action")
    @ResponseBody
    public List<BrandInfo> selBrandInfoByType(HttpServletRequest request,Integer id) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            AttributeInfoVo attributeInfoVo = new AttributeInfoVo();
            attributeInfoVo.setTypeId(id);
            attributeInfoVo.setPluteformid(user.getPluteformid());//平台Id
            List<BrandInfo> BrandInfoResult =  productInfoService.selBrandInfoByType(attributeInfoVo);
            return BrandInfoResult;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 获得产地条件
     * @param fatherId
     * @param depath
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selRegionInfo.action")
    @ResponseBody
    public List<RegionInfo> selAll(HttpServletRequest request,Integer fatherId,Integer depath) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            RegionInfoVo regionInfoVo = new RegionInfoVo();
            regionInfoVo.setParentId(fatherId);
            regionInfoVo.setDepth(depath);
            regionInfoVo.setPluteformid(user.getPluteformid());//平台Id
            List<RegionInfo> regionInfos=  productInfoService.selRegionInfoAll(regionInfoVo);
            return regionInfos;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 获得统一调整价
     * @param path
     * @return
     */
    @RequestMapping(value = "/selPrice.action")
    @ResponseBody
    public Double price(HttpServletRequest request,String path) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            CategoryInfoVo categoryInfoVo = new CategoryInfoVo();
            categoryInfoVo.setPath(path);
            categoryInfoVo.setPluteformid(user.getPluteformid());//平台Id
            CategoryInfo categoryInfo =  productInfoService.price(categoryInfoVo);
            if(categoryInfo == null || categoryInfo.getPriceChange()==null){
                return 0.00;
            }else{
                return categoryInfo.getPriceChange();
            }
        }catch (Exception e){
            throw e;
        }
    }
    //编码校验
    @RequestMapping(value = "/productCode.action")
    @ResponseBody
    public ProductInfo productCode(HttpServletRequest request,String productCode) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductCode(productCode);
            productInfoVo.setPluteformid(user.getPluteformid());//平台Id
            return productInfoService.productCode(productInfoVo);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 图片回显临时上传
     * @param request
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertProductFile.action" )
    @ResponseBody
    public String  insertProductFile(HttpServletRequest request,@RequestParam(value="file",required=false)MultipartFile file)throws Exception{
        try{
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            //当天日期
            String curDate = df.format(date);
            //时间戳
            long s =date.getTime();
            String sc = file.getOriginalFilename();
            String[] type = sc.split("\\.");
            //文件名
            String fileName = curDate+s+"."+type[type.length-1];

            //上传路径
            String path = "/Upload/Temp/"+curDate+"/";
            //String uppath = request.getSession().getServletContext().getRealPath(path);
            String uppath = env.getProperty("url.upload") + path;
            File fileLocation = new File(uppath);
            //此处也可以在应用根目录手动建立目标上传目录
            if(!fileLocation.exists()){
                fileLocation.mkdirs();
            }
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(uppath+"/"+fileName));
            File srcFile = new File(uppath+"/"+fileName);

            Image srcImg = ImageIO.read(srcFile);
            //获得图片规格
            List<ThumbnailSize> thumbnailSizes = productInfoService.selThumbnailSizeSpec();
            for(int i =0;i<thumbnailSizes.size();i++){
                String ThumName = thumbnailSizes.get(i).getThumName();
                Integer ThumWidth =thumbnailSizes.get(i).getThumWidth();
                Integer ThumHeight =thumbnailSizes.get(i).getThumHeight();
                BufferedImage tag=new BufferedImage(ThumWidth, ThumHeight, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(srcImg, 0, 0, ThumWidth, ThumHeight,null);
                FileOutputStream out=new FileOutputStream(uppath+"/"+ThumName+fileName);
                JPEGImageEncoder encoder= JPEGCodec.createJPEGEncoder(out);
                encoder.encode(tag);
            }

            return path+"{0}"+fileName;
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 提交产品
     */

    @RequestMapping(value = "/insertProduct.action")
    public ModelAndView  insertProduct(HttpServletRequest request,String[] filePath, String hasSku,
                                       String SpecStr, String skusStr, String searchProductCategories,
                                       String productName, String productCode, String typeId,
                                      String brandId, String lowestSalePrice, String displaySequence,
                                      String supplierId,String unit, String regionId,
                                      String marketPrice,String points,String saleStatus,
                                      String salesType,String description,String productGuanLian,
                                      String[] Station, String imageUrls) throws Exception {
        try {
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setCategoryId(-1);
            productInfoVo.setHasSku(Boolean.parseBoolean(hasSku));
            productInfoVo.setSpecStr(SpecStr);
            productInfoVo.setSkusStr(skusStr);
            productInfoVo.setSearchProductCategories(searchProductCategories);
            productInfoVo.setProductName(productName);
            productInfoVo.setProductCode(productCode);
            if(typeId!="" && typeId.length()>0){
                productInfoVo.setTypeId(Integer.parseInt(typeId));
            }
            if(!("").equals(brandId) && brandId.length()>0 ){
                productInfoVo.setBrandId(Integer.parseInt(brandId));
            }else{
                productInfoVo.setBrandId(0);
            }
            if(lowestSalePrice!="" && lowestSalePrice.length()>0){
                productInfoVo.setLowestSalePrice(Double.parseDouble(lowestSalePrice));
            }else{
                productInfoVo.setLowestSalePrice(0.00);
            }
            if(!("").equals(displaySequence) && displaySequence.length()>0){
                productInfoVo.setDisplaySequence(Integer.parseInt(displaySequence));
            }else{
                productInfoVo.setDisplaySequence(-1);
            }
            if(supplierId!="" && supplierId.length()>0){
                productInfoVo.setSupplierId(Integer.parseInt(supplierId));
            }else{
                productInfoVo.setSupplierId(0);
            }
            productInfoVo.setUnit(unit);
            if(!("").equals(regionId) && regionId.length()>0){
                productInfoVo.setRegionId(Integer.parseInt(regionId));
            }
            if(marketPrice!="" && marketPrice.length()>0){
                productInfoVo.setMarketPrice(Double.parseDouble(marketPrice));
            }
            if(points!="" && points.length()>0){
                productInfoVo.setPoints(Double.parseDouble(points));
            }else{
                productInfoVo.setPoints(0.00);
            }
            if(saleStatus!="" && saleStatus.length()>0){
                productInfoVo.setSaleStatus(Integer.parseInt(saleStatus));
            }
            if(salesType!="" && salesType.length()>0){
                productInfoVo.setSalesType(Integer.parseInt(salesType));
            }
            if(!("").equals(productGuanLian) && productGuanLian.length()>0){
                productInfoVo.setRelatedProductId(productGuanLian);
            }else{
                productInfoVo.setRelatedProductId("");
            }
            productInfoVo.setDescription(description);
            productInfoVo.setStation(Station);
            productInfoVo.setPluteformid(user.getPluteformid());
            if (user.getShopId() != null) {
                productInfoVo.setShopId(user.getShopId());
                productInfoVo.setPluteformid(user.getParentPluteformId());
            }
            Long productResultID = productInfoService.insertProduct(productInfoVo);

            /*------ 旧版图片上传 Start ------*/
            //更新图片路径
            /*List<String> str = new ArrayList<String>();
            for(int f =0;f<filePath.length;f++){
                String fileUrl = saveFile(request,filePath[f]);
                if(fileUrl!=null && fileUrl !=""){
                    str.add(fileUrl);
                }
            }
            if(productResultID!=null&&str.size()>0) {
                //productInfoService.upProuctUrlPrice(str,productResultID,productInfoVo.getPluteformid());
                productInfoService.updateProductImageUrl(str,productResultID,productInfoVo.getPluteformid(), user.getShopId());
            }*/
            /*------ 旧版图片上传 End ------*/

            /*------ 新版图片上传 Start ------*/
            if (productResultID != null && imageUrls != null) {
                productInfoService.insertProductImageUrls(InsertProductImageDTO.newInstance()
                                                            .imageUrls(imageUrls.split(","))
                                                            .productId(productResultID)
                                                            .platformId(productInfoVo.getPluteformid())
                                                            .shopId(user.getShopId()));
            }
            /*------ 新版图片上传 End ------*/



            return  new ModelAndView("redirect:/business/preEntering.action");
        }catch (Exception e){
            throw e;
        }
    }

    //文件上传
    @Deprecated
    private String saveFile(HttpServletRequest request, String filePath) {
        if (!("").equals(filePath)) {
            try {
                String[] f= filePath.split("\\*");
                //String testPath = request.getSession().getServletContext().getRealPath(f[0]);
                String testPath = env.getProperty("url.upload") + f[0];
                File test = new File(testPath);
                if(test.exists()){
                    return filePath;
                }else if(f.length>1){
                    return filePath;
                }else{
                    String path =filePath.replace("../..", "");
                    String ImagePath = path.replace("{0}","");
                    //当天日期
                    Date date = new Date();
                    DateFormat df = new SimpleDateFormat("yyyyMMdd");
                    String curDate = df.format(date);
                    //系统路径
                    //String SysPath = request.getSession().getServletContext().getRealPath(ImagePath);
                    String SysPath = env.getProperty("url.upload") + ImagePath;
                    //上传路径
                    String number = "/Upload/Shop/Images/Product/"+curDate+"/";
                    //String numberPath = request.getSession().getServletContext().getRealPath(number);
                    String numberPath = env.getProperty("url.upload") + number;
                    File file = new File(SysPath);
                    String str = file.getName();
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

                    File fileLocation = new File(numberPath);
                    //此处也可以在应用根目录手动建立目标上传目录
                    if(!fileLocation.exists()){
                        fileLocation.mkdirs();
                    }
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(numberPath+"/"+str));
                    //使用输入流和输出流复制文件
                    int n = bis.read();
                    while(n!=-1){
                        //写
                        bos.write(n);
                        //读
                        n=bis.read();
                    }
                    //手动刷新
                    bos.flush();
                    //关闭输入流和输出流
                    bis.close();
                    bos.close();

                    List<ThumbnailSize> thumbnailSizes = productInfoService.selThumbnailSizeSpec();
                    //缩略图路径
                    String ProductThumbs = "Upload/Shop/Images/ProductThumbs/"+curDate+"/";
                    //String ProductThumbspath = request.getSession().getServletContext().getRealPath(ProductThumbs);
                    String ProductThumbspath = env.getProperty("url.upload") + ProductThumbs;
                    //文件名
                    for(int i =0;i<thumbnailSizes.size();i++){
                        //String ProductThumbsImagePath = request.getSession().getServletContext().getRealPath(path.replace("{0}", thumbnailSizes.get(i).getThumName()));
                        String ProductThumbsImagePath = env.getProperty("url.upload") + path.replace("{0}", thumbnailSizes.get(i).getThumName());
                        File ptFile = new File(ProductThumbsImagePath);
                        String ptStr = ptFile.getName();
                        BufferedInputStream oldProductThumbs = new BufferedInputStream(new FileInputStream(ptFile));
                        File ProductThumbsLocation = new File(ProductThumbspath);
                        //此处也可以在应用根目录手动建立目标上传目录
                        if(!ProductThumbsLocation.exists()){
                            ProductThumbsLocation.mkdirs();
                        }
                        BufferedOutputStream newProductThumbspath = new BufferedOutputStream(new FileOutputStream(ProductThumbspath+"/"+ptStr));
                        //使用输入流和输出流复制文件
                        int io = oldProductThumbs.read();
                        while(io!=-1){
                            //写
                            newProductThumbspath.write(io);
                            //读
                            io=oldProductThumbs.read();
                        }
                        //手动刷新
                        newProductThumbspath.flush();
                        //关闭输入流和输出流
                        oldProductThumbs.close();
                        newProductThumbspath.close();
                    }
                    return number+str+"*"+ProductThumbs+"{0}"+str;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 删除产品
     * @param delProductId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delProduct.action")
    @ResponseBody
    public Integer delProduct(HttpServletRequest request,String delProductId)throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            String platformId = user.getPluteformid();
            if (user.getShopId() != null) {
                platformId = user.getParentPluteformId();
            }
            Integer count = productInfoService.delProduct(delProductId, platformId);
            if(count!=null){
                return count;
            }else{
                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 更新
     * @param productId
     * @return
     */
    @RequestMapping(value = "preUpDateStation.action")
    @ResponseBody
    public  List<ProductStationModes> upProductInfo(HttpServletRequest request,Long productId)throws  Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductId(productId);
            productInfoVo.setPluteformid(user.getPluteformid());
            List<ProductStationModes> productStationModeses = productInfoService.selProductStationModesByProductId(productInfoVo);
            return productStationModeses;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 更新
     * @param id
     * @return
     */
    @RequestMapping(value = "upProductInfo.action")
    public ModelAndView upProductInfo(HttpServletRequest request,Long id,String cate)throws  Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            Map map = new HashMap();
            //产品
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductId(id);
            productInfoVo.setPluteformid(user.getPluteformid());
            if (user.getShopId() != null) {
                productInfoVo.setShopId(user.getShopId());
                productInfoVo.setPluteformid(user.getParentPluteformId());
            }
            ProductInfoResult productInfoResult = productInfoService.selProductInfoById(productInfoVo);
            //地址
            RegionInfoVo regionInfoVo = new RegionInfoVo();
//            regionInfoVo.setPluteformid(user.getPluteformid());
            regionInfoVo.setRegionId(productInfoResult.getRegionId());
            SelProductUtil RegionByProductId = productInfoService.selRegionByProductId(regionInfoVo);
            //分类
            CategoryInfoVo categoryInfoVo = new CategoryInfoVo();
            categoryInfoVo.setPath(cate);
//            categoryInfoVo.setPluteformid(user.getPluteformid());
            SelProductUtil CategoryByProductId = productInfoService.selCateByPath(categoryInfoVo);

            map.put("productInfoResult",productInfoResult);
            map.put("categoryInfos",CategoryByProductId);
            map.put("regionInfos",RegionByProductId);
            return new ModelAndView("/business/upProductInfo",map);
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 白洋洋 根据产品Id 获取对应的规格信息
     */
    @RequestMapping(value = "/selectAttribute.action")
    @ResponseBody
    public Object selectAttribute(HttpServletRequest request,Long productId){
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        ProductInfoVo productInfoVo = new ProductInfoVo();
        productInfoVo.setProductId(productId);
        productInfoVo.setPluteformid(user.getPluteformid());

        ProductInfo productInfo = new ProductInfo();
        List<AttributeInfo> list = new ArrayList<AttributeInfo>();
        List<AttributeInfo> attributeInfoList = productInfoService.selectAttribute(productInfoVo);
        productInfo.setAttributeInfoList(attributeInfoList);
        return JSON.toJSON(productInfo);
    }

    /**
     * 规格获取产品
     * @param cate
     * @param typeId
     * @param appearence
     * @param thick
     * @param productName
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selProductBySku.action")
    @ResponseBody
    public  Page selProductBySku(String cate,Integer typeId,String appearence,String thick,String productName,String productCode,Integer currentPage)throws  Exception{
        try{
            Page page =productInfoService.selProductBySku(cate,typeId,appearence,thick,productName,productCode,currentPage);
            return page;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 更新产品
     * @param request
     * @param hasSku
     * @param SpecStr
     * @param skusStr
     * @param searchProductCategories
     * @param productName
     * @param productCode
     * @param typeId
     * @param brandId
     * @param lowestSalePrice
     * @param displaySequence
     * @param supplierId
     * @param unit
     * @param regionId
     * @param marketPrice
     * @param points
     * @param saleStatus
     * @param salesType
     * @param productGuanLian
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateProduct.action")
    public ModelAndView  updateProduct(HttpServletRequest request,String[] filePath,String productId, String hasSku,String SpecStr,String skusStr,String searchProductCategories,String productName,String productCode,String typeId,
                                       String brandId,String lowestSalePrice,String displaySequence,String supplierId,String unit,
                                       String regionId,String marketPrice,String points,String saleStatus,String salesType,String description,String productGuanLian,String[] Station) throws Exception{
        try {
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductId(Long.parseLong(productId));
            productInfoVo.setCategoryId(-1);
            productInfoVo.setHasSku(Boolean.parseBoolean(hasSku));
            productInfoVo.setSpecStr(SpecStr);
            productInfoVo.setSkusStr(skusStr);
            productInfoVo.setSearchProductCategories(searchProductCategories);
            productInfoVo.setProductName(productName);
            productInfoVo.setProductCode(productCode);
            if(typeId!="" && typeId.length()>0){
                productInfoVo.setTypeId(Integer.parseInt(typeId));
            }
            if(!("").equals(brandId) && brandId.length()>0 ){
                productInfoVo.setBrandId(Integer.parseInt(brandId));
            }else{
                productInfoVo.setBrandId(0);
            }
            if(lowestSalePrice!="" && lowestSalePrice.length()>0){
                productInfoVo.setLowestSalePrice(Double.parseDouble(lowestSalePrice));
            }else{
                productInfoVo.setLowestSalePrice(0.00);
            }
            if(!("").equals(displaySequence) && displaySequence.length()>0){
                productInfoVo.setDisplaySequence(Integer.parseInt(displaySequence));
            }else{
                productInfoVo.setDisplaySequence(-1);
            }
            if(supplierId!="" && supplierId.length()>0){
                productInfoVo.setSupplierId(Integer.parseInt(supplierId));
            }else{
                productInfoVo.setSupplierId(0);
            }
            productInfoVo.setUnit(unit);
            if(!("").equals(regionId) && regionId.length()>0){
                productInfoVo.setRegionId(Integer.parseInt(regionId));
            }
            if(marketPrice!="" && marketPrice.length()>0){
                productInfoVo.setMarketPrice(Double.parseDouble(marketPrice));
            }
            if(points!="" && points.length()>0){
                productInfoVo.setPoints(Double.parseDouble(points));
            }else{
                productInfoVo.setPoints(0.00);
            }
            if(saleStatus!="" && saleStatus.length()>0){
                productInfoVo.setSaleStatus(Integer.parseInt(saleStatus));
            }
            if(salesType!="" && salesType.length()>0){
                productInfoVo.setSalesType(Integer.parseInt(salesType));
            }
            if(!("").equals(productGuanLian) && productGuanLian.length()>0){
                productInfoVo.setRelatedProductId(productGuanLian);
            }else{
                productInfoVo.setRelatedProductId("");
            }
            productInfoVo.setDescription(description);
            productInfoVo.setStation(Station);
            productInfoVo.setPluteformid(user.getPluteformid());
            Long productResultID = productInfoService.upDateProduct(productInfoVo);
            //更新图片路径
            List<String> str = new ArrayList<String>();
            for(int f =0;f<filePath.length;f++){
                String fileUrl = saveFile(request,filePath[f]);
                if(fileUrl!=null && fileUrl !=""){
                    str.add(fileUrl);
                }
            }
            if(productResultID!=null){
                productInfoService.upProuctUrlPrice(str,productResultID,productInfoVo.getPluteformid());
            }
            return  new ModelAndView("redirect:/business/preEntering.action");
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 添加收藏商品
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/AddShopFavorite.action")
    public Object AddShopFavorite(HttpServletRequest request) throws Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            FavoriteInfoVo favoriteInfoVo= JSONObject.parseObject(str, FavoriteInfoVo.class);
            Integer integer = productInfoService.AddShopFavorite(favoriteInfoVo);
            return integer;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 删除收藏商品
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delShopFavorite.action")
    @ResponseBody
    public String DelShopFavorite(HttpServletRequest request) throws Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            FavoriteInfoVo mallConfigVo= JSONObject.parseObject(str, FavoriteInfoVo.class);
            Integer integer = productInfoService.delFavoriteInfoById(mallConfigVo.getFavoritestr(), mallConfigVo.getPluteformid());
            return integer.toString();
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 查询收藏商品
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selShopFavorite.action")
    @ResponseBody
    public Object selShopFavorite(HttpServletRequest request) throws Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            Page page= JSONObject.parseObject(str, Page.class);
            List<ProductInfo> productInfos = productInfoService.selFavoriteInfoById(page);
            return productInfos;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 微信App查询商品分类
     * by liupeng
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/weCartAddAppSelectCategory.action")
    @ResponseBody
    public Object weCartAddAppSelectCategory(HttpServletRequest request) throws Exception {
        try {
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            CategoryInfoVo categoryInfoVo= JSONObject.parseObject(str, CategoryInfoVo.class);
            List<CategoryInfoResult> categoryInfoResults = productInfoService.weCartAddAppSelectCategory(categoryInfoVo);
            return categoryInfoResults;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 微信App查询分类中的商品
     * by liupeng
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/weCartAddAppSelectProductByPage.action")
    @ResponseBody
    public Object weCartAddAppSelectProductByPage(HttpServletRequest request) throws Exception {
        try {
             StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            Page page= JSONObject.parseObject(str, Page.class);
            Page resultPage = productInfoService.selProductAppWeCart(page);
            return resultPage;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 微信App查询商品
     * by liupeng
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/weCartAddAppSelectProduct.action")
    @ResponseBody
    public Object weCartAddAppSelectProduct(HttpServletRequest request) throws Exception {
        try {
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            ObjectMapper om = new ObjectMapper();
            Page page= om.readValue(str, Page.class);
            EasyUIPaginationAd ad = new EasyUIPaginationAd();
            ad.setPlatformId(page.getPluteformid());
            EasyUIListResult<Ad> result = adService.getAdList(ad);
            ArrayList<String> list = new ArrayList<String>();
            for (Ad a : result.getRows()) {
                list.add(env.getProperty("url.showImage") + "?relUrl=" + a.getImgUrl() + "&proportion=16:9");
            }
            Integer imageNum = result.getTotal().intValue();
            Page resultPage = productInfoService.weCartAddAppSelectProduct(page);
            JSONObject object=new JSONObject();
            object.put("resultPage",resultPage);
            object.put("imageNum",imageNum);
            object.put("imageUrl",list);
            String sss = object.toJSONString();
            return sss;
        }catch (Exception e){
            throw e;
        }
    }



    /**
     * 微信端产品首页
     */
    @RequestMapping(value = "/selectProductWX.action")
    @ResponseBody
    public Object selectProductWX(HttpServletRequest request) throws Exception {
        try {
        StringBuffer sb = new StringBuffer() ;
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        br.close();
        is.close();
        String productInfoPage=sb.toString();
        isr.close();
        JSONObject obj = JSONObject.parseObject(productInfoPage);
        String plateFormId=obj.getString("appid");
        String currentPages=obj.getString("currentPage");
        String productN=obj.getString("productN");
        String searchProductCategories=obj.getString("searchProductCategories");
        Integer  currentPage=Integer.parseInt(currentPages);
        Page page = new Page();
        EasyUIPaginationAd ad = new EasyUIPaginationAd();
        ad.setPlatformId(plateFormId);
        EasyUIListResult<Ad> result = adService.getAdList(ad);
        ArrayList<String> list = new ArrayList<String>();
        //String = "http://192.168.1.104:80";
        for (Ad a : result.getRows()) {
            list.add(env.getProperty("url.showImage") + "?relUrl=" + a.getImgUrl() + "&proportion=16:9");
        }
        Integer imageNum = result.getTotal().intValue();
        page.setPluteformid(plateFormId);
        page.setCurrentPage(currentPage);
        ProductInfoVo productInfoVo = new ProductInfoVo();
        productInfoVo.setPluteformid(plateFormId);
        if(searchProductCategories==null) {
            productInfoVo.setSearchProductCategories("");
        }else {
            productInfoVo.setSearchProductCategories(searchProductCategories);
        }
        productInfoVo.setProductName(productN);
        Page resultPages = productInfoService.selectProductInfo(page,productInfoVo);
        JSONObject object=new JSONObject();
        object.put("resultPageP",resultPages);
        object.put("imageNum",imageNum);
        object.put("imageUrl",list);
        String sss = object.toJSONString();
        return sss;
        }catch (Exception e){
            throw  e;
        }
    }
    /**
     * 微信首页分类(暂时不用)
     */
    @RequestMapping(value = "/categoryInfoWX.action")
    @ResponseBody
    public Object categoryInfoWX(HttpServletRequest request) throws Exception {
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String CategoryInfo=sb.toString();
            isr.close();
            CategoryInfoVo categoryInfoVo=new CategoryInfoVo();
            JSONObject obj = JSONObject.parseObject(CategoryInfo);
            String plateFormId=obj.getString("appid");
            String depaths=obj.getString("depath");
            Integer  depath=Integer.parseInt(depaths);
            //String parentCategoryIds=obj.getString("parentCategoryId");
            //Integer parentCategoryId=Integer.parseInt(parentCategoryIds);
            categoryInfoVo.setPluteformid(plateFormId);
            categoryInfoVo.setDepth(depath);
            categoryInfoVo.setParentCategoryId(null);
             List<CategoryInfo> categoryInfos=productInfoService.categoryInfo(categoryInfoVo);
            String list = JSONObject.toJSONString(categoryInfos);
            return list;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 微信地址三级联动
     */
    @RequestMapping(value = "/selRegionInfoAllByDePath.action")
    @ResponseBody
    public Object selRegionInfoAllByDePath(HttpServletRequest request) throws Exception {
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String regionInfo=sb.toString();
            isr.close();
            ObjectMapper om = new ObjectMapper();
            RegionInfoVo regionInfoVo= om.readValue(regionInfo, RegionInfoVo.class);
            List<RegionInfo> regionInfos=  productInfoService.selRegionInfoAll(regionInfoVo);
            return regionInfos;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 根据用户查询收藏产品
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selFavoriteInfoByUserId")
    @ResponseBody
    public Object selFavoriteInfoByUserId(HttpServletRequest request) throws Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String userIds=sb.toString();
            isr.close();
            JSONObject obj = JSONObject.parseObject(userIds);
            String userId=obj.getString("userId");
            FavoriteInfo favoriteInfo=new FavoriteInfo();
            favoriteInfo.setUserId(Integer.parseInt(userId));
           List<FavoriteInfo> favoriteInfos= productInfoService.selFavoriteInfoByUserId(favoriteInfo);
            return favoriteInfos;
        }catch (Exception e){
            throw  e;
        }
    }

    /**
     * 查询店铺的下的产品
     * 根据平台Id和openId查询用户是否关注店铺
     * 关注返回最后时间关注店铺产品   没有关注返回该平台产品
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="selectShopProductBySupplier.action")
    @ResponseBody
    public Object  selectShopProductBySupplier(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String shopUser=sb.toString();
            isr.close();
            Page page=JSONObject.parseObject(shopUser, Page.class);
            JSONObject object=new JSONObject();
            Page productPage=null;
            if(page.getShopId()==null){
                productPage=shopUserService.selectShopProductBySupplier(page);
            }else {
                productPage=shopUserService.selectShopProductByshopId(page);
            }
            EasyUIPaginationAd ad = new EasyUIPaginationAd();
            ad.setPlatformId(page.getPluteformid());
            ad.setShopId(page.getShopId());
            EasyUIListResult<Ad> result = adService.getAdList(ad);
            ArrayList<String> list = new ArrayList<String>();
            //String = "http://192.168.1.104:80";
            for (Ad a : result.getRows()) {
                list.add(env.getProperty("url.showImage") + "?relUrl=" + a.getImgUrl() + "&proportion=16:9");
            }
            Integer imageNum = result.getTotal().intValue();
            object.put("resultPage",productPage);
            object.put("imageNum",imageNum);
            object.put("imageUrl",list);
            return  object.toJSONString();
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 兆美自营产品管理页面
     * @return
     */
    @RequestMapping("/zmSelfProductList")
    public ModelAndView zmSelfProductList() {
        ModelAndView modelAndView = new ModelAndView("business/zmSelfProductList");
        return modelAndView;
    }

    /**
     * 兆美产品列表页面，提供设置自营产品功能
     * @return
     */
    @RequestMapping("/zmProductList")
    public ModelAndView zmProductList() {
        ModelAndView modelAndView = new ModelAndView("business/zmProductList");
        return modelAndView;
    }

    @RequestMapping("/zmSelfProductDetailDlg")
    public ModelAndView zmSelfProductDetailDlg(String type, Long id) {
        ModelAndView modelAndView = new ModelAndView("business/zmSelfProductDetailDlg");
        modelAndView.addObject("type", type);
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @RequestMapping("/getSelfProductListByShopId")
    @ResponseBody
    public EasyUIListResult getSelfProductListByShopId(Long page, Long rows, Long shopId) {
        return productInfoService.getSelfProductListByShopId(page, rows, shopId);
    }

    @RequestMapping("/getSelfProductListByMallId")
    @ResponseBody
    public EasyUIListResult getSelfProductListByPlatformId(Long page, Long rows, Long platformId) {
        return productInfoService.getSelfProductListByPlatformId(page, rows, platformId);
    }

    @RequestMapping("/removeSelfProduct")
    @ResponseBody
    public EasyUIListResult removeSelfProduct(Long platformId) {
        return null;
    }

    @RequestMapping("/saveSelfProduct")
    @ResponseBody
    public EasyUIResult saveSelfProduct(String type, Long id, String[] productIds) {
        List<Long> prodIds = new ArrayList<Long>();
        if (productIds != null) {
            for (String productId : productIds) {
                if (productId.startsWith("prod")) {
                    prodIds.add(Long.valueOf(productId.replaceAll("prod", "")));
                }
            }
        }
        Long[] arr = new Long[prodIds.size()];
        if (ConfigConstants.SHOP_VARIABLE_NAME.equals(type)) {
            productInfoService.saveSelfProductByShopId(id, prodIds.toArray(arr));
        } else {
            productInfoService.saveSelfProductByMallId(id, prodIds.toArray(arr));
        }
        return new EasyUIResult();
    }

    @RequestMapping("/deleteSelfProduct")
    @ResponseBody
    public EasyUIResult deleteSelfProduct(String type, Long id, Long productId) {
        if (ConfigConstants.SHOP_VARIABLE_NAME.equals(type)) {
            productInfoService.deleteSelfProductByShopId(id, productId);
        } else {
            productInfoService.deleteSelfProductByMallId(id, productId);
        }
        return new EasyUIResult();
    }

    /**
     * 获取父分类下所有子分类，用于页面树形展现
     * @param request
     * @param parentId
     * @return
     */
    @RequestMapping("/getZMProductCategoriesByParentId")
    @ResponseBody
    public List<ProductCategory> getProductCategoriesByParentId(
            HttpServletRequest request, @RequestParam(value="id", required=false) Long parentId) {
        return couponService.getProductCategoriesByParentId(ConfigConstants.ZM_PLATFORM_ID, parentId);
    }

    @RequestMapping("/updateSelfProduct")
    @ResponseBody
    public EasyUIResult updateSelfProduct(HttpServletRequest request, String productId, Integer selfSell) {
        if (productId.startsWith("prod")) {
            productId = productId.substring("prod".length());
        }
        productInfoService.updateSelfProduct(Long.valueOf(productId), selfSell);
        return new EasyUIResult();
    }



    /**
     * 兆美自营产品管理页面-查询商家（商城和店铺）
     * @return
     */
    @RequestMapping("/getBusinessList")
    @ResponseBody
    public EasyUIListResult getBusinessList(Long page, Long rows, String filter) {
        return productInfoService.getBusinessList(page, rows, filter);
    }

    /**
     *
     * @return
     */
    @RequestMapping("/getSelfProductList")
    @ResponseBody
    public EasyUIListResult getSelfProductList(Long page, Long rows, String type, Long id) {
        if (ConfigConstants.SHOP_VARIABLE_NAME.equals(type)) {
            return productInfoService.getSelfProductListByShopId(page, rows, id);
        } else {
            return productInfoService.getSelfProductListByPlatformId(page, rows, id);
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping("/weChat/getSelfProductList")
    @ResponseBody
    public EasyUIListResult getSelfProductList(Long id) {
        return null;
        //return productInfoService.getSelfProductList();
    }

    //根据店铺id或者平台id查询商品客服手机号
    @RequestMapping(value = "/getServicePhoneByShopIdOrPId.action")
    @ResponseBody
    public String getServicePhoneByShopIdOrPId(@RequestBody String param,ProductInfo productInfo){
        try{
            if(param.startsWith("{")){
                productInfo=JSONObject.parseObject(param,ProductInfo.class);
            }
            String phone=productInfoService.getServicePhoneByShopIdOrPId(productInfo);
            return  phone;
        }catch (Exception e){
            e.printStackTrace();
            return "后台报错！";
        }

    }
}




