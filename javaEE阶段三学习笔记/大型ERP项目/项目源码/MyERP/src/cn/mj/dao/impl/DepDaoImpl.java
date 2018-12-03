package cn.mj.dao.impl;

import org.apache.commons.lang3.StringUtils;

import cn.mj.dao.DepDao;
import cn.mj.model.Dep;
import cn.mj.query.DepQuery;

public class DepDaoImpl extends BaseDaoImpl<Dep, DepQuery> implements DepDao {



	@Override
	public String creatHql(DepQuery q) {
		 String hql=" from Dep d where 1=1";
		 String str = creatHqlStat(q);
		return hql+str;
	}

	@Override
	public String creatHqlStat(DepQuery q) {
		String hql="";
		if(StringUtils.isNotBlank(q.getName())){
			hql=hql+" and d.name like :name";
		}
		if(StringUtils.isNotBlank(q.getTel())){
			hql=hql+" and d.tel like :tel";
		}
		
		return hql;
	}

	@Override
	public String creatHqlCount(DepQuery q) {
		String hql="select count(d.depId) from Dep d where 1=1";
		String str = creatHqlStat(q);
		return hql+str;
	}

}
