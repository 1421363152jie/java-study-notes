package cn.mj.service;

import java.util.List;

import cn.mj.model.BillBean;
import cn.mj.model.Emp;
import cn.mj.model.OrderModel;
import cn.mj.query.BillQuery;
import cn.mj.query.OrderModelQuery;

public interface OrderModelService extends BaseService<OrderModel, OrderModelQuery>{
	
	public void submitOrder(OrderModel order);

	public void updateAuditOrder(Emp emp,OrderModel order,String note);
	
	
	public void updateAssignOrder(OrderModel orderModel);
	
	
	public void updateOrder(OrderModel orderModel);
	
	
	public List<BillBean> selectBillByCondition(BillQuery query);	
}
