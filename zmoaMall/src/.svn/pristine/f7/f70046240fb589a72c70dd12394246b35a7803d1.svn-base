package com.zm.mall.client.service.business.orders;


import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.orders.OrderInfoResult;
import com.zm.mall.client.vo.business.accounts.AccountUserVo;
import com.zm.mall.client.vo.business.orders.OrderInfoVo;
import com.zm.mall.client.vo.business.product.ProductInfoVo;
import com.zm.mall.client.vo.business.product.RegionInfoVo;
import com.zm.mall.domain.business.accountsUsers.RegionExpressPrice;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.appointment.AppointmentOrder;
import com.zm.mall.domain.business.orders.*;
import com.zm.mall.domain.business.product.AttributeInfo;
import com.zm.mall.domain.business.product.ProductType;
import com.zm.mall.domain.business.product.RegionInfo;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/11.
 */
@WebService
public interface OrderInfoService {
    /**
     * 分页  查询出订单数量
     */
    Integer getCount(OrderByTip tip);
    /**
     * 订单显示和查询方法
     * @param
     * @return
     */
    List<OrderInfo>  selectAllOrderInfo(OrderByTip tip);

    /**
     * 查询一个订单的详细信息
     * @param orderId
     * @return  OrderInfo实体类
     */

    OrderInfo  selectGuesterInfo(String orderId);
    /**
     * 取消订单
     */
    int deleteOrder(String orderCode);
    //根据用户名获得对应收货信息
    Users getShippingAddress(String buyerName);
    List<OrderInfoResult>  startMakePurchase(OrderInfoVo orderInfoVo);
    OrderInfo selectItems(Long orderId);
    /**
     * 根据regionId获取运费
     */
    RegionExpressPrice getYunFei(RegionExpressPrice regionExpressPrice);
    /**
     * 插入新增订单
     */
    Integer insertOrder(OrderInfo orderInfo);
    /**
     * 获取地区信息
     */
    List<RegionInfo> getRegionInfo();
    /**
     * 查询产品类型
     */
    List<ProductType> getProductType(String platformId, Long shopId);
    /**
     * 根据typeId类型Id和规格属性名获取AttributeId,ValueId,ValueStr
     */
    List<AttributeInfo> selectAttributeValue(AttributeInfo attributeInfo);
    /**
     * 白洋洋   根据产品Id获取产品价格
     */
    List<SkuData> checkPrice(ProductInfoVo productInfoVo);

    /**
     * autoComplete可编辑下拉框订单
     * @return
     */
    List<OrderInfo> autoCompleteOrderInfo();
    /**
     * 白洋洋   订单处理
     */
    List<OrderInfo> getAllOrderInfo(OrderByTip orderByTip);
    /**
     * 白洋洋   订单处理页面查询
     */
    List<OrderInfo> getOrderByTip(OrderByTip tip);
    List<OrderInfo> getOrderByTipByM(OrderByTip tip);
    /**
     * 白洋洋    处理选中的订单
     */
    List<OrderItems> selectOrdersByChecked(Long departmentId,String[] ids);
    /**
     * 白洋洋   订单审核功能
     */
    Integer passOrder(String message,String orderIds);
    /**
     * webService接口方法
     * @param orderInfo
     * @return
     * @throws Exception
     */
    @Transactional
    Integer insertOrderByWebService(OrderInfo orderInfo)throws Exception;
    /**
     * 打印的接口
     *
     */
    Map<String,Object> selectForPrint(String orderId);
    /**
     * 订单处理页面分页
     */
    Integer getOrderCount(OrderByTip orderByTip);
    Integer getOrderCountByM(OrderByTip orderByTip);
    /**
     * 改变订单状态为已生成物流
     */
    Integer updateOrderPurchasing(String orderCode);
    /**
     * 订单修改--查询要修改的订单信息
     */
    OrderInfo getOrderInfoByOrderId(String orderId);
    List<OrderItems> getOrderItems(String orderId);
    List<OrderItemsAttribute> getOrderAttribute(Long itemId);
    /**
     * 订单修改
     */
    Integer updateOrderInfo(OrderInfo orderInfo);

    /**
     * 修改已经采购的订单状态
     * OrderCheckStatus为1
     * @param
     * @return
     */
    Integer updatePurchaseOrderCheckStatus(String orderIds);
    /**
     * selectNoCarOrderList 查询所有没有车的订单 分页
     * @param page
     * @return
     */
    Page selectNoCarOrderList(Page page, OrderInfo orderInfo);

