package cn.mj.ecps.service;

import cn.mj.ecps.dao.EbAreaDao;

import java.util.List;

public interface EbAreaService {

    public List<EbAreaDao> selectAddrByPid(Long pId);
}
