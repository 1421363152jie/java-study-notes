package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.TsPtlUserDao;
import cn.mj.ecps.model.TsPtlUser;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class TsPtlUserDaoImpl  extends SqlSessionDaoSupport implements TsPtlUserDao {

    String ns="cn.mj.ecps.mapper.TsPtlUserMapper.";

    public TsPtlUser selectUserByUnameAndPword(Map<String, String> map) {
        return this.getSqlSession().selectOne(ns+"selectUserByUnameAndPword",map);
    }
}
