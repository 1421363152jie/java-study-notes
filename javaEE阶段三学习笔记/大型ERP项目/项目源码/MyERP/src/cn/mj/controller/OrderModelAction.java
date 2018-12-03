package cn.mj.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mj.model.Emp;
import cn.mj.model.OrderDetail;
import cn.mj.model.OrderModel;
import cn.mj.model.Product;
import cn.mj.model.Supplier;
import cn.mj.query.OrderModelQuery;
import cn.mj.service.OrderModelService;
import cn.mj.service.ProductService;
import cn.mj.service.SupplierService;
import cn.mj.utils.ERPConstant;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class OrderModelAction extends BaseAction {

	private OrderModelService orderModelService;

	private OrderModelQuery query = new OrderModelQuery();

	private OrderModel order = new OrderModel();

	private SupplierService supplierService;
	
	private ProductService productService;
	

	private Integer[] productTypeIds;

	private Integer[] productIds;

	private Integer[] detailNum;

	private Double[] detailPries;

	private String note;
	
	
	
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer[] getProductTypeIds() {
		return productTypeIds;
	}

	public void setProductTypeIds(Integer[] productTypeIds) {
		this.productTypeIds = productTypeIds;
	}

	public Integer[] getProductIds() {
		return productIds;
	}

	public void setProductIds(Integer[] productIds) {
		this.productIds = productIds;
	}

	public Integer[] getDetailNum() {
		return detailNum;
	}

	public void setDetailNum(Integer[] detailNum) {
		this.detailNum = detailNum;
	}

	public Double[] getDetailPries() {
		return detailPries;
	}

	public void setDetailPries(Double[] detailPries) {
		this.detailPries = detailPries;
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
	public String orderModel_list() {
		ActionContext context = ActionContext.getContext();
		Integer pageNo = query.getPageNo();
		if (pageNo == null) {
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjBycondition(query, exclude);
		context.put("page", page);
		return SUCCESS;

	}

	/**
	 * 订单详情的页面展示
	 */
	public String orderModel_orderDetail() {
		order = orderModelService.getObj(order.getOrderId());
		Set<OrderDetail> details = order.getDetails();
		ActionContext context = ActionContext.getContext();
		context.put("details", details);
		return SUCCESS;

	}

	/**
	 * 订单添加页面的展示
	 */
	public String orderModel_input() {
		List<Supplier> slist = supplierService.listObj();
		ActionContext context = ActionContext.getContext();
		context.put("slist", slist);
		return SUCCESS;

	}
	public void ajax_orderModel_submitOrder() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp= (Emp) session2.get("user");
		Supplier supplier = supplierService.getObj(order.getSupplierId());
		//订单号
		order.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmssSSS")
		.format(new Date()));
		//订单创建人
		order.setOrderCreater(emp);
		//订单创建时间
		order.setCreateTime(new Date());
		//供应商
		order.setSupplier(supplier);
		//订单类型
		order.setOrderType(new Integer(ERPConstant.ORDER_PURCHASE));
		//订单状态
		order.setOrderState(new Integer(ERPConstant.ORDER_PURCHASE_AUDIT));
		//商品总量
		int totalNum=0;
		//商品总价
		double totalPrice=0;
		//创建订单明细集合
		Set<OrderDetail> ods=new HashSet<OrderDetail>();
		 for (int i = 0; i < productTypeIds.length; i++) {
			//创建订单明细
			 OrderDetail od=new OrderDetail();
			 //设置明细总数目
			 od.setDetailNum(detailNum[i]);
			 //设置明细单价
			 od.setDetailPrice(detailPries[i]);
			 //获得明显下的商品
			 Product product = productService.getObj(productIds[i]);
			//设置明细下的商品
			 od.setProduct(product);
			 //设置剩余数量
			 od.setSurplus(detailNum[i]);
			 //把明细加入到集合
			 ods.add(od);
			 totalNum=totalNum+detailNum[i];
			 totalPrice=totalPrice+detailPries[i]*detailNum[i];
			 
		}
		 //设置商品的总量
		order.setTotalNum(totalNum);
		//设置商品的总价
		order.setTotalPrice(totalPrice);
		//设置商品明细
		order.setDetails(ods);
		 //添加订单
		orderModelService.submitOrder(order);
		response.getWriter().write("success");
	}
	/**
	 * 订单的审核页面展示
	 * @return
	 */
	 public String orderModel_Checklist() {
		ActionContext context = ActionContext.getContext();
		Integer pageNo = query.getPageNo();
		if (pageNo == null) {
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjBycondition(query, exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
    /**
     * 订单审核日志的展示
     */
	 public String orderModel_auditText(){
		
		 
		 return SUCCESS;
	 }
	 
	 /**
	  * 订单的审核
	 * @throws IOException 
	  */
	 
	 public void ajax_orderModel_auditOrder() throws IOException{
		 ActionContext context = ActionContext.getContext();
		 Map<String, Object> session2 = context.getSession();
		 Emp emp= (Emp) session2.get("user");
		 orderModelService.updateAuditOrder(emp, order, note);
         response.getWriter().write("success");
	 }
	 /**
	  * 修改页面信息展示
	  */
	 
		public String orderModel_update() {
			List<Supplier> slist = supplierService.listObj();
			ActionContext context = ActionContext.getContext();
		   order = orderModelService.getObj(order.getOrderId());
			context.put("slist", slist);
			return SUCCESS;
		}
	 
	 /**
	  * 修改信息
	  */
		public void ajax_orderModel_update() throws IOException{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session2 = context.getSession();
			Emp emp= (Emp) session2.get("user");
			Supplier supplier = supplierService.getObj(order.getSupplier().getSupplierId());
			//订单号
			order.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmssSSS")
			.format(new Date()));
			//订单创建人
			order.setOrderCreater(emp);
			//订单创建时间
			order.setCreateTime(new Date());
			//供应商
			order.setSupplier(supplier);
			//订单类型
			order.setOrderType(new Integer(ERPConstant.ORDER_PURCHASE));
			//订单状态
			order.setOrderState(new Integer(ERPConstant.ORDER_PURCHASE_AUDIT));
			//商品总量
			int totalNum=0;
			//商品总价
			double totalPrice=0;
			//创建订单明细集合
			Set<OrderDetail> ods=new HashSet<OrderDetail>();
			 for (int i = 0; i < productTypeIds.length; i++) {
				//创建订单明细
				 OrderDetail od=new OrderDetail();
				 //设置明细总数目
				 od.setDetailNum(detailNum[i]);
				 //设置明细单价
				 od.setDetailPrice(detailPries[i]);
				 //获得明显下的商品
				 Product product = productService.getObj(productIds[i]);
				//设置明细下的商品
				 od.setProduct(product);
				 //设置剩余数量
				 od.setSurplus(detailNum[i]);
				 //把明细加入到集合
				 ods.add(od);
				 totalNum=totalNum+detailNum[i];
				 totalPrice=totalPrice+detailPries[i]*detailNum[i];
				 
			}
			 //设置商品的总量
				order.setTotalNum(totalNum);
				//设置商品的总价
				order.setTotalPrice(totalPrice);
				//设置商品明细
				order.setDetails(ods);
				 //添加订单
				orderModelService.updateOrder(order);
				response.getWriter().write("success");
	 
		}
}
