package cn.mj.service;

import java.util.List;

import cn.mj.utils.Page;

public interface BaseService<T,Q> {
   

	/*
	 * 添加
	 */
	public void sava(T t);
	
	
	/*
	 * 修改
	 */
	public void update(T t);
	
	
	/*
	 *根据id删除对象
	 */
	public void delete(Integer id);
	
	
	/*
	 * 直接删除对象
	 */
	public void delete(T t);
	
	
	/*
	 * 根据id获得对象
	 */
	public T  getObj(Integer id);
	
	
	/*
	 * 分页查询
	 */
	public Page queryObjBycondition(Q q,List<String> exclude);
	
	
	/*
	 * 获得总记录数
	 */
	public long queryObjByconditionConut(Q q,List<String> exclude);
	
	
	/*
	 *查询全部 
	 */
	
	public List<T> listObj();
	
	
	public List<T> queryObjByconditionNoPage(Q q,List<String> exclude);
}
