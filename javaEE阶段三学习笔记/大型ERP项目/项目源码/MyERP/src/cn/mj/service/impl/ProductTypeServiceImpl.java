package cn.mj.service.impl;

import cn.mj.dao.ProductTypeDao;
import cn.mj.model.ProductType;
import cn.mj.query.ProductTypeQuery;
import cn.mj.service.ProductTypeService;

public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, ProductTypeQuery> implements ProductTypeService {
	private ProductTypeDao productTypeDao;

	public void setProductTypeDao(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
		this.baseDao=productTypeDao;
	}

	@Override
	public ProductType getSupplierIdAndPname(ProductType productType) {
		
		return productTypeDao.getSupplierIdAndPname(productType);
	}
	
	
	

}
