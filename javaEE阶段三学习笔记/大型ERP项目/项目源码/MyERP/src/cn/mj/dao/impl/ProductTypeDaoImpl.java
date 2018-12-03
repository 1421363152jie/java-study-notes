package cn.mj.dao.impl;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.mj.dao.ProductTypeDao;
import cn.mj.model.ProductType;
import cn.mj.query.ProductTypeQuery;

public class ProductTypeDaoImpl extends BaseDaoImpl<ProductType, ProductTypeQuery> implements ProductTypeDao {

	public String creatHql(ProductTypeQuery q) {
		  String hql="from ProductType p where 1=1";
		  String str = creatHqlStat(q);
			return hql+str;
	}

	@Override
	public String creatHqlStat(ProductTypeQuery q) {
		String hql="";
		if(q.getSupplierId()!=null){
			hql=hql+" and p.supplier.supplierId =:supplierId";
		}
		if(StringUtils.isNotBlank(q.getName())){
			hql=hql+" and p.name like:name";
		}
		return hql;
	}

	@Override
	public String creatHqlCount(ProductTypeQuery q) {
		String hql="select count(productTypeId) from ProductType p where 1=1";
		String str = creatHqlStat(q);
		return hql+str;
	}
/**
 * 检验商品类型是否重复
 */
	@Override
	public ProductType getSupplierIdAndPname(final ProductType productType) {
		final String hql=" from ProductType p where p.supplier.supplierId=:supplierId and p.name=:name";
		ProductType pt = this.getHibernateTemplate().execute(new HibernateCallback<ProductType>() {

			@Override
			public ProductType doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameter("supplierId", productType.getSupplierId());
				query.setParameter("name", productType.getName());
				ProductType ptype = (ProductType) query.uniqueResult();
				return ptype;
			}
		});
		
		
		return pt;
	}

}
