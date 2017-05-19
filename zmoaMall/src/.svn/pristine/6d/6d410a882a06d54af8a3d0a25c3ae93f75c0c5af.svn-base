package com.zm.mall.web.business.orders;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.service.business.shop.ShopInfoService;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.orders.FilterObj;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.orders.OrderItems;
import com.zm.mall.domain.business.orders.UserOrderDto;
import com.zm.mall.domain.business.shop.WeChatIdentity;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import com.zm.mall.domain.system.systemCode.EasyUIResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 微信端订单模块
 * Created by Bochao on 2017/3/14.
 */
@Controller
@RequestMapping("/business/wxOrder")
public class WeChatOrderController {
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private ShopInfoService shopInfoService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping("/wxOrderList")
    @ResponseBody
    public EasyUIListResult wxOrderList(HttpServletRequest request, OrderInfo order, String filterRules, Long page, Long rows) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }

        if (filterRules != null) {
            try {
                List<FilterObj> list = MAPPER.readValue(filterRules,
                        MAPPER.getTypeFactory().constructCollectionType(List.class, FilterObj.class));
                for (FilterObj obj : list) {

                    Field field = order.getClass().getDeclaredField(obj.getField());
                    field.setAccessible(true);

                    if (obj.getValue().contains("已")) {
                        field.set(order, Integer.valueOf(1));
                    } else if (obj.getValue().contains("未")) {
                        field.set(order, Integer.valueOf(0));
                    } else {
                        field.set(order, obj.getValue());
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                //e.printStackTrace();
            }
        }

        order.setBegionNumber(((page.intValue() - 1) * rows.intValue())); //(page - 1) * rows, rows
        order.setPageSize(rows.intValue());

        order.setPluteformid(userResult.getPluteformid());

        List<OrderInfo> orderInfoList = orderInfoService.selectAllOrderInfo2(order);
        EasyUIListResult result = new EasyUIListResult<OrderInfo>();
        result.setRows(orderInfoList);
        result.setTotal(orderInfoService.getCount2(order).longValue());
        return result;
    }

    /**
     * 订单具体信息查看
     */
    @RequestMapping("/wxOrderDetail")
    @ResponseBody
    public List<OrderItems> wxOrderDetail(String orderId) {
        return orderInfoService.selectGuesterInfo(orderId).getOrderItems();
    }

    @RequestMapping("/updateOrderStatus")
    @ResponseBody
    public EasyUIResult updateOrderStatus(HttpServletRequest request, String orderId, String key, Integer value) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        orderInfoService.updateOrderStatus(userResult.getPluteformid(), orderId, key, value);
        return new EasyUIResult();
    }

    /*----------------------微信接口 Start-----------------------*/

    /**
     * 微信用户更改订单状态，主要用于确认订单已收货。
     * @return
     */
    @RequestMapping(value = "/updateOrderStatusByUser", method = RequestMethod.POST)
    @ResponseBody
    public EasyUIResult updateOrderStatusByUser(@RequestBody String param, UserOrderDto userOrderDto) {
        //对象封装
        if (param.startsWith("{") && param.endsWith("}")) {
            userOrderDto = JSONObject.parseObject(param, UserOrderDto.class);
        }
        //非空判断
        if (userOrderDto == null
                || userOrderDto.getOrderCode() == null
                || userOrderDto.getPlatformId() == null
                || userOrderDto.getOpenId() == null
                || userOrderDto.getStatus() == null) {
            return new EasyUIResult("缺少必须参数。必须参数列表：openId, platformId, orderCode, status");
        }
        //订单状态合法性校验
        if (!userOrderDto.getStatus().equals(0)
                && !userOrderDto.getStatus().equals(1)
                && !userOrderDto.getStatus().equals(2)) {
            return new EasyUIResult("非法的订单状态。取值：0, 1, 2");
        }
        //用户信息真实性校验
        Users user = shopInfoService.getUserByWeChatIdentity(
                new WeChatIdentity(userOrderDto.getOpenId(), userOrderDto.getPlatformId()));
        if (user == null) {
            return new EasyUIResult("用户不存在");
        }
        userOrderDto.setUserId(user.getUserId().longValue());
        try {
            orderInfoService.updateOrderStatusByUser(userOrderDto);
            return new EasyUIResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }
    }

    /*----------------------微信接口 End-------------------------*/

    /*----------------------页面跳转-----------------------*/
    @RequestMapping("/wxOrderListPage")
    public String wxOrderListPage() {
        return "business/wxOrder/wxOrderList";
    }
    @RequestMapping("/wxOrder")
    public String wxOrder() {
        return "business/wxOrder/wxOrder";
    }
    @RequestMapping("/addOrder")
    public String addOrder() {
        return "business/wxOrder/addOrder";
    }
    @RequestMapping("/addProductDlg")
    public String addProductDlg() {
        return "business/wxOrder/addProductDlg";
    }
    @RequestMapping("/addUserDlg")
    public String addUserDlg() {
        return "business/wxOrder/addUserDlg";
    }
}
