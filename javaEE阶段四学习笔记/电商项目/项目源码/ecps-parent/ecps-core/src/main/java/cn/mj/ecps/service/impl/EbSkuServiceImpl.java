package cn.mj.ecps.service.impl;

import cn.mj.ecps.dao.EbSkuDao;
import cn.mj.ecps.model.EbItem;
import cn.mj.ecps.model.EbSku;
import cn.mj.ecps.model.EbSpecValue;
import cn.mj.ecps.service.EbSkuService;
import cn.mj.ecps.utils.EbMJUtis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbSkuServiceImpl implements EbSkuService {

    @Autowired
    private EbSkuDao skuDao;

   public EbSku selectSkuDetailByIdWithRedis(Long skuId) {
        Jedis jedis=new Jedis(EbMJUtis.readProp("redis_ip"),new Integer(EbMJUtis.readProp("redis_port")));
        String skuPrice = jedis.hget("sku:" + skuId, "skuPrice");
        String marketPrice = jedis.hget("sku:" + skuId, "marketPrice");
        String stockInventory = jedis.hget("sku:" + skuId, "stockInventory");
        String itemId = jedis.hget("sku:" + skuId, "itemId");
        //创建对象
        EbSku sku=new EbSku();
        sku.setSkuId(skuId);
        sku.setSkuPrice(new BigDecimal(skuPrice));
        sku.setMarketPrice(new BigDecimal(marketPrice));
        sku.setStockInventory(new Integer(stockInventory));
        String itemName = jedis.hget("sku:" + skuId + ":item:" + itemId, "itemName");
        String itemNo = jedis.hget("sku:" + skuId + ":item:" + itemId, "itemNo");
        String imgs = jedis.hget("sku:" + skuId + ":item:" + itemId, "imgs");
        EbItem item=new EbItem();
        item.setItemId(new Long(itemId));
        item.setItemName(itemName);
        item.setItemNo(itemNo);
        item.setImgs(imgs);
        sku.setItem(item);
        List<String> specIds = jedis.lrange("sku:" + skuId + ":specList", 0, -1);
        List<EbSpecValue> specList=new ArrayList<EbSpecValue>();
        for (String specId:specIds){
            String specValue = jedis.hget("sku:" + skuId + ":spec:" + specId, "specValue");
            EbSpecValue spec=new EbSpecValue();
            spec.setSkuId(skuId);
            spec.setSpecId(new Long(specId));
            spec.setSpecValue(specValue);
            specList.add(spec);
        }
        sku.setSpecList(specList);
        return sku;
    }


    public List<EbSku> selectItemByOrderWithRedis() {
        Jedis jedis=new Jedis(EbMJUtis.readProp("redis_ip"),new Integer(EbMJUtis.readProp("redis_port")));
        SortingParams sortingParameters = new SortingParams();
        sortingParameters.desc();
        List<String> salesIds = jedis.sort("salesList", sortingParameters);
        List<EbSku> skuList = new ArrayList<EbSku>();
        for(String sales:salesIds) {
            String skuPrice = jedis.hget("sku:" + sales, "skuPrice");
            String skuId = jedis.hget("sku:" + sales, "skuId");
            String sales1 = jedis.hget("sku:" + sales, "sales");
            String itemId = jedis.hget("sku:" + sales, "itemId");
            if(sales.equals(sales1)) {
                //创建对象
                EbSku sku = new EbSku();
                sku.setSkuId(new Long(skuId));
                sku.setSales(new Long(sales));
                sku.setSkuPrice(new BigDecimal(skuPrice));
                String itemName = jedis.hget("sku:" + sales + ":item:" + itemId, "itemName");
                String imgs = jedis.hget("sku:" + sales + ":item:" + itemId, "imgs");
                EbItem item = new EbItem();
                item.setItemId(new Long(itemId));
                item.setItemName(itemName);
                item.setImgs(imgs);
                sku.setItem(item);
                skuList.add(sku);
            }
        }

        return skuList;
    }




}
