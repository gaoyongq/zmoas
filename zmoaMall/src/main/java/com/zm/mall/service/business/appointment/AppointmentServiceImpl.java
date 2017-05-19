package com.zm.mall.service.business.appointment;

import com.zm.mall.client.service.business.appointment.AppointmentOrderService;
import com.zm.mall.dao.business.appointment.AppointmentDao;
import com.zm.mall.domain.business.orders.OrderInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/5/12.
 */
@Service("appointmentOrderService")
public class AppointmentServiceImpl implements AppointmentOrderService {
	@Resource
	private AppointmentDao appointmentDao;
	@Override
	public OrderInfo selectOneAppointment(OrderInfo orderInfo) {
		OrderInfo o=appointmentDao.selectOneAppointment(orderInfo);
		return o;
	}
}
