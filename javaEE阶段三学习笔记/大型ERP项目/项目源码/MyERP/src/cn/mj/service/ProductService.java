package cn.mj.service;

import cn.mj.model.Product;
import cn.mj.query.ProductQuery;

public interface ProductService extends BaseService<Product, ProductQuery>{
	

	public void updateProduct(Product product);
	
}
