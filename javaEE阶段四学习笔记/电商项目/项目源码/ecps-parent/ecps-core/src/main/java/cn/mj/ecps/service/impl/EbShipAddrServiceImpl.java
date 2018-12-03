package cn.mj.ecps.service.impl;

import cn.mj.ecps.dao.EbShipAddrDao;
import cn.mj.ecps.model.EbShipAddr;
import cn.mj.ecps.service.EbShipAddrService;
import cn.mj.ecps.utils.EbMJUtis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class EbShipAddrServiceImpl implements EbShipAddrService {

    @Autowired
    private EbShipAddrDao addrDao;

    public List<EbShipAddr> selectAddrByUserId(Long userId) {
        return addrDao.selectAddrByUserId(userId);
    }

    public EbShipAddr selectAddrById(Long shipAddrId) {
        return addrDao.selectAddrById(shipAddrId);
    }

    public void savaOrUpdateAddr(Long userId, EbShipAddr shipAddr) {
         addrDao.updateDefaultAddr(userId);
         if(shipAddr.getShipAddrId()==null){
             addrDao.savaAddr(shipAddr);
         }else{
             addrDao.updateAddr(shipAddr);
         }
    }

    public void deleteAddrById(Long shipAddrId) {
        addrDao.deleteAddrById(shipAddrId);
    }

    public void updateDefaultAddrByShipAddrId(Long userId,Long shiAddrId) {
        addrDao.updateDefaultAddr(userId);
        addrDao.updateDefaultAddrByShipAddrId(shiAddrId);
    }

    public List<EbShipAddr> selectAddrByUserIdWithRedis(Long userId) {
        Jedis jedis=new Jedis(EbMJUtis.readProp("redis_ip"),new Integer(EbMJUtis.readProp("redis_port")));
        List<String> addrIds = jedis.lrange("user:" + userId + ":addrList", 0, -1);
        List<EbShipAddr> addrList=new ArrayList<EbShipAddr>();
        for (String addrId:addrIds){
            String shipName = jedis.hget("user:" + userId + ":addr:" + addrId, "shipName");
            String province = jedis.hget("user:" + userId + ":addr:" + addrId, "province");
            String city = jedis.hget("user:" + userId + ":addr:" + addrId, "city");
            String district = jedis.hget("user:" + userId + ":addr:" + addrId, "district");
            String zipCode = jedis.hget("user:" + userId + ":addr:" + addrId, "zipCode");
            String addrStr= jedis.hget("user:" + userId + ":addr:" + addrId, "addr");
            String phone = jedis.hget("user:" + userId + ":addr:" + addrId, "phone");
            String defaultAddr = jedis.hget("user:" + userId + ":addr:" + addrId, "defaultAddr");
            String provText = jedis.hget("user:" + userId + ":addr:" + addrId, "provText");
            String cityText = jedis.hget("user:" + userId + ":addr:" + addrId, "cityText");
            String distText = jedis.hget("user:" + userId + ":addr:" + addrId, "distText");

            //创建对象
            EbShipAddr addr=new EbShipAddr();
            addr.setShipAddrId(new Long(addrId));
            addr.setShipName(shipName);
            addr.setProvince(province);
            addr.setCity(city);
            addr.setDistrict(district);
            addr.setZipCode(zipCode);
            addr.setAddr(addrStr);
            addr.setPhone(phone);
            addr.setDefaultAddr(new Short(defaultAddr));
            addr.setProvText(provText);
            addr.setCityText(cityText);
            addr.setDistText(distText);
            addrList.add(addr);
        }
        return addrList;
    }

    public EbShipAddr selectAddrByUserIdWithRedis(Long userId, Long addrId) {
        Jedis jedis=new Jedis(EbMJUtis.readProp("redis_ip"),new Integer(EbMJUtis.readProp("redis_port")));
            String shipName = jedis.hget("user:" + userId + ":addr:" + addrId, "shipName");
            String province = jedis.hget("user:" + userId + ":addr:" + addrId, "province");
            String city = jedis.hget("user:" + userId + ":addr:" + addrId, "city");
            String district = jedis.hget("user:" + userId + ":addr:" + addrId, "district");
            String zipCode = jedis.hget("user:" + userId + ":addr:" + addrId, "zipCode");
            String addrStr= jedis.hget("user:" + userId + ":addr:" + addrId, "addr");
            String phone = jedis.hget("user:" + userId + ":addr:" + addrId, "phone");
            String defaultAddr = jedis.hget("user:" + userId + ":addr:" + addrId, "defaultAddr");
            String provText = jedis.hget("user:" + userId + ":addr:" + addrId, "provText");
            String cityText = jedis.hget("user:" + userId + ":addr:" + addrId, "cityText");
            String distText = jedis.hget("user:" + userId + ":addr:" + addrId, "distText");
             EbShipAddr addr=new EbShipAddr();
            //创建对象
            addr.setShipAddrId(new Long(addrId));
            addr.setShipName(shipName);
            addr.setProvince(province);
            addr.setCity(city);
            addr.setDistrict(district);
            addr.setZipCode(zipCode);
            addr.setAddr(addrStr);
            addr.setPhone(phone);
            addr.setDefaultAddr(new Short(defaultAddr));
            addr.setProvText(provText);
            addr.setCityText(cityText);
            addr.setDistText(distText);
        return addr;
    }

}

