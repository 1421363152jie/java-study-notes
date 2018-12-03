package cn.mj.ecps.service;


import cn.mj.ecps.model.EbBrand;

import java.util.List;

public interface EbBrandService {

    public void savaBrand(EbBrand brand);

    public List<EbBrand> selectBrandAll();

    public EbBrand selectBrandByName(String brandName);

    public EbBrand selectBrandById(Long brandId);

    public void updateBrand(EbBrand brand);

    public void deleteBrandById(Long brandId);
}
