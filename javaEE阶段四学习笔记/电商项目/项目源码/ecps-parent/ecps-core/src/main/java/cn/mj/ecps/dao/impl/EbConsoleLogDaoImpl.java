package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbConsoleLogDao;
import cn.mj.ecps.model.EbConsoleLog;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class EbConsoleLogDaoImpl extends SqlSessionDaoSupport implements EbConsoleLogDao {

    String ns="cn.mj.ecps.mapper.EbConsoleLogMapper.";

    public void savaLog(EbConsoleLog log) {
        this.getSqlSession().insert(ns+"insert",log);
    }

}
