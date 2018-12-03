package cn.mj.dao.impl;

import org.apache.commons.lang3.StringUtils;

import cn.mj.dao.MenuDao;
import cn.mj.model.Menu;
import cn.mj.query.MenuQuery;

public class MenuDaoImpl extends BaseDaoImpl<Menu, MenuQuery> implements MenuDao {

	public String creatHql(MenuQuery q) {
		  String hql="from Menu t where 1=1";

			return hql;
	}

	@Override
	public String creatHqlStat(MenuQuery q) {
		
		return null;
	}

	@Override
	public String creatHqlCount(MenuQuery q) {
		String hql="select count(demoId) from Menu r where 1=1";
		String str = creatHqlStat(q);
		return null;
	}

}
