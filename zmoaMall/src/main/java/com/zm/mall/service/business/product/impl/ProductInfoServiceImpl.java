package com.zm.mall.service.business.product.impl;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.product.CategoryInfoResult;
import com.zm.mall.client.result.business.product.ProductInfoResult;
import com.zm.mall.client.service.business.product.ProductInfoService;
import com.zm.mall.client.vo.business.product.*;
import com.zm.mall.common.utils.OrderBySku;
import com.zm.mall.constant.ConfigConstants;
import com.zm.mall.dao.business.accountsUsers.UserListDao;
import com.zm.mall.dao.business.orders.OrderInfoDao;
import com.zm.mall.dao.business.product.*;
import com.zm.mall.dao.business.shop.ShopInfoDao;
import com.zm.mall.dao.system.MallConfigDao;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.product.*;
import com.zm.mall.domain.business.shop.PageableSupplierInfo;
import com.zm.mall.domain.system.MallConfig;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lp on 2016/11/12.
 */
@Service("productInfoService")
@WebService(endpointInterface = "com.zm.mall.client.service.business.product.ProductInfoService",serviceName = "ProductInfoService")
@PropertySource("classpath:urlPrefix.properties")
public class ProductInfoServiceImpl implements ProductInfoService {

    private static Log log = LogFactory.getLog(ProductInfoService.class);
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private SKUInfoDao skuInfoDao;
    @Autowired
    private ProductCategoriesDao productCategoriesDao;
    @Autowired
    private CategoryInfoDao categoryInfoDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private ProductTypeDao productTypeDao;
    @Autowired
    private  AttributeInfoDao attributeInfoDao;
    @Autowired
    private RegionInfoDao regionInfoDao;
    @Autowired
    private BrandInfoDao brandInfoDao;
    @Autowired
    private AttributeValueDao attributeValueDao;
    @Autowired
    private ProductAttributeDao productAttributeDao;
    @Autowired
    private SKUItemDao skuItemDao;
    @Autowired
    private SKURelationDao skuRelationDao;
    @Autowired
    private RelatedProductDao relatedProductDao;
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private ThumbnailSizeDao thumbnailSizeDao;
    @Autowired
    private ProductStationModesDao productStationModesDao;
    @Autowired
    private FavoriteInfoDao favoriteInfoDao;
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private UserListDao userListDao;
    @Resource
    private ShopInfoDao shopInfoDao;
    @Resource
    private MallConfigDao mallConfigDao;
    @Autowired
    private Environment env;

    public ProductInfoDao getProductInfoDao() {
        return productInfoDao;
    }

    public void setProductInfoDao(ProductInfoDao productInfoDao) {
        this.productInfoDao = productInfoDao;
    }

    /**
     * 分页查询产品
     * @param page
     * @return
     */
    @Override
    public Page selectProductInfo(Page page,ProductInfoVo productInfoVo) {

        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        page.setProductInfo(productInfo);
        //分页开始行数
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<ProductInfo> list = productInfoDao.selectByContion(page);
        //分类集合
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setPluteformid(page.getPluteformid());
        categoryInfo.setShopId(page.getShopId());
        List<CategoryInfo> categoryInfos = categoryInfoDao.selectAll(categoryInfo);
        //总条数
        Integer pageCount = productInfoDao.selectAllCount(page);
        //总页数
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        ProductInfo productInfo1 = new ProductInfo();
        if(list != null && list.size()>0){
            for (int k =0;k<list.size();k++){

                //图片路径
                String path = list.get(k).getThumbnailUrl1();
                if(path!=null){
                    path = path.replace("{0}", "T207x270_");
                }
                list.get(k).setThumbnailUrl1(path);

                List<String> imageUrls = productInfoDao.listProductImageUrl(
                        InsertProductImageDTO.newInstance()
                                .productId(list.get(k).getProductId())
                                .shopId(list.get(k).getShopId())
                                .platformId(list.get(k).getPluteformid()));
                list.get(k).setImageUrls(imageUrls);


                //查询库存
                SKUInfo skuInfo = new SKUInfo();
                skuInfo.setProductId(list.get(k).getProductId());
                skuInfo.setPluteformid(productInfo.getPluteformid());
                skuInfo.setShopId(productInfo.getShopId());
                Integer stock = skuInfoDao.selectSkuCount(skuInfo);
                list.get(k).setStock(stock);
                //获得商家
                List<SupplierInfo> supplierInfoList =supplierInfoDao.selAllById(list.get(k).getSupplierId());
                if(supplierInfoList.size()>0){
                    list.get(k).setSupplier(supplierInfoList.get(0).getName());
                }else{
                    list.get(k).setSupplier("");
                }
                BrandInfo brandInfo = new BrandInfo();
                brandInfo.setBrandId(list.get(k).getBrandId());
                brandInfo.setPluteformid(productInfo.getPluteformid());
                brandInfo.setShopId(productInfo.getShopId());
                //获得品牌
                List<BrandInfo> brandInfoList =brandInfoDao.selBrandInfoById(brandInfo);
                if(brandInfoList.size()>0) {
                    list.get(k).setBrand(brandInfoList.get(0).getBrandName());
                }else{
                    list.get(k).setBrand("");
                }
                ProductType productType = new ProductType();
                productType.setTypeId(list.get(k).getTypeId());
                productType.setPluteformid(productInfo.getPluteformid());
                productType.setShopId(productInfo.getShopId());
                //获得类型
                List<ProductType> productTypeList = productTypeDao.selectTypeById(productType);
                if(productTypeList.size()>0) {
                    list.get(k).setProductType(productTypeList.get(0).getTypeName());
                }else {
                    list.get(k).setProductType("");
                }
                //分类结构
                String str = "";
                if(!("").equals(productInfo.getSearchProductCategories()) && productInfo.getSearchProductCategories().length()>0){
                    String[] category = productInfo.getSearchProductCategories().split("\\|");
                    list.get(k).setSearchProductCategories(productInfo.getSearchProductCategories());
                    //遍历数组
                    for (int j=0;j<category.length;j++){
                        //遍历集合
                        for (int i=0;i<categoryInfos.size();i++){
                            if (Integer.parseInt(category[j]) == categoryInfos.get(i).getCategoryId()){
                                str +=categoryInfos.get(i).getName()+">>";
                            }
                        }
                    }
                }else {
                    productInfo1.setProductId(list.get(k).getProductId());
                    List<ProductCategories> productCategories = productCategoriesDao.selectByProductId(productInfo1);
                    if(productCategories.size()>0){
                        //分割字符串
                        String[] category = productCategories.get(0).getCategoryPath().split("\\|");
                        list.get(k).setSearchProductCategories(productCategories.get(0).getCategoryPath());
                        //遍历数组
                        for (int j=0;j<category.length;j++){
                            //遍历集合
                            for (int i=0;i<categoryInfos.size();i++){
                                if (Integer.parseInt(category[j]) == categoryInfos.get(i).getCategoryId()){
                                    str +=categoryInfos.get(i).getName()+">>";
                                }
                            }
                        }
                    }
                }
                list.get(k).setMainCategoryPath(str);
            }
            List<ProductInfoResult> productInfoResultList = new ArrayList<ProductInfoResult>();
            for(int i =0;i<list.size();i++){
                //list.get(i).setImageUrl("" + list.get(i).getImageUrl());
                productInfoResultList.add(SpaceBeanCopy.productInfoToProductInfoResult(list.get(i)));
            }
            page.setResultProductInfo(productInfoResultList);
            return page;
        }
        return page;
    }

