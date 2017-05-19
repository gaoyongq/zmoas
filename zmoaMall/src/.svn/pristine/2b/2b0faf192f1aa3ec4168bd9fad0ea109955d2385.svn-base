package com.zm.mall.service.system.impl;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.service.system.CouponService;
import com.zm.mall.client.service.system.UserService;
import com.zm.mall.dao.AbstractRedisBaseDao;
import com.zm.mall.dao.business.product.ProductCategoryDao;
import com.zm.mall.dao.system.CouponDao;
import com.zm.mall.domain.business.accountsUsers.UserScore;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.domain.system.User;
import com.zm.mall.domain.system.coupon.*;
import com.zm.mall.domain.system.systemCode.EasyUIResult;
//import com.zm.mall.service.RedisService;
import com.zm.mall.service.business.impl.orders.OrderInfoServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Bochao on 2017/2/27.
 */
@Service(value="couponService")
public class CouponServiceImpl extends AbstractRedisBaseDao<String, CouponCache> implements CouponService {
    @Resource
    private CouponDao couponDao;
    @Resource
    private OrderInfoService orderInfoService;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Resource
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoriesByParentId(String platformId, Long parentId) {
        List<ProductCategory> productCategoryList = couponDao.getProductCategoriesByParentId(platformId, parentId);
        for (ProductCategory productCategory : productCategoryList) {
            List<ProductCategory> list = couponDao.getProductCategoriesByParentId(
                    platformId, productCategory.getId());
            if (list == null || list.isEmpty()) {
                //如果是叶子分类，则设置分类下的商品
                List<Product> productList = couponDao.getProductsByCategoryId(platformId, productCategory.getId());
                productCategory.setProductList(productList);
                //productCategory.setState("open");
                productCategory.setState("closed");
            } else {
                productCategory.setState("closed");
            }

        }
        return productCategoryList;
    }

    @Override
    public int addCouponActivityCategory(CouponActivityCategory couponActivityCategory) {
        return couponDao.addCouponActivityCategory(couponActivityCategory);
    }

    @Override
    public EasyUIListResult getCouponActivityCategories(String platformId, Long page, Long rows) {
        List<CouponActivityCategory> couponActivityCategories = couponDao.getCouponActivityCategories(platformId, (page - 1) * rows, rows);
        Long total = couponDao.getCouponActivityCategoryCount(platformId);
        return new EasyUIListResult<CouponActivityCategory>(total, couponActivityCategories);
    }

    @Override
    public List<CouponActivityCategory> getCouponActivityCategoryList(String platformId) {
        return couponDao.getCouponActivityCategories(platformId, 0L, Long.MAX_VALUE);
    }

    @Override
    public int addCouponActivity(CouponActivity couponActivity) {
        return couponDao.addCouponActivity(couponActivity);
    }

    @Override
    public List<CouponActivity> getCouponActivityList(String platformId) {
        return couponDao.getCouponActivities(platformId, 0L, Long.MAX_VALUE);
    }

    @Override
    public EasyUIListResult getCouponActivities(String platformId, Long page, Long rows) {
        List<CouponActivity> couponActivities = couponDao.getCouponActivities(platformId, (page - 1) * rows, rows);
        Long total = couponDao.getCouponActivityCount(platformId);
        return new EasyUIListResult<CouponActivity>(total, couponActivities);
    }

