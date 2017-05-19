package com.zm.mall.service.business.impl.orders;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.orders.OrderInfoResult;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.vo.business.accounts.AccountUserVo;
import com.zm.mall.client.vo.business.orders.OrderInfoVo;
import com.zm.mall.client.vo.business.product.ProductInfoVo;
import com.zm.mall.client.vo.business.product.RegionInfoVo;
import com.zm.mall.dao.business.accountsUsers.UserListDao;
import com.zm.mall.dao.business.appointment.AppointmentDao;
import com.zm.mall.dao.business.orders.OrderInfoDao;
import com.zm.mall.dao.business.product.*;
import com.zm.mall.dao.system.ApplyCarDao;
import com.zm.mall.dao.system.ApplyCarSonDao;
import com.zm.mall.domain.business.accountsUsers.RegionExpressPrice;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.accountsUsers.UsersExp;
import com.zm.mall.domain.business.appointment.AppointmentOrder;
import com.zm.mall.domain.business.orders.*;
import com.zm.mall.domain.business.product.*;
import com.zm.mall.domain.system.ApplyCar;
import com.zm.mall.domain.system.ApplyCarSon;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/11.
 */
@Service("orderInfoService")
@WebService(endpointInterface = "com.zm.mall.client.service.business.orders.OrderInfoService",serviceName = "OrderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

    private  static Log log = org.apache.commons.logging.LogFactory.getLog(OrderInfoService.class);
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Resource
    private UserListDao userListDao;
    @Resource
    private AppointmentDao appointmentDao;
    public OrderInfoDao getOrderInfoDao() {
        return orderInfoDao;
    }

    public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
        this.orderInfoDao = orderInfoDao;
    }

    @Autowired
    private RegionInfoDao regionInfoDao;
    @Autowired
    private ProductTypeDao productTypeDao;
    @Autowired
    private AttributeInfoDao attributeInfoDao;
    @Autowired
    private SKUInfoDao skuInfoDao;
    @Autowired
    private SKUItemDao skuItemDao;
    @Autowired
    private ApplyCarDao applyCarDao;
    @Autowired
    private ApplyCarSonDao applyCarSonDao;

    /**
     * 分页  查询订单数量
     * @param tip
     * @return
     */
    @Override
    public Integer getCount(OrderByTip tip) {
        return orderInfoDao.getCount(tip);
    }

    @Override
    /**
     * 订单显示和查询方法
     */
    public List<OrderInfo> selectAllOrderInfo(OrderByTip tip) {

        return orderInfoDao.selectAllOrders(tip);
    }

    /**
     * 查询订单详细信息方法
     * @param orderId
     * @return
     */

    @Override
    public OrderInfo selectGuesterInfo(String orderId) {
        OrderInfo orderInfo =orderInfoDao.selectGuesterInfo(orderId);
        List<OrderItems> orderItems = orderInfo.getOrderItems();
        for (int i=0;i<orderItems.size();i++){
            orderItems.get(i).setThumbnailsUrl(orderItems.get(i).getThumbnailsUrl().replace("{0}","T207x270_"));
        }
        orderInfo.setOrderItems(orderItems);
        return orderInfo;    }

    /**
     * 订单删除方法
     * @param orderCode
     * @return
     */
    @Override
    public int deleteOrder(String orderCode) {
        return orderInfoDao.deleteOrder(orderCode);
    }
    /**
     * 根据用户名获得对应的收货信息
     */
    @Override
    public Users getShippingAddress(String buyerName) {
        return orderInfoDao.getShippingAddress(buyerName);
    }
    /**
     * 根据regionId获取运费
     */
    @Override
    public RegionExpressPrice getYunFei(RegionExpressPrice regionExpressPrice) {
        return orderInfoDao.getYunFei(regionExpressPrice);
    }
    /**
     * 插入新增订单
     */
    @Override
    public Integer insertOrder(OrderInfo orderInfo) {
        String orderCode =orderInfo.getOrderCode();
        String orderId="";
        if(orderCode.endsWith("yy")) {
            orderId=appointmentDao.insertOrder(orderInfo);
        }else {
            orderId = orderInfoDao.insertOrder(orderInfo);
        }
        orderInfo.setOrderId(orderId);
        Integer an = orderInfoDao.insertOrderItems(orderInfo);
        List<OrderItems> orderItemses = orderInfoDao.selectOrderItems(orderInfo);
        List<OrderItems> orderItemsList = orderInfo.getOrderItems();
        for (int i=0;i<orderItemses.size();i++){
            Long itemId = orderItemses.get(i).getItemId();
            orderItemsList.get(i).setItemId(itemId);
            List<OrderItemsAttribute> orderItemsAttributes = orderItemsList.get(i).getOrderItemsAttributes();
            for (int b=0;b<orderItemsAttributes.size();b++){
                orderItemsAttributes.get(b).setItemId(itemId);
                orderInfoDao.insertOrderItemsAttribute(orderItemsAttributes.get(b));
            }
        }
        return an;
    }
    //查询地区信息
    @Override
    public List<RegionInfo> getRegionInfo() {
        return regionInfoDao.selRegionInfoFirst();
    }
    //查询产品类型

    @Override
    public List<ProductType> getProductType(String platformId, Long shopId) {
        return productTypeDao.selAllProductType(platformId, shopId);
    }

    //根据typeId类型Id和规格属性名获取AttributeId,ValueId,ValueStr
    @Override
    public List<AttributeInfo> selectAttributeValue(AttributeInfo attributeInfo) {
        return attributeInfoDao.selectAttributeValue(attributeInfo);
    }
    /**
     * 白洋洋   根据产品Id获取产品价格
     */
    @Override
    public List<SkuData> checkPrice(ProductInfoVo productInfoVo) {
        ProductInfo productInfo = SpaceBeanCopy.productInfoVoToProduct(productInfoVo);
        SKUInfo skuInfo=new SKUInfo();
        skuInfo.setProductId(productInfo.getProductId());
        skuInfo.setPluteformid(productInfo.getPluteformid());
        skuInfo.setShopId(productInfo.getShopId());
        List<SkuData> list = new ArrayList<SkuData>();
        SkuData skuData;
        List<SKUInfo> skuInfos = skuInfoDao.selSkuInfoById(skuInfo);
        for (int i = 0; i < skuInfos.size(); i++) {
            List<SKUItem> skuItems = skuItemDao.selSkuItemById(skuInfos.get(i));
            String key = "";
            if (skuItems.size() != 0) {
                for (int a = 0; a < skuItems.size(); a++) {
                    key += skuItems.get(a).getValueId();
                    key += ",";
                }
                skuData = new SkuData();
                key = key.substring(0, key.length() - 1);
                skuData.setKey(key);
                skuData.setSkuInfo(skuInfos.get(i));
                list.add(skuData);
            }
        }
        return list;
    }
    /**
     *  白洋洋     订单处理
     */
    @Override
    public List<OrderInfo> getAllOrderInfo(OrderByTip orderByTip) {
        return orderInfoDao.getAllOrderInfo(orderByTip);
    }
    /**
     * 白洋洋   订单处理页面查询-OA订单
     */
    @Override
    public List<OrderInfo> getOrderByTip( OrderByTip tip) {
        return orderInfoDao.getOrderByTip(tip);
    }
    /**
     * 白洋洋   订单处理页面查询-商城订单
     */
    @Override
    public List<OrderInfo> getOrderByTipByM(OrderByTip tip) {
        return orderInfoDao.getOrderByTipByM(tip);
    }

    @Override
    public List<OrderInfo> autoCompleteOrderInfo() {
        return orderInfoDao.autoCompleteOrderInfo();
    }

    @Override
    public OrderInfo selectItems(Long orderId) {
        return orderInfoDao.selectOrderItem(orderId);
    }

    /**
     * 为生成采购单做准备
     */
