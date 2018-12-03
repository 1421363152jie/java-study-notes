package cn.mj.ecps.dao;

import cn.mj.ecps.model.EbItemClob;

public interface EbItemClobDao {

   public void savaItemClob(EbItemClob itemClob,Long itemId);

  public void deleteClobByItemId(Long itemId);
}
