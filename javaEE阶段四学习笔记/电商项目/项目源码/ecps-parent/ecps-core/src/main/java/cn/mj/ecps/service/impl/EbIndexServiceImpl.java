package cn.mj.ecps.service.impl;

import cn.mj.ecps.dao.EbItemDao;
import cn.mj.ecps.model.EbItem;
import cn.mj.ecps.model.EbParaValue;
import cn.mj.ecps.service.EbIndexService;
import cn.mj.ecps.utils.EbMJUtis;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbIndexServiceImpl implements EbIndexService {

    @Autowired
    private EbItemDao itemDao;

    /**
     * 向solr中导入索引
     */
    public void importIndex() throws Exception {
        List<EbItem> itemList = itemDao.selectIsSelectItem();
        HttpSolrServer solrServer = EbMJUtis.getHttpSolrServer();
        for (EbItem item:itemList) {
            SolrInputDocument sd=new SolrInputDocument();
            sd.addField("id", item.getItemId());
            sd.addField("item_name", item.getItemName());
            sd.addField("brand_id", item.getBrandId());
            sd.addField("sku_price", item.getSkuPrice());
            sd.addField("promotion", item.getPromotion());
            sd.addField("item_keywords", item.getKeywords());
            sd.addField("imgs", item.getImgs());
            List<EbParaValue> paraList = item.getParaList();
            String paraValue = "";
            for (EbParaValue paraVals : paraList) {
                String paraVal = paraVals.getParaValue();
                paraValue = paraValue + paraVal + " ";
            }
            sd.addField("para_values", paraValue);
            solrServer.add(sd);
        }
        solrServer.commit();
    }
}

