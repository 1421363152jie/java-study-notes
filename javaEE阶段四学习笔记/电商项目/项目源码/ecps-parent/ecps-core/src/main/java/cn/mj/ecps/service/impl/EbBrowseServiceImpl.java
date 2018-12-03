package cn.mj.ecps.service.impl;

import cn.mj.ecps.model.EbBrowse;
import cn.mj.ecps.model.EbCart;
import cn.mj.ecps.model.EbSku;
import cn.mj.ecps.model.EbSpecValue;
import cn.mj.ecps.service.EbBrowseService;
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
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EbBrowseServiceImpl implements EbBrowseService {

    public void addrBrowse(Long itemId,String itemName, BigDecimal skuPrice, String imgs, HttpServletRequest request, HttpServletResponse response) {
        //配置json转换对象和排除的属性
        JsonConfig jc = new JsonConfig();
        jc.setRootClass(EbBrowse.class);
        //获得单前所有cookie
        Cookie[] cookies = request.getCookies();
        List<EbBrowse> browseList=new ArrayList<EbBrowse>();
        if(cookies!=null&&cookies.length>0) {
            for (Cookie cookie : cookies) {
                //获得cookie的name
                String cookieName = cookie.getName();
                //判断当前cookie是否存在
                if (StringUtils.equals(cookieName, EbMJUtis.readProp("cookie_key1"))) {
                    //获得cookie的value
                    String coookieVal = cookie.getValue();
                    //解码
                    coookieVal = URLDecoder.decode(coookieVal);
                    //将字符串转换为集合数组
                    JSONArray ja = JSONArray.fromObject(coookieVal);
                    //配置json转换对象和排除的属性
                    browseList = (List<EbBrowse>) JSONSerializer.toJava(ja, jc);
                    for (int i = 0; i < browseList.size(); i++) {
                        if (browseList.size() >= 5) {
                            browseList.remove(0);
                        }
                    }
                        EbBrowse browse=new EbBrowse();
                        browse.setItemId(itemId);
                        browse.setItemName(itemName);
                        browse.setSkuPrice(skuPrice);
                        browse.setImgs(imgs);
                        browseList.add(browse);
                }else{
                    EbBrowse browse=new EbBrowse();
                    browse.setItemId(itemId);
                    browse.setItemName(itemName);
                    browse.setSkuPrice(skuPrice);
                    browse.setImgs(imgs);
                    browseList.add(browse);
                }

            }

        }
        //使用json将数组转换为字符串
        JSONArray ja=JSONArray.fromObject(browseList,jc);
        String result = ja.toString();
        //编码
        result=URLEncoder.encode(result);
        //创建cookie
        Cookie cookie=new Cookie("cookie_Browse_key",result);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public List<EbBrowse> listBrowse(HttpServletResponse response, HttpServletRequest request) {
        //配置json转换对象和排除的属性
        JsonConfig jc = new JsonConfig();
        jc.setRootClass(EbBrowse.class);
        //获得单前所有cookie
        Cookie[] cookies = request.getCookies();
        List<EbBrowse> browseList=new ArrayList<EbBrowse>();
        if(cookies!=null&&cookies.length>0) {
            for (Cookie cookie : cookies) {
                //获得cookie的name
                String cookieName = cookie.getName();
                //判断当前cookie是否存在
                if (StringUtils.equals(cookieName, EbMJUtis.readProp("cookie_key1"))) {
                    //获得cookie的value
                    String coookieVal = cookie.getValue();
                    //解码
                    coookieVal = URLDecoder.decode(coookieVal);
                    //将字符串转换为集合数组
                    JSONArray ja = JSONArray.fromObject(coookieVal);
                    //配置json转换对象和排除的属性
                    browseList = (List<EbBrowse>) JSONSerializer.toJava(ja, jc);

                }
            }
        }
        return browseList;
    }

    public void clearBrowse(HttpServletResponse response, HttpServletRequest request) {

    }
}
