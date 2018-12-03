package cn.mj.dao;

import cn.mj.model.Emp;
import cn.mj.query.EmpQuery;


public interface EmpDao extends BaseDao<Emp, EmpQuery>{

	/**
	 * 验证用户名是否重复
	 * @param username
	 * @return
	 */
	public Emp getEmpByUname(String username);
	
	/**
	 * 验证用户和密码是否存在
	 * @param username
	 * @param password
	 * @return
	 */
	public Emp getEmpByUnameAndPword(String username,String password);
	
}


