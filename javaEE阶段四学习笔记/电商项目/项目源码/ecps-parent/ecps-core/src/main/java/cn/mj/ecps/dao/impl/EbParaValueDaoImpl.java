package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbItemDao;
import cn.mj.ecps.dao.EbParaValueDao;
import cn.mj.ecps.model.EbItem;
import cn.mj.ecps.model.EbParaValue;
import cn.mj.ecps.model.QueryCondition;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EbParaValueDaoImpl extends SqlSessionDaoSupport implements EbParaValueDao {

    String ns="cn.mj.ecps.mapper.EbParaValueMapper.";

    public void savaParaValue(List<EbParaValue> paraList,Long itemId) {
        SqlSession session = this.getSqlSession();
        for (EbParaValue para:paraList){
            para.setItemId(itemId);
            session.insert(ns+"insert",para);
        }

    }

    public void deleteParaValueByItemId(Long itemId) {
        this.getSqlSession().delete(ns+"deleteParaValueByItemId",itemId);
    }
}
