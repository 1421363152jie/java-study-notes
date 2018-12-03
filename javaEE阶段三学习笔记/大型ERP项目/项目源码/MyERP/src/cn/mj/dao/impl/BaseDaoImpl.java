package cn.mj.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.mj.dao.BaseDao;

public abstract class BaseDaoImpl<T,Q> extends HibernateDaoSupport implements BaseDao<T, Q>{

	/**
	 * 添加
	 */
	@Override
	public void sava(T t) {
	  this.getHibernateTemplate().save(t);
		
	}

	/**
	 * 修该
	 */
	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}
/**
 * 根据id删除
 */
	@Override
	public void delete(Integer id) {
		T obj = getObj(id);
		this.getHibernateTemplate().delete(obj);
	}

	
	/**
	 * 根据对象删除
	 */
	@Override
	public void delete(T t) {
	this.getHibernateTemplate().delete(t);
		
	}

	/**
	 * 根据id获得
	 */
	@Override
	public T getObj(Integer id) {
	   Class<?> class1 = getGenerricClass();
		return (T) this.getHibernateTemplate().get(class1, id);
	}

	/**
	 * 分页查询
	 */
	@Override
	public List<T> queryObjBycondition(final Q q, final List<String> exclude) {
	   @SuppressWarnings("unchecked")
	List<T>  plist = this.getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {

		@Override
		public List<T> doInHibernate(Session session) throws HibernateException,
				SQLException {
			//获得的hql语句
			String hql=creatHql(q);
			Query query = session.createQuery(hql);
			//获得分页的开始行号属性
			Class<? extends Object> class1 = q.getClass();
			Object  startNum=null;	
			try {
				//获得开始行号属性
				Field startNumfield = class1.getDeclaredField("startNum");
				//破坏属性权限
				startNumfield.setAccessible(true);
			   //获得属性值
			 startNum = startNumfield.get(q);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//动态设置参数
			setDynamicPre(query,q,exclude);
			List<T> list = query.setFirstResult((Integer)startNum).setMaxResults(5).list();
			
			
			return list;
			
		}
	});
return plist;
	}
	
	/**
	 * 不分页的查询
	 */
	public List<T> queryObjByconditionNoPage(final Q q,
			final List<String> exclude) {
		@SuppressWarnings("unchecked")
		List<T> plist = this.getHibernateTemplate().executeFind(
				new HibernateCallback<List<T>>() {

					@Override
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 获得的hql语句
						String hql = creatHql(q);
						Query query = session.createQuery(hql);
						// 获得分页的开始行号属性
						// 动态设置参数
						setDynamicPre(query, q, exclude);
						List<T> list = query.list();
						return list;

					}
				});
		return plist;
	}
	
	/**
	 * 动态设置参数
	 */
	public void setDynamicPre( Query query,Q q, List<String> exclude){
		//反射获的查询对象
	    Class<? extends Object> class1 = q.getClass();
	    //获得查询对象的父类
		Class<?> superclass = class1.getSuperclass();
	    //获得属性
		Field[] fields1 = class1.getDeclaredFields();
		//获得父类的参数
		 Field[] fields2 = superclass.getDeclaredFields();
		//将属性数组转换为集合
		 List<Field> flist1 = Arrays.asList(fields1);
		 List<Field> flist2 = Arrays.asList(fields2);
		 //创建大集合
		 List<Field> list=new ArrayList<Field>();
		list.addAll(flist1);
		list.addAll(flist2);
		//遍历集合
		for (Field field : list) {
			//获得属性的名字
			String fname = field.getName();
			//排除不用赋值的属性
			if(exclude!=null&&exclude.contains(fname)){
				//跳出本次循环
				continue;
			}
			Object val =null;
	    	//获得属性的值
			try {
				//破坏属性
				field.setAccessible(true);
				val= field.get(q);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			//判断属性值不为空
			if(val!=null){
				  //判断类型是否是字符串类型
				 if(val.getClass()==String.class){
					 //判断不为空字符串
					 if(StringUtils.isNotBlank(val.toString())){
						 //给query设置参数
						 query.setParameter(fname, "%"+val+"%");
					 }
					 
					 
				 }else{
					 query.setParameter(fname, val);
				 }
			}
		}		
		
	}
	
/**
 * 获得总记录数
 */
	@Override
	public long queryObjByconditionConut(final Q q, final List<String> exclude) {
		Long  totalNum = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {

			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				//获得的hql语句
				String hql=creatHqlCount(q);
				Query query = session.createQuery(hql);
				//获得分页的开始行号属性
				Class<? extends Object> class1 = q.getClass();
				Object  startNum=null;	
				try {
					//获得开始行号属性
					Field startNumfield = class1.getDeclaredField("startNum");
					//破坏属性权限
					startNumfield.setAccessible(true);
				   //获得属性值
				 startNum = startNumfield.get(q);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//动态设置参数
				setDynamicPre(query,q,exclude);
				 Long count = (Long) query.uniqueResult();
				
				return count;
				
			}
		});
	return totalNum;
		}
   
	/**
	 * 获得反射的泛型的类型
	 * @return
	 */
	public Class<?> getGenerricClass(){
		Type superclass = this.getClass().getGenericSuperclass();
		ParameterizedType p=(ParameterizedType)superclass;
		Type[] typeArguments = p.getActualTypeArguments();
		Class<?> clazz= (Class<?>) typeArguments[0];
		return clazz;		
	}
	
	
	
	/**
	 * 查询全部的
	 */
	public List<T> listObj(){
		//获得泛型的对象
		Class<?> class1 = getGenerricClass();
		//获得类名
		String name = class1.getName();
		//组装查询全部的hql语句
		String hql=" from "+name;
		List find = this.getHibernateTemplate().find(hql);
		return find;
	}
	
	
	
	/**
	 * 获得hql语句
	 * @return 
	 */
	public abstract String creatHql(Q q);
	
	
	/**
	 * 拼接hql语句
	 * 
	 */
	 public abstract String creatHqlStat(Q q);
	 
	 
	 /**
	  * 拼接查询总记录数的hql
	  */
	 public abstract String creatHqlCount(Q q);
	
	
	
	 
	
	
}