//    public List<OrderItemsResult> startMakePurchase(OrderInfoVo orderInfoVo) {
//        OrderInfo orderInfo= SpaceBeanCopy.orderInfoVoToOrderInfo(orderInfoVo);
//       List<OrderInfo> orderInfos= orderInfoDao.select(orderInfo);
//        if(orderInfos!=null&orderInfos.size()>0){
//            List<OrderItemsResult> orderInfoResults=new ArrayList<OrderItemsResult>();
//            for(OrderInfo oo:orderInfos){
//                for(OrderItems orderItems : oo.getOrderItemses()){
//                    OrderItemsResult orderItemsResult=SpaceBeanCopy.orderItemToOrderItemResult(oo,orderItems);
//                    orderInfoResults.add(orderItemsResult);
//                }
//
//
//            }
//            return orderInfoResults;
//        }
//        return null;
//    }
    @Override
    public List<OrderInfoResult> startMakePurchase(OrderInfoVo orderInfoVo) {
        OrderInfo orderInfo= SpaceBeanCopy.orderInfoVoToOrderInfo(orderInfoVo);
        List<OrderInfo> orderInfos= orderInfoDao.select(orderInfo);
        if(orderInfos!=null&orderInfos.size()>0){
            List<OrderInfoResult> orderInfoResults=new ArrayList<OrderInfoResult>();
            for(OrderInfo oo:orderInfos){
                OrderInfoResult orderInfoResult=SpaceBeanCopy.orderInfoToOrderInfoResult(oo);
                orderInfoResults.add(orderInfoResult);
            }
            return orderInfoResults;
        }
        return null;
    }
    /**
     * 白洋洋   处理选中的订单
     */
    @Override
    public List<OrderItems> selectOrdersByChecked(Long departmentId,String[] ids) {
        return orderInfoDao.selectOrdersByChecked(departmentId,ids);
    }
    /**
     * 白洋洋  订单审核功能
     */
    @Override
    public Integer passOrder(String message,String orderIds) {
        Integer an = 0;
        Integer a =0;
        if ("yes".equals(message)) {
            an = orderInfoDao.passOrder(orderIds);
        }else {
            a = orderInfoDao.notPassOrder(orderIds);
        }
        return an+a;
    }

    /**
     * 商城webService对接
     * @param orderInfo
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Integer insertOrderByWebService(OrderInfo orderInfo) throws Exception {
        return orderInfoDao.insertOrderByWebService(orderInfo);
    }

    /**
     * 打印的接口
     * @return
     */

    @Override
    public Map<String, Object> selectForPrint(String orderId) {
        return orderInfoDao.selectForPrint(orderId);
    }

    /**
     * 订单处理页面分页-OA的订单
     */
    @Override
    public Integer getOrderCount(OrderByTip orderByTip) {
        return orderInfoDao.getOrderCount(orderByTip);
    }
    /**
     * 订单处理页面分页-商城的订单
     */

    @Override
    public Integer getOrderCountByM(OrderByTip orderByTip) {
        return orderInfoDao.getOrderCountByM(orderByTip);
    }

    /**
     * 改变订单状态为已生成物流
     */
    @Override
    public Integer updateOrderPurchasing(String orderCode) {
        return orderInfoDao.updateOrderPurchasing(orderCode);
    }
    /**
     * 订单修改--查询要修改订单的信息
     */
    @Override
    public OrderInfo getOrderInfoByOrderId(String orderId) {
        return orderInfoDao.getOrderInfoByOrderId(orderId);
    }

    @Override
    public List<OrderItems> getOrderItems(String orderId) {
        return orderInfoDao.getOrderItems(orderId);
    }

    @Override
    public List<OrderItemsAttribute> getOrderAttribute(Long itemId) {
        return orderInfoDao.getOrderAttribute(itemId);
    }
    /**
     * 订单修改
     */
    @Override
    public Integer updateOrderInfo(OrderInfo orderInfo) {
       Integer a = orderInfoDao.updateOrder(orderInfo);
        //先删除订单-产品表和产品规格表，然后将修改的数据插入表中
        Integer an = orderInfoDao.deleteOrderProduct(orderInfo);
        Integer n = orderInfoDao.insertOrderItems(orderInfo);
        List<OrderItems> orderItemses = orderInfoDao.selectOrderItems(orderInfo);
        List<OrderItems> orderItemsList = orderInfo.getOrderItems();
        for (int i=0;i<orderItemses.size();i++){
            Long itemId = orderItemses.get(i).getItemId();
            orderItemsList.get(i).setItemId(itemId);
            List<OrderItemsAttribute> orderItemsAttributes = orderItemsList.get(i).getOrderItemsAttributes();
            for (int b=0;b<orderItemsAttributes.size();b++){
                orderItemsAttributes.get(b).setItemId(itemId);
                orderInfoDao.insertOrderItemsAttribute(orderItemsAttributes.get(b));
            }
        }
        return null;
    }

   /**
     * 修改已经采购的订单状态
     * OrderCheckStatus为1
     * @param
     * @return
     */
    @Override
    public Integer updatePurchaseOrderCheckStatus(String orderIds) {
        return orderInfoDao.updatePOrderCheckStatus(orderIds);
    }
    /**
     * 改变订单状态为已生成物流
    /**
     * selectNoCarOrderList 查询所有没有车的订单 分页
     * @param page
     * @return
     */
    @Override
    public Page selectNoCarOrderList(Page page, OrderInfo orderInfo) {
        page.setOrderInfo(orderInfo);
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<OrderInfo> list = orderInfoDao.selectNoCarByLimit(page);
        Integer pageCount = orderInfoDao.selectNoCarAllCount(page);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        if (list !=null && list.size()>0){
            page.setResultOrderInfo(list);
            return page;
        }
        return null;
    }

    @Override
    public OrderInfo selectOneOrder(String orderCode) {
        return orderInfoDao.selectOneOrder(orderCode);
    }

    @Override
    public void updateOrderCarState(String orderCode) {
        orderInfoDao.updateOrderCarState(orderCode);
    }
    /**
     * 订单新增-将申请车辆的订单插入申请车辆表
     */
    @Override
    public Integer insertApplyCar(OrderInfo orderInfo) {
        //获取车辆申请编号
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String applyCarCode = sdf.format(date).toString();
        ApplyCar applyCar = new ApplyCar();
        applyCar.setApplyCarCode(applyCarCode);
        applyCar.setSmallgroup(orderInfo.getDepartmentName());
        applyCar.setTransactor(orderInfo.getRealName());
        Integer a = applyCarDao.insertApplyCar(applyCar);
        //插入申请车辆子表
        ApplyCarSon applyCarSon = new ApplyCarSon();
        applyCarSon.setOrderCode(orderInfo.getOrderCode());
        applyCarSon.setApplyCarCode(applyCarCode);
        applyCarSon.setSmallGroup(orderInfo.getDepartmentName());
        applyCarSon.setTransactor(orderInfo.getRealName());
        applyCarSon.setOrderWeigth(orderInfo.getWeight());
        Integer an = applyCarSonDao.insertOrder(applyCarSon);
        return a+an;
    }

    /**
     * 处理商城订单时加上对应的小组
     * @param orderInfo
     */
    @Override
    public void updateGroup(OrderInfo orderInfo) {
       orderInfoDao.updateGroup(orderInfo);
    }

    /**
     * 处理订单时修改订单为已处理状态
     * @param orderId
     */
    @Override
    public void updateOrderToHandled(String orderId) {
        orderInfoDao.updateOrderToHandled(orderId);

    }

    @Override
    public void updateOrderCarTwoState(String orderCode) {
        orderInfoDao.updateOrderCarTwoState(orderCode);
    }
    /**
     * 计算平方米价格 by liupeng
     * @param layout
     * @param price
     * @return
     */
    @Override
    public Double layoutPrice(String layout, String price) {
        BigDecimal squarePrice = new BigDecimal(price);
        layout = layout.replace("mm","");
        String[] arr = layout.split("\\*");
        BigDecimal arr0 =new BigDecimal(arr[0]);
        BigDecimal arr1 =new BigDecimal(arr[1]);
        BigDecimal percent =new BigDecimal(0.001);
        BigDecimal square = arr0.multiply(percent).multiply(arr1.multiply(percent));
        BigDecimal layoutPrice  = square.multiply(squarePrice);
        Double doubleValue   = layoutPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return doubleValue;
    }

    @Override
    public RegionInfo selRegionInfo(RegionInfoVo regionInfoVo) {
        RegionInfo regionInfo = SpaceBeanCopy.regionInfoVoToregionInfo(regionInfoVo);
        return regionInfoDao.selRegionInfoPath(regionInfo);
    }

    @Override
    public List<OrderInfo> selectWXOrder(Page page) {
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        return orderInfoDao.selectWXOrder(page);
    }

    @Override
    public List<OrderInfo> selectWXOrderDaiFuKuan(Page page) {
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        return orderInfoDao.selectWXOrderDaiFuKuan(page);
    }

    @Transactional
    @Override
    public void updateOrderStatus(String platformId, String orderId, String key, Integer value) {
        if (key.equalsIgnoreCase("ShippingStatus")) {
            int i = orderInfoDao.updateOrderStatus(platformId, orderId, "ShippingStatus", value);
            Integer val = null;
            if (value.equals(0)) val = -1;
            else if (value.equals(1)) val = 2;
            else if (value.equals(2)) val = 3;
            int j = orderInfoDao.updateOrderStatus(platformId, orderId, "PurchasingStatus", val);
        } else {
            orderInfoDao.updateOrderStatus(platformId, orderId, key, value);
        }

        if (value.equals(2)) {

            UsersExp usersExp = new UsersExp();
            OrderInfo orderInfo = orderInfoDao.getOrderInfoByOrderId(orderId);

            usersExp.setUserId(orderInfo.getBuyerID());
            usersExp.setPluteformid(orderInfo.getPluteformid());
            if (orderInfo.getPaymentTypeId().equals(2)) {
                //减少余额
                usersExp.setBalance(orderInfo.getOrderTotal());
                Integer integer = userListDao.updateBalanceConsumeWX(usersExp);
                if (integer.equals(0)) {
                    throw new RuntimeException("余额减少失败");
                }
            } else if (orderInfo.getPaymentTypeId().equals(4)) {
                //减少余额
                usersExp.setBalance(orderInfo.getOrderCostPrice());
                Integer integer = userListDao.updateBalanceConsumeWX(usersExp);
                if (integer.equals(0)) {
                    throw new RuntimeException("余额减少失败");
                }
            } else {
                usersExp.setBalance(orderInfo.getOrderTotal());
            }

            //增加余额
            Long userId = orderInfoDao.getUserIdByPlatformId(platformId);
            usersExp.setUserId(userId.intValue());
            usersExp.setPluteformid(platformId);
            int k = userListDao.updateRBP(usersExp);
            if (k == 0) {
                throw new RuntimeException("余额增加失败");
            }


        }
    }

    @Override
    public List<OrderInfo> selectAllOrderInfo2(OrderInfo order) {
        return orderInfoDao.selectAllOrders2(order);
    }

    @Override
    public Integer getCount2(OrderInfo order) {
        return orderInfoDao.getCount2(order);
    }

    //修改微信订单付款状态
    @Override
    public Integer updateOrderStatusWX(OrderInfo orderInfo) {
        return orderInfoDao.updateOrderStatusWX(orderInfo);
    }

    @Override
    public Integer orderSnagWx(OrderInfo orderInfo) {
           return orderInfoDao.orderSnagWx(orderInfo);
    }
    @Override
    public List<OrderItems> selOrderItemByWeCart(OrderInfo orderInfo) {
        return orderInfoDao.selOrderItemByWeCart(orderInfo);
    }

    @Override
    public List<OrderInfo> selIndividualOrdersApp(String buyerId){
        return orderInfoDao.selIndividualOrdersApp(buyerId);
    }

    @Override
    public Integer updateOrderComplete(String orderCode) {
        return orderInfoDao.updateOrderComplete(orderCode);
    }

    @Transactional
    @Override
    public void updateOrderStatusByUser(UserOrderDto userOrderDto) {
        int i = orderInfoDao.updateOrderShippingStatusByUser(userOrderDto);
        if (i == 0) {
            throw new RuntimeException("订单状态修改失败");
        }
        int j = orderInfoDao.updateOrderPurchasingStatusByUser(userOrderDto);
        if (j == 0) {
            throw new RuntimeException("订单状态修改失败");
        }
        if (userOrderDto.getStatus().equals(2)) {
            //修改余额
            UsersExp usersExp = new UsersExp();
            usersExp.setUserId(userOrderDto.getUserId().intValue());
            usersExp.setPluteformid(userOrderDto.getPlatformId());
            OrderInfo orderInfo = orderInfoDao.getOrderInfoByOrderCode(userOrderDto);
            usersExp.setBalance(orderInfo.getOrderTotal());
            int k = userListDao.updateRBP(usersExp);
            if (k == 0) {
                throw new RuntimeException("余额修改失败");
            }
        }

    }

    @Override
    public OrderInfo selOrderByCodeAndBuyName(OrderInfo orderInfo) {
        return orderInfoDao.selOrderByCodeAndBuyName(orderInfo);
    }

    //查询销量
    @Override
    public List<OrderInfo> selChampionHip(String startTime, String endTime,OrderInfo orderInfo) {
        return orderInfoDao.selChampionHip(startTime, endTime,orderInfo);
    }
    @Override
    public Integer selChampionHipCount(String startTime, String endTime,OrderInfo orderInfo) {
        return orderInfoDao.selChampionHipCount(startTime,endTime,orderInfo);
    }
    //月订单量
    @Override
    public List selOrderQuantity(String startTime, String endTime,OrderInfo orderInfo) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(date);
        Integer years = Integer.parseInt(year);
        String day = null;
        List list = new ArrayList();
        for (int i=1;i<=12;i++){
            //计算月份
            if(i==2){
                //判断闰年
                if(years%4==0 && years%100 !=0 ||years%400 ==0){
                    day = "29";
                }else {
                    day = "28";
                }
            }else if(i==4 || i==6 || i==9 || i==11){
                day = "30";
            }else {
                day = "31";
            }
            startTime = year+"-"+i+"-"+"1";
            endTime = year+"-"+i+"-"+day;
            Integer num = orderInfoDao.selOrderQuantity(startTime, endTime,orderInfo);
			if(num==null){
                num=0;
            }
            list.add(num);
        }
        return list;
    }
	@Override
    public List<OrderInfo> getYUYUEOrderInfo(Page page){
        return orderInfoDao.getYUYUEOrderInfo(page);
    }
    @Override
    public Integer updateItems(OrderItems items){
        Integer a=orderInfoDao.updateItems(items);
        if(a==null)
            return 0;
        return a;
    }

    //月金额数
    @Override
    public List selOrderTotal(String startTime, String endTime, OrderInfo orderInfo) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(date);
        Integer years = Integer.parseInt(year);
        String day = null;
        List list = new ArrayList();
        for (int i=1;i<=12;i++){
            //计算月份
            if(i==2){
                //判断闰年
                if(years%4==0 && years%100 !=0 ||years%400 ==0){
                    day = "29";
                }else {
                    day = "28";
                }
            }else if(i==4 || i==6 || i==9 || i==11){
                day = "30";
            }else {
                day = "31";
            }
            startTime = year+"-"+i+"-"+"1";
            endTime = year+"-"+i+"-"+day;
            Double money = orderInfoDao.selOrderTotal(startTime, endTime, orderInfo);
            if(money==null){
                money=0.0;
            }
            list.add(money);
        }
        return list;
    }

    @Override
    public List<OrderInfo> selEmporiumShopChampionHip(String startTime, String endTime, OrderInfo orderInfo, List productIds) {
        return orderInfoDao.selEmporiumShopChampionHip(startTime, endTime, orderInfo, productIds);
    }

    @Override
    public Integer selEmporiumShopChampionHipCount(String startTime, String endTime, OrderInfo orderInfo, List productIds) {
        return orderInfoDao.selEmporiumShopChampionHipCount(startTime, endTime, orderInfo, productIds);
    }

    @Override
    public List shopOrderQuantity(String startTime, String endTime, OrderInfo orderInfo, List productIds) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(date);
        Integer years = Integer.parseInt(year);
        String day = null;
        List list = new ArrayList();
        for (int i=1;i<=12;i++){
            //计算月份
            if(i==2){
                //判断闰年
                if(years%4==0 && years%100 !=0 ||years%400 ==0){
                    day = "29";
                }else {
                    day = "28";
                }
            }else if(i==4 || i==6 || i==9 || i==11){
                day = "30";
            }else {
                day = "31";
            }
            startTime = year+"-"+i+"-"+"1";
            endTime = year+"-"+i+"-"+day;
            Integer num = orderInfoDao.shopOrderQuantity(startTime, endTime, orderInfo, productIds);
            if(num==null){
                num=0;
            }
            list.add(num);
        }
        return list;
    }

    @Override
    public List shopOrderTotal(String startTime, String endTime, OrderInfo orderInfo, List productIds) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(date);
        Integer years = Integer.parseInt(year);
        String day = null;
        List list = new ArrayList();
        for (int i=1;i<=12;i++){
            //计算月份
            if(i==2){
                //判断闰年
                if(years%4==0 && years%100 !=0 ||years%400 ==0){
                    day = "29";
                }else {
                    day = "28";
                }
            }else if(i==4 || i==6 || i==9 || i==11){
                day = "30";
            }else {
                day = "31";
            }
            startTime = year+"-"+i+"-"+"1";
            endTime = year+"-"+i+"-"+day;
            OrderInfo orderInfo1 = orderInfoDao.shopOrderTotal(startTime, endTime, orderInfo, productIds);
            Double num = 0.0;
            if(orderInfo1!=null){
                num = orderInfo1.getOrderTotal();
            }
            list.add(num);
        }
        return list;
    }
    @Override
    public List selUserOrderTotal(AccountUserVo accountUserVo, OrderInfo orderInfo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(accountUserVo);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(date);
        Integer years = Integer.parseInt(year);
        String day = null;
        List list = new ArrayList();
        String startTime = null;
        String endTime = null;
        for (int i=1;i<=12;i++){
            //计算月份
            if(i==2){
                //判断闰年
                if(years%4==0 && years%100 !=0 ||years%400 ==0){
                    day = "29";
                }else {
                    day = "28";
                }
            }else if(i==4 || i==6 || i==9 || i==11){
                day = "30";
            }else {
                day = "31";
            }
            startTime = year+"-"+i+"-"+"1";
            endTime = year+"-"+i+"-"+day;
            Double num = orderInfoDao.selUserOrderTotal(users,orderInfo,startTime,endTime);
            if(num == null){
                num = 0.0;
            }
            list.add(num);
        }
        return list;
    }

    @Override
    public Integer updateAppointmentStatusWX(OrderInfo orderInfo) {
        return orderInfoDao.updateAppointmentStatusWX(orderInfo);
    }

    @Override
    public OrderInfo selectOneOrderByOrderCode(OrderInfo orderInfo) {
        return orderInfoDao.selectOneOrderByOrderCode(orderInfo);
    }
    @Override
    public Integer getAppointmentDataCount(AppointmentOrder order){
        return orderInfoDao.getAppointmentDataCount(order);
    }
    @Override
    public List<AppointmentOrder> getAppointmentData(AppointmentOrder order){
        return orderInfoDao.getAppointmentData(order);
    }
    @Override
    public Integer updateAppOrderStatus(String platformId, String orderId, String key, Integer value){
        return orderInfoDao.updateAppOrderStatus(platformId,orderId,key,value);
    }
    @Override
    public AppointmentOrder selectGuesterInfo2(String orderId){
        return orderInfoDao.selectGuesterInfo2(orderId);
    }
}
