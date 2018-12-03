package cn.mj.ecps.controller;

import cn.mj.ecps.model.*;
import cn.mj.ecps.service.*;
import cn.mj.ecps.utils.EbMJUtis;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/item")
public class EbItemController {

    @Autowired
    private EbBrandService brandService;

    @Autowired
    private EbFeatureService featureService;

    @Autowired
    private EbItemService itemService;

    @Autowired
    private EbSkuService skuService;

    @Autowired
    private EbBrowseService browseService;

    /**
     * 首页数据的加载
     */
    @RequestMapping("/toIndex.do")
    public String toIndex(Model model, HttpServletResponse response, HttpServletRequest request){
        //查询品牌
        List<EbBrand> bList = brandService.selectBrandAll();
        //查询前台展示的属性
        List<EbFeature> fList = featureService.selectIsSelectFeature();
        model.addAttribute("bList",bList);
       model.addAttribute("fList",fList);
        return "index";
    }

    /**
     * 商品的筛选展示
     */
    @RequestMapping("/listItem.do")
    public String listItem(String skuPrice,Long brandId,String keyworks,String paraVals, Model model){
        try {
            List<EbItem> itemList = itemService.selectItemByIndex(skuPrice,brandId,keyworks,paraVals);
            model.addAttribute("itemList",itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "phoneClassification";
    }

    /**
     * 商品详情页
     */
    @RequestMapping("/viewDetail.do")
    public String viewDetail(Long itemId,Model model){
        EbItem item = itemService.selectItemDetailByItemId(itemId);
        model.addAttribute("item",item);
        return "productDetail";
    }

    @RequestMapping("/getSkuById.do")
    public void getSkuById(Long skuId, HttpServletResponse response){
        EbSku sku = skuService.selectSkuDetailByIdWithRedis(skuId);
        JSONObject jo=new JSONObject();
        jo.accumulate("sku",sku);
        String result = jo.toString();
        EbMJUtis.printAjax(response,result);
    }

    @RequestMapping("/listSales.do")
    public String listSales(Model model){
        List<EbSku> skuList = skuService.selectItemByOrderWithRedis();
        model.addAttribute("sList",skuList);
        return "Sales";
    }



    @RequestMapping("/listBrowses.do")
    public String listBrowses(Model model,HttpServletResponse response,HttpServletRequest request){
        List<EbBrowse> browsesList= browseService.listBrowse(response, request);
        model.addAttribute("browsesList",browsesList);
        return "browses";
    }

}
