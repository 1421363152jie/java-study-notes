package cn.mj.dao.impl;

import org.apache.commons.lang3.StringUtils;

import cn.mj.dao.RoleDao;
import cn.mj.model.Role;
import cn.mj.query.RoleQuery;

public class RoleDaoImpl extends BaseDaoImpl<Role, RoleQuery> implements RoleDao {

	@Override
	public String creatHql(RoleQuery q) {
		  String hql="from Role r where 1=1";
		  String str = creatHqlStat(q);
			return hql+str;
	}

	@Override
	public String creatHqlStat(RoleQuery q) {
		String hql="";
		if(StringUtils.isNotBlank(q.getName())){
			hql=hql+" and r.name like:name";
		}
		if(StringUtils.isNotBlank(q.getCode())){
			hql=hql+" and r.code like:code";
		}
		return hql;
	}

	@Override
	public String creatHqlCount(RoleQuery q) {
		String hql="select count(r.roleId) from Role r where 1=1";
		String str = creatHqlStat(q);
		return hql+str;
	}

}
