package cn.mj.ecps.service;


import cn.mj.ecps.model.EbShipAddr;

import java.util.List;

public interface EbShipAddrService {

  public List<EbShipAddr> selectAddrByUserId(Long userId);

  public EbShipAddr selectAddrById(Long shipAddrId);

  public void savaOrUpdateAddr(Long userId,EbShipAddr shipAddr);

  public void deleteAddrById(Long shipAddrId);

  public void updateDefaultAddrByShipAddrId(Long userId,Long shiAddrId);

  public List<EbShipAddr> selectAddrByUserIdWithRedis(Long userId);


    public EbShipAddr selectAddrByUserIdWithRedis(Long userId,Long shipAddrId);





}
