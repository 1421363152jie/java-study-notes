package cn.mj.ecps.service;


import cn.mj.ecps.model.TsPtlUser;

import java.util.Map;

public interface TsPtlUserService {

  public TsPtlUser selectUserByUnameAndPword(Map<String,String> map);

}
