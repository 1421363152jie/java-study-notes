package cn.mj.ecps.dao;

import cn.mj.ecps.model.TsPtlUser;

import java.util.Map;

public interface TsPtlUserDao {

      public TsPtlUser selectUserByUnameAndPword(Map<String,String> map);


}
