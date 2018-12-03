package cn.mj.ecps.service.impl;

import cn.mj.ecps.dao.*;
import cn.mj.ecps.model.*;
import cn.mj.ecps.service.EbItemService;
import cn.mj.ecps.stub.EbWsItemService;
import cn.mj.ecps.stub.EbWsItemServiceService;
import cn.mj.ecps.utils.EbMJUtis;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EbItemServiceImpl implements EbItemService {

    @Autowired
     private EbItemDao itemDao;

    @Autowired
    private EbSkuDao skuDao;

    @Autowired
    private EbParaValueDao paraValueDao;

    @Autowired
    private EbItemClobDao itemClobDao;

    @Autowired
    private EbConsoleLogDao logDao;

    @Autowired
    private EbSpecValueDao specValueDao;



    public Page selectItemBycondition(QueryCondition condition) {
        //查询总记录数
        Integer totalCount = itemDao.selectItemByconditionCount(condition);
        //创建分页对象
        Page page =new Page();
        //设置总记录数
        page.setTotalCount(totalCount);
        //设置单前叶页码
        page.setPageNum(condition.getPageNum());
        //从分页对象中获取开始行号和结束行号
        int startNum = page.getStartNum();
        int endNum = page.getEndNum();
        //给查询对象设置开始行号和结束行号
        condition.setStartNum(startNum);
        condition.setEndNum(endNum);
        //分页查询
        List<EbItem> itemList = itemDao.selectItemBycondition(condition);
        //把结果集合设置给分页对象
        page.setList(itemList);
        return page;
    }

    public void savaItem(EbItem item, EbItemClob itemClob, List<EbSku> skuList, List<EbParaValue> paraValueList) {
        itemDao.savaItem(item);
        itemClobDao.savaItemClob(itemClob,item.getItemId());
        paraValueDao.savaParaValue(paraValueList,item.getItemId());
        skuDao.savaSku(skuList,item.getItemId());
    }

    public void updateAuditItem(Long itemId, Short auditStatus, String note) {
        EbItem item=new EbItem();
        item.setItemId(itemId);
        item.setAuditStatus(auditStatus);
        itemDao.updateIten(item);
        //创建日志对象
        EbConsoleLog log=new EbConsoleLog();
        log.setEntityName("商品表");
        log.setEntityId(itemId);
        log.setNotes(note);
        log.setOpTime(new Date());
        log.setOpType("审核");
        log.setTableName("eb_item");
        log.setUserId(1l);
        //保存日志对象
        logDao.savaLog(log);
    }

    public void updateshowItem(Long itemId, Short showStatus, String note) {
        EbItem item=new EbItem();
        item.setItemId(itemId);
        item.setShowStatus(showStatus);
        itemDao.updateIten(item);
        //创建日志对象
        EbConsoleLog log=new EbConsoleLog();
        log.setEntityName("商品表");
        log.setEntityId(itemId);
        log.setNotes(note);
        log.setOpTime(new Date());
        log.setOpType("商品的上下架");
        log.setTableName("eb_item");
        log.setUserId(1l);
        //保存日志对象
        logDao.savaLog(log);
    }

    public void deleteItem(Long itemId) {
        itemDao.deleteItemById(itemId);
        itemClobDao.deleteClobByItemId(itemId);
        paraValueDao.deleteParaValueByItemId(itemId);
         //查询sku获得对应的对象
        List<EbSku> skuList = skuDao.selectSkuByItemId(itemId);
        for (EbSku sku:skuList){
            specValueDao.deleteSpecBySkuId(sku.getSkuId());
        }
         skuDao.deleteSkuByItemId(itemId);
    }

    public List<EbItem> selectItemByIndex(String skuPrice,Long brandId,String keyworks,String paraVals) throws IOException, SolrServerException {
       List<EbItem> itemList=new ArrayList<EbItem>();
        HttpSolrServer solrServer = EbMJUtis.getHttpSolrServer();
        SolrQuery  sq=new SolrQuery();
         if(StringUtils.isNotBlank(skuPrice)){
             String[] priceAddr = skuPrice.split("-");
          sq.set("fq","sku_price:["+priceAddr[0]+" TO "+priceAddr[1]+"]");
         }
         String queryStr="*:*";
         if(brandId!=null){
             queryStr="brand_id:"+brandId;
         }
         if(StringUtils.isNotBlank(keyworks)){
             if(StringUtils.equals(queryStr,"*:*")){
                 queryStr="item_keywords:"+keyworks;
             }else{
                 queryStr=queryStr+" AND item_keywords:"+keyworks;
             }
         }
         if(StringUtils.isNotBlank(paraVals)){
             String paraQuery="";
             String[] paraVaues = paraVals.split(",");
             for (String pavaVal:paraVaues){
                 paraQuery=paraQuery+"para_values:"+pavaVal+" AND ";
             }
             paraQuery=paraQuery.substring(0,paraQuery.lastIndexOf(" AND "));
             if(StringUtils.equals(queryStr,"*:*")){
                 queryStr=paraQuery;
             }else{
                 queryStr=queryStr+paraQuery;
             }
         }
         sq.set("q",queryStr);
         sq.setHighlight(true);
         sq.addHighlightField("item_name");
         sq.addHighlightField("promotion");
         sq.setHighlightSimplePre("<font color='red'>");
         sq.setHighlightSimplePost("</font>");
        QueryResponse query = solrServer.query(sq);
        SolrDocumentList sdList = query.getResults();
          for (SolrDocument sd:sdList){
              String itemId = (String) sd.getFieldValue("id");
              String sku_price =sd.getFieldValue("sku_price").toString();
              String promotion = (String) sd.getFieldValue("promotion");
              String imgs = (String) sd.getFieldValue("imgs");
              String itemName = (String) sd.getFieldValue("item_name");
              //创建对象
              EbItem item=new EbItem();
              item.setItemId(new Long(itemId));
              item.setSkuPrice(new BigDecimal(sku_price));
              //高亮查询
              Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
              if(highlighting!=null){
                  Map<String, List<String>>  map = highlighting.get(itemId);
                  if(map!=null){
                      List<String> ilist = map.get("item_name");
                      if(ilist!=null&&ilist.size()>0){
                          String hlStr="";
                          for (String hl:ilist){
                              hlStr=hlStr+hl;
                          }
                          itemName=hlStr;
                      }
                      List<String> plist = map.get("promotion");
                      if(plist!=null&&plist.size()>0){
                          String hlStr="";
                          for (String hl:ilist){
                              hlStr=hlStr+hl;
                          }
                          promotion=hlStr;
                      }
                  }
              }
              item.setItemName(itemName);
              item.setPromotion(promotion);
              item.setImgs(imgs);
              itemList.add(item);
          }
        return itemList;
    }

    /**
     * 添加商品时添加索引
     */
    public void addItemIndex(EbItem item) throws IOException, SolrServerException {
        HttpSolrServer solrServer = EbMJUtis.getHttpSolrServer();
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
            String paraVal = paraVals.getParaValue() ;
            paraValue = paraValue + paraVal + " ";
        }
        sd.addField("para_values", paraValue);
        solrServer.add(sd);
        solrServer.commit();
    }

    public EbItem selectItemDetailByItemId(Long itemId) {
        return itemDao.selectItemDetailByItemId(itemId);
    }

    public String publishItem(String password, Long itemId) {
        EbWsItemServiceService ess=new EbWsItemServiceService();
        EbWsItemService es = ess.getEbWsItemServicePort();
        String result = es.publishItem(password, itemId);
        return result;
    }

    public List<EbItem> selectItemBySkuList() {
        return itemDao.selectItemBySkuList();
    }
}