    /**
     * 获得扩展属性
     * @param productInfoVo
     * @return
     */
    @Override
    public List<AttributeInfo> selSpecById(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        return attributeInfoDao.selSpecById(productInfo);
    }

    /**
     * 获得规格
     * @param productInfoVo
     * @return
     */
    @Override
    public List<AttributeInfo> selSkuById(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        return attributeInfoDao.selSkuById(productInfo);
    }

    /**
     * 获得库存
     * @param skuInfoVo
     * @return
     */
    @Override
    public List<SKUInfo> selSkuInfoById(SKUInfoVo skuInfoVo) {
        SKUInfo skuInfo = SpaceBeanCopy.skuInfoVoToskuInfo(skuInfoVo);
        return skuInfoDao.selSkuById(skuInfo);
    }

    /**
     * 获得分类方法
     * @param categoryInfoVo
     * @return
     */
    @Override
    public List<CategoryInfo> categoryInfo(CategoryInfoVo categoryInfoVo) {
        CategoryInfo categoryInfo = SpaceBeanCopy.categoryInfoVoTocategoryInfo(categoryInfoVo);
        List<CategoryInfo> cate = new ArrayList<CategoryInfo>();
        List<CategoryInfo> categoryInfos = categoryInfoDao.selectAll(categoryInfo);
        //第一次请求返回默认值
        if(categoryInfo.getParentCategoryId() == null || categoryInfo.getDepth()== null){
            for (int c=0;c<categoryInfos.size();c++){
                if(categoryInfos.get(c).getDepth()==1){
                    cate.add(categoryInfos.get(c));
                }
            }
        }else{
            //第二次根据深度 depath 。父Id  parentId 查询
            int size = categoryInfos.size();
            for (int c=0;c<categoryInfos.size();c++){
//System.out.println(categoryInfos.get(c).getDepth()+"==" +categoryInfo.getDepth() + "               "+ categoryInfos.get(c).getParentCategoryId()+ "=="+ categoryInfo.getParentCategoryId() );
                if(categoryInfos.get(c).getDepth().equals(categoryInfo.getDepth()) && categoryInfos.get(c).getParentCategoryId().equals(categoryInfo.getParentCategoryId())){
                    cate.add(categoryInfos.get(c));
                }
            }
        }
        return cate;
    }


    /**
     * 录入之前的查询相关数据
     * @return
     */
    @Override
    public ProductInfo preProductInfo(String platformId, Long shopId) {
        ProductInfo productInfo = new ProductInfo();
        //获得   商家  、类型  、以及产地
        productInfo.setSupplierInfoList(supplierInfoDao.selAllSupplierInfo(platformId, shopId));
        productInfo.setProductTypeList(productTypeDao.selAllProductType(platformId, shopId));
        RegionInfoVo regionInfoVo = new RegionInfoVo();
        productInfo.setRegionInfoList(this.selRegionInfoAll(regionInfoVo));
        return productInfo;
    }

    /**
     * 获得类型属性
     * @param attributeInfoVo
     * @return
     */
    @Override
    public  List<AttributeInfo> selAttributeInfo(AttributeInfoVo attributeInfoVo) {
        AttributeInfo attributeInfo = SpaceBeanCopy.attributeInfoVoToattributeInfo(attributeInfoVo);
        //判断状态
        if(attributeInfo.getStatus() ==0){
            //status 为 0   时    根据类型ID查询属性
            return attributeInfoDao.selProperty(attributeInfo);
        }else{
            //status 为 0   时    根据类型ID查询规格
            return attributeInfoDao.selSpec(attributeInfo);
        }
    }

    /**
     * 查产地
     * @param regionInfoVo
     * @return
     */
    @Override
    public List<RegionInfo> selRegionInfoAll(RegionInfoVo regionInfoVo) {
        RegionInfo regionInfo = SpaceBeanCopy.regionInfoVoToregionInfo(regionInfoVo);
        List<RegionInfo> RegionInfo = new ArrayList<RegionInfo>();
        //第一次请求返回默认值
        if(regionInfo.getParentId() == null || regionInfo.getDepth()== null){
            RegionInfo =  regionInfoDao.selRegionInfoFirst();
        }else{
            //根据深度  depath 。 父Id  parentId 查询
            RegionInfo = regionInfoDao.selRegionInfoScound(regionInfo);
        }
        return RegionInfo;
    }

    /**
     * 查品牌
     * @param attributeInfoVo
     * @return
     */
    @Override
    public List<BrandInfo> selBrandInfoByType(AttributeInfoVo attributeInfoVo) {
        AttributeInfo AttributeInfo =  SpaceBeanCopy.attributeInfoVoToattributeInfo(attributeInfoVo);
        List<BrandInfo> BrandInfoList= brandInfoDao.selBrandInfoByType(AttributeInfo);
        return BrandInfoList;
    }

    /**
     * 获得统一调整价
     * @param categoryInfoVo
     * @return
     */
    @Override
    public CategoryInfo price(CategoryInfoVo categoryInfoVo) {
        CategoryInfo categoryInfo = SpaceBeanCopy.categoryInfoVoTocategoryInfo(categoryInfoVo);
        return categoryInfoDao.price(categoryInfo);
    }

    /**
     * 编码校验
     * @param productInfoVo
     * @return
     */
    @Override
    public ProductInfo productCode(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        List<ProductInfo> productInfos = productInfoDao.productInfoCode(productInfo);
        if(productInfos.size()>0){
            return productInfos.get(0);
        }else{
            return new ProductInfo();
        }
    }

