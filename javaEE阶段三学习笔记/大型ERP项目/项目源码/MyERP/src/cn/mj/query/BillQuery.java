package cn.mj.query;

import java.util.Date;

public class BillQuery {

	
	/**
	 * 订单类型
	 */
	private Integer  orderType;
	
	/**
	 * 厂商
	 */
	private Integer supplierId;
	
	
	/**
	 * 开始日期
	 */
	private Date startTime;
	
	
	/**
	 *结束日期
	 */
	private Date endTime;


	public Integer getOrderType() {
		return orderType;
	}


	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}


	public Integer getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
	
}
