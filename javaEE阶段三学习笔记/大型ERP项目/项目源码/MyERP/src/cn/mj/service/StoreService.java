package cn.mj.service;

import cn.mj.model.Store;
import cn.mj.query.StoreQuery;

public interface StoreService extends BaseService<Store, StoreQuery>{
	
  public void updateInStock(Integer storeId,Integer productNum,Integer productId,Integer orderDetailId);
	
}
