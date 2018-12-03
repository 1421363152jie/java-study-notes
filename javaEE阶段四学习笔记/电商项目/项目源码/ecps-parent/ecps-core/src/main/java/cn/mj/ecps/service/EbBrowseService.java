package cn.mj.ecps.service;

import cn.mj.ecps.model.EbBrowse;
import cn.mj.ecps.model.EbCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public interface EbBrowseService {


    /**
     * 添加浏览记录
     */
    public void addrBrowse(Long itemId,String itemName, BigDecimal skuPrice,String imgs,HttpServletRequest request, HttpServletResponse response);


    /**
     * 查询浏览记录
     */
   public List<EbBrowse> listBrowse(HttpServletResponse response, HttpServletRequest request);





    /**
     * 清除浏览记录
     */
   public void clearBrowse(HttpServletResponse response, HttpServletRequest request);




}