    @Transactional
    @Override
    public void addCouponSource(CouponSource couponSource) {
        couponDao.addCouponSource(couponSource);
        if (couponSource.getProdIds().size() != 0) {
            couponDao.addCouponSourceProd(couponSource);
        }


        List<Long> prodCatIds = new ArrayList<Long>();
        Set<Long> cats = new HashSet<Long>();
        for (Long prodCatId : couponSource.getProdCatIds()) {
            //深度优先遍历
            Stack<Long> nodeDeque = new Stack<Long>();
            Long node = prodCatId; //分类Id
            nodeDeque.push(node);
            while (!nodeDeque.isEmpty()) {
                node = nodeDeque.pop();
                cats.add(node);
                List<Long> children = productCategoryDao.getChildCatIdsByParentId(node, couponSource.getPlatformId(), null);
                if (children != null && !children.isEmpty()) {
                    for (Long child : children) {
                        nodeDeque.push(child);
                    }
                }
            }
        }
        prodCatIds.addAll(cats);
        couponSource.setProdCatIds(prodCatIds);
        if (prodCatIds.size() != 0) {
            couponDao.addCouponSourceProdCat(couponSource);
        }


        //设置缓存对象
        /*final CouponCache couponCache = new CouponCache();
        couponCache.setCouponId(couponSource.getId());
        couponCache.setCouponName(couponSource.getName());
        couponCache.setFaceValue(couponSource.getFaceValue());
        couponCache.setMinimumValue(couponSource.getMinimumValue());
        List<Long> prodIds = new ArrayList<Long>();
        for (Product product : couponSource.getProductList()) {
            prodIds.add(Long.valueOf(product.getId()));
        }
        couponCache.setProdIds(prodIds);

        List<Long> prodCatIds = new ArrayList<Long>();
        Set<Long> cats = new HashSet<Long>();
        for (ProductCategory productCategory : couponSource.getProductCatList()) {
            //深度优先遍历
            Stack<Long> nodeDeque = new Stack<Long>();
            Long node = productCategory.getId(); //分类Id
            nodeDeque.push(node);
            while (!nodeDeque.isEmpty()) {
                node = nodeDeque.pop();
                cats.add(node);
                List<Long> children = productCategoryDao.getChildCatIdsByParentId(node, couponSource.getPlatformId());
                if (children != null && !children.isEmpty()) {
                    for (Long child : children) {
                        nodeDeque.push(child);
                    }
                }
            }
        }

        prodCatIds.addAll(cats);
        couponCache.setProdCatIds(prodCatIds);

        redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key  = serializer.serialize("couponSource_" + couponCache.getCouponId());
                byte[] name = serializer.serialize(JSONObject.toJSONString(couponCache));
                return connection.setNX(key, name);
            }
        });*/

    }

    @Override
    public List<Long> getCategoryIdsByProductId(String platformId, Long productId) {
        return couponDao.getCategoryIdsByProductId(platformId, productId);
    }

    @Override
    public Long getParentCategoryId(Long shopId, String platformId, Long categoryId) {
        return couponDao.getParentCategoryId(shopId, platformId, categoryId);
    }

    @Override
    public List<Long> getAncestorCategoryIds(Long shopId, String platformId, Long categoryId) {
        List<Long> ancestorCategoryIds = new ArrayList<Long>();
        while (true) {
            Long parentId = couponDao.getParentCategoryId(shopId, platformId, categoryId);
            if (parentId == null) break;
            ancestorCategoryIds.add(parentId);
            categoryId = parentId;
        }

        return ancestorCategoryIds;
    }

    @Override
    public List<Long> getAncestorCategoryIdsByProductId(Long shopId, String platformId, Long productId) {
        Set<Long> productAncestorCategoryIds = new HashSet<Long>();
        List<Long> categoryIds = getCategoryIdsByProductId(platformId, productId);
        productAncestorCategoryIds.addAll(categoryIds);

        for (Long catId : categoryIds) {
            List<Long> ancestorCategoryIds = getAncestorCategoryIds(shopId, platformId, catId);
            productAncestorCategoryIds.addAll(ancestorCategoryIds);
        }

        return new ArrayList<Long>(productAncestorCategoryIds);
    }

    @Override
    public CouponSource getCouponSourceById(String platformId, Long couponId) {
        return couponDao.getCouponSourceById(platformId, couponId);
    }

    @Override
    public int addCouponRule(CouponRule couponRule) {
        CouponRule cr = couponDao.getCouponRule(couponRule);
        if (cr != null) {
            return couponDao.updateCouponRule(couponRule);
        } else {
            return couponDao.addCouponRule(couponRule);
        }

    }

    @Override
    public EasyUIListResult getCouponRuleResult(String platformId, Long couponCodeId, Long couponSourceId) {
        List<CouponRuleResult> couponRuleResult = couponDao.getCouponRuleResult(platformId, couponCodeId, couponSourceId);
        return new EasyUIListResult<CouponRuleResult>(Long.valueOf(couponRuleResult.size()), couponRuleResult);
    }

    @Override
    public EasyUIListResult getCouponSources(String platformId, Long page, Long rows) {
        List<CouponSource> couponSources = couponDao.getCouponSources(platformId, (page - 1) * rows, rows);
        Long total = couponDao.getCouponSourceCount(platformId);
        return new EasyUIListResult<CouponSource>(total, couponSources);
    }

