package cn.mj.service;

import cn.mj.model.ProductType;
import cn.mj.query.ProductTypeQuery;

public interface ProductTypeService extends BaseService<ProductType, ProductTypeQuery>{
	
	
	  public ProductType getSupplierIdAndPname(ProductType productType);

}
