package cn.mj.ecps.service;

import cn.mj.ecps.model.EbCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EbCartService {


    /**
     * 添加购物车
     */
    public void addrCart(Long skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response);


    /**
     * 查询购物车
     */
   public List<EbCart> listCart(HttpServletResponse response,HttpServletRequest request);


    /**
     * 修改购物车
     */
    public void modifyCart(Long skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response);


    /**
     *删除购物车
     */
    public void removeCart(Long skuId,HttpServletRequest request,HttpServletResponse response);


    /**
     * 清空购物车
     */
   public void clearCart(HttpServletResponse response,HttpServletRequest request);


    /**
     * 校验购物车的商品库存
     */
   public String validCart(HttpServletRequest request,HttpServletResponse response);



}
