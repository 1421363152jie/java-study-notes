package cn.mj.ecps.dao;

import cn.mj.ecps.model.EbItem;
import cn.mj.ecps.model.EbParaValue;
import cn.mj.ecps.model.QueryCondition;

import java.util.List;

public interface EbParaValueDao {

    public void savaParaValue(List<EbParaValue> paraList,Long itemId);

    public void deleteParaValueByItemId(Long itemId);







}
