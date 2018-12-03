package cn.mj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.mj.dao.OrderModelDao;
import cn.mj.model.BillBean;
import cn.mj.model.OrderModel;
import cn.mj.query.BillQuery;
import cn.mj.query.OrderModelQuery;

public class OrderModelDaoImpl extends BaseDaoImpl<OrderModel, OrderModelQuery> implements OrderModelDao {

	public String creatHql(OrderModelQuery q) {
		  String hql="from OrderModel o where 1=1";
		  String str = creatHqlStat(q);
			return hql+str;
	}

	@Override
	public String creatHqlStat(OrderModelQuery q) {
		String hql="";
		if(StringUtils.isNotBlank(q.getCreaterName())){
			hql=hql+" and o.orderCreater.name like:createrName";
		}
		if(q.getMinTotalNum()!=null){
			hql=hql+" and o.totalNum >=:minTotalNum";
		}
		if(q.getMaxTotalNum()!=null){
			hql=hql+" and o.totalNum <=:maxTotalNum";
		}
		if(q.getMinCreateTime()!=null){
			hql=hql+" and o.createTime >=:minCreateTime";
		}
		if(q.getMaxCreateTime()!=null){
			hql=hql+" and o.createTime <=:maxCreateTime";
		}
		if(q.getMinTotalPrice()!=null){
			hql=hql+" and o.totalPrice >=:minTotalPrice";
		}
		if(q.getMaxTotalPrice()!=null){
			hql=hql+" and o.totalPrice <=:maxTotalPrice";
		}
		if(q.getOrderType()!=null){
			hql=hql+" and o.orderType =:orderType";
		}
		if(q.getOrderState()!=null){
			hql=hql+" and o.orderState =:orderState";
		}
		if(q.getMinCheckTime()!=null){
			hql=hql+" and o.checkTime >=:minCheckTime";
		}
		if(q.getMaxCheckTime()!=null){
			hql=hql+" and o.checkTime <=:maxCheckTime";
		}
		if(StringUtils.isNotBlank(q.getCheckerName())){
			hql=hql+" and o.checkerOrder.name like:checkerName";
		}
		if(StringUtils.isNotBlank(q.getCompleterName())){
			hql=hql+" and o.orderCompleter.name like:completerName";
		}
		if(q.getSupplierId()!=null){
			hql=hql+" and o.supplier.supplierId =:supplierId";
		}
		if(q.getNeeds()!=null){
			hql=hql+" and o.supplier.needs =:needs";
		}
		if(q.getCompleter()!=null){
			hql=hql+" and o.orderCompleter.empId=:completer";
		}
		if(StringUtils.isNotBlank(q.getOrderNum())){
		   hql=hql+" and o.orderNum like:orderNum";
		}
		return hql;
	}

	@Override
	public String creatHqlCount(OrderModelQuery q) {
		String hql="select count(orderId) from OrderModel o where 1=1";
		String str = creatHqlStat(q);
		return hql+str;
	}

	@Override
	public List<BillBean> selectBillByCondition(final BillQuery query) {
	  String hql="select new cn.mj.model.BillBean(d.product.name,count(*)) from OrderModel o,OrderDetail d  where o.orderId=d.orderId";
	    if(query.getOrderType()!=null){
	    	hql=hql+" and o.orderType =:orderType";
	    }
	    if(query.getSupplierId()!=null){
	    	hql=hql+" and o.supplier.supplierId =:supplierId";
	    }
	    if(query.getStartTime()!=null){
	    	hql=hql+" and o.createTime >=:startTime";
	    }
	    if(query.getEndTime()!=null){
	    	hql=hql+" and o.createTime <=:endTime";
	    }
	  hql=hql+" group by d.product.productId,d.product.name ";
	  final String hql1=hql;
	  List list = this.getHibernateTemplate().executeFind(new HibernateCallback<List<BillBean>>() {

		@Override
		public List<BillBean> doInHibernate(Session session)
				throws HibernateException, SQLException {
		     Query createQuery = session.createQuery(hql1);
		     if(query.getOrderType()!=null){
		    	 createQuery.setParameter("orderType", query.getOrderType());
			    }
			    if(query.getSupplierId()!=null){
			  	 createQuery.setParameter("supplierId", query.getSupplierId());
			    }
			    if(query.getStartTime()!=null){
			     createQuery.setParameter("startTime", query.getStartTime());
			    }
			    if(query.getEndTime()!=null){
			    	 createQuery.setParameter("endTime", query.getEndTime());
			    } 
			   List blist = createQuery.list(); 
			 return blist;
		}
	});
           return list;
	}

}
