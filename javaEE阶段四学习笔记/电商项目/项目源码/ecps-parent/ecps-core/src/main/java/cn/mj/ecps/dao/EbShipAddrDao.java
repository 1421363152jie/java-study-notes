package cn.mj.ecps.dao;

import cn.mj.ecps.model.EbShipAddr;

import java.util.List;

public interface EbShipAddrDao {

 public List<EbShipAddr> selectAddrByUserId(Long userId);

  public EbShipAddr selectAddrById(Long shipAddrId);

  public void savaAddr(EbShipAddr shipAddr);

  public void updateAddr(EbShipAddr shipAddr);

  public void updateDefaultAddr(Long userId);

  public void deleteAddrById(Long shipAddrId);

  public void updateDefaultAddrByShipAddrId(Long shipAddrId);


}
