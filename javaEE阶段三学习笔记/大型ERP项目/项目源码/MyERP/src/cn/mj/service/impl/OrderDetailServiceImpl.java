package cn.mj.service.impl;

import cn.mj.dao.OrderDetailDao;
import cn.mj.model.OrderDetail;
import cn.mj.query.OrderDetailQuery;
import cn.mj.service.OrderDetailService;

public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail, OrderDetailQuery> implements OrderDetailService {
	private OrderDetailDao orderDetailDao;

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
		this.baseDao=orderDetailDao;
	}
	
	
	

}
