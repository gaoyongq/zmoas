package com.zm.mall.dao.business.appointment;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.appointment.AppointmentOrder;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.orders.OrderItems;
import com.zm.mall.domain.business.orders.OrderItemsAttribute;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public interface AppointmentDao extends BaseDao<AppointmentOrder>{
	String insertOrder(OrderInfo orderInfo);
	Integer insertOrderItems(OrderInfo orderInfo);
	List<OrderItems> selectOrderItems(OrderInfo orderInfo);
	Integer insertOrderItemsAttribute(OrderItemsAttribute orderItemsAttribute);
	Integer updateOrderStatusWX(OrderInfo orderInfo);
	//查询单个预约表
	OrderInfo selectOneAppointment(OrderInfo orderInfo);
}
