package cn.mj.ecps.dao;

import cn.mj.ecps.model.EbItem;
import cn.mj.ecps.model.QueryCondition;

import java.util.List;

public interface EbItemDao {

    public List<EbItem>  selectItemBycondition(QueryCondition condition);

    public Integer selectItemByconditionCount(QueryCondition condition);

    public void savaItem(EbItem item);

    public void updateIten(EbItem item);

    public void deleteItemById(Long itemId);

    public List<EbItem> selectIsSelectItem();

    public EbItem selectItemDetailByItemId(Long itemId);

    public List<EbItem> selectItemBySkuList();

}
