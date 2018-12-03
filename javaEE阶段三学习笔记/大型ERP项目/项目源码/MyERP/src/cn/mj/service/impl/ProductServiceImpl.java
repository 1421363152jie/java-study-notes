package cn.mj.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import cn.mj.dao.ProductDao;
import cn.mj.model.Product;
import cn.mj.query.ProductQuery;
import cn.mj.service.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product, ProductQuery> implements ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		this.baseDao=productDao;
	}

	
	/**
	 * 修改商品数据
	 */
	@Override
	public void updateProduct(Product product) {
		Product prod = productDao.getObj(product.getProductId());
		try {
			BeanUtils.copyProperties(prod, product);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		productDao.update(prod);
		
	}
	
	
	

}
