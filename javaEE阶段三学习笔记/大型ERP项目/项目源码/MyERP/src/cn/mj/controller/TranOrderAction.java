package cn.mj.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mj.model.Dep;
import cn.mj.model.Emp;
import cn.mj.model.OrderDetail;
import cn.mj.model.OrderModel;
import cn.mj.model.Supplier;
import cn.mj.query.OrderModelQuery;
import cn.mj.service.DepService;
import cn.mj.service.OrderModelService;
import cn.mj.service.ProductService;
import cn.mj.service.SupplierService;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class TranOrderAction extends BaseAction {

	private OrderModelService orderModelService;

	private OrderModelQuery query = new OrderModelQuery();

	private OrderModel order = new OrderModel();

	private SupplierService supplierService;
	
	private ProductService productService;
	
    private DepService depService;
    
    
    
	
	
	public DepService getDepService() {
		return depService;
	}

	public void setDepService(DepService depService) {
		this.depService = depService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public OrderModelService getOrderModelService() {
		return orderModelService;
	}

	public void setOrderModelService(OrderModelService orderModelService) {
		this.orderModelService = orderModelService;
	}

	public OrderModelQuery getQuery() {
		return query;
	}

	public void setQuery(OrderModelQuery query) {
		this.query = query;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	/**
	 * 订单页面的展示
	 * 
	 * @return
	 */
	public String tranOrder_taskList() {
		List<Supplier> slist = supplierService.listObj();
		ActionContext context = ActionContext.getContext();
		Integer pageNo = query.getPageNo();
		if (pageNo == null) {
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjBycondition(query, exclude);
		context.put("page", page);
		context.put("slist", slist);
		return SUCCESS;
	}
    /**
     * 任务指派页面展示
     */
    public String tranOrder_taskDetail(){
    	order = orderModelService.getObj(order.getOrderId());
		Set<OrderDetail> details = order.getDetails();
		ActionContext context = ActionContext.getContext();
		context.put("details", details);
		Dep dep = depService.getObj(2);		
		context.put("dep", dep);
		return SUCCESS;
    }
   /**
    * 任务指派
 * @throws IOException 
    */
  public void ajax_tranOrder_assignOrder() throws IOException{
	  orderModelService.updateAssignOrder(order);
	  response.getWriter().write("success");
  }
  /**
   * 任务查询展示
   */
  public String  tranOrder_tasks(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp= (Emp) session2.get("user");
		query.setCompleter(emp.getEmpId());
	   List<Supplier> slist = supplierService.listObj();
		Integer pageNo = query.getPageNo();
		if (pageNo == null) {
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjBycondition(query, exclude);
		context.put("page", page);
		context.put("slist", slist);
	   return SUCCESS;
  }
  /**
   * 采购中的订单详情展示
   * @return
   */
  
  public String tranOrder_taskDetailBuying(){
	   order = orderModelService.getObj(order.getOrderId());
		Set<OrderDetail> details = order.getDetails();
		ActionContext context = ActionContext.getContext();
		context.put("details", details);
		Dep dep = depService.getObj(2);		
		context.put("dep", dep);
		return SUCCESS;
  }
  /**
   * 确认去采购货物
 * @throws IOException 
   */
  public void  ajax_tranOrder_greanOrder() throws IOException{
	  OrderModel orderModel = orderModelService.getObj(order.getOrderId());
	  orderModel.setOrderState(2);
	  orderModelService.update(orderModel);
	  response.getWriter().write("success");
  }
  
  /**
   * 完成采购
   */

  public void   ajax_tranOrder_finishgreanOrder() throws IOException{
	  OrderModel orderModel = orderModelService.getObj(order.getOrderId());
	  orderModel.setOrderType(3);
	  orderModel.setOrderState(1);
	  orderModelService.update(orderModel);
	  response.getWriter().write("success");
  }
  
  /**
   * 入库订单信息的展示
   */
  
  
  public String tranOrder_inList(){
	  ActionContext context = ActionContext.getContext();
		Integer pageNo = query.getPageNo();
		if (pageNo == null) {
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjBycondition(query, exclude);
		context.put("page", page);
		return "store_success";
  }
  
  /**
   * 入库单明细
   */
  public String tranOrder_inDetail(){
	  order=orderModelService.getObj(order.getOrderId()); 
	return "store_inDetail";
	  
  }
  
  
  
}
