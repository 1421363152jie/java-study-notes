package cn.mj.ecps.controller;

import cn.mj.ecps.model.EbCart;
import cn.mj.ecps.service.EbBrowseService;
import cn.mj.ecps.service.EbCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class EbCartController {

    @Autowired
    private EbCartService cartService;

    @Autowired
    private EbBrowseService browseService;


    /**
     * 购物车的添加
     */
    @RequestMapping("/addCart.do")
   public String addCart(Long skuId, Integer quantity, HttpServletResponse response, HttpServletRequest request, Model model){
           cartService.addrCart(skuId,quantity,request,response);
        List<EbCart> cartList = cartService.listCart(response, request);
        model.addAttribute("cartList",cartList);
        return "redirect:selectCart.do";
   }

    /**
     * 查询购物车
     */

   @RequestMapping("/selectCart.do")
   public String selectCart(HttpServletResponse response,HttpServletRequest request,Model model){
       List<EbCart> cartList = cartService.listCart(response, request);
       Integer itemNum=0;
       BigDecimal totalPrice=new BigDecimal(0);
       for (EbCart cart:cartList){
           itemNum=itemNum+cart.getQuantity();
           totalPrice=totalPrice.add(cart.getSku().getSkuPrice()).multiply(new BigDecimal(cart.getQuantity()));
       }
       model.addAttribute("itemNum",itemNum);
       model.addAttribute("totalPrice",totalPrice);
       model.addAttribute("cartList",cartList);
       return "shop/car";
   }
    /**
     * 修改购物车数据
     */
    @RequestMapping("/modifyCart.do")
   public String modifyCart(Long skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response){
        cartService.modifyCart(skuId,quantity,request,response);
        return "redirect:selectCart.do";
   }

    /**
     * 删除购物车指定商品
     */
    @RequestMapping("/removeCart.do")
    public String  removeCart(Long skuId, HttpServletRequest request, HttpServletResponse response){
            cartService.removeCart(skuId,request,response);
        return "redirect:selectCart.do";
    }

    /**
     * 检验库存是否足够购物车商品的数量
     */
    @RequestMapping("/validCart.do")
    public void validCart(HttpServletRequest request, HttpServletResponse response,PrintWriter pw){
        String result = cartService.validCart(request, response);
        System.out.println(result);
        pw.write(result);
    }
    /**
     * 清空购物车
     */
    @RequestMapping("/clearCart.do")
    public String clearCart(HttpServletResponse response,HttpServletRequest request){
        cartService.clearCart(response,request);
        return "redirect:selectCart.do";
    }

    //浏览记录的添加
    @RequestMapping("/addBrowse.do")
    public void addrBrowse(Long itemId,String itemName, String skuPrice,String imgs,HttpServletRequest request, HttpServletResponse response){
           BigDecimal skuPrice1=new BigDecimal(skuPrice);
        browseService.addrBrowse(itemId,itemName,skuPrice1,imgs,request,response);
    }



}
