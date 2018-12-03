package cn.mj.service.impl;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import cn.mj.dao.MenuDao;
import cn.mj.dao.RoleDao;
import cn.mj.model.Menu;
import cn.mj.model.Role;
import cn.mj.query.RoleQuery;
import cn.mj.service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl<Role, RoleQuery> implements RoleService {
	private RoleDao roleDao;

	
	private MenuDao menuDao;
	
	
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		this.baseDao=roleDao;
	}

	@Override
	public void updateGrentRole(Integer roleId, String permIds) {
		// 根据id获得角色
		Role role = roleDao.getObj(roleId);
		// 获得角色下的菜单
		Set<Menu> menus = role.getMenus();
		// 将其清空
		menus.clear();
		// 遍历权限菜单字符串,判断不为空串
		if(StringUtils.isNotBlank(permIds)){
			String[] permMenu = permIds.split(",");
			for (String menuId : permMenu) {
				// 根据id获得菜单
				Menu menu = menuDao.getObj(new Integer(menuId));
				// 把菜单加入到角色
				menus.add(menu);
			}
		}
		

	}
	
	

}
