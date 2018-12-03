package cn.mj.service;

import cn.mj.model.Role;
import cn.mj.query.RoleQuery;

public interface RoleService extends BaseService<Role, RoleQuery>{
	

	public void updateGrentRole(Integer roleId,String permIds);
	
	
}
