package cn.mj.service.impl;

import cn.mj.dao.MenuDao;
import cn.mj.model.Menu;
import cn.mj.query.MenuQuery;
import cn.mj.service.MenuService;

public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuQuery> implements MenuService {
	private MenuDao menuDao;

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
		this.baseDao=menuDao;
	}
	
	
	

}
