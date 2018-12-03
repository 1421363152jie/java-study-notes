package cn.mj.ecps.service.impl.ws.service.impl;

import cn.mj.ecps.model.EbItem;
import cn.mj.ecps.service.EbItemService;
import cn.mj.ecps.service.ws.service.EbWsItemService;
import cn.mj.ecps.utils.EbMJUtis;
import cn.mj.ecps.utils.FMutil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EbWsItemServiceImpl  implements EbWsItemService {

    @Autowired
    private EbItemService itemService;

    public String publishItem(String password, Long itemId) {
         if(StringUtils.equals(EbMJUtis.readProp("ws_pass"),password)){
             Map<String,Object> map=new HashMap<String, Object>();
             map.put("path",EbMJUtis.readProp("portal_path"));
             map.put("request_file_path",EbMJUtis.readProp("request_file_path"));
             EbItem item = itemService.selectItemDetailByItemId(itemId);
             map.put("item",item);
             try {
                 FMutil.outputFile("productDetail.ftl", map, item.getItemId()+".html");
             } catch (Exception e) {
                 e.printStackTrace();
             }
             return "success";
         }else{
             return "error";
         }
    }
}
