package cn.mj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.mj.dao.EmpDao;
import cn.mj.model.Emp;
import cn.mj.query.EmpQuery;

public class EmpDaoImpl extends BaseDaoImpl<Emp, EmpQuery> implements EmpDao{

	@Override
	public String creatHql(EmpQuery q) {
	   String hql=" from Emp e where 1=1";
	   String str = creatHqlStat(q);
		return hql+str;
	}

	@Override
	public String creatHqlStat(EmpQuery q) {
		String hql="";
		if(StringUtils.isNotBlank(q.getUsername())){
			hql=hql+" and e.username like:username";
		}
		if(StringUtils.isNotBlank(q.getName())){
			hql=hql+" and e.name like:name";
		}
		if(StringUtils.isNotBlank(q.getTel())){
			hql=hql+" and e.tel like:tel";
		}
		if(q.getGender()!=null){
			hql=hql+" and e.gender =:gender";
		}
		if(StringUtils.isNotBlank(q.getEmail())){
			hql=hql+" and e.email like:email";
		}
		if(q.getStartBirthday()!=null){
			hql=hql+" and e.birthday >=:startBirthday";
		}
		if(q.getEndBirthday()!=null){
			hql=hql+" and e.birthday <=:endBirthday";
		}
		if(q.getDepId()!=null){
			hql=hql+" and e.dep.depId =:depId";
		}
		
		
		return hql;
	}

	@Override
	public String creatHqlCount(EmpQuery q) {
		String hql="select count(t.empId) from Emp t where 1=1";
		String str = creatHqlStat(q);
		return hql+str;
	}
/**
 * 验证用户名是否重复
 * 
 */
	@Override
	public Emp getEmpByUname(String username) {
		Emp emp=null;
		String hql=" from Emp e where e.username=?";
		List list = this.getHibernateTemplate().find(hql, username);
		if(list.size()>0){
		  emp=(Emp)list.get(0);		
		}
		return emp;
	}

	
	/**
	 * 验证登陆用户
	 */
	@Override
	public Emp getEmpByUnameAndPword(final String username, final String password) {
		final String hql=" from Emp e where e.username=:username and e.password=:password";
		Emp emp = this.getHibernateTemplate().execute(new HibernateCallback<Emp>() {

			@Override
			public Emp doInHibernate(Session session) throws HibernateException,
					SQLException {
			 Query query = session.createQuery(hql);
			 query.setParameter("username",username);
			 query.setParameter("password", password);
				return (Emp) query.uniqueResult();
			}
		});
		return emp;
	}

}