    /**
     * 添加产品数据
     * @param productInfoVo
     * @return
     */
    @Override
    @Transactional
    public Long insertProduct(ProductInfoVo productInfoVo) {
        //插入数据
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        Integer i = productInfoDao.InsertProduct(productInfo);
        Long productId = productInfo.getProductId();
        //判断插入是否成功
        if(i>0){
            //查询分类路径id
            if(productInfo.getSearchProductCategories()!=""){
                    this.insertCategory(productInfoVo,productId);
            }
            //插入属性
            if(!("").equals(productInfo.getSpecStr()) && productInfo.getSpecStr().length()>0){
                this.insertAttrbute(productInfoVo,productId);
            }
            //添加规格
            if(!("").equals(productInfo.getSkusStr()) && productInfo.getSkusStr().length()>0){
               this.insertSkuInfoIntemRealtion(productInfoVo,productId);
            }
            //添加关联
            if(!("").equals(productInfo.getRelatedProductId()) && productInfo.getRelatedProductId().length()>0){
                this.insertProductRealtion(productInfoVo,productId);
            }
            //添加推荐，热销属性
            if(productInfo.getStation()!=null && productInfo.getStation().length>0 ) {
                this.AddStationModes(productInfo.getStation(), productInfo.getPluteformid(), productInfo.getShopId(), productId);
            }
            return productId;
        }else {
            return null;
        }
    }

    /**
     * 添加产品时添加分类信息
     * @param productInfoVo
     * @param productId
     * @return
     */
    public Integer insertCategory(ProductInfoVo productInfoVo,Long productId){
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        //查询分类路径id
        if(productInfo.getSearchProductCategories()!=""){
            //插入分类关系
            List<ProductCategories> categorieses = new ArrayList<ProductCategories>();
            String[] arr = productInfo.getSearchProductCategories().split("\\*");
            List<String[]> str = new ArrayList<String[]>();
            String[] category = new String[]{};
            for(int cate =1; cate<arr.length;cate++){
                category = arr[cate].split(",");
                str.add(category);
            }
            for(int s=0;s<str.size();s++){
                ProductCategories productCategories = new ProductCategories();
                String[] cate = str.get(s);
                productCategories.setCategoryId(Integer.parseInt(cate[cate.length-1]));
                productCategories.setProductId(productId);
                String categoryPath1="";
                for(int c=1;c<cate.length;c++){
                    if(c==1){
                        categoryPath1+=cate[c];
                    }else{
                        categoryPath1+="|"+cate[c];
                    }
                }
                productCategories.setCategoryPath(categoryPath1);
                productCategories.setPluteformid(productInfo.getPluteformid());
                productCategories.setShopId(productInfo.getShopId());
                categorieses.add(productCategories);
            }
            int i1;
            for(i1 = 0;i1<categorieses.size();i1++){
                productCategoriesDao.InsertPath(categorieses.get(i1));
            }
            return i1;
        }
        return null;
    }

    /**
     * 添加产品时添加属性信息
     * @param productInfoVo
     * @param productId
     * @return
     */
    public Integer insertAttrbute(ProductInfoVo productInfoVo,Long productId){
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        if(!("").equals(productInfo.getSpecStr()) && productInfo.getSpecStr().length()>0){
            String[] str =productInfo.getSpecStr().split("\\+");
            int a;
            for(a = 0;a<str.length;a++){
                String[] arr = str[a].split("\\|");
                //判断是否选中值
                if(arr.length>2){
                    ProductAttribute productAttributes = new ProductAttribute();
                    //判断渲染样式  根据情况添加数据
                    if(("1").equals(arr[0])){
                        String[] checkbox = arr[2].split("\\^");
                        for(int c=0;c<checkbox.length;c++){
                            //checkbox关系插入中间表
                            productAttributes.setAttributeId(Long.parseLong(arr[1]));
                            productAttributes.setProductId(productId);
                            productAttributes.setValueId(Long.parseLong(checkbox[c]));
                            productAttributes.setPluteformid(productInfo.getPluteformid());
                            productAttributes.setShopId(productInfo.getShopId());
                            productAttributeDao.insertProductAttribute(productAttributes);
                        }
                    }else if(("2").equals(arr[0])){
                        //插入文本属性
                        AttributeValue attributeValue = new AttributeValue();
                        attributeValue.setAttributeId(Long.parseLong(arr[1]));
                        attributeValue.setDisplaySequence(-1);
                        attributeValue.setValueStr(arr[2]);
                        attributeValue.setPluteformid(productInfo.getPluteformid());
                        Integer insertCount = attributeValueDao.insertAttributeValue(attributeValue);
                        if(insertCount>0) {
                            productAttributes.setAttributeId(Long.parseLong(arr[1]));
                            productAttributes.setProductId(productId);
                            productAttributes.setValueId(attributeValue.getValueId());
                            productAttributes.setPluteformid(productInfo.getPluteformid());
                            productAttributes.setShopId(productInfo.getShopId());
                            productAttributeDao.insertProductAttribute(productAttributes);
                        }
                    }else{
                        //radio关系插入中间表
                        productAttributes.setAttributeId(Long.parseLong(arr[1]));
                        productAttributes.setProductId(productId);
                        productAttributes.setValueId(Long.parseLong(arr[arr.length - 1]));
                        productAttributes.setPluteformid(productInfo.getPluteformid());
                        productAttributes.setShopId(productInfo.getShopId());
                        productAttributeDao.insertProductAttribute(productAttributes);
                    }
                }
            }
            return a;
        }
        return null;
    }

