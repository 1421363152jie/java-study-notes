package cn.mj.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import cn.mj.model.Store;
import cn.mj.model.StoreDetail;
import cn.mj.query.StoreQuery;
import cn.mj.service.StoreService;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class StoreAction extends BaseAction{



	private StoreService storeService;
	
	
     private StoreQuery query=new  StoreQuery();
   
       private Store store=new Store();
  

       private Integer productNum;
       
       private Integer productId;
       
       private Integer orderDetailId;
       
       
       

	public Integer getProductNum() {
		return productNum;
	}


	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public Integer getOrderDetailId() {
		return orderDetailId;
	}


	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}


	public StoreService getStoreService() {
		return storeService;
	}


	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}


	public StoreQuery getQuery() {
		return query;
	}


	public void setQuery(StoreQuery query) {
		this.query = query;
	}


	public Store getStore() {
		return store;
	}


	public void setStore(Store store) {
		this.store = store;
	}

    /**
     * 仓库的信息展示
     * @return
     */
	public String store_list(){
		ActionContext context = ActionContext.getContext();
         Integer pageNo = query.getPageNo();
	     if(pageNo==null){
	    	 query.setPageNo(1);
	     }
		Page page = storeService.queryObjBycondition(query, exclude);
		context.put("page", page);
		
		return SUCCESS;
		
	}
	/**
	 * 仓库的明细
	 * @return
	 */
  public String store_storeDetail(){
 	ActionContext context = ActionContext.getContext();
 	 Store  st= storeService.getObj(store.getStoreId());
	  Set<StoreDetail> details = st.getsDetails();
		context.put("details", details);
	return SUCCESS;
	  
  }
  
  /**
   * 订单入库的页面展示
   */
  public String  store_inStock(){
	  ActionContext context = ActionContext.getContext();
	  List<Store> slist = storeService.listObj();
	  context.put("slist", slist);
		
	  
	  
	return SUCCESS;	  
  }
  
  /**
   * 订单入库
 * @throws IOException 
   */
  public void ajax_store_inStock() throws IOException{
	  storeService.updateInStock(query.getStoreId(), productNum, productId, orderDetailId);
	  response.getWriter().write("success");
	  
  }
  
  
  
  
  
  
  
  
  	
}
