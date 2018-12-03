package cn.mj.dao;

import cn.mj.model.OrderDetail;
import cn.mj.query.OrderDetailQuery;

public interface OrderDetailDao extends BaseDao<OrderDetail, OrderDetailQuery> {

	
	public void deleteByorderId(Integer orderId);
}
