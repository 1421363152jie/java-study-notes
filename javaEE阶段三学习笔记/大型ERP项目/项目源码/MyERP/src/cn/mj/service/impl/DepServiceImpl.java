package cn.mj.service.impl;

import cn.mj.dao.DepDao;
import cn.mj.model.Dep;
import cn.mj.query.DepQuery;
import cn.mj.service.DepService;

public class DepServiceImpl extends BaseServiceImpl<Dep, DepQuery> implements DepService {
	private DepDao depDao;

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
		this.baseDao=depDao;
	}
	
	
	

}
