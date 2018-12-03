package cn.mj.service.impl;

import cn.mj.dao.StoreDetailDao;
import cn.mj.model.StoreDetail;
import cn.mj.query.StoreDetailQuery;
import cn.mj.service.StoreDetailService;

public class StoreDetailServiceImpl extends BaseServiceImpl<StoreDetail, StoreDetailQuery> implements StoreDetailService {
	private StoreDetailDao storeDetailDao;

	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
		this.baseDao=storeDetailDao;
	}
	
	
	

}
