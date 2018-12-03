package cn.mj.ecps.service;


import cn.mj.ecps.model.EbFeature;

import java.util.List;

public interface EbFeatureService {

  public List<EbFeature> selectFeatureByCommon();


  public List<EbFeature> selectFeatureBySpec();

  public List<EbFeature> selectIsSelectFeature();
}