    @Override
    public EasyUIListResult getUsers(String platformId, Long page, Long rows) {
        List<Users> users = couponDao.getUsers(platformId, (page - 1) * rows, rows);
        Long total = couponDao.getUserCount(platformId);
        return new EasyUIListResult(total, users);
    }

    @Override
    public EasyUIResult addCouponForUsers(UserResult userResult, Long couponSourceId, Long[] userIds) {
        CouponSource couponSource = new CouponSource();
        couponSource.setId(couponSourceId);
        couponSource.setPlatformId(userResult.getPluteformid());

        int sum = 0;
        for (Long userId : userIds) {
            Coupon coupon = new Coupon();
            coupon.setCode("YHQ" + System.currentTimeMillis());
            coupon.setSource(couponSource);

            Users user = new Users();
            user.setUserId(userId.intValue());
            coupon.setUser(user);

            User createUser = new User();
            createUser.setId(userResult.getId());
            coupon.setCreateUser(createUser);
            coupon.setPlatformId(userResult.getPluteformid());
            coupon.setStatus(1);

            int i = couponDao.addCoupon(coupon);
            sum += i;
        }
        couponSource.setSendNumber(sum);
        couponDao.updateSendNumber(couponSource);

        return new EasyUIResult();
    }

    @Override
    public EasyUIResult addCouponForUserGroup(
            UserResult userResult, Long couponSourceId, String userGroup, Long sendNumber) {
        List<Long> userIdList = null;

        //所有用户（允许透支发放）
        if (userGroup.equals("ALL")) {
            userIdList = couponDao.getUserIdsOfALL(userResult.getPluteformid());
        }
        //注册时间在一年以上的老用户（允许透支发放）
        if (userGroup.equals("DGT365")) {
            userIdList = couponDao.getUserIdsOfCreateDateGreaterThan(365, userResult.getPluteformid());
        }
        //注册时间在一月以内的新用户（允许透支发放）
        if (userGroup.equals("DLT30")) {
            userIdList = couponDao.getUserIdsOfCreateDateLessThan(30, userResult.getPluteformid());
        }
        //总消费金额在10000元以上的土豪用户（允许透支发放）
        if (userGroup.equals("MGT10000")) {
            userIdList = couponDao.getUserIdsOfTotalCostGreaterThan(10000, userResult.getPluteformid());
        }
        //总购物次数在100次以上的粉丝用户（允许透支发放）
        if (userGroup.equals("TGT100")) {
            userIdList = couponDao.getUserIdsOfShoppingTimeGreaterThan(100, userResult.getPluteformid());
        }
        //按总消费金额排序（发放前排用户，不允许透支发放）
        if (userGroup.equals("MDESC")) {
            userIdList = couponDao.getUserIdsByTotalCostDESC(sendNumber, userResult.getPluteformid());
        }
        //按总购物次数排序（发放前排用户，不允许透支发放）
        if (userGroup.equals("TDESC")) {
            userIdList = couponDao.getUserIdsByShoppingTimeDESC(sendNumber, userResult.getPluteformid());
        }

        if (userIdList == null) return new EasyUIResult("没有指定用户");

        CouponSource couponSource = new CouponSource();
        couponSource.setId(couponSourceId);
        couponSource.setPlatformId(userResult.getPluteformid());

        Coupon coupon = new Coupon();
        coupon.setSource(couponSource);

        User createUser = new User();
        createUser.setId(userResult.getId());
        coupon.setCreateUser(createUser);
        coupon.setPlatformId(userResult.getPluteformid());
        coupon.setStatus(1);

        coupon.setUserIds(userIdList);

        int sum = couponDao.addCouponWithUserIds(coupon);

        couponSource.setSendNumber(sum);
        couponDao.updateSendNumber(couponSource);

        return new EasyUIResult();
    }

    @Override
    public int updateActivityStatus(String platformId, Long activityId, int status) {
        return couponDao.updateActivityStatus(platformId, activityId, status);
    }

    @Override
    public int updateCouponSourceStatus(String platformId, Long couponId, int status) {
        return couponDao.updateCouponSourceStatus(platformId, couponId, status);
    }

    @Override
    public EasyUIListResult getUserCoupons(String platformId, Long page, Long rows) {
        List<Coupon> userCoupons = couponDao.getUserCoupons(platformId, (page - 1) * rows, rows);
        Long total = couponDao.getUserCouponCount(platformId);
        return new EasyUIListResult(total, userCoupons);
    }

