package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbOrderDao;
import cn.mj.ecps.dao.EbParaValueDao;
import cn.mj.ecps.model.EbOrder;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EbOrderDaoImpl extends SqlSessionDaoSupport implements EbOrderDao {

    String ns="cn.mj.ecps.mapper.EbOrderMapper.";


    public void savaOrder(EbOrder order) {
        this.getSqlSession().insert(ns+"insert",order);
    }

    public void updateOrder(EbOrder order) {
        this.getSqlSession().update(ns+"updateByPrimaryKeySelective",order);
    }

    public EbOrder selectOrderById(Long orderId) {
        return this.getSqlSession().selectOne(ns+"selectByPrimaryKey",orderId);
    }

    public EbOrder selectDetailByOrderId(Long orderId) {
        return this.getSqlSession().selectOne(ns+"selectDetailByOrderId",orderId);
    }


}
