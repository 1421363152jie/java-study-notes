package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbOrderDao;
import cn.mj.ecps.dao.EbOrderDetailDao;
import cn.mj.ecps.model.EbOrder;
import cn.mj.ecps.model.EbOrderDetail;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class EbOrderDetailDaoImpl extends SqlSessionDaoSupport implements EbOrderDetailDao {

    String ns="cn.mj.ecps.mapper.EbOrderDetailMapper.";

    public void savaOrderDetail(EbOrderDetail orderDetail) {
        this.getSqlSession().insert(ns+"insert",orderDetail);
    }
}
