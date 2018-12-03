package cn.mj.ecps.service.impl;

import cn.mj.ecps.dao.EbFeatureDao;
import cn.mj.ecps.model.EbFeature;
import cn.mj.ecps.service.EbFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbFeatureServiceImpl implements EbFeatureService {

    @Autowired
    private EbFeatureDao featureDao;

    /**
     * 查询普通属性
     * @return
     */
    public List<EbFeature> selectFeatureByCommon() {
        return featureDao.selectFeatureByCommon();
    }

    public List<EbFeature> selectFeatureBySpec() {
        return featureDao.selectFeatureBySpec();
    }

    public List<EbFeature> selectIsSelectFeature() {
        return featureDao.selectIsSelectFeature();
    }
}

