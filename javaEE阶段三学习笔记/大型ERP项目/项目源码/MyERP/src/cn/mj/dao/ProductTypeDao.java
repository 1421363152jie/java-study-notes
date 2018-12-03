package cn.mj.dao;

import cn.mj.model.ProductType;
import cn.mj.query.ProductTypeQuery;

public interface ProductTypeDao extends BaseDao<ProductType, ProductTypeQuery> {

  public ProductType getSupplierIdAndPname(ProductType productType);

}
