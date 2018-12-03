package cn.mj.dao.impl;

import org.apache.commons.lang3.StringUtils;

import cn.mj.dao.SupplierDao;
import cn.mj.model.Supplier;
import cn.mj.query.SupplierQuery;

public class SupplierDaoImpl extends BaseDaoImpl<Supplier, SupplierQuery> implements SupplierDao {

	public String creatHql(SupplierQuery q) {
		  String hql="from Supplier s where 1=1";
		  String str = creatHqlStat(q);
			return hql+str;
	}

	@Override
	public String creatHqlStat(SupplierQuery q) {
		String hql="";
		if(StringUtils.isNotBlank(q.getName())){
			hql=hql+" and s.name like:name";
		}
		if(StringUtils.isNotBlank(q.getContact())){
			hql=hql+" and s.contact like:contact";
		}
		if(StringUtils.isNotBlank(q.getTel())){
			hql=hql+" and s.tel like:tel";
		}
		if(q.getNeeds()!=null){
			hql=hql+" and s.needs =:needs";
		}
		return hql;
	}

	@Override
	public String creatHqlCount(SupplierQuery q) {
		String hql="select count(supplierId) from Supplier s where 1=1";
		String str = creatHqlStat(q);
		return hql+str;
	}

}
