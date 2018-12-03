package cn.mj.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import cn.mj.dao.ConsoleLogDao;
import cn.mj.dao.OrderDetailDao;
import cn.mj.dao.OrderModelDao;
import cn.mj.model.BillBean;
import cn.mj.model.ConsoleLog;
import cn.mj.model.Emp;
import cn.mj.model.OrderDetail;
import cn.mj.model.OrderModel;
import cn.mj.query.BillQuery;
import cn.mj.query.OrderModelQuery;
import cn.mj.service.OrderModelService;
import cn.mj.utils.ERPConstant;

public class OrderModelServiceImpl extends BaseServiceImpl<OrderModel, OrderModelQuery> implements OrderModelService {
	private OrderModelDao orderModelDao;
	
	private ConsoleLogDao logDao;
	
	
	private OrderDetailDao orderDetailDao;
	
	

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void setLogDao(ConsoleLogDao logDao) {
		this.logDao = logDao;
	}

	public void setOrderModelDao(OrderModelDao orderModelDao) {
		this.orderModelDao = orderModelDao;
		this.baseDao=orderModelDao;
	}

	@Override
	public void updateAuditOrder(Emp emp, OrderModel order, String note) {
		//根据id或的审核的订单
		OrderModel orderModel = orderModelDao.getObj(order.getOrderId());
		//设置订单审核人
		orderModel.setOrderCreater(emp);
		//设置订单审核时间
		orderModel.setCheckTime(new Date());
		//设置订单审核状态
		orderModel.setOrderState(order.getOrderState());
		//创建日志对象
		ConsoleLog col=new ConsoleLog();
		//实体的id
		col.setEntityId(order.getOrderId());
		//操作的表名
		col.setTableName("order_model");
		//操作类型
		col.setOptType("订单审核");
		//时间
		col.setOptTime(new Timestamp(new Date().getTime()));
		//操作人员
		col.setEmp(emp);
		//备注
		col.setNote(note);
		//提交日志
		logDao.sava(col);
		
	}

	
	/**
	 * 运输任务指派
	 */
	@Override
	public void updateAssignOrder(OrderModel orderModel) {
		//根据id获得指派的订单
		OrderModel order = orderModelDao.getObj(orderModel.getOrderId());
		//设置订单负责人
		order.setOrderCompleter(orderModel.getOrderCompleter());
		//设置订单类型
		order.setOrderType(new Integer(ERPConstant.ORDER_CARRIAGE));
		//设置订单类型
		order.setOrderState(new Integer(ERPConstant.ORDER_CARRIAGE_WAIT_PURCHASE));
		
	}

	@Override
	public void submitOrder(OrderModel order) {
		orderModelDao.sava(order);
		
	}

	@Override
	public void updateOrder(OrderModel orderModel) {
	    //查询原有的订单将其删除
		orderDetailDao.deleteByorderId(orderModel.getOrderId());
		//加入新的订单
		orderModelDao.update(orderModel);
	}

	@Override
	public List<BillBean> selectBillByCondition(BillQuery query) {
	
		return orderModelDao.selectBillByCondition(query);
	}
	
	
	

}
