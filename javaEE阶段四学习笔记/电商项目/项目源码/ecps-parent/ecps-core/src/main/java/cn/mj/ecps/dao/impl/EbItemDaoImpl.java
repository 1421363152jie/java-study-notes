package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbItemDao;
import cn.mj.ecps.model.EbItem;
import cn.mj.ecps.model.QueryCondition;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EbItemDaoImpl extends SqlSessionDaoSupport implements EbItemDao {

    String ns="cn.mj.ecps.mapper.EbItemMapper.";

    public List<EbItem> selectItemBycondition(QueryCondition condition) {
        return this.getSqlSession().selectList(ns+"selectItemBycondition",condition);
    }

    public Integer selectItemByconditionCount(QueryCondition condition) {
        return this.getSqlSession().selectOne(ns+"selectItemByconditionCount",condition);
    }

    public void savaItem(EbItem item) {
        this.getSqlSession().insert(ns+"insert",item);
    }

    public void updateIten(EbItem item) {
        this.getSqlSession().update(ns+"updateByPrimaryKeySelective",item);
    }

    public void deleteItemById(Long itemId) {
        this.getSqlSession().delete(ns+"deleteByPrimaryKey",itemId);
    }

    public List<EbItem> selectIsSelectItem() {
        return this.getSqlSession().selectList(ns+"selectIsSelectItem");
    }

    public EbItem selectItemDetailByItemId(Long itemId) {
        return this.getSqlSession().selectOne(ns+"selectItemDetailByItemId",itemId);
    }

    public List<EbItem> selectItemBySkuList() {
        return this.getSqlSession().selectList(ns+"selectItemBySkuList");
    }


}
