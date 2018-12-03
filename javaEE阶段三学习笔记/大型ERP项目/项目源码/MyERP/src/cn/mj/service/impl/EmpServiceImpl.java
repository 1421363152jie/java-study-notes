package cn.mj.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import cn.mj.dao.EmpDao;
import cn.mj.dao.RoleDao;
import cn.mj.model.Emp;
import cn.mj.model.Role;
import cn.mj.query.EmpQuery;
import cn.mj.service.EmpService;

public class EmpServiceImpl extends BaseServiceImpl<Emp, EmpQuery> implements EmpService {

	 private EmpDao empDao;
	 
	 private RoleDao roleDao;
	 
	 

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
		this.baseDao=empDao;
	}

	@Override
	public Emp getEmpByUname(String username) {
		 
		return empDao.getEmpByUname(username);
	}
/**
 * 修改用户数据
 */
	@Override
	public void updateEmp(Emp emp) {
		Emp emp2 = empDao.getObj(emp.getEmpId());
		emp.setPassword(emp2.getPassword());	
		try {
			BeanUtils.copyProperties(emp2, emp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		empDao.update(emp2);
		
	}

	@Override
	public Emp getEmpByUnameAndPword(String username, String password) {

		return empDao.getEmpByUnameAndPword(username, password);
	}

	@Override
	public List<Role> getEmpByRoles(Integer empId) {
		//获得所有角色
         List<Role> roles = roleDao.listObj();
		 //获得当前用户的角色
         Emp emp = empDao.getObj(empId);
         //获得当前用户下的所有角色
         Set<Role> roles2 = emp.getRoles();
         for (Role role : roles) {
			 for (Role r : roles2) {
				 //判断角色和用户的角色是否相等
				if(role.getRoleId().intValue()==r.getRoleId().intValue()){
					r.setSelect("yes");
				}
				 
				 
			}
        	 
        	 
		}

		return roles;
	}

	@Override
	public void updateEmpRoles(Integer empId, String roleIds) {
          //获得当前的用户
		Emp emp = empDao.getObj(empId);
		Set<Role> roles = emp.getRoles();
		//清空当前的角色
		roles.clear();
		//遍历角色id组
		String[] roleId = roleIds.split(",");
		for (String r : roleId) {
			//获得角色对象
			Role role = roleDao.getObj(new Integer(r));
			//将角色对象分配给角色
			roles.add(role);
		}
		
		
	}
	
	
	
}