    @Transactional
    @Override
    public ExchangeCouponResult exchangeCoupon(Long userId, String platformId, Long couponSourceId, Integer number) {

        CouponSource couponSource = couponDao.getCouponSourceById(platformId, couponSourceId);
        if (couponSource == null) return new ExchangeCouponResult("未找到指定优惠券");
        int needPoint = couponSource.getNeedPoint();
        int needPoints = number * needPoint;
        Integer restPoints = couponDao.getUserRestPoints(platformId, userId);
        if (restPoints == null) return new ExchangeCouponResult("用户不存在");

        if (needPoints > restPoints) {
            return new ExchangeCouponResult("积分不足");
        }

        List<CouponResult> coupons = new ArrayList<CouponResult>();

        for (int i=0; i<number; i++) {
            Coupon coupon = new Coupon();
            coupon.setCode("YHQ" + i + System.currentTimeMillis());
            coupon.setSource(couponSource);

            Users user = new Users();
            user.setUserId(userId.intValue());
            coupon.setUser(user);

            coupon.setPlatformId(platformId);
            coupon.setStatus(1);

            int c = couponDao.addCoupon(coupon);

            if (c == 1) coupons.add(new CouponResult(coupon.getId(), coupon.getCode()));
        }

        int a = couponDao.increaseExchangeNumber(platformId, couponSourceId, coupons.size()); //多线程问题，方法加锁

        int b = couponDao.reduceUserRestPoint(platformId, userId, coupons.size() * needPoint);

        if ((a&b) == 0) throw new RuntimeException("数据更新失败");

        ExchangeCouponResult<CouponResult> result = new ExchangeCouponResult<CouponResult>();
        result.setData(coupons);
        return result;
    }

    @Override
    public List<CouponSource> getCouponSourcesByActivityId(String platformId, Long activityId) {
        return couponDao.getCouponSourcesByActivityId(platformId, activityId);
    }

    @Override
    public ExchangeCouponResult getCouponsByUserId(String platformId, Long userId, Integer status) {
        List<Coupon> coupons = couponDao.getCouponsByUserId(platformId, userId, status);
        ExchangeCouponResult exchangeCouponResult = new ExchangeCouponResult();
        exchangeCouponResult.setData(coupons);
        return exchangeCouponResult;
    }

    @Override
    public List<CouponActivity> getCouponActivitiesInProgress(String platformId) {
        return couponDao.getCouponActivitiesInProgress(platformId);
    }

