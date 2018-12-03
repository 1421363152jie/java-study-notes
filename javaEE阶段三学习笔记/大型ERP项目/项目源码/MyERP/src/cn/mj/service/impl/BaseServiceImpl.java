package cn.mj.service.impl;

import java.lang.reflect.Field;
import java.util.List;

import cn.mj.dao.BaseDao;
import cn.mj.service.BaseService;
import cn.mj.utils.Page;

public class BaseServiceImpl<T, Q> implements BaseService<T, Q>{

	
	BaseDao<T, Q> baseDao;
	
	@Override
	public void sava(T t) {
		baseDao.sava(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public void delete(Integer id) {
		baseDao.delete(id);
		
	}

	@Override
	public void delete(T t) {
		baseDao.delete(t);
		
	}

	@Override
	public T getObj(Integer id) {
		
		return baseDao.getObj(id);
	}

	@Override
	public Page queryObjBycondition(Q q, List<String> exclude) {
		//创建分页对象
		Page page=new Page();
		//反射获得查询对象
		Class<? extends Object> class1 = q.getClass();
		try {
			Field pageNumfield = class1.getDeclaredField("pageNo");
			pageNumfield.setAccessible(true);
			//获得属性的值
			Integer pageNum= (Integer) pageNumfield.get(q);
			//将属性设置给分页对象
			page.setPageNo(pageNum);
			//获得开始行号
			int startNum = page.getStartNum();
			//获得查询对象的开始行号属性
			Field startNumfield = class1.getDeclaredField("startNum");
			startNumfield.setAccessible(true);
			//给查询对象设置开始对象
			startNumfield.set(q, startNum);
			//
			List<T> list = baseDao.queryObjBycondition(q, exclude);
			//把查询集合设置给分页对象
			page.setList(list);
			long conut = baseDao.queryObjByconditionConut(q, exclude);
			//给分页对象设置总记录数
			page.setTotalCount(new Integer(conut+""));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}

	@Override
	public long queryObjByconditionConut(Q q, List<String> exclude) {
		
		return baseDao.queryObjByconditionConut(q, exclude);
	}

	@Override
	public List<T> listObj() {
	
		return baseDao.listObj();
	}

	@Override
	public List<T> queryObjByconditionNoPage(Q q, List<String> exclude) {
	
		return baseDao.queryObjByconditionNoPage(q, exclude);
	}

}
