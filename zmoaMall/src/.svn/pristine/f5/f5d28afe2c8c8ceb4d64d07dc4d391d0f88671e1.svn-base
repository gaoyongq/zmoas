package com.zm.mall.service.business.shopUser.impl;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.product.ProductInfoResult;
import com.zm.mall.client.service.business.shopUser.ShopUserService;
import com.zm.mall.client.vo.business.shopUser.ShopUserVo;
import com.zm.mall.dao.business.accountsUsers.UserListDao;
import com.zm.mall.dao.business.product.*;
import com.zm.mall.dao.business.shopUser.ShopUserDao;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.product.*;
import com.zm.mall.domain.business.shopUser.ShopUser;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
@Service("ShopUserService")
@PropertySource("classpath:urlPrefix.properties")
public class ShopUserServiceImpl implements ShopUserService{
	private static Log log = LogFactory.getLog(ShopUserService.class);
	@Autowired
	private ShopUserDao shopUserDao;
	@Autowired
	private UserListDao userListDao;
	@Autowired
	private ProductInfoDao productInfoDao;
	@Autowired
	private SKUInfoDao skuInfoDao;
	@Autowired
	private ProductCategoriesDao productCategoriesDao;
	@Autowired
	private CategoryInfoDao categoryInfoDao;
	@Autowired
	private BrandInfoDao brandInfoDao;
	@Autowired
	private SupplierInfoDao supplierInfoDao;
	@Autowired
	private ProductTypeDao productTypeDao;
	@Autowired
	private Environment env;

	@Override
	public List<ShopUser> selectShopUser(ShopUserVo shopUserVo) {
		ShopUser  shopUser= SpaceBeanCopy.shopUserVoToShopUser(shopUserVo);
		Users users=new Users();
		users.setOpenId(shopUser.getOpenId());
		users.setPluteformid(shopUser.getPluteformid());
		Users u=userListDao.selUsersByAppIdopenId(users);
		shopUser.setUserId(u.getUserId());
		List<ShopUser> shopUsers=shopUserDao.selectShopUser(shopUser);
		return shopUsers;
	}

	@Override
	@Transactional
	public Integer insertShopUser(ShopUserVo shopUserVo) {
		ShopUser shopUser=SpaceBeanCopy.shopUserVoToShopUser(shopUserVo);
		Users users=new Users();
		users.setOpenId(shopUser.getOpenId());
		users.setPluteformid(shopUser.getPluteformid());
		Users u=userListDao.selUsersByAppIdopenId(users);
		shopUser.setUserId(u.getUserId());
		Integer i=shopUserDao.insertShopUser(shopUser);
		return i;
	}

