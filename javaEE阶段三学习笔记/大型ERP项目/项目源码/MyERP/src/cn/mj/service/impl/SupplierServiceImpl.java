package cn.mj.service.impl;

import cn.mj.dao.SupplierDao;
import cn.mj.model.Supplier;
import cn.mj.query.SupplierQuery;
import cn.mj.service.SupplierService;

public class SupplierServiceImpl extends BaseServiceImpl<Supplier, SupplierQuery> implements SupplierService {
	private SupplierDao supplierDao;

	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
		this.baseDao=supplierDao;
	}
	
	
	

}
