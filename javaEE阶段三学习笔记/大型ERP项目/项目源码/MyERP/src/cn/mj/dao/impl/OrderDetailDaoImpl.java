package cn.mj.dao.impl;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.mj.dao.OrderDetailDao;
import cn.mj.model.OrderDetail;
import cn.mj.query.OrderDetailQuery;

public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetail, OrderDetailQuery> implements OrderDetailDao {

	public String creatHql(OrderDetailQuery q) {
		  String hql="from OrderDetail t where 1=1";

			return hql;
	}

	@Override
	public String creatHqlStat(OrderDetailQuery q) {
		
		return null;
	}

	@Override
	public String creatHqlCount(OrderDetailQuery q) {
		String hql="select count(demoId) from OrderDetail r where 1=1";
		String str = creatHqlStat(q);
		return null;
	}

	@Override
	public void deleteByorderId(final Integer orderId) {
		 final String hql="delete from OrderDetail d where d.orderId =:orderId";
		 this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
			      Query query = session.createQuery(hql);
				 query.setParameter("orderId", orderId+"");
				query.executeUpdate();
				return null;
			}
		   
		 
		 });
		
	}

}
