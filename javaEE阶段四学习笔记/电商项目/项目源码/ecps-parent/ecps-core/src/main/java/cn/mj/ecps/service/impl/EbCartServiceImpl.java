package cn.mj.ecps.service.impl;

import cn.mj.ecps.model.EbCart;
import cn.mj.ecps.model.EbSku;
import cn.mj.ecps.model.EbSpecValue;
import cn.mj.ecps.service.EbCartService;
import cn.mj.ecps.service.EbSkuService;
import cn.mj.ecps.utils.EbMJUtis;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbCartServiceImpl implements EbCartService {


    @Autowired
    private EbSkuService skuService;


    public void addrCart(Long skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response) {
        //配置json转换对象和排除的属性
        JsonConfig jc = new JsonConfig();
        jc.setRootClass(EbCart.class);
        jc.setExcludes(new String[]{"sku"});
        //获得单前所有cookie
        Cookie[] cookies = request.getCookies();
        List<EbCart> cartList=new ArrayList<EbCart>();
       if(cookies!=null&&cookies.length>0) {
           for (Cookie cookie : cookies) {
               //获得cookie的name
               String cookieName = cookie.getName();
               //判断当前cookie是否存在
               if (StringUtils.equals(cookieName, EbMJUtis.readProp("cookie_key"))) {
                   //获得cookie的value
                   String coookieVal = cookie.getValue();
                   //解码
                   coookieVal = URLDecoder.decode(coookieVal);
                   //将字符串转换为集合数组
                   JSONArray ja = JSONArray.fromObject(coookieVal);
                   //配置json转换对象和排除的属性
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
                   boolean isExit=true;
                   for (EbCart cart:cartList){
                       if(cart.getSkuId().longValue()==skuId.longValue()){
                            cart.setQuantity(cart.getQuantity()+quantity);
                            isExit=false;
                            break;
                       }
                   }
                   if(!isExit){
                       EbCart cart=new EbCart();
                       cart.setSkuId(skuId);
                       cart.setQuantity(quantity);
                       cartList.add(cart);
                   }
               }else{
                   EbCart cart=new EbCart();
                   cart.setSkuId(skuId);
                   cart.setQuantity(quantity);
                   cartList.add(cart);
               }

           }

       }
     //使用json将数组转换为字符串
        JSONArray ja=JSONArray.fromObject(cartList,jc);
        String result = ja.toString();
        //编码
        result=URLEncoder.encode(result);
        //创建cookie
        Cookie cookie=new Cookie("cookie_cart_key",result);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public List<EbCart> listCart(HttpServletResponse response, HttpServletRequest request) {
        //配置json转换对象和排除的属性
        JsonConfig jc = new JsonConfig();
        jc.setRootClass(EbCart.class);
        jc.setExcludes(new String[]{"sku"});
        //获得单前所有cookie
        Cookie[] cookies = request.getCookies();
        List<EbCart> cartList=new ArrayList<EbCart>();
        if(cookies!=null&&cookies.length>0) {
            for (Cookie cookie : cookies) {
                //获得cookie的name
                String cookieName = cookie.getName();
                //判断当前cookie是否存在
                if (StringUtils.equals(cookieName, EbMJUtis.readProp("cookie_key"))) {
                    //获得cookie的value
                    String coookieVal = cookie.getValue();
                    //解码
                    coookieVal = URLDecoder.decode(coookieVal);
                    //将字符串转换为集合数组
                    JSONArray ja = JSONArray.fromObject(coookieVal);
                    //配置json转换对象和排除的属性
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
                    for (EbCart cart:cartList){
                        EbSku sku = skuService.selectSkuDetailByIdWithRedis(cart.getSkuId());
                        cart.setSku(sku);
                    }
                }
            }
            }
        return cartList;
        }
    public void modifyCart(Long skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response) {
        //配置json转换对象和排除的属性
        JsonConfig jc = new JsonConfig();
        jc.setRootClass(EbCart.class);
        jc.setExcludes(new String[]{"sku"});
        //获得单前所有cookie
        Cookie[] cookies = request.getCookies();
        List<EbCart> cartList=new ArrayList<EbCart>();
        if(cookies!=null&&cookies.length>0) {
            for (Cookie cookie : cookies) {
                //获得cookie的name
                String cookieName = cookie.getName();
                //判断当前cookie是否存在
                if (StringUtils.equals(cookieName, EbMJUtis.readProp("cookie_key"))) {
                    //获得cookie的value
                    String coookieVal = cookie.getValue();
                    //解码
                    coookieVal = URLDecoder.decode(coookieVal);
                    //将字符串转换为集合数组
                    JSONArray ja = JSONArray.fromObject(coookieVal);
                    //配置json转换对象和排除的属性
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
                    for (EbCart cart : cartList) {
                        if (cart.getSkuId().longValue() == skuId.longValue()) {
                           cart.setQuantity(quantity);
                        }
                    }
                }

            }

        }
        //使用json将数组转换为字符串
        JSONArray ja=JSONArray.fromObject(cartList,jc);
        String result = ja.toString();
        //编码
        result=URLEncoder.encode(result);
        //创建cookie
        Cookie cookie=new Cookie("cookie_cart_key",result);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void removeCart(Long skuId, HttpServletRequest request, HttpServletResponse response) {
        //配置json转换对象和排除的属性
        JsonConfig jc = new JsonConfig();
        jc.setRootClass(EbCart.class);
        jc.setExcludes(new String[]{"sku"});
        //获得单前所有cookie
        Cookie[] cookies = request.getCookies();
        List<EbCart> cartList=new ArrayList<EbCart>();
        if(cookies!=null&&cookies.length>0) {
            for (Cookie cookie : cookies) {
                //获得cookie的name
                String cookieName = cookie.getName();
                //判断当前cookie是否存在
                if (StringUtils.equals(cookieName, EbMJUtis.readProp("cookie_key"))) {
                    //获得cookie的value
                    String coookieVal = cookie.getValue();
                    //解码
                    coookieVal = URLDecoder.decode(coookieVal);
                    //将字符串转换为集合数组
                    JSONArray ja = JSONArray.fromObject(coookieVal);
                    //配置json转换对象和排除的属性
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
                    for (int i=0;i<cartList.size();i++){
                        EbCart cart = cartList.get(i);
                        if(cart.getSkuId().longValue()==skuId.longValue()){
                            cartList.remove(cart);
                        }
                    }

                }

            }

        }
        //使用json将数组转换为字符串
        JSONArray ja=JSONArray.fromObject(cartList,jc);
        String result = ja.toString();
        //编码
        result=URLEncoder.encode(result);
        //创建cookie
        Cookie cookie=new Cookie("cookie_cart_key",result);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void clearCart(HttpServletResponse response, HttpServletRequest request) {
        //配置json转换对象和排除的属性
        JsonConfig jc = new JsonConfig();
        jc.setRootClass(EbCart.class);
        jc.setExcludes(new String[]{"sku"});
        //获得单前所有cookie
        Cookie[] cookies = request.getCookies();
        List<EbCart> cartList=new ArrayList<EbCart>();
        if(cookies!=null&&cookies.length>0) {
            for (Cookie cookie : cookies) {
                //获得cookie的name
                String cookieName = cookie.getName();
                //判断当前cookie是否存在
                if (StringUtils.equals(cookieName, EbMJUtis.readProp("cookie_key"))) {
                    //获得cookie的value
                    String coookieVal = cookie.getValue();
                    //解码
                    coookieVal = URLDecoder.decode(coookieVal);
                    //将字符串转换为集合数组
                    JSONArray ja = JSONArray.fromObject(coookieVal);
                    //配置json转换对象和排除的属性
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
                    cartList.clear();
                }

            }

        }
        //使用json将数组转换为字符串
        JSONArray ja=JSONArray.fromObject(cartList,jc);
        String result = ja.toString();
        //编码
        result=URLEncoder.encode(result);
        //创建cookie
        Cookie cookie=new Cookie("cookie_cart_key",result);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public String validCart(HttpServletRequest request, HttpServletResponse response) {
        String result="success";
        //配置json转换对象和排除的属性
        JsonConfig jc = new JsonConfig();
        jc.setRootClass(EbCart.class);
        jc.setExcludes(new String[]{"sku"});
        //获得单前所有cookie
        Cookie[] cookies = request.getCookies();
        List<EbCart> cartList =new ArrayList<EbCart>();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                //获得cookie的name
                String cookieName = cookie.getName();
                //判断当前cookie是否存在
                if (StringUtils.equals(cookieName, EbMJUtis.readProp("cookie_key"))) {
                    //获得cookie的value
                    String coookieVal = cookie.getValue();
                    //解码
                    coookieVal = URLDecoder.decode(coookieVal);
                    //将字符串转换为集合数组
                    JSONArray ja = JSONArray.fromObject(coookieVal);
                    //配置json转换对象和排除的属性
                    cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
                    for (EbCart cart : cartList) {
                        //重redis数据库中获取数据进行校验
                        EbSku sku = skuService.selectSkuDetailByIdWithRedis(cart.getSkuId());
                        if(sku.getStockInventory()<cart.getQuantity()){
                            result="实在抱歉!"+cart.getSku().getItem().getItemName();
                            List<EbSpecValue> specList = cart.getSku().getSpecList();
                            for (EbSpecValue spec:specList){
                                result=spec.getSpecValue()+result+"库存不足"+cart.getQuantity()+"部,仅剩"+sku.getStockInventory()+"台!";
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
