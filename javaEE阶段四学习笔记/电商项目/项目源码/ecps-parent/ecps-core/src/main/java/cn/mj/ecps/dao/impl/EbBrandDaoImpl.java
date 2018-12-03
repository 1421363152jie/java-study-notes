package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbBrandDao;
import cn.mj.ecps.model.EbBrand;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EbBrandDaoImpl extends SqlSessionDaoSupport implements EbBrandDao{

    String ns="cn.mj.ecps.mapper.EbBrandMapper.";

    public void savaBrand(EbBrand brand) {

        this.getSqlSession().insert(ns+"insert",brand);
    }

    public List<EbBrand> selectBrandAll() {
        return this.getSqlSession().selectList(ns+"selectBrandAll");
    }

    public EbBrand selectBrandByName(String brandName) {
        return this.getSqlSession().selectOne(ns+"selectBrandByName",brandName);
    }

    public EbBrand selectBrandById(Long brandId) {
        return this.getSqlSession().selectOne(ns+"selectByPrimaryKey",brandId);
    }

    public void updateBrand(EbBrand brand) {
        this.getSqlSession().update(ns+"updateByPrimaryKeySelective",brand);
    }

    public void deleteBrandById(Long brandId) {
        this.getSqlSession().delete(ns+"deleteByPrimaryKey",brandId);
    }
}
