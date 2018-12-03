package cn.mj.ecps.dao;

import cn.mj.ecps.model.EbSku;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Map;

public interface EbSkuDao {

 public void savaSku(List<EbSku> skuList,Long itemId);

 public List<EbSku> selectSkuByItemId(Long itemId);

 public void deleteSkuByItemId(Long itemId);

 public List<EbSku> selectSkuDetailList();

 public int updateStock(Map<String,Object> map);

 public void updateStockRedis(Map<String,Object> map);


}
