package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbAreaDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EbAreaDaoImpl extends SqlSessionDaoSupport implements EbAreaDao {

    String ns="cn.mj.ecps.mapper.EbAreaMapper.";

    public List<EbAreaDao> selectAddrByPid(Long pId) {
        return this.getSqlSession().selectList(ns+"selectAddrByPid",pId);
    }
}