    /**
     * 添加产品时添加规格信息
     * @param productInfoVo
     * @param productId
     * @return
     */
    public Integer insertSkuInfoIntemRealtion(ProductInfoVo productInfoVo,Long productId){
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        if(!("").equals(productInfo.getSkusStr()) && productInfo.getSkusStr().length()>0){
            String[] sku = productInfo.getSkusStr().split("\\*");
            int e;
            for(e = 0;e<sku.length;e++){
                SKUInfo resultskuInfo =null;
                String[] skuitem = sku[e].split("\\+");
                for(int item=0;item<skuitem.length;item++){
                    if(item==0){
                        String[] a = skuitem[item].split("\\^");
                        for(int s = 0;s<a.length;s++){
                            String[] skus = a[s].split("\\|");
                            SKUItem skuItem = new SKUItem();
                            skuItem.setAttributeId(Long.parseLong(skus[0]));
                            skuItem.setProductId(productId);
                            skuItem.setValueId(Long.parseLong(skus[1]));
                            skuItem.setPluteformid(productInfo.getPluteformid());
                            skuItem.setShopId(productInfo.getShopId());
                            //查看skuitem是否有对应值
                            SKUItem resultsku  = skuItemDao.selSKUItem(skuItem);
                            if(resultsku==null){
                                //没有则添加
                                skuItemDao.insertSKUItem(skuItem);
                            }
                        }
                    }else{
                        //添加SKU表数据
                        String [] skuitems = skuitem[item].split("\\^");
                        SKUInfo skuInfo = new SKUInfo();
                        skuInfo.setProductId(productId);
                        skuInfo.setStock(Integer.parseInt(skuitems[5]));
                        skuInfo.setAlertStock(Integer.parseInt(skuitems[6]));
                        skuInfo.setSku(skuitems[0]);
                        skuInfo.setCostPrice(Double.parseDouble(skuitems[2]));
                        if(skuitems[7]!=null && skuitems[7] !=""){
                            skuInfo.setWeight(Integer.parseInt(skuitems[7]));
                        }
                        skuInfo.setUpSelling(Integer.parseInt(skuitems[8]));
                        //单价
                        skuInfo.setSinglePrice(Double.parseDouble(skuitems[9]));
                        skuInfo.setSalePrice(Double.parseDouble(skuitems[1]));
                        skuInfo.setProfit(Double.parseDouble(skuitems[3]));
                        skuInfo.setPluteformid(productInfo.getPluteformid());
                        skuInfo.setShopId(productInfo.getShopId());
                        Integer skuCount =  skuInfoDao.insertSKUInfo(skuInfo);
                        //掺入成功时插入关联表数据
                        if(skuCount>0){
                            resultskuInfo = skuInfoDao.selSKUInfo(skuInfo);
                            List<SKUItem> skuItems = new ArrayList<SKUItem>();
                            String[] a = skuitem[0].split("\\^");
                            //获得关联关系
                            for(int s = 0;s<a.length;s++){
                                String[] skus = a[s].split("\\|");
                                SKUItem skuItem = new SKUItem();
                                skuItem.setAttributeId(Long.parseLong(skus[0]));
                                skuItem.setProductId(productId);
                                skuItem.setValueId(Long.parseLong(skus[1]));
                                skuItem.setPluteformid(productInfo.getPluteformid());
                                skuItem.setShopId(productInfo.getShopId());
                                SKUItem result  = skuItemDao.selSKUItem(skuItem);
                                skuItems.add(result);
                            }
                            //创建关联关系
                            for(int l=0;l<skuItems.size();l++){
                                SKURelation skuRelation = new SKURelation();
                                skuRelation.setSpecId(skuItems.get(l).getSpecId());
                                skuRelation.setProductId(productId);
                                skuRelation.setSkuId(resultskuInfo.getSkuId());
                                skuRelation.setPluteformid(productInfo.getPluteformid());
                                skuRelation.setShopId(productInfo.getShopId());
                                skuRelationDao.insetSKURelation(skuRelation);
                            }
                        }
                    }
                }
            }
            return e;
        }
        return null;
    }

