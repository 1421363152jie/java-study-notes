package cn.mj.dao.impl;

import org.apache.commons.lang3.StringUtils;

import cn.mj.dao.StoreDao;
import cn.mj.model.Store;
import cn.mj.query.StoreQuery;

public class StoreDaoImpl extends BaseDaoImpl<Store, StoreQuery> implements StoreDao {

	public String creatHql(StoreQuery q) {
		  String hql="from Store s where 1=1";
		  String str = creatHqlStat(q);
			return hql+str;
	}

	@Override
	public String creatHqlStat(StoreQuery q) {
	   String hql="";
	   if(StringUtils.isNotBlank(q.getName())){
		   hql=hql+" and s.name like :name";
	   }
	   if(StringUtils.isNotBlank(q.getAdminName())){
		   hql=hql+" and s.stockAdmin.name like:AdminName";
	   }
		return hql;
	}

	
	@Override
	public String creatHqlCount(StoreQuery q) {
		String hql="select count(storeId) from Store s where 1=1";
		String str = creatHqlStat(q);
		return hql+str;
	}

}
