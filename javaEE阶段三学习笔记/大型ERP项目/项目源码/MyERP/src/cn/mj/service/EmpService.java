package cn.mj.service;

import java.util.List;

import cn.mj.model.Emp;
import cn.mj.model.Role;
import cn.mj.query.EmpQuery;

public interface EmpService extends BaseService<Emp, EmpQuery>{

	/**
	 * 检验用户名是否重复
	 * @param username
	 * @return
	 */
	public Emp getEmpByUname(String username);

	/**
	 * 修改用户
	 */
	public void updateEmp(Emp emp);

	/**
	 * 检验用户名和用户密码是否正确
	 * @param username
	 * @param password
	 * @return
	 */
	public Emp getEmpByUnameAndPword(String username, String password);
	
	
	/**
	 * 查询用户角色
	 */
    public List<Role> getEmpByRoles(Integer empId);
 
    /**
     * 分配角色
     */
    public void updateEmpRoles(Integer empId,String roleIds);
    

}