package cn.mj.ecps.service.impl;

import cn.mj.ecps.dao.EbBrandDao;
import cn.mj.ecps.model.EbBrand;
import cn.mj.ecps.service.EbBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbBrandServiceImpl implements EbBrandService {

    @Autowired
    private EbBrandDao brandDao;

    public void savaBrand(EbBrand brand) {
         brandDao.savaBrand(brand);
    }

    public List<EbBrand> selectBrandAll() {
        return brandDao.selectBrandAll();
    }

    public EbBrand selectBrandByName(String brandName) {
        return brandDao.selectBrandByName(brandName);
    }

    public EbBrand selectBrandById(Long brandId) {
        return brandDao.selectBrandById(brandId);
    }

    public void updateBrand(EbBrand brand) {
        brandDao.updateBrand(brand);
    }

    public void deleteBrandById(Long brandId) {
        brandDao.deleteBrandById(brandId);
    }
}