    /**
     * 添加产品时添加商品关联信息
     * @param productInfoVo
     * @param productId
     * @return
     */
    public Integer insertProductRealtion(ProductInfoVo productInfoVo,Long productId){
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        //判断参数是否为空
        if(!("").equals(productInfo.getRelatedProductId()) && productInfo.getRelatedProductId().length()>0){
            //参数分割成 数组   值是被关联的Id
            String[] RelatedProductId = productInfo.getRelatedProductId().split("\\+");
            RelatedProduct relatedProduct = new RelatedProduct();
            int r;
            for(r=0;r<RelatedProductId.length;r++ ){
                relatedProduct.setRelatedId(Long.parseLong(RelatedProductId[r]));
                relatedProduct.setProductId(productId);
                relatedProduct.setPluteformid(productInfo.getPluteformid());
                relatedProduct.setShopId(productInfo.getShopId());
                relatedProductDao.insertRelatedProduct(relatedProduct);
            }
            return r;
        }
        return null;
    }
    /**
     * 更新产品图片路径
     * @param str
     * @return
     */
    @Transactional
    @Override
    public Integer upProuctUrlPrice(List<String> str,Long productResultID,String pluteformid) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productResultID);
        ProductImage productImage = new ProductImage();
        //第一个 条路径 放在  主表记录里    第二条开始放在 外键表
        for(int i=0;i<str.size();i++) {
            if(i==0){
                String[] path = str.get(i).split("\\*");
                productInfo.setThumbnailUrl1(path[1]);
                productInfo.setImageUrl(path[0]);
                productInfo.setPluteformid(pluteformid);
                productInfoDao.updateProduct(productInfo);
            }else{
                String[] path = str.get(i).split("\\*");
                productImage.setThumbnailUrl1(path[1]);
                productImage.setImageUrl(path[0]);
                productImage.setProductId(productResultID);
                productImage.setPluteformid(pluteformid);
                productImageDao.insertProductImage(productImage);
            }
        }
        return null;
    }

    @Transactional
    @Override
    public Integer updateProductImageUrl(List<String> str, Long productResultID, String pluteformid, Long shopId) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productResultID);
        ProductImage productImage = new ProductImage();
        //第一个 条路径 放在  主表记录里    第二条开始放在 外键表
        for(int i=0;i<str.size();i++){
            if(i==0){
                String[] path = str.get(i).split("\\*");
                productInfo.setThumbnailUrl1(path[1]);
                productInfo.setImageUrl(path[0]);
                productInfo.setPluteformid(pluteformid);
                productInfo.setShopId(shopId);
                productInfoDao.updateProduct(productInfo);
            }else{
                String[] path = str.get(i).split("\\*");
                productImage.setThumbnailUrl1(path[1]);
                productImage.setImageUrl(path[0]);
                productImage.setProductId(productResultID);
                productImage.setPluteformid(pluteformid);
                productImage.setShopId(shopId);
                productImageDao.insertProductImage(productImage);
            }
        }
        return null;
    }

    /**
     * 逻辑删除
     * @param delProductId
     * @return
     */
    @Override
    @Transactional
    public Integer delProduct(String delProductId,String pluteformid) {
        String[] productId = delProductId.split("\\+");
        ProductInfo productInfo = new ProductInfo();
        Integer count = 0;
        //根据AppId  、 产品ID删除 产品  返回删除数量
        for(int i =0;i<productId.length;i++){
            productInfo.setProductId(Long.parseLong(productId[i]));
            productInfo.setPluteformid(pluteformid);
            Integer productCount = productInfoDao.delProduct(productInfo);
            count +=productCount;
        }
        return count;
    }

    /**
     *  预更新 根据id查询商品
     * @param productInfoVo
     * @return
     */
    @Override
    public ProductInfoResult selProductInfoById(ProductInfoVo productInfoVo) {
        //转换
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        //产品
        ProductInfo product = productInfoDao.selProductInfoById(productInfo);
        //获的供应商
        product.setSupplierInfoList(supplierInfoDao.selAllSupplierInfo(productInfoVo.getPluteformid(), productInfoVo.getShopId()));
        //获得类型
        product.setProductTypeList(productTypeDao.selAllProductType(productInfoVo.getPluteformid(), productInfoVo.getShopId()));
        //品牌
        AttributeInfo attributeInfo = new AttributeInfo();
        attributeInfo.setTypeId(product.getTypeId());
        List<BrandInfo> brandInfos = brandInfoDao.selBrandInfoByType(attributeInfo);
        product.setBrandInfos(brandInfos);
        ProductInfoResult productInfoResult = SpaceBeanCopy.productInfoToProductInfoResult(product);
        return productInfoResult;
    }

    /**
     * 根据子节点定位产地
     * @param regionInfoVo
     * @return
     */
    @Override
    public SelProductUtil selRegionByProductId(RegionInfoVo regionInfoVo) {
        RegionInfo regionInfo = SpaceBeanCopy.regionInfoVoToregionInfo(regionInfoVo);
        SelProductUtil selProductRegionInfo = new SelProductUtil();
        List<RegionInfo> regionInfoList;
        if(regionInfo.getRegionId()!=null){
            //RegionId不为空  根据Path查询
            RegionInfo region = regionInfoDao.selRegionInfoPath(regionInfo);
            String regionpath = region.getPath()+String.valueOf(region.getRegionId());
            regionpath =regionpath.replace("0,","");
            String[] path = regionpath.split("\\,");
            for(int i=0;i<path.length;i++){
                //判断 索引如果等于0 调用方法时不用传参数
                if(i==0){
                    regionInfoList = this.selRegionInfoAll(regionInfoVo);
                }else{
                    regionInfo.setRegionId(Integer.parseInt(path[i]));
                    regionInfoList =  regionInfoDao.regionInfos(regionInfo);
                }
                //根据depath深度  将第各级别的分类与产品分类个级别的分类Id 按等级封装
                if(regionInfoList.size()>0 && regionInfoList!=null){
                    if(regionInfoList.get(0).getDepth()==1){
                        selProductRegionInfo.setFirst(regionInfoList);
                        selProductRegionInfo.setFirstId(Integer.parseInt(path[i]));
                    }else if(regionInfoList.get(0).getDepth()==2){
                        selProductRegionInfo.setSecond(regionInfoList);
                        selProductRegionInfo.setSecondId(Integer.parseInt(path[i]));
                    }else if(regionInfoList.get(0).getDepth()==3){
                        selProductRegionInfo.setThree(regionInfoList);
                        selProductRegionInfo.setThreeId(Integer.parseInt(path[i]));
                    }
                }
            }
        }else{
            //RegionId为空 查询第一级分类
            regionInfoList = this.selRegionInfoAll(regionInfoVo);
            selProductRegionInfo.setFirst(regionInfoList);
        }
        return selProductRegionInfo;
    }

    /**
     * 根据子节点定位分类
     * @param categoryInfoVo
     * @return
     */
    @Override
    public SelProductUtil selCateByPath(CategoryInfoVo categoryInfoVo) {
        CategoryInfo categoryInfo = SpaceBeanCopy.categoryInfoVoTocategoryInfo(categoryInfoVo);
        SelProductUtil<CategoryInfo> selProductCategoryInfo = new SelProductUtil();
        List<CategoryInfo> categoryInfos;
        //判断Path参数是否为空
        if (categoryInfo.getPath()!=null){
            //Path不为空  根据Path查询
            String[] catePath = categoryInfo.getPath().split("\\|");
            for(int i=0;i<catePath.length;i++){
                //判断 索引如果等于0 调用方法时不用传参数
                if(i==0){
                    categoryInfos = this.categoryInfo(categoryInfoVo);
                }else{
                    categoryInfo.setCategoryId(Integer.parseInt(catePath[i]));
                    categoryInfos = categoryInfoDao.categoryInfoListById(categoryInfo);
                }
                //根据depath深度  将第各级别的分类与产品分类个级别的分类Id 按等级封装
                if(categoryInfos.size()>0 && categoryInfos!=null){
                    if(categoryInfos.get(0).getDepth()==1){
                        selProductCategoryInfo.setFirst(categoryInfos);
                        selProductCategoryInfo.setFirstId(Integer.parseInt(catePath[i]));
                    }else if(categoryInfos.get(0).getDepth()==2){
                        selProductCategoryInfo.setSecond(categoryInfos);
                        selProductCategoryInfo.setSecondId(Integer.parseInt(catePath[i]));
                    }else if(categoryInfos.get(0).getDepth()==3){
                        selProductCategoryInfo.setThree(categoryInfos);
                        selProductCategoryInfo.setThreeId(Integer.parseInt(catePath[i]));
                    }
                }
            }
        }else{
            //Path为空 查询第一级分类
            categoryInfos = this.categoryInfo(categoryInfoVo);
            selProductCategoryInfo.setFirst(categoryInfos);
        }
        return selProductCategoryInfo;
    }
    public List<SelProductUtil<CategoryInfo>> preUpDateCateGory(ProductInfoVo productInfoVo ){
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        List<SelProductUtil<CategoryInfo>> selProductUtilList = new ArrayList<SelProductUtil<CategoryInfo>>();
        List<CategoryInfo> categoryInfoList;
        //获得产品的  所属分类
        List<ProductCategories> productCategoriesList = productCategoriesDao.selectByProductId(productInfo);
        if(productCategoriesList.size()>0){
            //遍历分类 将分类的 一二三级，封装到公用类中
            for (int i = 0;i<productCategoriesList.size();i++){
                SelProductUtil<CategoryInfo> selProductUtil = new SelProductUtil();
                //循环遍历  当前索引的分类路径
                String[] str = productCategoriesList.get(i).getCategoryPath().split("\\|");
                categoryInfoList = categoryInfoDao.categoryInfoListByIdOnIn(str);
                //根据路径查出三级分类  根据索引 添加到  SelProductUtil类中
                if(categoryInfoList.size()>=1){
                        selProductUtil.setFirstDome(categoryInfoList.get(0));
                        selProductUtil.setFirstId(categoryInfoList.get(0).getCategoryId());
                    }
                if(categoryInfoList.size()>=2){
                        selProductUtil.setSecondDome(categoryInfoList.get(1));
                        selProductUtil.setSecondId(categoryInfoList.get(1).getCategoryId());
                    }
                if(categoryInfoList.size()>=3){
                        selProductUtil.setThreeDome(categoryInfoList.get(2));
                        selProductUtil.setThreeId(categoryInfoList.get(2).getCategoryId());
                    }
                //添加  SelProductUtil类到集合中
                selProductUtilList.add(selProductUtil);
            }
        }
        return selProductUtilList;
    }

    /**
     * 根据规格查询
     * @param cate
     * @param typeId
     * @param appearence
     * @param thick
     * @param productName
     * @param currentPage
     * @return
     */
    @Override
    public Page selProductBySku(String cate, Integer typeId, String appearence, String thick, String productName, String productCode,Integer currentPage) {
        Page page = new Page();
        OrderBySku orderBySku = new OrderBySku();
        orderBySku.setBeginNumber((currentPage-1)*page.getPageSize());

        if(appearence !="" && appearence !=null ){
            String[] app = appearence.split("\\|");
            orderBySku.setBmId(Long.parseLong(app[0]));
            orderBySku.setBmValue(Long.parseLong(app[1]));
        }
        if(thick !="" && thick !=null ){
            String[] th = thick.split("\\|");
            orderBySku.setHdId(Long.parseLong(th[0]));
            orderBySku.setHdValue(Long.parseLong(th[1]));
        }
        orderBySku.setSearchProductCategories(cate);
        orderBySku.setTypeId(typeId);
        orderBySku.setProductName(productName);
        orderBySku.setPageSize(page.getPageSize());
        orderBySku.setProductCode(productCode);
        Integer totalCount = productInfoDao.selectTotalCount(orderBySku);
        List<ProductInfo> productInfos = productInfoDao.selProductBySku(orderBySku);
        List<ProductInfoResult> productInfoResultList = new ArrayList<ProductInfoResult>();
        for(int i =0;i<productInfos.size();i++){
            //图片路径
            String path = productInfos.get(i).getThumbnailUrl1();
            if(path!=null){
                path = path.replace("{0}", "T207x270_");
            }
            productInfos.get(i).setThumbnailUrl1(path);
            productInfoResultList.add(SpaceBeanCopy.productInfoToProductInfoResult(productInfos.get(i)));
        }
        page.setResultProductInfo(productInfoResultList);
        page.setCurrentPage(currentPage);
        page.setTotalCount(totalCount);
        return page;
    }
    /**
     *  白洋洋   根据产品Id查询对应的规格信息
     */
    @Override
    public List<AttributeInfo> selectAttribute(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        return attributeInfoDao.selSkuById(productInfo);
    }

    @Override
    public List<ProductInfo> selProductRelated(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        return productInfoDao.selProductById(productInfo);
    }

    /**
     * 获得产品路径
     * @param productInfoVo
     * @return
     */
    @Override
    public List<ProductImage> selProductImageByProductId(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        //获得产品主表的图片路径
        ProductImage productImage = new ProductImage();
        List<ProductImage> productImages = new ArrayList<ProductImage>();
        ProductInfo  productInfoTwo= productInfoDao.selProductInfoById(productInfo);
        productImage.setProductId(productInfoTwo.getProductId());
        productImage.setImageUrl(env.getProperty("url.showImage") + "?relUrl=" + productInfoTwo.getImageUrl());
        //productImage.setThumbnailUrl1(productInfoTwo.getThumbnailUrl1());
        productImage.setThumbnailUrl1(env.getProperty("url.showImage") + "?relUrl=" + productInfoTwo.getImageUrl());
       productImages.add(productImage);
        //获得外键Image表的路径   将主表路径添加到集合中
        List<ProductImage> productImagesTwo =  productImageDao.selProductImageByproductId(productImage);
        for (ProductImage prodImg : productImagesTwo) {
            prodImg.setImageUrl(env.getProperty("url.showImage") + "?relUrl=" + prodImg.getImageUrl());
            prodImg.setThumbnailUrl1(env.getProperty("url.showImage") + "?relUrl=" + prodImg.getImageUrl());
        }
        productImages.addAll(productImagesTwo);
        return productImages;
    }

    /**
     * 更新产品
     * @param productInfoVo
     * @return
     */
    @Transactional
    public Long upDateProduct(ProductInfoVo productInfoVo) {
        //更新数据
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        Integer i = productInfoDao.updateProductById(productInfo);
        //判断插入是否成功
        if(i>0){
            Long productId = productInfo.getProductId();
            productImageDao.delProductImage(productInfo);
            skuInfoDao.delSKUInfo(productInfo);
            skuItemDao.delSKUItem(productInfo);
            skuRelationDao.delSKURelation(productInfo);
            relatedProductDao.delRelatedProduct(productInfo);
            attributeValueDao.delAttributeValue(productInfo);
            productAttributeDao.delProductAttribute(productInfo);
            productCategoriesDao.delProductCategoriesDao(productInfo);
            //删除热销属性
            this.delProductStationModesByProductId(productInfoVo);
                //插入分类
                if(productInfo.getSearchProductCategories()!=""){
                    this.insertCategory(productInfoVo,productId);
                }
                //插入属性
                if(!("").equals(productInfo.getSpecStr()) && productInfo.getSpecStr().length()>0){
                    this.insertAttrbute(productInfoVo,productId);
                }
                //添加规格
                if(!("").equals(productInfo.getSkusStr()) && productInfo.getSkusStr().length()>0){
                    this.insertSkuInfoIntemRealtion(productInfoVo,productId);
                }
                //添加关联
                if(!("").equals(productInfo.getRelatedProductId()) && productInfo.getRelatedProductId().length()>0){
                    this.insertProductRealtion(productInfoVo,productId);
                }
                //添加推荐，热销属性
                if(productInfo.getStation()!=null && productInfo.getStation().length>0 ) {
                    this.AddStationModes(productInfo.getStation(), productInfo.getPluteformid(), productInfo.getShopId(), productId);
                }
                return productId;
        }else {
            return null;
        }
    }

    /**
     * 获得图片大小
     * @return
     */
    @Override
    public List<ThumbnailSize> selThumbnailSizeSpec() {
        return thumbnailSizeDao.selThumbnailSizeAllByProduct();
    }


    //webservice数据同步
    @Transactional
    public Integer web_InsertProduct(ProductInfo productInfo)throws Exception{
            Integer result = productInfoDao.web_insertProduct(productInfo);
        return result;
    }
    @Transactional
    public  Integer web_updateProduct(ProductInfo productInfo) throws Exception{
            Integer result = productInfoDao.web_updateProduct(productInfo);
        return result;
    }
    @Transactional
    public Integer web_deleteProduct(List<Long> productInfo,Integer status) throws Exception{
            Integer result = productInfoDao.web_deleteProduct(productInfo,status);
        return result;
    }
    /**
     * 根据产品ID查询产品
     * @param productInfoVo
     * @return
     */
    @Override
    public ProductInfo productId(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        List<ProductInfo> productInfos = productInfoDao.productInfoId(productInfo);
        for (ProductInfo prod : productInfos) {
            if (prod.getDescription() != null)
                prod.setDescription(prod.getDescription().replaceAll("src=\"/ueditor", "src=\"" + ConfigConstants.ZM_MALL_DOMAIN_NAME + "/ueditor"));
        }
        if (productInfos != null && productInfos.size() != 0) {

            if(productInfos.get(0).getThumbnailUrl1() != null){
                //String path = productInfos.get(0).getThumbnailUrl1();
                String path = productInfos.get(0).getImageUrl();
                if(path!=null){
                    //path = path.replace("{0}", "T207x270_");
                    productInfos.get(0).setImageUrl(env.getProperty("url.showImage") + "?proportion=1:1&relUrl=" + path);
                    productInfos.get(0).setThumbnailUrl1(env.getProperty("url.showImage") + "?proportion=1:1&relUrl=" + path);
                }
                //新版图片
                //productInfos.get(0).setThumbnailUrl1(path);
                List<String> imageUrls = productInfoDao.listProductImageUrl(
                        InsertProductImageDTO.newInstance()
                                .productId(productInfos.get(0).getProductId())
                                .shopId(productInfos.get(0).getShopId())
                                .platformId(productInfos.get(0).getPluteformid()));
                productInfos.get(0).setImageUrls(imageUrls);

                if(productInfos.size()>0){
                    return productInfos.get(0);
                }else{
                    return new ProductInfo();
                }
            } else {
                return new ProductInfo();
            }

        } else {
            return new ProductInfo();
        }
    }

    /**
     * 添加商品推荐
     * @param favoriteInfoVo
     * @return
     */
    @Transactional
    @Override
    public Integer AddShopFavorite(FavoriteInfoVo favoriteInfoVo) {
        FavoriteInfo favoriteInfo = SpaceBeanCopy.favoriteInfoVoTofavoriteInfo(favoriteInfoVo);
        Date date = new Date();
        favoriteInfo.setType(1);
//        favoriteInfo.setCreatedDate(date);
        Integer integer = favoriteInfoDao.AddShopFavorite(favoriteInfo);
        return integer;
    }
    /**
     * 删除商品收藏
     * @param favoriteStr
     * @param pluteformid
     * @return
     */
    @Override
    public Integer delFavoriteInfoById(String favoriteStr[], String pluteformid) {
        FavoriteInfo favoriteInfo = new FavoriteInfo();
        Integer integer=0;
        for (int i=0;i<favoriteStr.length;i++){
            favoriteInfo.setFavoriteId(Integer.parseInt(favoriteStr[i]));
            favoriteInfo.setPluteformid(pluteformid);
            integer += favoriteInfoDao.delFavoriteInfoById(favoriteInfo);
        }
        return integer;
    }
    /**
     * 收藏查询产品
     * @param page
     * @return
     */
    @Override
    public List<ProductInfo> selFavoriteInfoById(Page page) {
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<ProductInfo> productInfos = productInfoDao.selFavoriteInfoById(page);
        return productInfos;
    }

    /**
     * 添加商品属性
     * 0推荐1热卖2特价3新品4分类首页推荐
     * @param stationModesString
     * @param pluteformid
     * @param productId
     * @return
     */
    @Override
    public Integer AddStationModes(String[] stationModesString, String pluteformid, Long shopId, Long productId) {
        int i=0;
        if(stationModesString.length>0 && stationModesString!=null){
            for (;i<stationModesString.length;i++){
                ProductStationModes productStationModes = new ProductStationModes();
                productStationModes.setProductId(productId);
                productStationModes.setPluteformid(pluteformid);
                productStationModes.setShopId(shopId);
                productStationModes.setType(Integer.parseInt(stationModesString[i]));
                Integer integer = productStationModesDao.AddStationModes(productStationModes);
            }
        }
        return i;
    }

    /**根据产品查属性
     *
     * @param productInfoVo
     * @return
     */
    @Override
    public List<ProductStationModes> selProductStationModesByProductId(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        List<ProductStationModes> productStationModeses = productStationModesDao.selProductStationModesByProductId(productInfo);
        return productStationModeses;
    }

    /**
     * 根据属性差产品
     * @param productStationModesVo
     * @return
     */
    @Override
    public List<ProductStationModes> selProductStationModesByStationId(ProductStationModesVo productStationModesVo) {
        ProductStationModes productStationModes = SpaceBeanCopy.productStationModesVoToProductStationModes(productStationModesVo);
        List<ProductStationModes> productStationModeses = productStationModesDao.selProductStationModesByStationId(productStationModes);
        return productStationModeses;
    }

    /**
     * 删除属性  根据产品
     * @param productInfoVo
     * @return
     */
    @Override
    public Integer delProductStationModesByProductId(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        Integer integer = productStationModesDao.delProductStationModesByProductId(productInfo);
        return integer;
    }

    /**
     * 微信App查询商品分类
     * @param categoryInfoVo
     * @return
     */
    @Override
    public List<CategoryInfoResult> weCartAddAppSelectCategory(CategoryInfoVo categoryInfoVo) {
        CategoryInfo categoryInfo = SpaceBeanCopy.categoryInfoVoTocategoryInfo(categoryInfoVo);
        List<CategoryInfo> categoryInfos = categoryInfoDao.weCartAddAppSelectCategory(categoryInfo);
        List<CategoryInfoResult> categoryInfoResultList = new ArrayList<CategoryInfoResult>();
        for(int i=0;i<categoryInfos.size();i++){
            categoryInfoResultList.add(SpaceBeanCopy.categoryInfoTocategoryInfoResult(categoryInfos.get(i)));
        }
        return categoryInfoResultList;
    }

    /**
     * 微信App查询商品
     * @param page
     * @return
     */
    @Override
    public Page weCartAddAppSelectProduct(Page page) {
        page.getUsers().setPluteformid(page.getPluteformid());
        //根据AppId 、openId 获得用户的Id
        Users users = userListDao.selUsersByAppIdopenId(page.getUsers());
        if(users!=null){
            page.setUserId(users.getUserId());
        }
        Page resultPage;
        ProductInfo productInfo = page.getProductInfo();
        //判断参数是否我空 默认所有参数为空时 查询推荐
        if (productInfo.getCategoryId()==null && "".equals(productInfo.getProductName()) || productInfo.getProductName()==null && productInfo.getStationType()== null){
                Users user = new Users();
                user.setUserId(page.getUserId());
                user.setPluteformid(productInfo.getPluteformid());
                List<OrderInfo> orderInfos = orderInfoDao.orderList(user);
                //判断客户订单  是否有记录
                if (orderInfos.size()>0 && orderInfos!=null){
                    //有记录查询 根据订单 查询同类推荐产品
                    page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
                    List<ProductInfo> productInfos = productInfoDao.productListByCate(page);
                    if(productInfos != null && productInfos.size()>0) {
                        for (int k = 0; k < productInfos.size(); k++) {
                            //替换图片路径
                            String path = productInfos.get(k).getThumbnailUrl1();
                            if (path != null) {
                                path = path.replace("{0}", "T207x270_");
                            }
                            productInfos.get(k).setThumbnailUrl1(path);
                        }
                    }
                    //返回ResultList
                    List<ProductInfoResult> productInfoResultList = new ArrayList<ProductInfoResult>();
                    for(int i =0;i<productInfos.size();i++){
                        productInfoResultList.add(SpaceBeanCopy.productInfoToProductInfoResult(productInfos.get(i)));
                    }
                    resultPage = new Page();
                    resultPage.setResultProductInfo(productInfoResultList);
                }else{
                    //无记录 查询自定义推荐产品 此处属性 id  可以扩展
                    productInfo.setStationType(1);
                    page.setProductInfo(productInfo);
                    resultPage = this.selProductAppWeCart(page);
                }
        }else{
            //调用商品查询
            resultPage = this.selProductAppWeCart(page);
        }
       return resultPage;
    }

    /**
     * app、weCart查询  根据分类、产品推荐属性。根据名称模糊查询
     * @param page
     * @return
     */
    public Page selProductAppWeCart(Page page){
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<ProductInfo> list = productInfoDao.weCartAddAppSelectByContion(page);
        //总条数
        Integer pageCount = productInfoDao.weCartAddAppSelectAllCount(page);
        //总页数
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        ProductInfo productInfo1 = new ProductInfo();
        if(list != null && list.size()>0) {
            for (int k = 0; k < list.size(); k++) {
                //替换图片路径
                String path = list.get(k).getThumbnailUrl1();
                if (path != null) {
                    path = path.replace("{0}", "T207x270_");
                }
                list.get(k).setThumbnailUrl1(path);
            }
        }
        //返回ResultList
        List<ProductInfoResult> productInfoResultList = new ArrayList<ProductInfoResult>();
        for(int i =0;i<list.size();i++){
            productInfoResultList.add(SpaceBeanCopy.productInfoToProductInfoResult(list.get(i)));
        }
        //新版图片
        for (ProductInfoResult result : productInfoResultList) {
            result.setThumbnailUrl1(env.getProperty("url.wxImage") + result.getImageUrl());
            result.setImageUrl(env.getProperty("url.wxImage") + result.getImageUrl());
        }
        page.setResultProductInfo(productInfoResultList);
        return page;
    }

    /**
     *移动APP根据userId查询收藏
     * @return
     */
    @Override
    public List<FavoriteInfo> selFavoriteInfoByUserId(FavoriteInfo favoriteInfo) {
            List<FavoriteInfo> productInfos = productInfoDao.selFavoriteInfoByUserId(favoriteInfo);
        return productInfos;
    }
    /**
     * 移动APP根据userId查询收藏数量
     */
    @Override
    public Integer getFavoriteInfoCount(String userId) {
        Integer favoriteNum = favoriteInfoDao.getFavoriteInfoCount(userId);
        return favoriteNum;
    }

    @Override
    public EasyUIListResult getBusinessList(Long page, Long rows, String filter) {
        if (ConfigConstants.SHOP_VARIABLE_NAME.equals(filter)) {
            PageableSupplierInfo pageableSupplierInfo = new PageableSupplierInfo();
            pageableSupplierInfo.setRows(page);
            pageableSupplierInfo.setRows(rows);
            List<SupplierInfo> supplierInfoList = shopInfoDao.getShopInfoList(pageableSupplierInfo);
            List<BusinessVO> businessVOList = new ArrayList<BusinessVO>();
            for (SupplierInfo info : supplierInfoList) {
                BusinessVO businessVO = new BusinessVO();
                businessVO.setId(info.getSupplierId());
                businessVO.setName(info.getShopName());
                businessVO.setType(ConfigConstants.SHOP_VARIABLE_NAME);
                businessVO.setShopId(info.getSupplierId());
                businessVOList.add(businessVO);
            }
            Long total = shopInfoDao.getShopInfoCount(pageableSupplierInfo);
            return new EasyUIListResult<BusinessVO>(total, businessVOList);
        } else if (ConfigConstants.PLATFORM_VARIABLE_NAME.equals(filter)) {
            List<MallConfig> allMallConfig = mallConfigDao.listMall((page - 1) * rows, rows);
            List<BusinessVO> businessVOList = new ArrayList<BusinessVO>();
            for (MallConfig mallConfig : allMallConfig) {
                BusinessVO businessVO = new BusinessVO();
                businessVO.setId(mallConfig.getId());
                businessVO.setName(mallConfig.getNickname());
                businessVO.setType(ConfigConstants.PLATFORM_VARIABLE_NAME);
                businessVO.setPlatformId(mallConfig.getPid());
                businessVOList.add(businessVO);
            }
            Long total = mallConfigDao.countMall();
            return new EasyUIListResult<BusinessVO>(total, businessVOList);
        } else {
            return new EasyUIListResult(0L, new ArrayList());
        }
    }


    @Override
    public EasyUIListResult getSelfProductListByShopId(Long page, Long rows, Long shopId) {
        List<ProductInfo> productInfoList = productInfoDao.listSelfProductByShopId((page - 1) * rows, rows, shopId);
        Long total = productInfoDao.countSelfProductByShopId(shopId);
        return new EasyUIListResult<ProductInfo>(total, productInfoList);
    }

    @Override
    public EasyUIListResult getSelfProductListByPlatformId(Long page, Long rows, Long platformId) {
        //MySQL分页参数计算
        Long start = (page - 1) * rows;
        Long size = rows;
        List<ProductInfo> productInfoList = productInfoDao.listSelfProductByMallId(start, size, platformId);
        Long total = productInfoDao.countSelfProductByMallId(platformId);
        return new EasyUIListResult<ProductInfo>(total, productInfoList);
    }

    @Override
    public void deleteSelfProductByShopId(Long shopId, Long productId) {
        productInfoDao.removeSelfProductByShopId(shopId, productId);
    }

    @Override
    public void deleteSelfProductByMallId(Long mallId, Long productId) {
        productInfoDao.removeSelfProductByMallId(mallId, productId);
    }

    @Override
    public void saveSelfProductByShopId(Long shopId, Long[] productIds) {
        productInfoDao.saveShopSelfProducts(shopId, productIds);
    }

    @Override
    public void saveSelfProductByMallId(Long mallId, Long[] productIds) {
        productInfoDao.saveMallSelfProducts(mallId, productIds);
    }

    @Override
    public void updateSelfProduct(Long productId, Integer selfSell) {
        Integer i = productInfoDao.updateSelfProduct(productId, selfSell);
    }

	@Override
    public List<ProductInfo> selShopProduct(ProductInfo productInfo) {
        return productInfoDao.selShopProduct(productInfo);
    }

    @Override
    public void insertProductImageUrls(InsertProductImageDTO insertProductImageDTO) {
        Integer i = productInfoDao.insertProductImageUrls(insertProductImageDTO);
    }
    @Override
    public String getServicePhoneByShopIdOrPId(ProductInfo productInfo){
        productInfo=productInfoDao.selProductInfoById(productInfo);
        if(productInfo==null)
            return "数据出错！";
        String phone=productInfoDao.getServicePhoneByShopIdOrPId(productInfo);
        if(phone==null){
            return "号码为空！";
        }
        return phone;
    }

}

