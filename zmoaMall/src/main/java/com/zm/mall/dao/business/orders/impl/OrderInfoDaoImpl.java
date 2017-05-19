package com.zm.mall.dao.business.orders.impl;


import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.orders.OrderInfoDao;
import com.zm.mall.domain.business.accountsUsers.MsRegions;
import com.zm.mall.domain.business.accountsUsers.RegionExpressPrice;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.appointment.AppointmentOrder;
import com.zm.mall.domain.business.orders.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/11.
 */
@Service
public class OrderInfoDaoImpl extends BaseDaoImpl<OrderInfo> implements OrderInfoDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.orders.OrderInfoDao.";

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }



    /**
     * 分页-查询订单数量
     * @param tip
     * @return
     */
    @Override
    public Integer getCount(OrderByTip tip) {
        return sqlTemplate.selectOne(getNameSpace("selectCount"),tip);
    }

    @Override
    /**
     * 订单显示和查询的方法
     */
    public List<OrderInfo> selectAllOrders(OrderByTip tip) {

        return sqlTemplate.selectList(getNameSpace("selectAllOrders"),tip);
    }

    @Override

    /**
     * 获取订单详细信息的方法
     */
    public OrderInfo selectGuesterInfo(String orderId) {

        return sqlTemplate.selectOne(getNameSpace("selectGuesterInfo"),orderId);
    }

    @Override
    public OrderInfo selectOrderItem(Long orderId) {
        return sqlTemplate.selectOne(getNameSpace("selectone"),orderId);
    }

    /**
     * 订单删除方法
     * @param orderCode
     * @return
     */
    @Override
    public int deleteOrder(String orderCode) {
        return sqlTemplate.update(getNameSpace("deleteOrder"),orderCode);
    }
    /**
     * 根据用户名获得对应的收货信息
     */
    @Override
    public Users getShippingAddress(String buyerName) {
        //对用户名去首尾空格
        buyerName = buyerName.trim();
        return sqlTemplate.selectOne(getNameSpace("selectShippingInfo"), buyerName);
    }
    @Override
    public List<OrderInfo> select(OrderInfo orderInfo) {
        return sqlTemplate.selectList(getNameSpace("startMakePurchase"), orderInfo);
    }
    /**
     * 根据regionId获取运费
     */
    @Override
    public RegionExpressPrice getYunFei(RegionExpressPrice regionExpressPrice) {
        return sqlTemplate.selectOne(getNameSpace("checkYunFei"),regionExpressPrice);
    }
    /**
     * 插入新增订单
     */
    @Override
    public String insertOrder(OrderInfo orderInfo) {
//        //先查出最大的订单Id
//        String orderId= sqlTemplate.selectOne(getNameSpace("selectOrderId"));
//        if (orderId !=null){
//            orderId = orderId.replaceAll("zm","");
//            Integer orderId1 =Integer.parseInt(orderId)+1;
//            orderId = orderId1.toString();
//        }else {
//            orderId = "1";
//        }
////        if (orderId.indexOf("zm")!=-1){
////            orderId = orderId.replaceAll("zm","");
////            Integer orderId1 =Integer.parseInt(orderId)+1;
////            orderId = orderId1.toString();
////        }
//        orderInfo.setOrderId("zm"+orderId);
//        sqlTemplate.insert(getNameSpace("insertEntry"),orderInfo);
//        return "zm"+orderId;
//    }
        //先查出最大的WX订单Id 王樵
        if (orderInfo.getOrderCheckStatus() == 1) {
            String orderId = sqlTemplate.selectOne(getNameSpace("selectOrderIdWX"));
            if (orderId != null) {
                orderId = orderId.replaceAll("wx", "");
                Integer orderId1 = Integer.parseInt(orderId) + 1;
                orderId = orderId1.toString();
            } else {
                orderId = "1";
            }
            orderInfo.setOrderId("wx" + orderId);
            sqlTemplate.insert(getNameSpace("insertEntry"), orderInfo);
            return "wx" + orderId;
        } else {
            String orderId = sqlTemplate.selectOne(getNameSpace("selectOrderId"));
            if (orderId != null) {
                orderId = orderId.replaceAll("zm", "");
                Integer orderId1 = Integer.parseInt(orderId) + 1;
                orderId = orderId1.toString();
            } else {
                orderId = "1";
            }
            orderInfo.setOrderId("zm" + orderId);
            sqlTemplate.insert(getNameSpace("insertEntry"), orderInfo);
            return "zm" + orderId;
        }
    }
        //插入订单产品信息

    @Override
    public Integer insertOrderItems(OrderInfo orderInfo) {
        return sqlTemplate.insert(getNameSpace("insertOrderItems"),orderInfo);
    }

    //根据订单Id查询出对应的订单产品信息按照顺序查


    @Override
    public List<OrderItems> selectOrderItems(OrderInfo orderInfo) {
        return sqlTemplate.selectList(getNameSpace("selectOrderItemsByOrderId"),orderInfo);
    }

    @Override
    public Integer insertOrderItemsAttribute(OrderItemsAttribute orderItemsAttribute) {
        return sqlTemplate.insert(getNameSpace("insertOrderItemsAttribute"),orderItemsAttribute);
    }
    /**
     * 白洋洋   订单处理页面
     */
    @Override
    public List<OrderInfo> getAllOrderInfo(OrderByTip orderByTip) {
        return sqlTemplate.selectList(getNameSpace("searchOrder"),orderByTip);
    }
    /**
     * 白洋洋   订单处理页面查询-OA订单
     */
    @Override
    public List<OrderInfo> getOrderByTip(OrderByTip tip) {
        return sqlTemplate.selectList(getNameSpace("searchOrder"),tip);
    }
    /**
     * 白洋洋   订单处理页面查询-商城订单
     */

    @Override
    public List<OrderInfo> getOrderByTipByM(OrderByTip tip) {
        return sqlTemplate.selectList(getNameSpace("searchOrderByM"),tip);
    }

    @Override
    public List<OrderInfo> autoCompleteOrderInfo() {
        return sqlTemplate.selectList(getNameSpace("autoCompleteOrderInfo"));
    }
    /**
     *  白洋洋  处理选中的订单
     */
    @Override
    public List<OrderItems> selectOrdersByChecked(Long departmentId,String[] ids) {
        //先将orderId进行拆分
//        String[] orderIds = orderId.split(",");
//        List list = new ArrayList();
//        for (int i=0;i<orders.length;i++){
//            String orderIdS = orders[i];
//            List<OrderItems> orderItemsList =sqlTemplate.selectList(getNameSpace("selectcheckedOrders"),orderIdS);
//            list.add(orderItemsList);
//        }
        Map map = new HashMap();
        map.put("departmentId",departmentId);
        map.put("ids",ids);
        return sqlTemplate.selectList(getNameSpace("selectcheckedOrders"),map);
    }
    /**
     * 白洋洋   订单审核通过
     */
    @Override
    public Integer passOrder(String orderIds) {
        String[] orderId = orderIds.split(",");
        return sqlTemplate.update(getNameSpace("updateOrder"),orderId);
    }
    /**
     * 白洋洋  订单未通过审核
     */
    @Override
    public Integer notPassOrder(String orderIds) {
        String[] orderId = orderIds.split(",");
        return sqlTemplate.update(getNameSpace("updateOrderCheckStatus"),orderId);
    }
    /**
     * webService同步订单新增
     */
    @Override
    public Integer insertOrderByWebService(OrderInfo orderInfo) {
        sqlTemplate.insert(getNameSpace("insertOrderAction"),orderInfo.getOrderAction());
        //根据订单RegionId获取收货人所在的省市县
        MsRegions msRegions = new MsRegions();
        msRegions = sqlTemplate.selectOne(getNameSpace("selectRegion"), orderInfo.getRegionId());
        String shipCounty = msRegions.getRegionName();
        String path = msRegions.getPath();
        path = path.substring(0,path.length()-1);
        String[] paths = path.split(",");
        String shipProvince = sqlTemplate.selectOne(getNameSpace("selectRegionName"),Integer.parseInt(paths[1]));
        String shipCity = sqlTemplate.selectOne(getNameSpace("selectRegionName"),Integer.parseInt(paths[2]));
        orderInfo.setShipProvince(shipProvince);
        orderInfo.setShipCity(shipCity);
        orderInfo.setShipCounty(shipCounty);
        Integer a = sqlTemplate.insert(getNameSpace("insertOrder"), orderInfo);
        List<OrderItems> orderItemses = orderInfo.getOrderItems();
        for (int i=0;i<orderItemses.size();i++){
            Long productId = orderItemses.get(i).getProductId();
            String typeName = sqlTemplate.selectOne(getNameSpace("selectTypeName"),productId);
            if (typeName.indexOf("白玻")!=-1){
             orderItemses.get(i).setGoodsType("白玻");
            }else{
                orderItemses.get(i).setGoodsType("艺玻");
            }
            sqlTemplate.insert(getNameSpace("insertOrderItemsByWeb"),orderItemses.get(i));
            //减去库存
            sqlTemplate.update(getNameSpace("updateSkuStock"),orderItemses.get(i));

        }
        return a;
    }
    /**
     * 打印
     */
    @Override
    public Map<String,Object> selectForPrint(String orderId) {
        Map<String,Object> map = new HashMap<String, Object>();
        OrderInfo orderInfo = sqlTemplate.selectOne(getNameSpace("selectOrder"),orderId);
        map.put("orderInfo",orderInfo);
        List<OrderItems> orderItemsList = sqlTemplate.selectList(getNameSpace("selectOrderItems"),orderId);
        map.put("list",orderItemsList);
        return map;
    }
    /**
     * 订单处理页面分页-OA的订单
     */
    @Override
    public Integer getOrderCount(OrderByTip orderByTip) {
        return sqlTemplate.selectOne(getNameSpace("selectOrderCount"),orderByTip);
    }
    /**
     * 订单处理页面分页-商城的订单
     */
    @Override
    public Integer getOrderCountByM(OrderByTip orderByTip) {
        return sqlTemplate.selectOne(getNameSpace("selectOrderCountFromM"),orderByTip);
    }

    /**
     * 改变订单状态为已生成物流
     */
    @Override
    public Integer updateOrderPurchasing(String orderCode) {
        return sqlTemplate.update(getNameSpace("updateOrderPurchasing"),orderCode);
    }
    /**
     * 订单修改--查询要修改订单的信息
     * param orderId
     * return OrderInfo
     */
    @Override
    public OrderInfo getOrderInfoByOrderId(String orderId) {
        return sqlTemplate.selectOne(getNameSpace("selectOrderInfoByOrderId"),orderId);
    }

    @Override
    public List<OrderItems> getOrderItems(String orderId) {
        return sqlTemplate.selectList(getNameSpace("selectOrderItemsInfo"),orderId);
    }

    @Override
    public List<OrderItemsAttribute> getOrderAttribute(Long itemId) {
        return sqlTemplate.selectList(getNameSpace("selectOrderAttributes"),itemId);
    }
    /**
     * 订单修改
     */
    @Override
    public Integer updateOrder(OrderInfo orderInfo) {
        return sqlTemplate.update(getNameSpace("updateOrderInfo"),orderInfo );
    }

    @Override
    public Integer deleteOrderProduct(OrderInfo orderInfo) {
        List<OrderItems> orderItemsList = sqlTemplate.selectList(getNameSpace("selectOrderItemsInfo"),orderInfo);
        for (int i=0;i<orderItemsList.size();i++){
            sqlTemplate.delete(getNameSpace("deleteOrderAttribute"),orderItemsList.get(i).getItemId());
        }
        sqlTemplate.delete(getNameSpace("deleteOrderItems"),orderInfo);
        return null;
    }

    /**
     * 修改已经采购的订单状态
     * OrderCheckStatus为1
     * @param orderId
     * @return
     */
    @Override
    public Integer updatePOrderCheckStatus(String orderId) {
        String[] orderIds = orderId.split(",");
        return sqlTemplate.update(getNameSpace("updatePurchaseOrder"),orderIds);
    }
    //查询所有没有申请车辆的订单
    @Override
    public List<OrderInfo> selectNoCarByLimit(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectNoCarByLimit"),page);
    }
    //统计所有没有申请车辆的订单
    @Override
    public Integer selectNoCarAllCount(Page page) {
        return sqlTemplate.selectOne(getNameSpace("selectNoCarAllCount"),page);
    }

    @Override
    public OrderInfo selectOneOrder(String orderCode) {
        OrderInfo orderInfo=  sqlTemplate.selectOne(getNameSpace("selectOneOrder"), orderCode);
        return orderInfo;
    }

    @Override
    public void updateOrderCarState(String orderCode) {
        sqlTemplate.update(getNameSpace("updateOrderCarState"),orderCode);
    }

    /**
     * 处理商城订单时加上对应的小组
     * @param orderInfo
     */
    @Override
    public void updateGroup(OrderInfo orderInfo) {
        sqlTemplate.update(getNameSpace("updateOrderFromM"),orderInfo);
    }

    /**
     * 处理订单时修改订单为已处理状态
     * @param orderId
     */
    @Override
    public void updateOrderToHandled(String orderId) {
        sqlTemplate.update(getNameSpace("updateOrderToHandled"),orderId);

    }

    @Override
    public void updateOrderCarTwoState(String orderCode) {
        sqlTemplate.update(getNameSpace("updateOrderCarTwoState"),orderCode);
    }
    //根据用户id查订单
    @Override
    public List<OrderInfo> orderList(Users users) {
        return sqlTemplate.selectList(getNameSpace("orderList"),users);
    }
    //微信查询用户自己的订单
    @Override
    public List<OrderInfo> selectWXOrder(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectWXOrder"),page);
    }
    @Override
	public List<OrderInfo> selectWXOrderDaiFuKuan(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectWXOrderDaiFuKuan"),page);
    }

    @Override
    public int updateOrderStatus(String platformId, String orderId, String key, Integer value) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("orderId", orderId);
        map.put("key", key);
        map.put("value", value);
        return sqlTemplate.update(getNameSpace("updateOrderStatus"), map);
    }

    @Override
    public List<OrderInfo> selectAllOrders2(OrderInfo order) {
        return sqlTemplate.selectList(getNameSpace("selectAllOrders2"), order);
    }

    @Override
    public Integer getCount2(OrderInfo order) {
        return sqlTemplate.selectOne(getNameSpace("selectCount2"), order);
    }
    //修改微信订单付款状态
    @Override
    public Integer updateOrderStatusWX(OrderInfo orderInfo) {
        return sqlTemplate.update(getNameSpace("updateOrderStatusWX"),orderInfo);
    }
    //抢购公共订单
    @Override
    public Integer orderSnagWx(OrderInfo orderInfo) {
        return sqlTemplate.update(getNameSpace("orderSnagWx"),orderInfo);
    }
    @Override
    public List<OrderItems> selOrderItemByWeCart(OrderInfo orderInfo) {
        return sqlTemplate.selectList("selOrderItemByWeCart", orderInfo);
    }
	//根据订单orderCode和平台Id和购买人名字buyerName
    @Override
    public OrderInfo selOrderByCodeAndBuyName(OrderInfo orderInfo) {
        return sqlTemplate.selectOne(getNameSpace("selOrderByCodeAndBuyName"),orderInfo);
    }
    //查询个人中心订单信息-移动端
    @Override
    public List<OrderInfo> selIndividualOrdersApp(String buyerId){
        return sqlTemplate.selectList(getNameSpace("IndividualOrdersApp"),buyerId);
    }

    //修改订单状态为已完成
    @Override
    public Integer updateOrderComplete(String orderCode) {
        return sqlTemplate.update(getNameSpace("updateOrderComplete"),orderCode);
    }
    @Override
    public int updateOrderShippingStatusByUser(UserOrderDto userOrderDto) {
        return sqlTemplate.update(getNameSpace("updateOrderShippingStatusByUser"), userOrderDto);
    }

    @Override
    public int updateOrderPurchasingStatusByUser(UserOrderDto userOrderDto) {
        return sqlTemplate.update(getNameSpace("updateOrderPurchasingStatusByUser"), userOrderDto);
    }

    @Override
    public OrderInfo getOrderInfoByOrderCode(UserOrderDto userOrderDto) {
        return sqlTemplate.selectOne(getNameSpace("getOrderInfoByOrderCode"), userOrderDto);
    }

    //查询销量
    @Override
    public List<OrderInfo> selChampionHip(String startTime, String endTime,OrderInfo orderInfo) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("begionNumber",orderInfo.getBegionNumber());
        map.put("pageSize",orderInfo.getPageSize());
        map.put("pluteformid",orderInfo.getPluteformid());

        return sqlTemplate.selectList(getNameSpace("championHip"),map);
    }
    @Override
    public Integer selChampionHipCount(String startTime, String endTime,OrderInfo orderInfo) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("pluteformid",orderInfo.getPluteformid());

        return sqlTemplate.selectOne(getNameSpace("championHipCount"),map);
    }
    //月订单量
    @Override
    public Integer selOrderQuantity(String startTime, String endTime,OrderInfo orderInfo) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("pluteformid",orderInfo.getPluteformid());

        return sqlTemplate.selectOne(getNameSpace("orderQuantity"),map);
    }

    @Override
    public Long getUserIdByPlatformId(String platformId) {
        return sqlTemplate.selectOne(getNameSpace("getUserIdByPlatformId"), platformId);
    }

    @Override
    public List<OrderInfo> getYUYUEOrderInfo(Page page){
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        return  sqlTemplate.selectList(getNameSpace("getYUYUEOrderInfo"),page);
    }
    @Override
    public Integer updateItems(OrderItems items){
        return sqlTemplate.update(getNameSpace("updateItems"),items);
    }
    //月金额量
    @Override
    public Double selOrderTotal(String startTime, String endTime, OrderInfo orderInfo) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("pluteformid",orderInfo.getPluteformid());

        return sqlTemplate.selectOne(getNameSpace("orderTotal"),map);
    }
	//商城下店铺销量
    @Override
    public List<OrderInfo> selEmporiumShopChampionHip(String startTime, String endTime, OrderInfo orderInfo, List productIds) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("begionNumber",orderInfo.getBegionNumber());
        map.put("pageSize",orderInfo.getPageSize());
        map.put("pluteformid",orderInfo.getPluteformid());
        map.put("productTds",productIds);
        return sqlTemplate.selectList(getNameSpace("emporiumShopChampionHip"),map);
    }

    @Override
    public Integer selEmporiumShopChampionHipCount(String startTime, String endTime, OrderInfo orderInfo, List productIds) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("pluteformid",orderInfo.getPluteformid());
        map.put("productTds",productIds);
        return sqlTemplate.selectOne(getNameSpace("emporiumShopChampionHipCount"),map);
    }
	//店铺订单数量
    @Override
    public Integer shopOrderQuantity(String startTime, String endTime, OrderInfo orderInfo, List productIds) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("pluteformid",orderInfo.getPluteformid());
        map.put("productTds",productIds);
        return sqlTemplate.selectOne(getNameSpace("shopOrderQuantity"),map);
    }
	//店铺订单金额
    @Override
    public OrderInfo shopOrderTotal(String startTime, String endTime, OrderInfo orderInfo, List productIds) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("pluteformid",orderInfo.getPluteformid());
        map.put("productTds",productIds);
        return sqlTemplate.selectOne(getNameSpace("shopOrderTotal"),map);
    }

    //查询用户的月交易额
    @Override
    public Double selUserOrderTotal(Users users, OrderInfo orderInfo,String startTime, String endTime) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("users",users);
        return sqlTemplate.selectOne(getNameSpace("selUserOrderTotal"),map);
    }

    @Override
    public Integer updateAppointmentStatusWX(OrderInfo orderInfo) {
        return sqlTemplate.update(getNameSpace("updateAppointmentStatusWX"),orderInfo);
    }

    @Override
    public OrderInfo selectOneOrderByOrderCode(OrderInfo orderInfo) {
        return sqlTemplate.selectOne(getNameSpace("selectOneOrderByOrderCode"),orderInfo);
    }
    @Override
    public Integer getAppointmentDataCount(AppointmentOrder order){
        return sqlTemplate.selectOne(getNameSpace("getAppointmentDataCount"),order);
    }
    @Override
     public List<AppointmentOrder> getAppointmentData(AppointmentOrder order){
        return  sqlTemplate.selectList(getNameSpace("getAppointmentData"),order);
    }
    @Override
     public Integer updateAppOrderStatus(String platformId, String orderId, String key, Integer value){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("plueformId",platformId);
        map.put("orderId",orderId);
        map.put("key",key);
        map.put("value",value);
        return sqlTemplate.update("updateAppOrderStatus",map);
    }
    @Override
    public AppointmentOrder selectGuesterInfo2(String orderId) {
        return sqlTemplate.selectOne(getNameSpace("selectGuesterInfo2"),orderId);
    }
}
