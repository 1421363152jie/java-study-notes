package cn.mj.controller;

import cn.mj.model.OrderDetail;
import cn.mj.query.OrderDetailQuery;
import cn.mj.service.OrderDetailService;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class OrderDetailAction extends BaseAction{



	private OrderDetailService orderDetailService;
	
	
     private OrderDetailQuery query=new  OrderDetailQuery();
   
       private OrderDetail orderDetail=new OrderDetail();
  


	public OrderDetailService getOrderDetailService() {
		return orderDetailService;
	}


	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}


	public OrderDetailQuery getQuery() {
		return query;
	}


	public void setQuery(OrderDetailQuery query) {
		this.query = query;
	}


	public OrderDetail getOrderDetail() {
		return orderDetail;
	}


	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}


	public String orderDetail_list(){
		ActionContext context = ActionContext.getContext();
         Integer pageNo = query.getPageNo();
	     if(pageNo==null){
	    	 query.setPageNo(1);
	     }
         
         
		Page page = orderDetailService.queryObjBycondition(query, exclude);
		context.put("page", page);
		
		return SUCCESS;
		
	}

	
}
