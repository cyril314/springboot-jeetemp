package com.fit.service;

import com.fit.base.BaseService;
import com.fit.dao.TbsUserMapper;
import com.fit.entity.TbsUserModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("tbsUserService")
public class TbsUserService extends BaseService<TbsUserMapper, TbsUserModel> {

    public List<Map<String, Object>> selectByRoleUrls(Map<?, ?> map) throws Exception {
        return dao.selectByRoleUrls(map);
    }
}