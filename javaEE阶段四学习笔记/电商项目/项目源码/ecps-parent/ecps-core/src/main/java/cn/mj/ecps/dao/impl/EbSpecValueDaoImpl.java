package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbSpecValueDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class EbSpecValueDaoImpl extends SqlSessionDaoSupport implements EbSpecValueDao {

    String ns = "cn.mj.ecps.mapper.EbSpecValueMapper.";

    public void deleteSpecBySkuId(Long skuId) {
        this.getSqlSession().delete(ns+"deleteSpecBySkuId",skuId);
    }
}
