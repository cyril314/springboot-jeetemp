package com.fit.service;

import com.fit.base.BaseService;
import com.fit.dao.TbsRoleUserMapper;
import com.fit.entity.TbsRoleUserModel;
import org.springframework.stereotype.Service;

@Service("tbsRoleUserService")
public class TbsRoleUserService<T> extends BaseService<TbsRoleUserMapper, TbsRoleUserModel> {

}