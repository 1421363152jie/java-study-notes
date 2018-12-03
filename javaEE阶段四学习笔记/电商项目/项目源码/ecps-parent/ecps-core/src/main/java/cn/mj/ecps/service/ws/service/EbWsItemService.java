package cn.mj.ecps.service.ws.service;

import javax.jws.WebService;

@WebService
public interface EbWsItemService {

    public String publishItem(String password,Long itemId);

}
