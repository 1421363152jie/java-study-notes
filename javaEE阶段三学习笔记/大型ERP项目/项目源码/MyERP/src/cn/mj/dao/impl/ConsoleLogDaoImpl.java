package cn.mj.dao.impl;

import cn.mj.dao.ConsoleLogDao;
import cn.mj.model.ConsoleLog;
import cn.mj.query.ConsoleLogQuery;

public class ConsoleLogDaoImpl extends BaseDaoImpl<ConsoleLog, ConsoleLogQuery> implements ConsoleLogDao {

	public String creatHql(ConsoleLogQuery q) {
		  String hql="from ConsoleLog t where 1=1";
		  String str = creatHqlStat(q);
			return hql+str;
	}

	@Override
	public String creatHqlStat(ConsoleLogQuery q) {
		String hql=" and t.entityId=:entityId and t.tableName like:tableName and t.optType like:optType";
		return hql;
	}

	@Override
	public String creatHqlCount(ConsoleLogQuery q) {
		
		return null;
	}

}
