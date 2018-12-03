package cn.mj.ecps.controller;

import cn.mj.ecps.model.*;
import cn.mj.ecps.service.EbCartService;
import cn.mj.ecps.service.EbOrderService;
import cn.mj.ecps.service.EbShipAddrService;
import cn.mj.ecps.utils.EbStockException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Delayed;

@Controller
@RequestMapping("/order")
public class EbOrderController {

    @Autowired
   private EbCartService cartService;

    @Autowired
    private EbShipAddrService addrService;

    @Autowired
    private EbOrderService orderService;


    /**
     * 订单数据的查询展示
     */
    @RequestMapping("/toSubmitOrder.do")
    public String  toSubmitOrder(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model){
        TsPtlUser user= (TsPtlUser) session.getAttribute("user");
        List<EbCart> cartList = cartService.listCart(response, request);
        Integer itemNum=0;
        BigDecimal totalPrice=new BigDecimal(0);
        for (EbCart cart:cartList){
            itemNum=itemNum+cart.getQuantity();
            totalPrice=totalPrice.add(cart.getSku().getSkuPrice()).multiply(new BigDecimal(cart.getQuantity()));
        }
        List<EbShipAddr> addrList = addrService.selectAddrByUserIdWithRedis(user.getPtlUserId());
        model.addAttribute("itemNum",itemNum);
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("cartList",cartList);
        model.addAttribute("addrList",addrList);
    return "shop/confirmProductCase";
    }

    /**
     * 订单的提交
     */
    @RequestMapping("/submitOrder.do")
   public String  submitOrder(EbOrder order,HttpSession session,String address,HttpServletResponse response,HttpServletRequest request,Model model){
      TsPtlUser user= (TsPtlUser) session.getAttribute("user");
      order.setPtlUserId(user.getPtlUserId());
      order.setUsername(user.getUsername());
      //使用时间生成
      order.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmsssSSS").format(new Date()));
      //判断地址是否是address
        if(address!="add"){
            //查询redis中的地址信息
            EbShipAddr addr = addrService.selectAddrByUserIdWithRedis(user.getPtlUserId(), new Long(address));
            try {
                addr.setPtlUserId(user.getPtlUserId());
                BeanUtils.copyProperties(order,addr);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        //获得当前购物车的所有商品
        List<EbCart> cartList = cartService.listCart(response, request);
        List<EbOrderDetail> detailList=new ArrayList<EbOrderDetail>();
        for (EbCart cart:cartList){
            EbOrderDetail detail=new EbOrderDetail();
            detail.setItemId(cart.getSku().getItem().getItemId());
            detail.setItemName(cart.getSku().getItem().getItemName());
            detail.setItemNo(cart.getSku().getItem().getItemNo());
            detail.setSku(cart.getSku().getSku());
            detail.setSkuId(cart.getSkuId());
            detail.setSkuName(cart.getSku().getSkuName());
            List<EbSpecValue> specList = cart.getSku().getSpecList();
            String specVal="";
            for (EbSpecValue spec:specList){
                specVal=specVal+spec.getSpecValue()+",";
            }
            specVal=specVal.substring(0,specVal.length()-1);
            detail.setSkuSpec(specVal);
            detail.setSkuPrice(cart.getSku().getSkuPrice());
            detail.setMarketPrice(cart.getSku().getMarketPrice());
            detail.setQuantity(cart.getQuantity());
          detailList.add(detail);
        }
        try {
            String processInstanceId = orderService.savaOrder(request, response, order, detailList);
            session.setAttribute("processInstanceId",processInstanceId);
            session.setAttribute("orderId",order.getOrderId());
            model.addAttribute("order",order);
        } catch (Exception e) {
            if(e instanceof EbStockException){
             model.addAttribute("tip","stock_error");
            }
            e.printStackTrace();
        }
        return "shop/confirmProductCase2";
   }


    /**
     * 付款执行流程任务
     */
    @RequestMapping("/updatePayOrder.do")
    public void updatePayOrder(HttpSession session, PrintWriter pw){
        String processInstanceId= (String) session.getAttribute("processInstanceId");
        Long orderId= (Long) session.getAttribute("orderId");
        orderService.updatePayOrder(processInstanceId,orderId);
        pw.write("success");
    }

















}
