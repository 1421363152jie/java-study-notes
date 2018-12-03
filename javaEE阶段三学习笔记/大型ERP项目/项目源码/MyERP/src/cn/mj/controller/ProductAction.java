package cn.mj.controller;

import java.io.IOException;
import java.util.List;

import cn.mj.model.Product;
import cn.mj.model.ProductType;
import cn.mj.model.Supplier;
import cn.mj.query.ProductQuery;
import cn.mj.service.ProductService;
import cn.mj.service.ProductTypeService;
import cn.mj.service.SupplierService;
import cn.mj.utils.JSONUtils;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class ProductAction extends BaseAction{



	private ProductService productService;
	
	
	private SupplierService supplierService;
	
	
	private ProductTypeService productTypeService;
	
	
	
	
     public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}


	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}


	private ProductQuery query=new  ProductQuery();
   
       private Product product=new Product();
  


	public ProductService getProductService() {
		return productService;
	}


	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public ProductQuery getQuery() {
		return query;
	}


	public void setQuery(ProductQuery query) {
		this.query = query;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}

/**
 * 商品的页面展示
 * @return
 */
	public String product_list(){
		List<Supplier> slist = supplierService.listObj();
		ActionContext context = ActionContext.getContext();
         Integer pageNo = query.getPageNo();
	     if(pageNo==null){
	    	 query.setPageNo(1);
	     }
		Page page = productService.queryObjBycondition(query, exclude);
		context.put("page", page);
		context.put("slist", slist);
		
		return SUCCESS;
		
	}
/**
 * 添加页面的展示
 */
	public String product_input() {
		ActionContext context = ActionContext.getContext();
		List<Supplier> slist = supplierService.listObj();
		context.put("slist", slist);
		return SUCCESS;

	}
   /**
    * 添加	
 * @throws IOException 
    */
	public void ajax_product_input() throws IOException{
		productService.sava(product);
		response.getWriter().write("success");
	}
	/**
	 * 修改页面信息展示
	 */
	public String product_update(){
		ActionContext context = ActionContext.getContext();
		List<Supplier> slist = supplierService.listObj();
		context.put("slist", slist);
		product=productService.getObj(product.getProductId());
		return SUCCESS;
		
	}
	/**
	 * 修改商品
	 * @throws IOException 
	 */
	public void ajax_product_update() throws IOException{
		productService.updateProduct(product);
		response.getWriter().write("success");
	}
	
	//删除
	public String product_delete(){
		productService.delete(product.getProductId());
		return LIST;
	}
	
	/**
	 * 获得商品的具体明细
	 */
	public void ajax_product_getproductDetail(){
		Product prod = productService.getObj(query.getProductId());
		JSONUtils.printObject(response, prod, new String[]{"productType"});
	}
	
	
}
