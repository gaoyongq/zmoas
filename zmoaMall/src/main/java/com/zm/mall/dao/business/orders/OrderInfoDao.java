package com.zm.mall.dao.business.orders;


import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.accountsUsers.RegionExpressPrice;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.appointment.AppointmentOrder;
import com.zm.mall.domain.business.orders.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/11.
 */
//@Repository("OrderInfoDao")
public interface OrderInfoDao extends BaseDao<OrderInfo> {
    //分页-查询出订单数量
    Integer getCount(OrderByTip tip);
    //订单显示和查询方法
    List<OrderInfo>  selectAllOrders(OrderByTip tip);
    //查询订单详细信息方法
    OrderInfo  selectGuesterInfo(String orderId);
    //订单删除方法
    int deleteOrder(String orderCode);
    //根据用户名获得对应收货信息方法
    Users getShippingAddress(String buyerName);
    //第一次查询
    OrderInfo selectOrderItem(Long orderId);
    //根据regionId获取运费
    RegionExpressPrice getYunFei(RegionExpressPrice regionExpressPrice);

    /**
     * 插入新增订单
     */
    String insertOrder(OrderInfo orderInfo);

    //插入订单产品信息
    Integer insertOrderItems(OrderInfo orderInfo);
    //可编辑下拉框
    List<OrderInfo> autoCompleteOrderInfo();
    List<OrderItems> selectOrderItems(OrderInfo orderInfo);
    //插入订单产品规格表
    Integer insertOrderItemsAttribute(OrderItemsAttribute orderItemsAttribute);
    /**
     * 白洋洋    订单处理页面
     */
    List<OrderInfo> getAllOrderInfo(OrderByTip orderByTip);
    /**
     * 白洋洋   订单处理页面查询
     */
    List<OrderInfo> getOrderByTip(OrderByTip tip);
    List<OrderInfo> getOrderByTipByM(OrderByTip tip);
    /**
     * 白洋洋   处理选中的订单
     */
    List<OrderItems> selectOrdersByChecked(Long departmentId,String[] ids);
    /**
     * 白洋洋   订单审核通过方法
     */
    Integer passOrder(String orderIds);
    /**
     * 白洋洋   订单审核未通过的方法
     */
    Integer notPassOrder(String orderIds);
    /**
     * webservice 同步订单新增
     */
    Integer insertOrderByWebService(OrderInfo orderInfo);
    /**
     * 打印的接口
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
    Integer updateOrder(OrderInfo orderInfo);
    Integer deleteOrderProduct(OrderInfo orderInfo);
    /**
     * 修改已经采购的订单状态
     * OrderCheckStatus为1
     */
    Integer updatePOrderCheckStatus(String orderId);
    //查询所有没有申请车辆的订单
    List<OrderInfo> selectNoCarByLimit(Page page);
    //统计所有没有申请车辆的订单
    Integer selectNoCarAllCount(Page page);
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
     *处理商城订单时加上对应的小组
     */
    void updateGroup(OrderInfo orderInfo);
    /**
     * 处理订单时改变订单状态为已处理
     */
    void updateOrderToHandled(String orderId);
    /**
     * 修改订单车辆的状态
     * @param orderCode
     */
    void updateOrderCarTwoState(String orderCode);
    //根据用户id查订单
    List<OrderInfo> orderList(Users users);
    //微信查询 用户自己的订单
    List<OrderInfo> selectWXOrder(Page page);
	//微信查询 用户自己的待付款订单
    List<OrderInfo> selectWXOrderDaiFuKuan(Page page);
    //修改微信订单付款状态
    Integer updateOrderStatusWX(OrderInfo orderInfo);
    /**
     * 更改订单状态
     * Created by JiaBochao on 2017-3-16 07:57:26
     * @param platformId 平台id
     * @param orderId 订单id
     * @param key 状态对应数据库字段名
     * @param value 状态对应数据库字段值
     * @return
     */

    int updateOrderStatus(String platformId, String orderId, String key, Integer value);

    /**
     * 获取所有订单
     * Created by JiaBochao on 2017-3-16 07:57:26
     * @param order
     * @return
     */
    List<OrderInfo> selectAllOrders2(OrderInfo order);

    Integer getCount2(OrderInfo order);
    //抢购公共订单
    Integer orderSnagWx(OrderInfo orderInfo);
    List<OrderItems> selOrderItemByWeCart(OrderInfo orderInfo);
	//根据订单orderCode和平台Id和购买人名字buyerName
    OrderInfo selOrderByCodeAndBuyName(OrderInfo orderInfo);
    //查询个人中心订单信息-移动端
    List<OrderInfo> selIndividualOrdersApp(String buyerId);
    //修改订单的状态为已完成
    Integer updateOrderComplete(String orderCode);
    //微信用户修改订单状态
    int updateOrderShippingStatusByUser(UserOrderDto userOrderDto);
    //微信用户修改订单状态
    int updateOrderPurchasingStatusByUser(UserOrderDto userOrderDto);

    //通过订单编号查询订单信息
    OrderInfo getOrderInfoByOrderCode(UserOrderDto userOrderDto);
    //查询销量
    List<OrderInfo> selChampionHip(String startTime,String endTime,OrderInfo orderInfo);
    Integer selChampionHipCount(String startTime,String endTime,OrderInfo orderInfo);
    //订单量
    Integer selOrderQuantity(String startTime,String endTime,OrderInfo orderInfo);

    //通过平台id查询userId
    Long getUserIdByPlatformId(String platformId);
    //查询预约订单
    public List<OrderInfo> getYUYUEOrderInfo(Page page);
    //修改预约订单
    public Integer updateItems(OrderItems items);

    //月金额量
    Double selOrderTotal(String startTime,String endTime,OrderInfo orderInfo);
    //查询商城的店铺销量
    List<OrderInfo> selEmporiumShopChampionHip(String startTime,String endTime,OrderInfo orderInfo,List productIds);
    Integer selEmporiumShopChampionHipCount(String startTime,String endTime,OrderInfo orderInfo,List productIds);
    //店铺订单量
    Integer shopOrderQuantity(String startTime,String endTime,OrderInfo orderInfo,List productIds);
    //店铺订单金额
    OrderInfo shopOrderTotal(String startTime,String endTime,OrderInfo orderInfo,List productIds);
    //查询用户的月交易额
    Double selUserOrderTotal(Users users,OrderInfo orderInfo,String startTime,String endTime);
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
