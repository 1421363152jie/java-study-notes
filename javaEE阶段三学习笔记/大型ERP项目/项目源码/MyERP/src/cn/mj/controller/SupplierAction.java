package cn.mj.controller;

import java.io.IOException;
import java.util.Set;

import cn.mj.model.ProductType;
import cn.mj.model.Supplier;
import cn.mj.query.SupplierQuery;
import cn.mj.service.SupplierService;
import cn.mj.utils.JSONUtils;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class SupplierAction extends BaseAction{



	private SupplierService supplierService;
	
	
     private SupplierQuery query=new  SupplierQuery();
   
       private Supplier supplier=new Supplier();
  


	public SupplierService getSupplierService() {
		return supplierService;
	}


	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}


	public SupplierQuery getQuery() {
		return query;
	}


	public void setQuery(SupplierQuery query) {
		this.query = query;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

/**
 * 供应商页面的展示
 * @return
 */
	public String supplier_list(){
		ActionContext context = ActionContext.getContext();
         Integer pageNo = query.getPageNo();
	     if(pageNo==null){
	    	 query.setPageNo(1);
	     }
         
         
		Page page = supplierService.queryObjBycondition(query, exclude);
		context.put("page", page);
		
		return SUCCESS;
		
	}
	
	/**
	 * 供应商添加页面展示
	 */

	public String supplier_input(){
		
		return SUCCESS;
		
	}
	/**
	 * 添加供应商
	 * @throws IOException 
	 */
	public void ajax_supplier_input() throws IOException{
		
		supplierService.sava(supplier);
		
		response.getWriter().write("success");
	}
	/**
	 * 修改供应商信息页面展示
	 * @return
	 */
	
     public String supplier_update(){
    	 supplier=supplierService.getObj(supplier.getSupplierId());
		return SUCCESS;
		
	}
   /**
    * 修改供应商数据
 * @throws IOException 
    *   
    */
     public void ajax_supplier_update() throws IOException{
    	 supplierService.update(supplier);
    	 response.getWriter().write("success");
    	 
     }
     /**
      * 删除
      * @return
      */
     public String supplier_delete(){
    	 supplierService.delete(supplier.getSupplierId());
		return LIST;
     }
     
     /**
      * 获得供应商下的商品类型
      */
     public void ajax_supplier_getSupplier(){
    	 //根据id获得供应商
    	 Supplier supplier2 = supplierService.getObj(query.getSupplierId());
    	 //获得供应商下的商品类型
    	 Set<ProductType> productTypes = supplier2.getProductTypes();
    	 JSONUtils.printArray(response, productTypes, new String[]{"supplier","products"});
         
     }
	
}
