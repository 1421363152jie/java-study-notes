package cn.mj.ecps.service.impl;

import cn.mj.ecps.dao.EbShipAddrDao;
import cn.mj.ecps.dao.EbSkuDao;
import cn.mj.ecps.model.EbShipAddr;
import cn.mj.ecps.model.EbSku;
import cn.mj.ecps.model.EbSpecValue;
import cn.mj.ecps.service.EbRedisService;
import cn.mj.ecps.utils.EbMJUtis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class EbRedisServiceImpl implements EbRedisService {

    @Autowired
    private EbSkuDao skuDao;

    @Autowired
    private EbShipAddrDao shipAddrDao;


    public void importSkuDetail() {
        Jedis jedis=new Jedis(EbMJUtis.readProp("redis_ip"),new Integer(EbMJUtis.readProp("redis_port")));
        List<EbSku> skuList = skuDao.selectSkuDetailList();
        for (EbSku sku:skuList){
          jedis.lpush("skuList",sku.getSkuId()+"");
          jedis.hset("sku:"+sku.getSkuId(),"skuId",sku.getSkuId()+"");
          jedis.hset("sku:"+sku.getSkuId(),"skuPrice",sku.getSkuPrice()+"");
          jedis.hset("sku:"+sku.getSkuId(),"sales",sku.getSales()+"");
          jedis.hset("sku:"+sku.getSkuId(),"marketPrice",sku.getMarketPrice()+"");
          jedis.hset("sku:"+sku.getSkuId(),"stockInventory",sku.getStockInventory()+"");
          jedis.hset("sku:"+sku.getSkuId(),"itemId",sku.getItemId()+"");

          jedis.hset("sku:"+sku.getSkuId()+":item:"+sku.getItemId(),"itemId",sku.getItem().getItemId()+"");
          jedis.hset("sku:"+sku.getSkuId()+":item:"+sku.getItemId(),"itemName",sku.getItem().getItemName());
          jedis.hset("sku:"+sku.getSkuId()+":item:"+sku.getItemId(),"itemNo",sku.getItem().getItemNo());
          jedis.hset("sku:"+sku.getSkuId()+":item:"+sku.getItemId(),"imgs",sku.getItem().getImgs());

            List<EbSpecValue> specList = sku.getSpecList();
            for (EbSpecValue spec:specList){
                jedis.lpush("sku:"+sku.getSkuId()+":specList",spec.getSpecId()+"");
                jedis.hset("sku:"+sku.getSkuId()+":spec:"+spec.getSpecId(),"specId",spec.getSpecId()+"");
                jedis.hset("sku:"+sku.getSkuId()+":spec:"+spec.getSpecId(),"skuId",spec.getSkuId()+"");
                jedis.hset("sku:"+sku.getSkuId()+":spec:"+spec.getSpecId(),"specValue",spec.getSpecValue());
            }

        }
    }

    public void importShipAddr() {
        Jedis jedis=new Jedis(EbMJUtis.readProp("redis_ip"),new Integer(EbMJUtis.readProp("redis_port")));
        List<EbShipAddr> addrList = shipAddrDao.selectAddrByUserId(3500l);
         for (EbShipAddr addr:addrList){
              jedis.lpush("user:3500:addrList",addr.getShipAddrId()+"");

             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"shipAddrId",addr.getShipAddrId()+"");
              jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"shipName",addr.getShipName());
             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"province",addr.getProvince());
             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"city",addr.getCity());
             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"district",addr.getDistrict());
             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"zipCode",addr.getZipCode());
             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"addr",addr.getAddr());
             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"phone",addr.getPhone());
             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"defaultAddr",addr.getDefaultAddr()+"");

             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"provText",addr.getProvText());
             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"cityText",addr.getCityText());
             jedis.hset("user:3500:addr:"+addr.getShipAddrId(),"distText",addr.getDistText());




         }


    }
    public void importSkuSales() {
        Jedis jedis=new Jedis(EbMJUtis.readProp("redis_ip"),new Integer(EbMJUtis.readProp("redis_port")));
        List<EbSku> skuList = skuDao.selectSkuDetailList();
        for (EbSku sku:skuList){
            jedis.lpush("salesList",sku.getSales()+"");
            jedis.hset("sku:"+sku.getSales(),"skuId",sku.getSkuId()+"");
            jedis.hset("sku:"+sku.getSales(),"skuPrice",sku.getSkuPrice()+"");
            jedis.hset("sku:"+sku.getSales(),"sales",sku.getSales()+"");
            jedis.hset("sku:"+sku.getSales(),"marketPrice",sku.getMarketPrice()+"");
            jedis.hset("sku:"+sku.getSales(),"stockInventory",sku.getStockInventory()+"");
            jedis.hset("sku:"+sku.getSales(),"itemId",sku.getItemId()+"");

            jedis.hset("sku:"+sku.getSales()+":item:"+sku.getItemId(),"itemId",sku.getItem().getItemId()+"");
            jedis.hset("sku:"+sku.getSales()+":item:"+sku.getItemId(),"itemName",sku.getItem().getItemName());
            jedis.hset("sku:"+sku.getSales()+":item:"+sku.getItemId(),"itemNo",sku.getItem().getItemNo());
            jedis.hset("sku:"+sku.getSales()+":item:"+sku.getItemId(),"imgs",sku.getItem().getImgs());
        }
    }
}
