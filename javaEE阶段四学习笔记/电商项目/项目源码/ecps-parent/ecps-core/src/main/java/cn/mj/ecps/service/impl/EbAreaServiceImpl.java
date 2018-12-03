package cn.mj.ecps.service.impl;

import cn.mj.ecps.dao.EbAreaDao;
import cn.mj.ecps.service.EbAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EbAreaServiceImpl implements EbAreaService {

    @Autowired
    private  EbAreaDao areaDao;

    public List<EbAreaDao> selectAddrByPid(Long pId) {
        return areaDao.selectAddrByPid(pId);
    }
}
