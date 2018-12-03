package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbItemClobDao;
import cn.mj.ecps.dao.EbItemDao;
import cn.mj.ecps.model.EbItemClob;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class EbItemClobDaoImpl extends SqlSessionDaoSupport implements EbItemClobDao {

    String ns="cn.mj.ecps.mapper.EbItemClobMapper.";

    public void savaItemClob(EbItemClob itemClob,Long itemId) {
        itemClob.setItemId(itemId);
        this.getSqlSession().insert(ns+"insert",itemClob);
    }

    public void deleteClobByItemId(Long itemId) {
        this.getSqlSession().delete(ns+"deleteByPrimaryKey",itemId);
    }
}
