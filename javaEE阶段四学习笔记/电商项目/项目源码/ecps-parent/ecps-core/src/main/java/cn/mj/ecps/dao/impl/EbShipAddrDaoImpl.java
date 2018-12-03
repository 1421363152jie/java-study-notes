package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbShipAddrDao;
import cn.mj.ecps.model.EbShipAddr;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EbShipAddrDaoImpl extends SqlSessionDaoSupport implements EbShipAddrDao {

    String ns="cn.mj.ecps.mapper.EbShipAddrMapper.";

    public List<EbShipAddr> selectAddrByUserId(Long userId) {
        return this.getSqlSession().selectList(ns+"selectAddrByUserId",userId);
    }

    public EbShipAddr selectAddrById(Long shipAddrId) {
        return this.getSqlSession().selectOne(ns+"selectByPrimaryKey",shipAddrId);
    }

    public void savaAddr(EbShipAddr shipAddr) {
        this.getSqlSession().insert(ns+"insert",shipAddr);
    }

    public void updateAddr(EbShipAddr shipAddr) {
        this.getSqlSession().update(ns+"updateByPrimaryKeySelective",shipAddr);
    }

    public void updateDefaultAddr(Long userId) {
       this.getSqlSession().update(ns+"updateDefaultAddr",userId);
    }

    public void deleteAddrById(Long shipAddrId) {
        this.getSqlSession().delete(ns+"deleteByPrimaryKey",shipAddrId);
    }

    public void updateDefaultAddrByShipAddrId(Long shipAddrId) {
        this.getSqlSession().update(ns+"updateDefaultAddrByShipAddrId",shipAddrId);
    }

}
