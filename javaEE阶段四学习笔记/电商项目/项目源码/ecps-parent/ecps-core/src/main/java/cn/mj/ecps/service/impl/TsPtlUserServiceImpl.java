package cn.mj.ecps.service.impl;

import cn.mj.ecps.dao.TsPtlUserDao;
import cn.mj.ecps.model.TsPtlUser;
import cn.mj.ecps.service.TsPtlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TsPtlUserServiceImpl implements TsPtlUserService {

    @Autowired
    private TsPtlUserDao userDao;

    public TsPtlUser selectUserByUnameAndPword(Map<String, String> map) {
        return userDao.selectUserByUnameAndPword(map);
    }
}

