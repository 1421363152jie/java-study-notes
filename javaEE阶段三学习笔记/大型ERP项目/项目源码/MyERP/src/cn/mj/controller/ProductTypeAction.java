package cn.mj.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import cn.mj.model.Product;
import cn.mj.model.ProductType;
import cn.mj.model.Supplier;
import cn.mj.query.ProductTypeQuery;
import cn.mj.service.ProductTypeService;
import cn.mj.service.SupplierService;
import cn.mj.utils.JSONUtils;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class ProductTypeAction extends BaseAction{



	private ProductTypeService productTypeService;
	
	
	private SupplierService  supplierService;
	
	
     private ProductTypeQuery query=new  ProductTypeQuery();
   
       private ProductType productType=new ProductType();
  

       
       


	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}


	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}


	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}


	public ProductTypeQuery getQuery() {
		return query;
	}


	public void setQuery(ProductTypeQuery query) {
		this.query = query;
	}


	public ProductType getProductType() {
		return productType;
	}


	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

   /**
    * 商品类别的展示
    * @return
    */
	public String productType_list(){
		List<Supplier> slist = supplierService.listObj();
		ActionContext context = ActionContext.getContext();
         Integer pageNo = query.getPageNo();
	     if(pageNo==null){
	    	 query.setPageNo(1);
	     }      
		Page page = productTypeService.queryObjBycondition(query, exclude);
		context.put("page", page);
		context.put("slist", slist);
		return SUCCESS;
		
	}
	/**
	 * 添加商品类别页面的展示
	 * @return
	 */
	public String productType_input(){
		List<Supplier> slist = supplierService.listObj();
		ActionContext context = ActionContext.getContext();
		context.put("slist", slist);
		return SUCCESS;
	}
	/**
	 * 添加商品类别
	 * @throws IOException 
	 */
	public void ajax_productType_input() throws IOException{
		productTypeService.sava(productType);
		response.getWriter().write("success");
	}
	
	/**
	 * 修改商品页面展示
	 */
	public String productType_update(){
		List<Supplier> slist = supplierService.listObj();
		ActionContext context = ActionContext.getContext();
		context.put("slist", slist);
		productType=productTypeService.getObj(productType.getProductTypeId());
		return SUCCESS;
		
	}
	/**
	 * 修改商品类型
	 * @throws IOException 
	 */
	public void ajax_productType_update() throws IOException{
		productTypeService.update(productType);
		response.getWriter().write("success");
	}
	/**
	 * 删除
	 */
	public String productType_delete(){
		productTypeService.delete(productType.getProductTypeId());
		return LIST;
	}
	
	/**
	 * 检验商品类型是否重复
	 * @throws IOException 
	 */
	public void ajax_productType_validSupplierIdAndName() throws IOException{
		String result="yes";
		ProductType pt = productTypeService.getSupplierIdAndPname(productType);
		if(pt!=null){
			result="no";
		}
		response.getWriter().write(result);
		
	}
	
	/**
	 * 根据商品类型的id获得商品
	 */
	
	public void ajax_productType_getproduct() throws IOException{
		  ProductType pt = productTypeService.getObj(query.getProductTypeId());
		  Set<Product> products = pt.getProducts();
		  JSONUtils.printArray(response, products, new String[]{"productType"});
	}
	
	
	
	
	
	
	
}
