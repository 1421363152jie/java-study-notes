package cn.mj.ecps.dao.impl;

import cn.mj.ecps.dao.EbItemDao;
import cn.mj.ecps.dao.EbSkuDao;
import cn.mj.ecps.model.EbItem;
import cn.mj.ecps.model.EbSku;
import cn.mj.ecps.model.EbSpecValue;
import cn.mj.ecps.model.QueryCondition;
import cn.mj.ecps.utils.EbMJUtis;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

@Repository
public class EbSkuDaoImpl extends SqlSessionDaoSupport implements EbSkuDao {

    String ns = "cn.mj.ecps.mapper.EbSkuMapper.";

    public void savaSku(List<EbSku> skuList, Long itemId) {
        SqlSession session = this.getSqlSession();
        for (EbSku sku:skuList){
            sku.setItemId(itemId);
            session.insert(ns+"insert",sku);
            List<EbSpecValue> specList = sku.getSpecList();
            for (EbSpecValue spec:specList){
                spec.setSkuId(sku.getSkuId());
                session.insert("cn.mj.ecps.mapper.EbSpecValueMapper.insert",spec);
            }


        }

    }

    public List<EbSku> selectSkuByItemId(Long itemId) {
        return this.getSqlSession().selectList(ns+"selectSkuByItemId",itemId);
    }

    public void deleteSkuByItemId(Long itemId) {
        this.getSqlSession().delete(ns+"deleteSkuByItemId",itemId);
    }

    public List<EbSku> selectSkuDetailList() {
        return this.getSqlSession().selectList(ns+"selectSkuDetailList");
    }


    /**
     * 修改扣减库存
     */
    public int updateStock(Map<String, Object> map) {
        int result = this.getSqlSession().update(ns + "updateStock", map);
        return result;
    }

    /**
     * 扣减redis中对应的库存同步
     */
    public void updateStockRedis(Map<String, Object> map) {
        Long skuId = (Long) map.get("skuId");
        Integer quantity= (Integer) map.get("quantity");
        Jedis jedis=new Jedis(EbMJUtis.readProp("redis_ip"),new Integer(EbMJUtis.readProp("redis_port")));
        jedis.hset("sku:"+skuId,"stockInventory",new  Integer(jedis.hget("sku:"+skuId,"stockInventory"))-quantity+"");
        jedis.hset("sku:"+skuId,"sales",new  Integer(jedis.hget("sku:"+skuId,"sales"))+quantity+"");

        jedis.hset("sku:"+skuId,"sales",new  Integer(jedis.hget("sku:"+skuId,"sales"))+quantity+"");
    }
}
