package cn.mj.ecps.dao;

import cn.mj.ecps.model.EbFeature;

import java.util.List;

public interface EbFeatureDao {

    public List<EbFeature> selectFeatureByCommon();


    public List<EbFeature> selectFeatureBySpec();


    public List<EbFeature> selectIsSelectFeature();


}
