package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbBrandDao;
import cn.mj.ecps.dao.EbFeatureDao;
import cn.mj.ecps.model.EbBrand;
import cn.mj.ecps.model.EbFeature;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EbFeatureDaoImpl extends SqlSessionDaoSupport implements EbFeatureDao{

    String ns="cn.mj.ecps.mapper.EbFeatureMapper.";

    public List<EbFeature> selectFeatureByCommon() {
        return this.getSqlSession().selectList(ns+"selectFeatureByCommon");
    }

    public List<EbFeature> selectFeatureBySpec() {
        return this.getSqlSession().selectList(ns+"selectFeatureBySpec");
    }

    public List<EbFeature> selectIsSelectFeature() {
        return this.getSqlSession().selectList(ns+"selectIsSelectFeature");
    }
}
