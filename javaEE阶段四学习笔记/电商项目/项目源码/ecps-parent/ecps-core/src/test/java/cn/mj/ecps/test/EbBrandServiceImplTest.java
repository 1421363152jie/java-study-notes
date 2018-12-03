package cn.mj.ecps.test;

import cn.mj.ecps.model.EbBrand;
import cn.mj.ecps.model.EbItem;
import cn.mj.ecps.service.*;
import cn.mj.ecps.utils.FMutil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class EbBrandServiceImplTest {

    @Autowired
    private EbBrandService brandService;

    @Autowired
    private EbIndexService indexService;

    @Autowired
    private EbRedisService redisService;

    @Autowired
    private EbItemService itemService;

    @Autowired
    private EbOrderFlowService orderFlowService;


    @Autowired
    private EbSkuService skuService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void savaBrand() {
     EbBrand brand=new EbBrand();
     brand.setBrandName("索尼");
     brand.setBrandDesc("高清防水，日本科技");
     brand.setImgs("update...");
     brand.setWebsite("http://suoni..");
     brand.setBrandSort(2);
     brandService.savaBrand(brand);
    }

    @Test
    public  void importIndex(){
        try {
            indexService.importIndex();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void importSkuByRedis(){
        redisService.importSkuDetail();
    }

    @Test
    public void importShipAddr(){
        redisService.importShipAddr();
    }

    @Test
    public void importSkuSales(){
        redisService.importSkuSales();
    }


    @Test
    public void deployHtml(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("path","http://localhost:8089");
        map.put("request_file_path","http://localhost:8099/ecps-file");
        EbItem item = itemService.selectItemDetailByItemId(3131l);
        map.put("item",item);
        try {
            FMutil.outputFile("productDetail.ftl", map, item.getItemId()+".html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deploy(){
        orderFlowService.deployFlow();
    }

    @Test
    public void selectItemByOrderWithRedis(){
        skuService.selectItemByOrderWithRedis();
    }

    @Test
    public void selectItemBySkuList(){
        List<EbItem> ebItems = itemService.selectItemBySkuList();
        System.out.println(ebItems);
    }
}