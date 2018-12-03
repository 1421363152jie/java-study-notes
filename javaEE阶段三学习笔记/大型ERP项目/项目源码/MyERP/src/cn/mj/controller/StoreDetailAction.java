package cn.mj.controller;

import cn.mj.model.StoreDetail;
import cn.mj.query.StoreDetailQuery;
import cn.mj.service.StoreDetailService;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class StoreDetailAction extends BaseAction{



	private StoreDetailService storeDetailService;
	
	
     private StoreDetailQuery query=new  StoreDetailQuery();
   
       private StoreDetail storeDetail=new StoreDetail();
  


	public StoreDetailService getStoreDetailService() {
		return storeDetailService;
	}


	public void setStoreDetailService(StoreDetailService storeDetailService) {
		this.storeDetailService = storeDetailService;
	}


	public StoreDetailQuery getQuery() {
		return query;
	}


	public void setQuery(StoreDetailQuery query) {
		this.query = query;
	}


	public StoreDetail getStoreDetail() {
		return storeDetail;
	}


	public void setStoreDetail(StoreDetail storeDetail) {
		this.storeDetail = storeDetail;
	}


	public String storeDetail_list(){
		ActionContext context = ActionContext.getContext();
         Integer pageNo = query.getPageNo();
	     if(pageNo==null){
	    	 query.setPageNo(1);
	     }
         
         
		Page page = storeDetailService.queryObjBycondition(query, exclude);
		context.put("page", page);
		
		return SUCCESS;
		
	}

	
}
