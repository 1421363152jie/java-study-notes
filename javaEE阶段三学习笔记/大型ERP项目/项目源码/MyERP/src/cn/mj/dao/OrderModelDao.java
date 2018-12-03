package cn.mj.dao;

import java.util.List;

import cn.mj.model.BillBean;
import cn.mj.model.OrderModel;
import cn.mj.query.BillQuery;
import cn.mj.query.OrderModelQuery;

public interface OrderModelDao extends BaseDao<OrderModel, OrderModelQuery> {

	
	public List<BillBean> selectBillByCondition(BillQuery query);	
	
}
