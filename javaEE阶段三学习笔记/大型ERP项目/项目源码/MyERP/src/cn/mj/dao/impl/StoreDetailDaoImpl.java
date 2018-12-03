package cn.mj.dao.impl;

import org.apache.commons.lang3.StringUtils;

import cn.mj.dao.StoreDetailDao;
import cn.mj.model.StoreDetail;
import cn.mj.query.StoreDetailQuery;

public class StoreDetailDaoImpl extends BaseDaoImpl<StoreDetail, StoreDetailQuery> implements StoreDetailDao {

	public String creatHql(StoreDetailQuery q) {
		  String hql="from StoreDetail t where 1=1";

			return hql;
	}

	@Override
	public String creatHqlStat(StoreDetailQuery q) {
		
		return null;
	}

	@Override
	public String creatHqlCount(StoreDetailQuery q) {
		String hql="select count(demoId) from StoreDetail r where 1=1";
		String str = creatHqlStat(q);
		return null;
	}

}
