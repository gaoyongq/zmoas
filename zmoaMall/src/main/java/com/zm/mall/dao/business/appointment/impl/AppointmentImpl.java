package com.zm.mall.dao.business.appointment.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.appointment.AppointmentDao;
import com.zm.mall.domain.business.appointment.AppointmentOrder;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.orders.OrderItems;
import com.zm.mall.domain.business.orders.OrderItemsAttribute;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class AppointmentImpl extends BaseDaoImpl<AppointmentOrder> implements AppointmentDao{
	private final static String NAMESPACE = "com.zm.mall.dao.business.appointment.AppointmentDao.";
	@Override
	public String getNameSpace(String statement) {
		return NAMESPACE+statement;
	}

	@Override
	public Integer insertOrderItemsAttribute(OrderItemsAttribute orderItemsAttribute) {
		return sqlTemplate.insert(getNameSpace("insertOrderItemsAttribute"),orderItemsAttribute);
	}

	@Override
	public List<OrderItems> selectOrderItems(OrderInfo orderInfo) {
		return sqlTemplate.selectList(getNameSpace("selectOrderItemsByOrderId"),orderInfo);
	}

	@Override
	public Integer insertOrderItems(OrderInfo orderInfo) {
		return sqlTemplate.insert(getNameSpace("insertOrderItems"),orderInfo);
	}

	@Override
	public String insertOrder(OrderInfo orderInfo) {
		if (orderInfo.getOrderCheckStatus() == 1) {
			String orderId = sqlTemplate.selectOne(getNameSpace("selectOrderIdWX"));
			if (orderId != null) {
				orderId = orderId.replaceAll("wxyy", "");
				Integer orderId1 = Integer.parseInt(orderId) + 1;
				orderId = orderId1.toString();
			} else {
				orderId = "1";
			}
			orderInfo.setOrderId("wxyy" + orderId);
			sqlTemplate.insert(getNameSpace("insertEntry"), orderInfo);
			return "wxyy" + orderId;
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
	//修改微信预约单付款状态
	@Override
	public Integer updateOrderStatusWX(OrderInfo orderInfo) {
		return sqlTemplate.update("updateOrderStatusWX",orderInfo);
	}

	@Override
	public OrderInfo selectOneAppointment(OrderInfo orderInfo) {
		return sqlTemplate.selectOne("selectOneAppointment",orderInfo);
	}
}
