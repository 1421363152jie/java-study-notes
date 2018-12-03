package cn.mj.dao.impl;

import org.apache.commons.lang3.StringUtils;

import cn.mj.dao.ProductDao;
import cn.mj.model.Product;
import cn.mj.query.ProductQuery;

public class ProductDaoImpl extends BaseDaoImpl<Product, ProductQuery> implements ProductDao {

	public String creatHql(ProductQuery q) {
		  String hql="from Product p where 1=1";
		  String str = creatHqlStat(q);
			return hql+str;
	}

	@Override
	public String creatHqlStat(ProductQuery q) {
		String hql="";
		if(q.getSupplierId()!=null){
			hql=hql+" and p.productType.supplier.supplierId =:supplierId";
		}
		if(StringUtils.isNotBlank(q.getName())){
			hql=hql+" and p.name like:name";
		}
		if(StringUtils.isNotBlank(q.getProducer())){
			hql=hql+" and p.producer like:producer";
		}
		if(StringUtils.isNotBlank(q.getUnit())){
			hql=hql+" and p.unit like:unit";
		}
		if(q.getMinInPrice()!=null){
			hql=hql+" and p.inPrice >=:minInPrice";
		}
		if(q.getMaxInPrice()!=null){
			hql=hql+" and p.inPrice <=:maxInPrice";
		}
		if(q.getMinOutPrice()!=null){
			hql=hql+" and p.outPrice >=:minOutPrice ";
		}
		if(q.getMaxOutPrice()!=null){
			hql=hql+" and p.outPrice <=:maxOutPrice";
		}
		return hql;
	}

	@Override
	public String creatHqlCount(ProductQuery q) {
		String hql="select count(productId) from Product p where 1=1";
		String str = creatHqlStat(q);
		return hql+str;
	}

}
