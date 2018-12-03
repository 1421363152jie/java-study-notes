package cn.mj.ecps.service;

import cn.mj.ecps.model.EbSku;

import java.util.List;

public interface EbSkuService {

    public EbSku selectSkuDetailByIdWithRedis(Long skuId);

    public List<EbSku> selectItemByOrderWithRedis();
}