    @Override
    public ExchangeCouponResult getUsableCouponsByProducts(String platformId, Long userId, String products, Long page, Long rows) {

        //将页面字符串数据转成Map
        Map<Long, NumAndPrice> userProductMap = new HashMap<Long, NumAndPrice>();
        for (String product : products.split(",")) {
            String[] p = product.split(":");
            try {
                userProductMap.put(Long.valueOf(p[0]),
                        new NumAndPrice(Integer.valueOf(p[1]), Double.valueOf(p[2])));
            } catch (Exception e) {
                return new ExchangeCouponResult("非法参数");
            }
        }

        //将用户购买商品的id转成List
        List<Long> userProductIds = new ArrayList<Long>();
        for (Long upId : userProductMap.keySet()) {
            userProductIds.add(upId);
        }

        //查询用户持有的所有未使用的优惠券
        int status = 1; //优惠券状态，1代表未使用
        List<Coupon> coupons = couponDao.getCouponsByUserId(platformId, Long.valueOf(userId), status);

        //获取本次订单可使用的优惠券（商品类别限制，最低消费金额限制）
        List<Coupon> usableCoupons = new ArrayList<Coupon>();

        for (Coupon coupon : coupons) {
            /*ValueOperations<String, CouponCache> valueOperations = redisTemplate.opsForValue();
            CouponCache couponCache = valueOperations.get("couponSource_" + coupon.getSource().getId());*/

            //将此优惠券可应用的商品id转成List
            List<Long> productIds = couponDao.getCouponSourceProdIdsBySourceId(coupon.getSource().getId());

            //将此优惠券可应用的商品分类id转成List
            List<Long> productCatIds = couponDao.getCouponSourceProdCatIdsBySourceId(coupon.getSource().getId());

            //获取本次订单中可应用此优惠券的商品id
            List<Long> usableProductIds = new ArrayList<Long>();
            for (Long userProductId : userProductIds) {
                if (productIds.contains(userProductId)) {
                    usableProductIds.add(userProductId);
                } else {
                    //List<Long> ancestorCategoryIds = getAncestorCategoryIdsByProductId(platformId, userProductId);
                    List<Long> categoryIds = couponDao.getCategoryIdsByProductId(platformId, userProductId);
                    for (Long categoryId : categoryIds) {
                        if (productCatIds.contains(categoryId)) {
                            usableProductIds.add(userProductId);
                        }
                    }
                }
            }

            //获取可使用该优惠券的商品id和数量
            Map<Long, NumAndPrice> usableUserProductMap = new HashMap<Long, NumAndPrice>();
            for (Map.Entry<Long, NumAndPrice> entry : userProductMap.entrySet()) {
                if (usableProductIds.contains(entry.getKey())) {
                    usableUserProductMap.put(entry.getKey(), entry.getValue());
                }
            }

            if (usableUserProductMap.isEmpty()) continue;

            //获取可使用该优惠券的商品总价格
            Double totalPrice = 0.0;
            for (Map.Entry<Long, NumAndPrice> entry : usableUserProductMap.entrySet()) {
                totalPrice += entry.getValue().getPrice() * entry.getValue().getNum();
            }

            //获取该优惠券的最低消费金额
            Double limitPrice = coupon.getSource().getMinimumValue();

            //如果总价格大于最低消费金额，返回此优惠券
            if (limitPrice == 0.0 || totalPrice >= limitPrice) {
                usableCoupons.add(coupon);
            }

        }

        ExchangeCouponResult result = new ExchangeCouponResult();
        if (page != null && rows != null) {
            List<Coupon> subList = null;
            if (page*rows > usableCoupons.size()) {
                subList = usableCoupons.subList((int)(page*rows-rows), usableCoupons.size());
            } else {
                subList = usableCoupons.subList((int)(page*rows-rows), (int)(page*rows));
            }
            result.setData(subList);
        } else {
            result.setData(usableCoupons);
        }



        return result;
    }

    @Override
    public ExchangeCouponResult useCoupon(String platformId, Long couponId, Long orderId) {
        int i = couponDao.useCoupon(platformId, couponId, orderId);
        if (i == 0) {
            return new ExchangeCouponResult("修改失败");
        }
        return new ExchangeCouponResult();
    }

    @Override
    public List<Product> getCouponProductsBySourceId(String platformId, Long id) {
        return couponDao.getCouponProductsBySourceId(platformId, id);
    }

    @Override
    public List<ProductCategory> getCouponProductCatsBySourceId(String platformId, Long id) {
        return couponDao.getCouponProductCatsBySourceId(platformId, id);
    }

    @Override
    public String getCouponImgUrlById(String platformId, Long id) {
        return couponDao.getCouponImgUrlById(platformId, id);
    }

    @Override
    public String getActivityImgUrlById(String platformId, Long id) {
        return couponDao.getActivityImgUrlById(platformId, id);
    }

    @Override
    public String getActivityIntroductionById(String platformId, Long id) {
        return couponDao.getActivityIntroductionById(platformId, id);
    }

    @Override
    public int updateCouponStatus(String platformId, Long couponId, int status) {
        return couponDao.updateCouponStatus(platformId, couponId, status);
    }

    @Override
    public ExchangeCouponResult getCouponsByUserId(String platformId, Long userId, Integer status, Long page, Long rows) {
        List<Coupon> coupons = couponDao.getCouponsByUserId(platformId, userId, status, (page - 1) * rows, rows);
        //List<Coupon> coupons = couponDao.getCouponsByUserId(platformId, userId, status);
        ExchangeCouponResult exchangeCouponResult = new ExchangeCouponResult();
        exchangeCouponResult.setData(coupons);
        return exchangeCouponResult;
    }

    @Override
    public EasyUIListResult<ProductInfo> getProductsByCouponId(PageableCouponSource couponSource) {
        List<ProductInfo> productInfoList = couponDao.getProductsByCouponId(couponSource);
        Long total = couponDao.getProductsByCouponIdCount(couponSource);
        return new EasyUIListResult<ProductInfo>(total, productInfoList);
    }

}