    /**
     * 根据订单编号，查询单个订单
     */
    OrderInfo selectOneOrder(String orderCode);

    /**
     * 修改订单车辆的状态
     * @param orderCode
     */
    void updateOrderCarState(String orderCode);
    /**
     * 订单新增时-申请车辆插入申请车辆表
     */
    Integer insertApplyCar(OrderInfo orderInfo);
    /**
     *处理商城订单时加上对应的小组
     */
    void updateGroup(OrderInfo orderInfo);
    /**
     * 处理订单时修改订单状态为已处理
     */
    void updateOrderToHandled(String orderId);
    /**
     * 修改订单车辆的状态
     * @param orderCode
     */
    void updateOrderCarTwoState(String orderCode);

    /**
     * 计算平方米价格 by liupeng
     * @param layout
     * @param price
     * @return
     */
    Double layoutPrice(String layout, String price);

    RegionInfo selRegionInfo(RegionInfoVo regionInfo);


    //微信查询 用户自己的订单
    List<OrderInfo> selectWXOrder(Page page);
	//微信查询 用户自己的待付款订单
    List<OrderInfo> selectWXOrderDaiFuKuan(Page page);
    /**
     * 更新订单状态
     * Created by JiaBochao on 2017-3-16 07:57:26
     * @param platformId 平台id
     * @param orderId 订单id
     * @param key 状态对应数据库字段名
     * @param value 状态对应数据库字段值
     * @return
     */
    void updateOrderStatus(String platformId, String orderId, String key, Integer value);

    /**
     * 获取所有订单
     * Created by JiaBochao on 2017-3-16 07:57:26
     * @param order
     * @return
     */
    List<OrderInfo> selectAllOrderInfo2(OrderInfo order);

    Integer getCount2(OrderInfo order);
    //抢购公共订单
    Integer orderSnagWx(OrderInfo orderInfo);
    //获得用户订单详细
    List<OrderItems> selOrderItemByWeCart(OrderInfo orderInfo);

    //修改微信订单付款状态
    Integer updateOrderStatusWX(OrderInfo orderInfo);
	
	//根据订单orderCode和平台Id和购买人名字buyerName
    OrderInfo selOrderByCodeAndBuyName(OrderInfo orderInfo);

    //查询个人中心订单信息-移动端
    List<OrderInfo> selIndividualOrdersApp(String buyerId);

    //修改订单状态为已完成
    Integer updateOrderComplete(String orderCode);
    //微信用户修改订单状态
    void updateOrderStatusByUser(UserOrderDto userOrderDto);

    //查询销量
    List<OrderInfo> selChampionHip(String startTime,String endTime,OrderInfo orderInfo);
    Integer selChampionHipCount(String startTime,String endTime,OrderInfo orderInfo);
    //订单量
    List selOrderQuantity(String startTime,String endTime,OrderInfo orderInfo);


    //查询预约订单
    List<OrderInfo> getYUYUEOrderInfo(Page page);
    //修改预约订单信息
    Integer updateItems(OrderItems items);

    //月金额量
    List selOrderTotal(String startTime,String endTime,OrderInfo orderInfo);
    //查询商城的店铺销量
    List<OrderInfo> selEmporiumShopChampionHip(String startTime,String endTime,OrderInfo orderInfo,List productIds);
    Integer selEmporiumShopChampionHipCount(String startTime,String endTime,OrderInfo orderInfo,List productIds);
    //店铺订单量
    List shopOrderQuantity(String startTime,String endTime,OrderInfo orderInfo,List productIds);
    //店铺订单金额
    List shopOrderTotal(String startTime,String endTime,OrderInfo orderInfo,List productIds);

    //用户交易额
    List selUserOrderTotal(AccountUserVo accountUserVo,OrderInfo orderInfo);
    //修改预约支付状态
    Integer updateAppointmentStatusWX(OrderInfo orderInfo);
    //查询一个订单(获取平台Id和openId)
    OrderInfo selectOneOrderByOrderCode(OrderInfo orderInfo);

    //按条件查询预约数据总条数
    Integer getAppointmentDataCount(AppointmentOrder order);

    //按条件查询预约数据
    List<AppointmentOrder> getAppointmentData(AppointmentOrder order);
    //修改预约表某些状态
    Integer updateAppOrderStatus(String platformId, String orderId, String key, Integer value);
    //获取预约订单产品信息
    AppointmentOrder selectGuesterInfo2(String orderId);
}