	/**
	 * 查询店铺的下的产品
	 * 根据平台Id和openId查询用户是否关注店铺
	 * 关注返回最后时间关注店铺产品   没有关注返回该平台产品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page selectShopProductBySupplier(Page page) throws  Exception{
		try {
			Integer currentPage=page.getCurrentPage();
			if(currentPage==null){
				page.setCurrentPage(1);
			}
			//首先根据平台ID和openID查询accountUser表
			Users users=new Users();
			users.setOpenId(page.getOpenId());
			users.setPluteformid(page.getPluteformid());
			Users u=userListDao.selUsersByAppIdopenId(users);
			ShopUser shopUser=new ShopUser();
			shopUser.setUserId(u.getUserId());
			//根据查询到的用户设置shopUser表的shopId和userId
			List<ShopUser> shopUsers=shopUserDao.selectShopUser(shopUser);
			ShopUser shopUser1=shopUsers.get(0);
			ProductInfo productInfo=new ProductInfo();
			productInfo.setPluteformid(page.getPluteformid());
			String category1="";
			productInfo.setSearchProductCategories(category1);
			CategoryInfo categoryInfo=new CategoryInfo();
			categoryInfo.setPluteformid(page.getPluteformid());
			productInfo.setProductCategories(page.getProductInfo().getProductCategories());
			if(shopUsers==null) {
			}else {
				page.setShopId(shopUser1.getShopInfoId());
				productInfo.setShopId(shopUser1.getShopInfoId());
				categoryInfo.setShopId(shopUser1.getShopInfoId());
			}
			page.setProductInfo(productInfo);
			//分页开始行数
			page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
			List<ProductInfo> list = productInfoDao.selectByContion(page);
			//分类集合
			List<CategoryInfo> categoryInfos = categoryInfoDao.selectAll(categoryInfo);
			//总条数
			Integer pageCount = productInfoDao.selectAllCount(page);
			//总页数
			page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
			if(list != null && list.size()>0){
				for (int k =0;k<list.size();k++){
					//图片路径
					String path = list.get(k).getThumbnailUrl1();
					if(path!=null){
						path = path.replace("{0}", "T207x270_");
					}
					list.get(k).setThumbnailUrl1(path);
					//查询库存
					SKUInfo skuInfo = new SKUInfo();
					skuInfo.setProductId(list.get(k).getProductId());
					skuInfo.setPluteformid(productInfo.getPluteformid());
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
					productType.setShopId(shopUser1.getShopInfoId());
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
						productInfo.setProductId(list.get(k).getProductId());
						List<ProductCategories> productCategories = productCategoriesDao.selectByProductId(productInfo);
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
					productInfoResultList.add(SpaceBeanCopy.productInfoToProductInfoResult(list.get(i)));
				}
				//新版图片2017年4月29日00:29:24
				for (ProductInfoResult productInfoResult : productInfoResultList) {
					productInfoResult.setImageUrl(env.getProperty("url.showImage") + "?proportion=207:270&relUrl=" + productInfoResult.getImageUrl());//
					productInfoResult.setThumbnailUrl1(env.getProperty("url.showImage") + "?proportion=207:270&relUrl=" + productInfoResult.getImageUrl());
				}
				page.setResultProductInfo(productInfoResultList);
				return page;
			}
			return page;
		}catch (Exception e){
		throw e;
		}
	}
	//查询单一个店铺的产品
	@Override
	public Page selectShopProductByshopId(Page page) throws Exception {
		try {
			Integer currentPage=page.getCurrentPage();
			if(currentPage==null){
				page.setCurrentPage(1);
			}
			ProductInfo productInfo=new ProductInfo();
			productInfo.setPluteformid(page.getPluteformid());
			String category1="";
			productInfo.setSearchProductCategories(category1);
			CategoryInfo categoryInfo=new CategoryInfo();
			categoryInfo.setPluteformid(page.getPluteformid());
			productInfo.setProductCategories(page.getProductInfo().getProductCategories());
				page.setShopId(page.getShopId());
				productInfo.setShopId(page.getShopId());
				categoryInfo.setShopId(page.getShopId());
			page.setProductInfo(productInfo);
			//分页开始行数
			page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
			List<ProductInfo> list = productInfoDao.selectByContion(page);
			//分类集合
			List<CategoryInfo> categoryInfos = categoryInfoDao.selectAll(categoryInfo);
			//总条数
			Integer pageCount = productInfoDao.selectAllCount(page);
			//总页数
			page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
			if(list != null && list.size()>0){
				for (int k =0;k<list.size();k++){
					//图片路径
					String path = list.get(k).getThumbnailUrl1();
					if(path!=null){
						path = path.replace("{0}", "T207x270_");
					}
					list.get(k).setThumbnailUrl1(path);
					//查询库存
					SKUInfo skuInfo = new SKUInfo();
					skuInfo.setProductId(list.get(k).getProductId());
					skuInfo.setPluteformid(productInfo.getPluteformid());
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
					productType.setShopId(page.getShopId());
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
						productInfo.setProductId(list.get(k).getProductId());
						List<ProductCategories> productCategories = productCategoriesDao.selectByProductId(productInfo);
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
					productInfoResultList.add(SpaceBeanCopy.productInfoToProductInfoResult(list.get(i)));
				}
				//新版图片2017年4月29日00:29:24
				for (ProductInfoResult productInfoResult : productInfoResultList) {
					productInfoResult.setImageUrl(env.getProperty("url.showImage") + "?proportion=207:270&relUrl=" + productInfoResult.getImageUrl());//
					productInfoResult.setThumbnailUrl1(env.getProperty("url.showImage") + "?proportion=207:270&relUrl=" + productInfoResult.getImageUrl());
				}
				page.setResultProductInfo(productInfoResultList);
				return page;
			}
			return page;
		}catch (Exception e){
			throw e;
		}
	}
}
