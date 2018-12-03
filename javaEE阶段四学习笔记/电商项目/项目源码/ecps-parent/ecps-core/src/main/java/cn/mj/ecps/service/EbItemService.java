package cn.mj.ecps.service;


import cn.mj.ecps.model.*;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;


public interface EbItemService {

  public Page selectItemBycondition(QueryCondition condition);

  public void savaItem(EbItem item, EbItemClob itemClob, List<EbSku> skuList,List<EbParaValue> paraValueList);

  public void  updateAuditItem(Long itemId,Short auditStatus,String note);

  public void updateshowItem(Long itemId, Short showStatus, String note);

  public void deleteItem(Long itemId);

  public List<EbItem> selectItemByIndex(String skuPrice,Long brandId,String keyworks,String paraVals) throws IOException, SolrServerException;

  public void addItemIndex(EbItem item) throws IOException, SolrServerException;

  public EbItem selectItemDetailByItemId(Long itemId);

  public String publishItem(String password,Long itemId);

  public List<EbItem> selectItemBySkuList();
}
